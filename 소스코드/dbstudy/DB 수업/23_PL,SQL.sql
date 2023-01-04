/**
    
        1. PL/SQL
            - 오라클의 프로그래밍 처리가 가능한 SQL
            - 프로그래밍 형식
                [DECLARE]
                    [변수선언]
                BEGIN 
                    수행할 쿼리문
                END;
            - PL/SQL의 데이터(변수, 상수)는 서버메시지로 출력
            - 서버메시지 출력을 위해서 최초 1회 설정해줄 데이터가 있음(디폴트 SET SERVEROUTPUT OFF)
            - SET SERVEROUTPUT ON;
            
            
            * 순서
            1. 기초데이터 작성
            2. 서버메시지 출력기능 
                => SET SERVEROUTPUT ON;
                
            3. 변수선언
                => (1) DECLARE 
                    변수명1 변수타입;
                    변수명2 변수타입;
                   (2) 참조변수 : 특정 칼럼의 타입을 그대로 이용
                     => 테이블명.칼럼명%TYPE
                     
            4. 변수대입 서버메시지 출력
                => BEGIN 
                   (1) 변수값 대입( := )
                    변수명1 := '';
                    변수명2 := 숫자;
                   (2) 출력
                    DBMS_OUTPUT.PUT_LINE(출력문);
                   (3) 참조변수 활용
                    => 다른 테이블의 값을 참조하여 해당 변수에 대입  
                    DECLARE : 변수명 참조할 테이블.참조할 칼럼%TYPE;
                    BEGIN   : SELECT 칼럼 INTO 변수 FROM 테이블 WHERE 조건식
                    
                    * 변수종류
                    1) 스칼라 변수
                    2) 참조변수
                    3) 참조변수 활용
                    4) 레코드 변수
                    5) 행변수
            
*/



-- 기초데이터 준비
-- ex) HR 계정의 EMPLOYEES 테이블을 SCOTT 계정으로 복사해오기

CREATE TABLE EMPLOYEES AS (SELECT * 
                             FROM HR.EMPLOYEES);

-- 기본키, 외래키는 안넘어왔으니 직접 추가(기본키만)
ALTER TABLE EMPLOYEES
    ADD CONSTRAINT PK_EMPLOYEES PRIMARY KEY(EMPLOYEE_ID);
        
-- 서버메시지 출력 가능 상태로 변경
SET SERVEROUTPUT ON;

-- 서버메시지 출력
BEGIN
    DBMS_OUTPUT.PUT_LINE('HELLO'); -- HELLO 출력 후 줄바꿈
END;    

--------------------------------------------------------------------------------

-- 2. 스칼라 변수 선언
--  - 스칼라 변수 : 직접 타입을 명시하는 변수
--  - 대입연산자 : 콜론(:=)
--  - 변수선언은 DECLARE에서 처리
DECLARE 
    -- 스칼라 변수선언
    NAME VARCHAR(20 BYTE);
    AGE NUMBER(3);
BEGIN 
    NAME := '이승준';
    AGE := 27;
    DBMS_OUTPUT.PUT_LINE('내 이름은 ' || NAME || '입니다.');
    DBMS_OUTPUT.PUT_LINE('내 나이는 ' || AGE || '살 입니다.');
END;    

--------------------------------------------------------------------------------

-- 3. 참조변수 선언                                                               *
--      - 참조변수 : 특정 칼럼의 타입을 그대로 사용하는 변수
--      - 선언방법 : 테이블명.칼럼명%TYPE

DECLARE 
        NAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN 
        NAME := '이승준';
        DBMS_OUTPUT.PUT_LINE('내 이름은' || NAME || '입니다');
END; 

--------------------------------------------------------------------------------

-- 4. 참조변수 활용
-- 테이블의 데이터를 읽어 참조변수에 저장
-- SELECT 칼럼 INTO 변수 FROM 테이블 WHERE 조건식                                 *

-- 문제. EMPLOYEE_ID가 100인 회원의 FIRST_NAME, LAST_NAME, SALARY 정보를 참조변수에 저장


DECLARE 
    F_NAME EMPLOYEES.FIRST_NAME%TYPE;
    L_NAME EMPLOYEES.LAST_NAME%TYPE;
    V_SALARY EMPLOYEES.SALARY%TYPE;
BEGIN
    SELECT 
        FIRST_NAME, LAST_NAME, SALARY INTO F_NAME, L_NAME, V_SALARY
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
    DBMS_OUTPUT.PUT_LINE(F_NAME || L_NAME || V_SALARY);
END;

--------------------------------------------------------------------------------

-- 5. 레코드 변수 선언
--  => 레코드      : 필드의 모임, DB에서 레코드는 행(ROW)          * 클래스 = 레코드, 필드(객체) : 칼럼
--  => 레코드 변수 : 여러 칼럼을 동시에 저장하는 변수
--  => 레코드 변수 정의와 선언과정으로 진행

DECLARE 
    -- 1) 레코드 변수 타입정의
    TYPE MY_TYPE IS RECORD(
        V_FNAME EMPLOYEES.FIRST_NAME%TYPE,
        V_LNAME EMPLOYEES.LAST_NAME%TYPE,
        V_SALARY EMPLOYEES.SALARY%TYPE
        );
    -- 2) 레코드 변수 선언    
        V_ROW MY_TYPE;              -- * V_ROW는 위의 변수3개가 모인것
BEGIN
    SELECT
            FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID                        -- 선언된 변수와 칼럼수가 안맞으면 오류
      INTO  V_ROW
      FROM  EMPLOYEES
     WHERE  EMPLOYEE_ID = 100;
DBMS_OUTPUT.PUT_LINE(V_ROW.V_FNAME || V_ROW.V_LNAME || V_ROW.V_SALARY);
END;

--------------------------------------------------------------------------------

-- 6. 행 변수
-- => 행(ROW) 전체를 저장할 수 있는 타입
-- => 선언방법 : 테이블%ROWTYPE

DECLARE
    V_ROW EMPLOYEES%ROWTYPE;    -- 행 변수정의
BEGIN
    SELECT
        *           -- 행 변수선언
      INTO V_ROW
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = 100;
DBMS_OUTPUT.PUT_LINE(V_ROW.FIRST_NAME || V_ROW.LAST_NAME || V_ROW.SALARY);
END;

-- 7. IF
/**
    IF 조건식 THEN
        실행문
    ELSIF 조건식 THEN
        실행문
    ELSE
        실행문
    END IF;    

*/

-- 1) 성인/ 미성년자
DECLARE
    AGE NUMBER(3);
    RESULT VARCHAR2(20 BYTE);                                               --    * RESULT도 정의해줘야한다
BEGIN
    AGE := 45;
    IF AGE >= 20 THEN
        RESULT := '성인';
    ELSE
        RESULT := '미성년자';
    END IF;    
    DBMS_OUTPUT.PUT_LINE(AGE || '살은' || RESULT || '이다');
END;    

-- 2) 학점(A,B,C,D,F)
DECLARE
    SCORE NUMBER(3);
    RESULT VARCHAR2(20 BYTE);
BEGIN
    SCORE := 50;
    IF SCORE >= 90 THEN
        RESULT := 'A';
    ELSIF SCORE >= 80 THEN
        RESULT := 'B';
    ELSIF SCORE >= 70 THEN
        RESULT := 'C';
    ELSIF SCORE >= 60 THEN
        RESULT := 'D';
    ELSE    
        RESULT := 'F';
    END IF;
    DBMS_OUTPUT.PUT_LINE(SCORE || '점은' || RESULT || '등급이다');
END;    


-- 3) EMPLOYEE_ID가 150인 사원의 연봉을 참조하여
-- 15000이상이면 '고연봉', 10000 이상이면 '중연봉', 나머지는 '저연봉'
DECLARE
    SAL EMPLOYEES.SALARY%TYPE;
    RES VARCHAR2(20 BYTE);
BEGIN
     SELECT SALARY
     INTO SAL
     FROM EMPLOYEES
    WHERE EMPLOYEE_ID = 150;
    IF SAL >= 15000 THEN
        RES := '고연봉';
    ELSIF SAL >= 10000 THEN
        RES := '중연봉';
    ELSE
        RES := '저연봉'; 
    END IF;  
     DBMS_OUTPUT.PUT_LINE(SAL || '는' || RES || '입니다');
END;     




DECLARE 
    F_NAME EMPLOYEES.FIRST_NAME%TYPE;
    L_NAME EMPLOYEES.LAST_NAME%TYPE;
    V_SALARY EMPLOYEES.SALARY%TYPE;

-- 8. CASE
/**
    CASE
        WHEN 조건식 THEN
            실행문
        WHEN 조건식 THEN
            실행문
        ELSE 
            실행문
    END CASE;        
*/

-- 문제1. 주민번호를 이용해서 성별 조회하기
SET SERVEROUTPUT ON;

DECLARE
    SNO VARCHAR2(14 BYTE);                     -- * NUM에 저장할 숫자
    GENDER_NUM CHAR(1 BYTE);                -- SUBSTR(SNO, 8, 1) 앞에서 8번째의 한글자
    GENDER VARCHAR2(1 BYTE);                    -- SUBSTR(SNO, -7, 1) 뒤에서 7번째의 한글자(뒤에서부터 순서는 - 붙이기)
BEGIN 
    SNO := '901010-1234567';
    SELECT SUBSTR(SNO, 8, 1)
      INTO GENDER_NUM
      FROM DUAL;
    CASE 
        WHEN GENDER_NUM = '1' THEN    -- 같은말 CASE GENDER_NUM WHEN 1 THEN 
            GENDER := 'M';
        WHEN GENDER_NUM = '2' THEN
            GENDER := 'W';
    END CASE;
    DBMS_OUTPUT.PUT_LINE('성별은' || GENDER || '입니다');
END;    
    
    
-- 9. WHILE 문
/**    
    WHILE 조건식 LOOP
        실행문
    END LOOP;    
*/

-- 문제1. 1~5 출력하기

SET SERVEROUTPUT ON;

DECLARE 
    NUM NUMBER(1);
BEGIN
    NUM := 1;
    WHILE NUM <= 5 LOOP
        DBMS_OUTPUT.PUT_LINE(NUM);
        NUM := NUM + 1;
    END LOOP;
END;    
    
    
-- 문제2. EMPLOYEES 테이블의 모든 사원의 FIRST_NAME, LAST_NAME 조회
-- FIRST_NAME과 LAST_NAME을 레코드 변수에 저장하고 해당 값을 조회
-- 레코드변수는 사원 1명의 정보만 저장할 수 있으니 한명씩 저장 후 출력
-- 사원번호는 100~206값을 가짐

SET SERVEROUT ON;

DECLARE 
    -- 참조변수 선언
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;
   
    -- 레코드 변수 정의
     TYPE NAME_TYPE IS RECORD(
        FNAME EMPLOYEES.FIRST_NAME%TYPE,
        LNAME EMPLOYEES.LAST_NAME%TYPE
    );
    -- 레코드 변수 선언
    FULL_NAME NAME_TYPE;
BEGIN
    -- 사원번호(100~206) 기준으로 WHILE LOOP
    EMP_NO : 100;
    WHILE EMP_NO <= 206 LOOP
        SELECT FIRST_NAME, LAST_NAME 
            INTO FULL_NAME 
                FROM EMPLOYEES
           WHERE EMPLOYEE_ID = EMP_NO;
          DBMS_OUTPUT.PUT_LINE(FULL_NAME.FNAME || ' ' || FULL_NAME.LNAME) 
          EMP_NO := EMP_NO + 1;
    END LOOP;      
END;    
    
    
    
DECLARE   
      -- 1) 레코드 변수 타입정의
    TYPE MY_TYPE IS RECORD(
        V_FNAME EMPLOYEES.FIRST_NAME%TYPE,
        V_LNAME EMPLOYEES.LAST_NAME%TYPE,
        V_SALARY EMPLOYEES.SALARY%TYPE
        );
    -- 2) 레코드 변수 선언    
        V_ROW MY_TYPE;              -- * V_ROW는 위의 변수3개가 모인것
BEGIN
    SELECT
            FIRST_NAME, LAST_NAME, SALARY
      INTO  V_ROW
      FROM  EMPLOYEES
     WHERE  EMPLOYEE_ID = 100;
DBMS_OUTPUT.PUT_LINE(V_ROW.V_FNAME || V_ROW.V_LNAME || V_ROW.V_SALARY);
END;
    
    

-- 10. FOR 문
/**
    
    FOR 변수 IN 시작..종료 LOOP
        실행문
    END LOOP;    
*/

-- 1) 1~5
DECLARE
    N NUMBER(1);
BEGIN
    FOR N IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE(N);
    END LOOP;
END;    

-- 2) 짝수/ 홀수
DECLARE
    N NUMBER;
    MODULAR NUMBER(1); -- 2로 나눈 나머지 값을 저장
    RESULT VARCHAR2(10 BYTE);
BEGIN 
    FOR N IN 1..10 LOOP
        SELECT MOD(N, 2)            -- * MOD(나머지)
          INTO MODULAR
          FROM DUAL;
         IF MODULAR = 0 THEN    -- 나머지가 0이면 짝수  * IF 연산자는 자바연산자?
            RESULT := '짝수';
         ELSE
            RESULT := '홀수';
         END IF;
         DBMS_OUTPUT.PUT_LINE(N || '은' || RESULT || '입니다.');
     END LOOP;     
END;      

-- 3) EMPLOYEES 테이블의 모든 사원의 연봉 합계/ 평균 조회하기
DECLARE
    EMP_NO EMPLOYEES.EMPLOYEE_ID%TYPE;
    SAL EMPLOYEES.SALARY%TYPE;
    TOTAL NUMBER;               -- 누적된 연봉 저장변수
    CNT NUMBER;                 -- 사원의 수가 몇명인지(평균)
    AVERAGE NUMBER;                 -- 평균 저장변수
    
BEGIN    
    TOTAL := 0;
    CNT := 0;       -- 초기값이 필요한경우는 0이 필요, AVG는 계산만하면되서 초기값X
    FOR EMP_NO IN 100..206 LOOP
        SELECT SALARY
          INTO SAL
          FROM EMPLOYEES
         WHERE EMPLOYEE_ID = EMP_NO;     -- 같은 사원
         TOTAL := TOTAL + SAL;          -- 연봉의 누적
         CNT := CNT + 1;
    END LOOP;    
    AVERAGE := TOTAL / CNT;
    DBMS_OUTPUT.PUT_LINE('연봉합계 : ' || TOTAL);
    DBMS_OUTPUT.PUT_LINE('연봉평균 : ' || AVERAGE);
END;   
-- AVG가 오류

-- 4) DEPARTMENT_ID 50인 사원정보를 DEPT50 테이블에 복사하기
    -- (1) EMPLOYEES와 구조가 동일한 DEPT50테이블 생성
    -- (2) 행 변수를 이용해 EMPLOYEES 정보 읽기
    -- (3) DEPARTMENT_ID 50이면 DEPT50에 INSERT
    
    CREATE TABLE DEPT50 
      AS (SELECT * FROM EMPLOYEES WHERE 1 = 2);
    
DECLARE
    V_ROW EMPLOYEES%ROWTYPE;
    
BEGIN
    FOR V_ROW IN(SELECT * FROM EMPLOYEES) LOOP
        IF V_ROW.DEPARTMENT_ID = 50 THEN
            INSERT INTO DEPT50 VALUES V_ROW;
        END IF;
    END LOOP;
    COMMIT;
END;    



-- 9. EXIT 문
DECLARE     
    N NUMBER; 
BEGIN 
    N := 1;
WHILE TURE LOOP         -- 무한루프 아웃
    IF N > 100 THEN
        EXIT;
    END IF;
    N := N + 1;
  EMD LOOP;
  DBMS_OUTPUT.PUT_LINE(N);
END;  
    
-- 10. CONTINUE 문
-- => LOOP문 시작부터 다시 시작
-- DEPARTMENT_ID가 50인 사원을 제외하고 연봉합계 조회하기
DECLARE 
    EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE;
    SAL EMPLOYEES.SALARY%TYPE;
    DEPT_ID EMPLOYEES.DEPARTMENT_ID%TYPE;
    TOTAL NUMBER;

BEGIN 
    TOTAL := 0;
    FOR EMP_ID IN 100..206 LOOP
        SELECT SALARY, DEPARTMENT_ID
          INTO SAL, DEPT_ID
          FROM EMPLOYEES
         WHERE EMPLOYEE_ID = EMP_ID;         
         IF DEPT_ID = 50 THEN
            CONTINUE;
         END IF;
         TOTAL := TOTAL + SAL;
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('연봉합계: ' || TOTAL);
END;    

SELECT SUM(SALARY) FROM EMPLOYEES WHERE DEPARTMENT_ID != 50; -- * WHERE 조건에서 아닐때? !=
    
    

-- 11. 배열선언
-- => 테이블타입 : 특정칼럼의 모든 데이터를 배열에 저장  
-- * 레코드는 행의 모임, 테이블은 열의 모임(배열)
    
DECLARE
    -- SALARY 칼럼의 모든 값을 저장할 배열(SALARIES) 생성
    -- 1) 배열의 타입 저장
    TYPE SALARY_TYPE IS TABLE OF EMPLOYEES.SALARY%TYPE INDEX BY BINARY_INTEGER; -- 인덱스의 타입
    
    -- 2) 배열선언
    SALARIES SALARY_TYPE;

    -- 3) 인덱스값 선언
    I NUMBER; 
    
    -- 4) 행(ROW)단위 연봉 선언
    V_ROW EMPLOYEES.ROWTYPE;

BEGIN 
    I := 0;
    FOR V_ROW IN(SELECT SALARY FROM EMPLOYEES) LOOP-- 셀러리값 전체를 SAL에 넘겨라(FOR반복)
        SALARIES(I) := SAL;
        I := I + 1;
    END LOOP;
    
    FOR I IN 0..106 LOOP
        DBMS_OUTPUT.PUT_LINE(SALARIES(I));
    END LOOP;    
END;    

-- 12. 예외처리
/**

    EXCEPTION 
        WHEN 예외 THEN
            예외처리
        WHEN 예외 THEN
            예외처리
        WHEN OTHERS THEN (나머지 모든경우)
*/

-- 문제1 : 
DECLARE 
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME
      INTO FNAME
      FROM EMPLOYEES
--     WHERE EMPLOYEE_ID = 0; -- 오류발생 : 존재하지 않는 사원 조회(No data was found from the objects.)
     WHERE DEPARTMENT_ID = 50; -- 오류발생 :변수에 저장할 데이터가 너무많음 exact fetch returns more than requested number of rows 
EXCEPTION
    WHEN OTHERS THEN -- 예외사유 (예외사항을 모를떄는 OTHERS, 데이터가 없는 경우에는 NO_DATA_FOUND 등등) 
        DBMS_OUTPUT.PUT_LINE('예외코드' || SQLCODE); 
        DBMS_OUTPUT.PUT_LINE('예외메시지' || SQLERRM);     
END;     


    


