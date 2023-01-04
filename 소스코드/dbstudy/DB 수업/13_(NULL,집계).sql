-- # 함수 확인용 기초데이터
DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NAME VARCHAR2(20 BYTE),
    KOR NUMBER(3),
    ENG NUMBER(3),
    MATH NUMBER(3)
);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES(NULL, 100, 100, 100);   
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('영숙', NULL, 100, 100);             -- 칼럼을 꼭 전부안써도 값의 개수를 맞추면 실행가능
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('정수', 100, NULL, 100);
INSERT INTO SAMPLE(NAME, KOR, ENG, MATH) VALUES('지영', 100, 100, NULL);
COMMIT;                                                                             -- DML은 자동 저장기능이 없어서 COMMIT 사용

-- NULL값은 연산시 결과가 NULL
SELECT 1 + NULL FROM DUAL;  -- NULL

-- NULL 처리 함수

-- 1. NVL 함수
--  NVL(칼럼, A)
-- => 칼럼이 NULL이면 A(문자는 '문자', 정수타입은 0)

-- ex) NAME이 없으면 '아무개', KOR, ENG, AMTH가 없으면(정수가 없으면) 0으로 조회
SELECT 
        NVL(NAME, '아무개') AS STU_NAME                                              -- AS 사용: 칼럼의 이름을 바꿀 수 있음
        ,NVL(KOR, 0)
        ,NVL(MATH, 0)
    FROM
        SAMPLE
--  WHERE 
--        STU_NAME != '아무개'           --아무개는 뺴고 조회하기
   ORDER BY 
--            NVL(NAME, '아무개')ASC;
            NAME ASC;                                    -- NULL은 기본적으로 오라클에서 제일 큰값으로 인식, 제일 밑에 나오게됨
            
                    
-- ex) 이름과 총점을 조회하시오                                        -- SELECT는 조회(DQL)
-- 이름이 없으면 '아무개', 점수가 없으면 0점 처리
-- 아무개 200, 영숙 200, 정수 200, 지영 200
SELECT 
        NVL(NAME, '아무개') AS 이름,
        NVL(KOR, 0) + NVL(ENG, 0) + NVL(MATH, 0) AS 총점      -- NULL이면 0점 즉 총점이 계산된다 
        
--      KOR + ENG + MATH(NULL처리 없이 계산되면 값이 NULL이 되니까 안됨)
    FROM
        SAMPLE;

-- 2. NVL2 함수(NVL은 NULL인 경우에만 값의 변경이 가능, NVL2는 값이 NULL이 아닌 경우에도 값의 지정이 가능 *
-- * 괄호안에 NULL인 경우와 NULL이 아닌 경우의 값은 같은 타입이어야한다 ex) 응시, 결시o, 응시, 5x **

--  NVL2(칼럼, NULL이 아닐때 사용할 값, NULL일 때 사용할 값)
SELECT 
    NVL2(NAME, NAME || '님', '아무개'), -- NULL값이 아니면 그대로 쓰고, NULL이면 '아무개'
    NVL2(KOR, '응시', '결시'),
    NVL2(ENG, '응시', '결시'),
    NVL2(MATH, '응시', '결시')
    FROM
        SAMPLE;                                         -- 자바의 ||는 오라클의 OR, &&는 AND  / ||는 문자열간 결합



--=========================================================================================================


-- [집계함수(그룹함수)]                         * 집계함수는 인수가 무조건 1개여야한다 **
-- 1. 통계(합계, 평균, 최대, 최소, 개수 등)
-- 2. NULL 
-- 3. 종류
--  1) SUM(칼럼)   : 칼럼 합계
--  2) AVG(칼럼)   : 칼럼 평균 
--  3) MAX(칼럼)   : 칼럼 최대값
--  4) MIN(칼럼)   : 칼럼 최소값
--  5) COUNT(칼럼) : 칼럼에 입력된 데이터의 개수 

-- 각 칼럼(KOR, ENG, MATH)의 합계
SELECT 
    SUM(KOR),
    SUM(ENG),
    SUM(MATH),
--    SUM(KOR,ENG,MATH),      -- 오류발생 : 인수(ARGUMENTS) 가 3개임으로 불가능함
    SUM(KOR + ENG + MATH),  -- = KOR + ENG + MATH와 같은 의미(SUM 함수를 잘못 사용한 예시)
    SUM(KOR) + SUM(ENG) + SUM(MATH) -- = 국어합 + 영어합 + 수학합
  FROM
       SAMPLE;
       
        -- * 집계함수들은 로우단위 연산이 아니라, 칼럼단위 연산을 쓰는것(아래로)
    -- 열단위 연산을 위해 SUM을 쓰는것

-- 각 칼럼(KOR, ENG, MATH)의 평균
SELECT 
    AVG(KOR)        -- NULL 제외한 KOR의 평균
   ,AVG(ENG)        -- NULL 제외한 ENG의 평균
   ,AVG(MATH)       -- NULL 제외한 MATH의 평균
   FROM
        SAMPLE;
        
--P NULL값은 결시를 의미하므로 0점을 처리함

SELECT 
      AVG(NVL(KOR, 0))
     ,AVG(NVL(ENG, 0))
     ,AVG(NVL(MATH, 0))
   FROM
        SAMPLE;
        
        -- * NVL을 AVG 안에 집어넣어 계산 **
        
-- 각 칼럼(KOR, ENG, MATH)의 최대값
SELECT 
    MAX(KOR)
   ,MAX(ENG)
   ,MAX(MATH)
   FROM
        SAMPLE;

-- 각 칼럼(KOR, ENG, MATH)의 최소값
SELECT
    MIN(NVL(KOR))
   ,MIN(NVL(ENG))
   ,MIN(NVL(MATH))
   FROM
        SAMPLE;

--국어 시험을 응시한 학생이 몇명인가? * COUNT는 기본적으로 행의 개수를 구하는 집계함수
SELECT
    COUNT(KOR)          -- 특정 칼럼에서 NULL이 아닌 행의 개수
    FROM
        SAMPLE; --3

-- 전체 학생은 몇명인가? -- 전체 ROW의 개수 구하기
SELECT
    COUNT(*)
    FROM
        SAMPLE;
    

-- * COUNT는 기본적으로 NULL을 제외한 개수기 때문에, COUNT(*)는 NULL 포함 전체 개수를 의미한다
-- => SUM, AVE 없이 연산하기

-- 성명   국어 영어 수학 합계 평균
-- 아무개 100  100  100  300 100
-- 영숙    0   100  100  200  66.6
-- 정수   100   0   100  200  66.6
-- 지영   100  100   0   200  66.6

SELECT 
    NVL(NAME, '아무개') AS 성명              -- * AS는 칼럼에 별명붙이기
   ,NVL(KOR, 0) AS 국어
   ,NVL(ENG, 0) AS 영어
   ,NVL(MATH, 0) AS 수학
--   ,국어 + 영어 + 수학 AS 합계
   , NVL(KOR, 0) + NVL(ENG, 0) + NVL(MATH, 0) AS 합계
   , (NVL(KOR, 0) + NVL(ENG, 0) + NVL(MATH, 0)) / 3 AS 평균
   FROM
    SAMPLE;
    
    
    
    
    
    