/**
    [26] 트리거
    
    -DML 수행 후 트리거가 자동으로 수행됨
    -종류: DML 직전에 수행되는 BEFORE 트리거, 직후에 수행되는 AFTER 트리거가 있다
    -기본적으로 작업 수행 행(ROW) 단위로 트리거가 적용
    
    1) 형식
    CREATE [OR REPLACE] TRIGGER 트리거명
    [BEFORE / AFTER]
    [INSERT OR UPDATE OR DELETE]
    [ON 테이블명]                               -- != 프로시저, 사용자 함수처럼 조건이 WHERE이 아님
    [FOR EACH ROW]
    BEGIN 
        트리거 작업
    END [트리거명];
*/

-- 2) 실습
-- (1)
-- 트리거 TRIG1 정의
CREATE OR REPLACE TRIGGER TRIG1
AFTER   
UPDATE 
    ON DEPARTMENT   -- DEPARTMENT 테이블을 UPDATE할때 동작(조건)
   FOR EACH ROW     -- UPDATE 되는 행마다 트리거가 동작                            *

-- 트리거 TRIG1 동작 확인
BEGIN
    DBMS_OUTPUT.PUT_LINE('업데이트 성공');    -- DML이 실행되면 추가적으로 나오게 될 반응
END TRIG1;

UPDATE DEPARTMENT
   SET LOCATION = '인천'      -- DML 작동시마다 특정한 추가동작을 실시
 WHERE DEPT_NO = 1;   


-- (2)
-- 트리거 TRIG2 정의
CREATE OR REPLACE TRIGGER TRIG2
    AFTER
    INSERT OR UPDATE OR DELETE
    ON DEPARTMENT
    FOR EACH ROW
BEGIN
    IF INSERTING THEN
        DBMS_OUTPUT.PUT_LINE('INSERT 이후 동작');
    ELSIF UPDATING THEN
        DBMS_OUTPUT.PUT_LINE('UPDATE 이후 동작');
    ELSIF DELETING THEN
        DBMS_OUTPUT.PUT_LINE('DELETE 이후 동작');
    END IF;
END TRIG2;

-- 트리거 TRIG2 동작 확인
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5, '개발부', '제주');
UPDATE DEPARTMENT SET LOCATION = '경주' WHERE DEPT_NO = 2;
DELETE FROM DEPARTMENT WHERE DEPT_NO = 3;
ROLLBACK;

-- (3) 
-- 트리거 TRIG3 정의
-- 3) OLD 테이블
--  INSERT, UPDATE, DELETE 수행 이전 정보가 저장되는 테이블
--  오라클에서는 :OLD 호출
--  AFTER 트리거와 함께 사용
--  INSERT 동작 이전 : NULL
--  UPDATE 동작 이전 : 수정 전 데이터
--  DELETE 동작 이전 : 삭제 된 데이터

CREATE OR REPLACE TRIGGER TRIG3
    AFTER
    UPDATE OR DELETE
    ON EMPLOYEE
    FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE(:OLD.NAME);
END TRIG3;

-- 트리거 TRIG3

UPDATE EMPLOYEE SET NAME = '구민서' WHERE EMP_NO = 1002; 
DELETE FROM EMPLOYEE WHERE EMP_NO = 1003; 
 
SELECT * FROM EMPLOYEE;
 ROLLBACK;
    
    
    
-- (4) 트리거 최종 실습
-- 예시 : 사원(EMPLOYEES) 테이블에서 삭제된 사원정보를 퇴사자(RETIRES) 테이블에 삽입하기
-- (해당 사원정보를 내가 사원에서 DELETE 하고, 트리거가 퇴사자에 INSERT해주기)
    
    
-- 1. 사전준비(퇴사자 테이블을 만들고 칼럼, 기본키설정, 시퀸스 생성)    
-- 1) 퇴사자 테이블 만들기(EMPLOYEES 테이블과 동일한 구조, 데이터없이 복사)
CREATE TABLE RETIRES 
    AS(SELECT * FROM EMPLOYEES WHERE 1 = 2);    -- (다른 테이블의 칼럼만 복사하는 쿼리문)

-- 2) RETIRE_ID, RETIRE_DATE 칼럼 추가
ALTER TABLE RETIRES ADD RETIRED_ID NUMBER;
ALTER TABLE RETIRES ADD RETIRED_DATE DATE;
    
-- 3) RETIRE_ID 기본키 설정
ALTER TABLE RETIRES ADD CONSTRAINT PK_RETIRE PRIMARY KEY(RETIRE_ID);

-- 4) RETIRE_SEQ 시퀀스 생성하기
DROP SEQUENCE RETIRE_SEQ;
CREATE SEQUENCE RETIRE_SEQ NOCACHE;

-- * 트리거를 사용하기 위한 준비 끝

    
-- 2. 트리거 생성, 퇴직자 테이블에 삭제된 데이터 추가
-- 5) RETIRE_TRIG 트리거 생성(삭제된 데이터들은 전부 RETIRE 테이블에 삽입된다)
CREATE OR REPLACE TRIGGER RETIRE_TRIG
    AFTER DELETE
    ON EMPLOYEES
    FOR EACH ROW
BEGIN
    INSERT INTO RETIRES(RETIRED_ID, RETIRED_DATE, EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID)
    VALUES(RETIRE_SEQ.NEXTVAL, SYSDATE, :OLD.EMPLOYEE_ID, :OLD.FIRST_NAME, :OLD.LAST_NAME, :OLD.EMAIL, :OLD.PHONE_NUMBER, :OLD.HIRE_DATE, :OLD.JOB_ID, :OLD.SALARY, :OLD.COMMISSION_PCT, :OLD.MANAGER_ID, :OLD.DEPARTMENT_ID);
END RETIRE_TRIG;    -- 삭제 전 데이터는 모두 OLD에 저장되있음

DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = 100;
SELECT * FROM EMPLOYEES;
SELECT * FROM RETIRES;











