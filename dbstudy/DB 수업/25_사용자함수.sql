/**
    [25] 사용자 함수
    
    - 사용자가 만든 함수
    - 어떤 값을 반환할 때 사용
    - 값을 반환할 때 RETURN 사용(따라서 출력파라미터 X)
    - 입력 파라미터 사용 가능
    - 값을 확인할 수 있도록 SELECT 문과 같은 쿼리문에서 호출
    
    1) 형식
        CREATE [OR REPLACE] FUNCTION 함수이름[(매개변수)]
        RETURN 반환타입
        IS
            변수선언
        BEGIN
            함수본문
        [EXCEPTION]
            예외사항
        END [함수이름 명시]
*/

-- 1. 
-- 함수 FUNC1 정의

CREATE OR REPLACE FUNCTION FUNC1
RETURN VARCHAR2                     -- * 반환타입에서는 크기를 명시하지 않음
IS
BEGIN
    RETURN 'HELLO WORLD';           -- * 실제 반환값
END FUNC1;    

-- 함수 FUNC2 호출(SELECT 쿼리문에서 호출)
SELECT FUNC1() FROM DUAL;

-- 2. 함수 FUNC2 정의
-- 사원번호를 전달하면 해당 사원의 FULL_NAME이 반환되는 함수
CREATE OR REPLACE FUNCTION FUNC2(EMP_ID EMPLOYEES.EMPLOYEE_ID%TYPE) 
RETURN VARCHAR2
IS
    FNAME EMPLOYEES.FIRST_NAME%TYPE;
    LNAME EMPLOYEES.LAST_NAME%TYPE;
BEGIN
    SELECT FIRST_NAME, LAST_NAME
      INTO FNAME, LNAME 
      FROM EMPLOYEES
     WHERE EMPLOYEE_ID = EMP_ID;
    RETURN FNAME || '' || LNAME;
END FUNC2;   

SELECT FUNC2(EMPLOYEE_ID) FROM EMPLOYEES;

-- 3. 함수 FUNC3 정의
-- 연봉을 전달하면 'A그룹', 'B그룹', 'C그룹', 을 반환하는 함수
-- 15000 이상 A그룹, 8000 이상 B그룹, 나머지 C그룹


CREATE OR REPLACE FUNCTION FUNC3(SAL EMPLOYEES.SALARY%TYPE)
RETURN VARCHAR2                                             -- *2 RESULT의 타입은 VARCHAR2타입

IS  
    RESULT VARCHAR(10 BYTE);
BEGIN
    IF SAL >= 15000 THEN
        RESULT := 'A그룹';
    ELSIF SAL >= 8000 THEN
        RESULT := 'B그룹';
    ELSE 
        RESULT := 'C그룹';
    END IF;
    RETURN RESULT;                                          -- *1 반환하는건 RESULT
END FUNC3;    

-- 함수 FUNC3 호출
SELECT EMPLOYEE_ID
      ,FIRST_NAME
      ,SALARY
      ,FUNC3(SALARY)
      FROM EMPLOYEES;
      
      
-- 4.
-- 함수 MY_CEIL 정의

-- 함수 MY_CEIL 호출
SELECT CEIL(1.123)          -- 정수로 올림
      ,MY_CEIL(1.123,2)     -- 소수 2자리 올림
      ,MY_CEIL(1.123,1)     -- 소수1자리 올림
      ,MY_CEIL(1.123,0)     -- 정수로 올림
      ,MY_CEIL(1.123, -1)   -- 일의자리 올림
      ,MY_CEIL(1.123, -2)   -- 십의자리 올림
      FROM DUAL;            

CREATE OR REPLACE FUNCTION MY_CEIL (N NUMBER, DIGIT NUMBER)
RETURN NUMBER

IS
BEGIN    
    RETURN CEIL(N * POWER(10, DIGIT)) / POWER(10, DIGIT);    -- 굳이 RESULT 변수선언 안해도 반환가능
    
END MY_CEIL;

    


























