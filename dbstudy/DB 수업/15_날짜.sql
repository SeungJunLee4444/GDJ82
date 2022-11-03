-- [날짜 함수]

-- 1. 현재 날짜와 시간
SELECT SYSDATE, SYSTIMESTAMP FROM DUAL;                                           -- * SYSDATE에도 시간단위가 데이터로 포함되있지만 출력만 안될 뿐이다

-- 2. 원하는 형식으로 날짜와 시간 조회
-- 1) TO_CHAR 함수, 문자로 변환해서 조회하는 방식(SYSDATE를 시간단위까지 표기하는법) **
SELECT 
        TO_CHAR(SYSDATE, 'YYYY-MM-DD PM HH:MI:SS'),    --12시간, AM 또는 PM을 쓰면 오전오후 표현가능 *
        TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')   --24시간
    FROM
        DUAL;

-- 2) 날짜 단위(년,월,일,시,분,초)를 추출하는 함수    => TO_CHAR로 대체해 사용할것    
--  EXTRACT(단위 FROM 날짜)
SELECT 
    EXTRACT(YEAR FROM SYSDATE) AS 년도
   ,EXTRACT(MONTH FROM SYSDATE) AS 월
   ,EXTRACT(DAY FROM SYSDATE) AS 일
   ,EXTRACT(HOUR FROM SYSTIMESTAMP) AS 시         -- 기본표기값에서 추출하기 때문에, 시분초가 기본표기가 아닌 SYSDATE는 사용할 수없다
   ,EXTRACT(MINUTE FROM SYSTIMESTAMP) AS 분
   ,EXTRACT(SECOND FROM SYSTIMESTAMP) AS 초
   ,EXTRACT(TIMEZONE_HOUR FROM SYSTIMESTAMP) AS 시   -- TIMEZONE_HOUR 세계표준시간 기준
   ,FLOOR(EXTRACT(SECOND FROM SYSTIMESTAMP))  -- 초단위 소수점 내림
   
   FROM
        DUAL;
        -- * UTD 세계 표준시로 출력되기 때문에, 한국시간과 다르게 나온다
   
-- 단위(년,월,일,시,분,초) 추출은 TO_CHAR 함수로도 가능함  (EXTRACT보다는 TOCHAR을 쓰는경우가 대부분인듯?)
SELECT
    TO_CHAR(SYSDATE, 'YYYY')
   ,TO_CHAR(SYSDATE, 'MM')
   ,TO_CHAR(SYSDATE, 'DD')
   ,TO_CHAR(SYSDATE, 'HH24')
   ,TO_CHAR(SYSDATE, 'MI')
   ,TO_CHAR(SYSDATE, 'SS')
   FROM
        DUAL;
        
SELECT SYSDATE FROM DUAL;

-- 2. 날짜연산 
-- * 날짜연산함수는 ADD_MOMTHS 뿐이다
--  1) 하루(1일)을 숫자 1로 처리
--    (12시간을 숫자 0.5로 처리)
--  2) 특정 단위 후 날짜
--    (1) 1년 후      : + 365, 또는 12개월 후 
--    (2) 1개월 후    : ADD_MONTHS 함수 사용(30인지 31인지 모르기떄문에)
--    (3) 1일후       : +1, 함수없음

SELECT 
    SYSDATE -1 AS 어제
   ,SYSDATE + 1 AS 내일                                                -- * SYSDATE가 연속된다해서 값이 중첩되는게 아니다 **
   ,SYSDATE - 0.5 AS "12시간전"     -- * 칼럼에는 숫자가 올수없음
   ,SYSDATE + 0.5 AS "12시간후"
   ,SYSDATE + (1/24) AS "1시간후"
   ,TO_CHAR(SYSDATE - 0.5, 'MM/DD AM HH24:MI:SS') AS "12시간 전"
   ,TO_CHAR(SYSDATE + 0.5, 'MM/DD HH24:MI:SS') AS "12시간 후"
    FROM
        DUAL;

-- N개월 전후의 날짜
--  ADD_MONTHS(날짜, N)
SELECT
        ADD_MONTHS(SYSDATE, -1) AS "1개월 전"
       ,ADD_MONTHS(SYSDATE, +1) AS "1개월 후"
    FROM
        DUAL;
        
-- 경과한 개월 수 
-- MONTHS BETWEEN(최근날짜, 이전날짜) : 두 날짜 사이의 경과한 개월 수
SELECT 
        MONTHS_BETWEEN(SYSDATE, HIRE_DATE)
    FROM    
        EMPLOYEE;   -- 다른 계정의 테이블 가져오기 **









   
   