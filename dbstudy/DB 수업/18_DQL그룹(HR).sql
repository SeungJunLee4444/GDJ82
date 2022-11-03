
-- 1. 그룹(GROUP BY)
--  => GROUP BY를 사용한 칼럼은 중복데이터가 제거
--  => GROUP BY에서 쓰인 칼럼은 SELECT에도 똑같이 있어야한다
--  => GROUP BY 사용시, GROUP BY에 쓰인 칼럼만 SELECT에 존재할 수 있으며, 그 외는 함수뿐이다 
 
-- ex1) 동일한 부서번호로 그룹화하여 조회
SELECT DEPARTMENT_ID
  FROM EMPLOYEES
  GROUP BY DEPARTMENT_ID;

-- ex2) 대표적인 그룹화 실패 : SELECT와 GROUP BY의 칼럼 일치시킬것 *
SELECT EMPLOYEE_ID
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID;
 
-- ex3) 복수의 칼럼 조회 : 복수의 칼럼을 그룹화하면, 각 칼럼 모두가 값이 일치한 경우로 그룹이 생성된다         
SELECT DEPARTMENT_ID, MANAGER_ID ,COUNT(*)
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID, MANAGER_ID    -- * 중복제거
 ORDER BY DEPARTMENT_ID;
-- => 여기선 부서번호와 매니저번호가 둘다 값이 일치해야만 같은 그룹이 되며,
-- COUNT를 통해 그룹의 개수를 확인할 수 있다(중복제거가 안된게 아니니 오해하지말것) *
 

-- 2. 그룹화 함수 
-- GROUP 지정 칼럼 외에는 SELECT에 작성금지, 그 외에는 전부 함수만 가능(집계함수)
SELECT
       DEPARTMENT_ID
      ,SUM(SALARY) AS 부서별연봉합계
      ,FLOOR(AVG(SALARY)) AS 부서별연봉평균
      ,MAX(SALARY) AS 부서별최대연봉
      ,MIN(SALARY) AS 부서별최소연봉
      ,COUNT(*) AS 부서별사원수
  FROM EMPLOYEES
 GROUP BY 
       DEPARTMENT_ID; -- * 동일한 부서번호를 그룹화 한후, 그룹화 함수 사용 (OVER PARTITION BY 사용안해도됨)

       /*
       WHERE    : 그룹 이전 조건
       GROUP BY
       HAVING   : 그룹 이후 조건
       */

-- 3. 그룹조건 지정(HAVING) &&&&&&&&&&&&&&&&&&&&&&&
--  1) GROUP BY로 처리할 행은 적을수록 성능이 좋다
--  2) WHERE절은 GROUPBY 이전에 처리되므로, 가능한 모든 조건은 WHERE절에서 처리(미리 데이터를 선별해 추려둬야함)
--  3) HAVING절  WHERE절에서 처리할 수 없는 조건

-- 1) 부서번호가 100 미만인 부서들의 연봉평균을 조회하시오(WHERE, HAVING 모두 가능한 조건)
SELECT
      DEPARTMENT_ID
     ,FLOOR(AVG(SALARY)) AS 부서별연봉평균
  FROM EMPLOYEES
-- WHERE 
--       DEPARTMENT_ID < 100
 GROUP BY 
       DEPARTMENT_ID
HAVING 
       DEPARTMENT_ID < 100; -- * WHERE에 쓰든 HAVING에 쓰든 결과는 동일(WHERE을 쓸것 *)
                            --(WHERE에서 처리하는게 더 우수한 쿼리문)


-- 2) 소속된 사원수가 10명 이상인 부서의 연봉평균 조회하기(HAVING만 가능)
-- (그룹에)'소속된' 사원수는 GROUP BY 이후에만 알 수 있기 때문에, WHERE절에서는 처리불가능



SELECT DEPARTMENT_ID
      ,COUNT(*) AS 부서별사원수
      ,FLOOR(AVG(SALARY)) AS 부서별연봉평균
  FROM 
       EMPLOYEES
 GROUP BY 
       DEPARTMENT_ID        -- GROUP BY의 칼럼은 SELECT에 있어야한다
HAVING COUNT(*) >= 10;
-- ex) 소속된 직원수, 팔린 물건들(특정 그룹에 묶인 것들) ***



-------------



-- 연습

-- 1. 급여평균이 10000이상인 부서의 부서번호와 급여평균 조회하기
SELECT
      DEPARTMENT_ID  
     ,FLOOR(AVG(SALARY)) AS 급여평균
  FROM EMPLOYEES
-- WHERE AVG(SALARY) >= 10000 
 GROUP BY
       DEPARTMENT_ID                    -- * GROUPBY에 있는 칼럼만 SELECT있을 수 있음, 그 외에는 함수만 **
HAVING FLOOR(AVG(SALARY)) >= 10000;     -- 그루핑한 부서의 조건이 급여평균 10000이니 HAVING에 ?  


-- 2. 동일한 부서번호(DEPARTMENT_IN)로 조회하기
--  동일한 부서번호를 가진 사원들을 직업아이디(JOB_ID)로 다시 그룹화
--  즉, 부서별 직업아이디별로 그룹화하기
--  부서별 직업아이디별로 그룹화하여 각 그룹의 사원수 조회하기
--  부서번호가 없는 사원은 제외하기

SELECT DEPARTMENT_ID, JOB_ID, COUNT(*)  AS 사원수      -- * 두개 다와도 되고 하나만 와도되고
  FROM EMPLOYEES                                       -- (그룹바이 안의 칼럼 중에서만 SELECT에 쓸수있음)
 WHERE DEPARTMENT_ID IS NOT NULL
 GROUP BY DEPARTMENT_ID, JOB_ID                         -- * 여러개 그룹가능
 ORDER BY 사원수;

 
 
-------------- 
 

-- DEPARTMENTS 테이블 연습.

-- 1. 동일한 지역(LOCATION_ID)으로 그룹화하여 조회하기
SELECT LOCATION_ID
  FROM DEPARTMENTS
 GROUP BY LOCATION_ID;         -- * 중복제거하며 같은 값을 지닌 칼럼끼리 그룹화
       
       
--------------      
    
        
-- 2. 동일한 지역(LOCATION_ID)으로 그룹화하여 각 지역별 존재하는 부서수 조회하기
--    부서수가 2 이상인 지역만 조회하기
SELECT 
       LOCATION_ID AS 지역
      ,COUNT(*) AS 부서수 
  FROM 
       DEPARTMENTS
 GROUP BY 
       LOCATION_ID
HAVING COUNT(*) >= 2;    



--------------
       
-- 3. 동일한 지역(LOCATION_ID)으로 그룹화하여 각 지역별 존재하는 부서수 조회하기
--    MANAGER_ID가 없는 지역은 제외하고 조회하기

SELECT LOCATION_ID, COUNT(*)
  FROM DEPARTMENTS
 WHERE MANAGER_ID IS NOT NULL
 GROUP BY LOCATION_ID;


--------------

-- 4. 부서명(DEPARTMENT_NAME)의 첫 2글자로 그룹화하여 해당 그룹의 개수 조회하기

SELECT SUBSTR(DEPARTMENT_NAME, 1, 2), COUNT(*)      -- * GROUP BY 안에있는 값이 SELECT에 들어가기 때문에 함수채로 입력
  FROM DEPARTMENTS
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2);
 


--------------

-- 5. 부서명(DEPARTMENT_NAME)의 첫 2글자로 그룹화하여 해당 그룹의 개수 조회하기
--    부서명의 첫 2글자가 'It', 'Co'인 부서만 조회하기

SELECT SUBSTR(DEPARTMENT_NAME, 1, 2), COUNT(*)     
  FROM DEPARTMENTS
 WHERE SUBSTR(DEPARTMENT_NAME, 1, 2) IN ('IT', 'Co')            -- * OR이나 IN 사용, IN연산을 쓰는게 좋으나 가끔 못쓰는 경우발생
 GROUP BY SUBSTR(DEPARTMENT_NAME, 1, 2);                        -- * IN('김%', '이%') : 안됨 => OR사용





























