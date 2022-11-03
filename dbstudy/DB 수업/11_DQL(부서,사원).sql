/*
    1. DQL(DML의 일부)
    => 질의어
    => 테이블의 데이터를 조회할 때 사용한다
    => 조회만 하기 때문에, DB에 변화를 주지않는다, 즉 COMMIT 개념이 없음, 트랜잭션에 속하지않음(수정)
    => 형식 *
        SELECT 칼럼1, 칼럼2, .....
            FROM 테이블
        [WHERE 조건식]              * [] 생략가능
        [GROUP BY 그룹화]           * 특정 칼럼을 그룹화하는 할때 그룹핑을 해줌, 통계관련 함수에 사용
        [HAVING 그룹화_조건식]
        [ORDER BY 정렬]
    => 실행순서 *    
        SELECT 칼럼                 (5)번
          FROM 테이블               (1)번
         WHERE 조건식               (2)번
      GROUP BY 그룹화               (3)번
        HAVING 그룹화_조건식        (4)번
      ORDER BY 정렬                 (6)번       
*/

-- 1. 특정 칼럼의 도메인 조회
-- 1) 사원테이블에서 사원이름 조회하기
SELECT NAME
    FROM SCOTT.EMPLOYEE; -- * 테이블의 주인을 명시하는법

-- 2) 칼럼에 테이블 명시
SELECT EMPLOYEE.NAME
    FROM EMPLOYEE;
    
-- 2. 칼럼과 테이블에 별명 지정 *
SELECT NAME AS 사원명  -- * 칼럼 별명
    FROM EMPLOYEE EMP; -- * 테이블 별명 EMP 지정

-- 3. 사원 테이블의 모든 칼럼 조회하기
-- 1) * (애스터리스크) 사용
-- 모든칼럼 = * (실무에서는 *사용 X, 성능을 떨어트리기 때문)
SELECT *
    FROM EMPLOYEE;


-- 2) 모든 칼럼이 필요하면 *가 아니라 모든 칼럼을 명시
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY
    FROM EMPLOYEE;
    
    
-- 3) 부서 테이블에서 지역명을 조회하기                               * 중복제거 DISDINCT 사용 
    -- (단, 동일한 지역은 한번만 조회하기)
    -- * DISTINCT : 중복제거, 칼럼 하나에 주로 사용함(두개 이상은 설계미스)
SELECT * FROM DEPARTMENT;                                           -- DEAPRTMENT 부서 칼럼 전체
SELECT LOCATION FROM DEPARTMENT;                                    -- 대구, 서울, 대구, 서울
SELECT DISTINCT LOCATION                                            -- 대구, 서울
    FROM DEPARTMENT;
    
--SELECT DISTINCT DEPT_NAME, LOCATION
--    FROM DEPARTMENT;


-- 4. WHERE 조건

-- 1) 사원테이블에 직급이 '과장'인 사원 조회하기                        -- * WHERE
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY      -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE           
   WHERE POSITION = '과장';                                          -- POSITION 칼럼이 '과장'인 로우만 출력                                      

-- 2) 사원테이블에서 급여가 '200만~500만'인 사원조회                    -- * WHERE BETWEEN AND (2방법 선호)
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   WHERE SALARY BETWEEN 2000000 AND 5000000;  -- 1 방법               -- SALARY 칼럼값이 '200만에서 500만'사이인 로우
   WHERE SALARY >= 2000000                    -- 2 방법                 
     AND SALARY <= 5000000;

-- 3) 사원테이블에서 소속 부서가 1,2인 사원 조회하기                   * WHERE OR(2번방법 선호)
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   WHERE DEPART = 1         -- 1 방법
      OR DEPART = 2;    
   WHERE DEPART IN(1, 2);   -- 2 방법
   
-- 4) 사원 테이블에서 성별이 없는 사원 조회(NULL)
-- (1) NULL 이다 : IS NULL
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE 
   WHERE GENDER IS NULL;

-- (2) NULL 아니다 : IS NOT NULL
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE 
   WHERE GENDER IS NOT NULL;

-- 5) 사원 테이블에서 김씨 조회
--  1) 만능문자(WILD CARD)
        --(1) % : 모든 문자를 의미, 글자수 제한 없음
        --(2) _ : 모든 문자를 의미, 한글자로 제한 
--  2) 예시
        --(1) 김으로 시작하는 이름 찾기    : 김%, 김_
--      김기동 -> %는 읽어내지만 _는 읽어내지 못함
        --(2) 김으로 끝나는 이름 찾기      : %김
        --(3) 김을 포함하는 이름           : %김%    
--  3) 만능문자 연산자
        --Like, not like

SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE 
   WHERE NAME LIKE '김%';                                            
   -- => 김씨로 시작하는 경우를 포함하여 조회


-- 6) 사원 테이블에서 사원번호가 1로 시작하는 사원 조회하기
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE 
   WHERE EMP_NO LIKE '1%';                                           
   -- * 오라클은 NO와 TEXT가 섞여있으면 텍스트로 자동 변환
   -- * 직원번호 1과 101 1001을 같은 범위로 묶을 수 있기 때문에, 숫자를 만능문자와 쓰는법은
   -- 좋은 방법이 아니다
   
   
-- 5. ORDER BY 절
    -- ASC             : 오름차순 정렬, 생략가능
    -- DESC(디센딩)    : 내림차순 정렬(아래로 내려갈수록 작은숫자)
    --1) 사원 테이블에서 사원명의 가나다 순으로 조회하기
SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   ORDER BY NAME ASC;                                               -- 오름차순으로 출력
   ORDER BY NAME DESC;                                              -- 내림차순으로 출력
   
    --2) 사원 테이블에서 급여가 높은 사원을 먼저 조회하기 
   SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   ORDER BY SALARY DESC;                                                -- 급여가 높은순은 내림차순이다 * 
   
    --3) 사원 테이블에서 성별의 가나다 순으로 조회하기(NULL값이 포함될 때)
     SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   ORDER BY SALARY;                                                    
   -- => 오름차순 기준 알파벳순, NULL은 제일 마지막에 추력
   
    --4) 사원 테이블에 먼저 고용된 순으로 조회하기 
       SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   ORDER BY HIRE_DATE;   
   -- 90, 93, 95, 98, 17년도 순으로 나옴(17이 숫자상 제일 작지만 제일 뒤에)
   -- * 날짜는 오름차순, 날짜가 단순히 숫자가 아닌, DATE타입이 날짜로서 인식됨
   
   -- 5) 사원 테이블에서 소속부서의 오름차순 정렬조회, 단 같은 소속 부서 내에서는 먼저 고용된 순으로
      SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
    -- * 1차 정렬 기준은 소속부서, 2차정렬 기준은 고용일자다
   ORDER BY DEPART ASC, HIRE_DATE DESC;           
    -- 시험문제
   ORDER BY DEPART, HIRE_DATE DESC;  --: DEPART는 오름, HIRE은 내림
   
   
-- 6. WHERE과 ORDER BY절 함께 사용

    --1) 사원 테이블에서 급여가 500만원 이상인 사원들을 고용된 순으로 조회
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE AS HD, SALARY
    FROM EMPLOYEE
   WHERE SALARY >= 5000000
   ORDER BY HD;   
   
   -- * SELECT문의 작동순서에 대한 문제 ***
SELECT EMP_NO, NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY AS S
    FROM EMPLOYEE       --1번
   WHERE S >= 5000000   -- 2번
   ORDER BY HIRD_DATE;  -- 3번 
   -- FROM - WHERE - SELECT - ORDER BY 순으로 처리되기 때문에, SELECT에서 만들어진 별명 S가 아직
   -- 만들어지지 않은 상태기 때문에 오류가 발생 ***

   --2) S
   
   
   
   
   
   
   
   
   
   
   
   
   
   


