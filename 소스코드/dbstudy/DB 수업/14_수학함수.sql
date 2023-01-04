-- 숫자 함수

-- 1. 제곱
-- POWER(A, B) : A의 B제곱
SELECT POWER(2,10) FROM DUAL; -- 2의 10제곱


-- 2. 제곱근(루트)
-- SQRT(A) : 루트 A
SELECT SQRT(25) FROM DUAL; -- 5


-- 3. 절대값
-- ABS(A) : A의 절대값
SELECT ABS(5), ABS(-5) FROM DUAL;

-- 4. 나머지
-- MOD(A, B) : A를 B로 나눈 나머지
SELECT MOD(7, 2) FROM DUAL;

-- 5. 부호 판별
-- SIGN(A) : A의 부호가 + 이면 1, -이면 -1, 0이면 0을 반환
SELECT SIGN(5), SIGN(-5), SIGN(0) FROM DUAL; -- 1, -1, 0

-- 6. 정수로 올림
-- CEIL(A) : 실수 A를 정수로 올림
SELECT CEIL(1.1), CEIL(-1.1) FROM DUAL; -- 2, -1

-- 7. 정수로 내림
-- FLOOR(A) 실수A를 정수로 내림
SELECT FLOOR(1.1), FLOOR(-1.1) FROM DUAL; -- 1, -2

-- 8. 원하는 자릿수로 절사 *
-- * TRUNCATE는 DDL에서 ROW 데이터값들만 전부 지우고 칼럼만 남긴다, DROP은 테이블 자체를 삭제, DELETE는 특정 데이터만 삭제
-- TRUNC(A, [DIGIT]) : 실수A를 DIGIT 자릿수로 절사, DIGIT 생략하면 0취급, 정수로 절사
SELECT
     TRUNC(1.99999)     -- 1
     ,TRUNC(1.99999, 1) -- 1.9  -- 소수점 한자리까지
     ,TRUNC(1.99999, 2) -- 1.99 -- 소수점 두자리까지
    FROM    
        DUAL;

SELECT
     TRUNC(9999, -1) -- 9990 (원단위 절사)   -- 일의자리
    ,TRUNC(9999, -2) -- 9900                -- 십의자리
   FROM
        DUAL;
        -- * 소수점은 자리를 잘라내고, 자연수는 0으로 만듬 **

-- 9. 원하는 자릿수로 반올림
-- ROUND(A, [DIGIT]) : 실수A를 DIGIT 자릿수로 반올림, DIGIT 생략하면(0) 정수로 반올림
SELECT 
    ROUND(145.45) -- 145
   ,ROUND(145.45, 1) -- 145.5
   ,ROUND(145.45, -1) -- 150
   FROM
        DUAL;

-- 문제발생
-- * 기본의 CEIL은 원하는 자리가 아닌 실수를 정수로만 바꿔주는 함수
-- 1. '원하는 자리'수로 올림 처리는? CEIL은 무조건 정수로 만들던데?
--  1) 소수 한자리 올림 : CEIL(값 * 10) / 10
--  2) 소수 두자리 올림 : CEIL(값 * 100) / 100
--  3) 소수 세자리 올림 : CEIL(값 * 1000) / 1000
--  4) 정수 올림        : CEIL(값 * 1) / 1
--  5) 1의자리 올림     : CEIL(값 * 0.1) / 0.1
--  6) 10의자리 올림    : CEIL(값 * 0.01) / 0.01
--  7) 100의자리 올림   : CEIL(값 * 0.001) / 0.001
-- * 일반화            : CEIL(값 * POWER(10, DIGHT)) / POWER(10, DIGIT)

SELECT 
    CEIL(1.111 * POWER(10,1)) / POWER(10,1)  -- 1.2
   ,CEIL(1.111 * POWER(10,2)) / POWER(10,2)  -- 1.12
   ,CEIL(11111 * POWER(10, -1)) / POWER(10, -1) -- 11120
   ,CEIL(11111 * POWER(10, -2)) / POWER(10, -2)  -- 11200
    FROM
        DUAL;
        -- * 소수점 자리는 '해당 소수점 자릿수 아래를 올림' **
        -- * 자연수 자릿수는 '지칭한 해당 자릿수를 올림'  **
        
        
-- 2. 원하는 자리수로 내림 처리는?
-- => FLOOR 쓰면될듯?




