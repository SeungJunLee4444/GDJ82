/**
    1. 프로시저
    
    - 여러개의 쿼리문을 한번에 실행
      (이체 : UPDATE문 2개)
    - 작성된 프로시저는 EXECUTE문으로 실행
      EXECUTE 프로시저()
    - 1) 형식
    
        (1) 프로시저 정의
        CREATE [OR REPLACE] 프로시저_이름(매개변수)                            
        (OR REPLACE로 프로시저 수정 가능, DROP 역할)   
        
        (2) 프로시저 구조
        IS -- AS 가능         != DECLARE
          (변수선언)
        BEGIN   
            프로시저본문
        [EXCEPTION
            예외처리]
        END 프로시저_이름  
        
        (2) 실행
        EXECUTE 프로시저_이름(EXEC로 줄여쓰기 가능, 오라클은 4글자까지 줄일수있음)
*/

-- 프로시저 PROC1 정의
CREATE OR REPLACE PROCEDURE PROC1
IS   -- IS 뒤에 생략가능
    NAME VARCHAR2(10 BYTE);
BEGIN
    NAME := '이승준';
    DBMS_OUTPUT.PUT_LINE(NAME);
END PROC1; 



-- 2. 프로시저(참조)
GRANT DBA TO SCOTT;

-- 프로시저 PROC1 실행
EXECUTE PROC1();

SET SERVEROUTPUT ON;

-- 프로시저 PROC2 정의
-- 사원번호가 100인 사원의 FIRST_NAME 서버메시지로 출력하기
CREATE OR REPLACE PROCEDURE PROC2
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;    -- 참조타입(다른테이블의 칼럼)
BEGIN
    SELECT FIRST_NAME 
      INTO FNAME
     FROM EMPLOYEES 
     WHERE EMPLOYEE_ID = 100;
     DBMS_OUTPUT.PUT_LINE(FNAME);
END;     
    
-- 프로시저 PROC2 호출
EXECUTE PROC2 ();



-- 3. 프로시저 PROC3 
-- 사원번호를 전달하면 해당 사원의 FIRST_NAME을 서버메시지로 전달하기
-- 입력 파라미터가 필요                                                           *
-- * 입력 파라미터
--  프로시저로 전달하는 값을 저장하는 변수
--  형식 : 변수명 IN 타입(입력을 의미하는 IN이 들어가줘야함)

CREATE OR REPLACE PROCEDURE PROC3(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME 
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID; -- (2) 입력된 EMP_ID 100과 조건비교 
     DBMS_OUTPUT.PUT_LINE(FNAME);
END PROC3;     

--  프로시저 PROC3 정의


-- 프로시저 PROC3 호출

EXECUTE PROC3(100); -- (1) 100은 EMP_ID에 저장
EXECUTE PROC3(101);
EXECUTE PROC3(500); -- 없는 번호를 입력하면 NO DATA 오류

/**
    입력 파라미터 : 프로시저로 전달된 값을 매개변수로 해당 값을 저장(IN)
    출력 파라미터 : 해당 파라미터에 입력되어 반환된 값을 외부로 출력(OUT)
*/


-- 4. 프로시저 

-- 1) PROC4 정의
-- 사원번호 = 100인 사원의 FIRST_NAME을 출력파라미터 FANME에 저장
CREATE OR REPLACE PROCEDURE PROC4(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)

-- 2) 출력 파라미터
--  프로시저의 결과값을 저장하는 변수
--  형식 : 변수명 OUT 타입
IS
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
END PROC4;     


-- 3) PROC4 호출
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;         -- 출력 파라미터로 사용한 변수
BEGIN     
    PROC4(FNAME);                            -- PLSQL 내부에서 프로시저를 호출할때는 EXECUTE 생략   * 
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;    

-- 5. EXCEPTION
-- 사원번호가 있으면 FIRST_NAME을 출력 파라미터로 전달, 없으면 'NoName'을 출력 
CREATE OR REPLACE PROCEDURE PROC5(FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 500;
EXCEPTION  
     WHEN OTHERS THEN   -- 모든 예외를 처리(WHEN NO_DATA_FOUND THEN)
        FNAME := 'NoName';
END PROC5; &

DECLARE
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
    PROC5(FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;    


-- 6. 연습1. 프로시저6(3번 + 4번)
-- 입력 파라미터에 사원번호 전달, 출력 파라미터에 FIRST_NAME 반환받기
-- 입력 파라미터 + 출력 파라미터
-- 존재하지 않는 사원번호는 출력 파라미터에 'NoName' 반환하기


CREATE OR REPLACE PROCEDURE PROC6(EMP_ID IN EMPLOYEES.EMPLOYEE_ID%TYPE, FNAME OUT EMPLOYEES.FIRST_NAME%TYPE)
IS 
BEGIN 
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
EXCEPTION 
    WHEN NO_DATA_FOUND THEN
        FNAME := 'NoName';
END PROC6;

DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    PROC6(100,FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
    PROC6(99,FNAME);
    DBMS_OUTPUT.PUT_LINE(FNAME);
END;

-- * 입력된 아이디 100의 이름을 저장하고, 이를 호출한다


-- 7. 연습2. 구매 테이블 시퀸스, 실전
DROP SEQUENCE BUY_SEQ;
CREATE SEQUENCE BUY_SEQ NOCACHE;

-- 구매 프로시저
-- 1. BUY_PROC(고객번호, 제품코드, 구매수량)
-- 2. 진행해야할 쿼리
--  1) 구매 테이블에 구매내역을 INSERT
--  2) 고객 테이블에 고객 포인트를 UPDATE (총 구매액의 10%)
--  3) 제품 테이블의 재고를 UPDATE

CREATE OR REPLACE PROCEDURE BUY_PROC(
    C_NO    IN CUSTOMER.CUST_NO%TYPE,
    P_CODE  IN PRODUCT.PROD_CODE%TYPE,    
    BUY_AMT IN BUY.BUY_AMOUNT%TYPE
    )
IS
BEGIN
--  1) 구매 테이블에 구매내역을 INSERT
INSERT INTO BUY(BUY_NO,CUST_NO,PROD_CODE,BUY_AMOUNT)
VALUES(BUY_SEQ.NEXTVAL, C_NO, P_CODE, BUY_AMT);

--  2) 고객테이블에 고객 포인트를 UPDATE
UPDATE CUSTOMER
   SET CUST_POINT = CUST_POINT + CEIL((SELECT PROD_PRICE
                                     FROM PRODUCT
                                    WHERE PROD_CODE = P_CODE) * BUY_AMT * 0.1)   -- 기존 포인트 + 이번에 새로생긴 포인트
 WHERE CUST_NO = C_NO; 
 
-- 3) 제품 테이블의 재고를 UPDATE
UPDATE PRODUCT 
   SET PROD_STOCK = PROD_STOCK - BUY_AMT
 WHERE PROD_CODE = P_CODE;  
COMMIT;
EXCEPTION 
-- 예외처리 발생시 아무일도 없었던 것으로하기(INSERT, UPDATE 전부 취소)
WHEN OTHERS THEN 
    DBMS_OUTPUT.PUT_LINE('예외코드' || SQLCODE);
    DBMS_OUTPUT.PUT_LINE('예외메시지' || SQLERRM);
    ROLLBACK;


END BUY_PROC;

-- 구매 후 프로시저 호출
EXECUTE BUY_PROC(1, 1000, 10); -- 고객번호 1, 제품번호 1000, 구매수량 10

-- 확인
SELECT PROD_CODE, PROD_NAME, PROD_PRICE, PROD_STOCK
  FROM PRODUCT;
SELECT CUST_NO, CUST_NAME, CUST_POINT
  FROM CUSTOMER;
SELECT BUY_NO, CUST_NO, PROD_CODE,BUY_AMOUNT
 FROM BUY;
 
 
 
 DROP TABLE BUY;
DROP TABLE CUSTOMER;
DROP TABLE PRODUCT;

-- 제품 테이블
CREATE TABLE PRODUCT(
    PROD_CODE  NUMBER             NOT NULL,  -- 제품코드
    PROD_NAME  VARCHAR2(10 BYTE),            -- 제품명
    PROD_PRICE NUMBER,                       -- 제품가격
    PROD_STOCK NUMBER                        -- 재고
);
-- 제품 기본키
ALTER TABLE PRODUCT
    ADD CONSTRAINT PK_PRODUCT PRIMARY KEY(PROD_CODE);
-- 제품 입력
INSERT INTO PRODUCT VALUES(1000, '진라면', 500, 100);
INSERT INTO PRODUCT VALUES(1001, '신라면', 600, 100);
COMMIT;


-- 고객 테이블
CREATE TABLE CUSTOMER(
    CUST_NO    NUMBER             NOT NULL,   -- 고객번호
    CUST_NAME  VARCHAR2(10 BYTE),             -- 고객명
    CUST_POINT NUMBER                         -- 고객포인트
);
-- 고객 기본키
ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY(CUST_NO);
-- 고객 입력
INSERT INTO CUSTOMER VALUES(1, '철수', 0);
INSERT INTO CUSTOMER VALUES(2, '영희', 0);
COMMIT;

-- 구매 테이블
CREATE TABLE BUY(
    BUY_NO     NUMBER NOT NULL,  -- 구매번호
    CUST_NO    NUMBER NOT NULL,  -- 고객번호(FK)
    PROD_CODE  NUMBER NOT NULL,  -- 제품코드(FK)
    BUY_AMOUNT NUMBER            -- 구매수량
);
ALTER TABLE BUY
    ADD CONSTRAINT PK_BUY PRIMARY KEY(BUY_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_CUSTOMER FOREIGN KEY(CUST_NO)
        REFERENCES CUSTOMER(CUST_NO);
ALTER TABLE BUY
    ADD CONSTRAINT FK_BUY_PRODUCT FOREIGN KEY(PROD_CODE)
        REFERENCES PRODUCT(PROD_CODE);

-- 구매 테이블 시퀀스
DROP SEQUENCE BUY_SEQ;
CREATE SEQUENCE BUY_SEQ NOCACHE;
