-- [문자열 함수]

-- 1. 대소문자 변환
--  1) UPPER(칼럼)    : 칼럼의 데이터를 모두 대문자로 변환
--  2) LOWER(칼럼)    : 칼럼의 데이터를 모두 소문자로 변환
--  3) INITCAP(칼럼)  : INITIAL CAPITAL(첫글자만 대문자, 나머지는 소문자)

--ex)
SELECT 
    EMAIL, LOWER(EMAIL), INITCAP(EMAIL)
   FROM
    EMPLOYEES;

-- FIRST_NAME 칼럼에서 'JAMES' 조회하기
SELECT UPPER(EMPLOYEE_ID), FIRST_NAME, LAST_NAME 
    FROM EMPLOYEES
   WHERE FIRST_NAME = INITCAP('JAMES');     -- INITCAP을 통해 해당 데이터의 형식에 맞춰서 출력
   
-- 2. 길이구하기
--  1) LENGTH(칼럼)   : 글자수 반환
--  2) LENGTHB(칼럼)  : 바이트 수 반환
-- * 한글하고 영어는 바이트 수가 다르기 때문에 LENGTHB를 사용하기도 함
SELECT 
     LENGTH('HELLO'), LENGTHB('HELLO'),  -- 5 / 5
     LENGTH('안녕'), LENGTHB('안녕')     -- 2 / 6
    FROM 
        DUAL;
    
-- 3. 문자연결
--  1) || : 문자열간 결합
--  2) CONCAT(A,B) : A와 B를 연결
--      (1) CONCAT 함수의 인수는 2개만 지원됨
--      (2) A + B + C 세 인수를 연결하려면, CONCAT(CONCAT(A,B),C)
SELECT 
    FIRST_NAME || ' ' || LAST_NAME AS FULL_NAME
    ,CONCAT(CONCAT(FIRST_NAME, ' '), LAST_NAME)     
   FROM
    EMPLOYEES;
    -- * '  ' 중간에 공백을 줄 수 있음
    
-- 4. 일부만 반환
-- SUBSTR(칼럼, BEGIN, LENGTH)
-- 칼럼 데이터의 BEGIN 위치부터 LENGTH개만큼 반환
-- * BEGIN은 INDEX가 아님(BEGIN은 1부터 시작하기 때문) **
SELECT 
    SUBSTR(FIRST_NAME, 1, 3)
   FROM
    EMPLOYEES;
    
-- 5. 특정 문자열의 위치 반환
-- INSTR(칼럼, 찾을 문자열)
-- 반환되는 위치는 인덱스가 아님(1부터 시작함)
-- 찾는 문자열이 없으면 0을 반환 *
SELECT
    INSTR(EMAIL, 'A')   -- 'A'의 위치를 반환
   FROM
    EMPLOYEES;

-- 6. 문자열 채우기(PADDING)
--  1) LPAD(칼럼, 전체폭(글자수), 채울 문자) : 왼쪽에 채우기
--  2) RPAD(칼럼, 전체폭, 채울 문자) : 오른쪽에 채우기
-- * 여기서 전체폭은 기존 글자까지 포함한 개수
            
SELECT LPAD(DEPARTMENT_ID, 3, 0),
       LPAD(NVL(DEPARTMENT_ID,0), 3, 0) -- NVL : NULL값은 0으로 표시
    FROM
        EMPLOYEES;
        
-- ex) 비밀번호 보안처리        
SELECT 
    RPAD(SUBSTR(EMAIL, 1, 2), 5, '*')
    FROM
        EMPLOYEES;
    
-- 7. 공백제거
--  1) LTRIM(칼럼) : 칼럼 데이터의 왼쪽 공백 제거
--  2) RTRIM(칼럼) : 칼럼 데이터의 오른쪽 공백 제거
--  3) TRIM(칼럼)  : 칼럼 데이터의 양쪽 공백 제거
--  성공하면 공백을 지운 나머지 글자수를 반환

SELECT
    LENGTH(LTRIM('   HELLO'))
   ,LENGTH(RTRIM('HELLO   '))
   ,LENGTH(TRIM('   HELLO   '))
   FROM
    DUAL;