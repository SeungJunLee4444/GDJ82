-- 1. 순위
--  1) RANK() OVER(ORDER BY 순위구할 칼럼 ASC) :   오름차순 순위, 낮은값이 1등, ASC 생략가능
--  2) RANK() OVER(ORDER BY 순위구할 칼럼 DESC) :  내림차순 순위, 높은값이 1등, ASC 생략가능
--  3) 같은 값이면 같은 등수(동점)으로 처리 :      ex) 1, 2, 3, 3, 5(동점 발생시 동점 처리하고 다음순위를 없앰)


-- 1) EMPLOYEES 테이블의 사원 정보를 연봉이 높은 순으로 조회하기
--  연봉 순위를 함께 조회하기
SELECT
       rank() OVER (ORDER BY SALARY DESC) AS 연봉순위         -- * SALARY값을 기준으로 내림차순 정렬
      ,EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY
  FROM
      EMPLOYEES;
    -- * RANK를 사용하면 자동으로 정렬까지 하게된다 **
    
-- 2) EMPLOYEE 테이블의 사원정보를 입사 순으로 조회하기
SELECT
       rank() OVER (ORDER BY HIRE_DATE ASC) AS 연봉순위       -- * 입사순 ASC    
      ,EMPLOYEE_ID
      ,FIRST_NAME
      ,LAST_NAME
      ,HIRE_DATE
  FROM
       EMPLOYEES;
       
--------------------------------------------------------------------------------       
       
-- 2. 그룹화 : 비슷한 값끼리 모아서 출력(중복O)                                     * 헷갈리던 쿼리문
--  1) OVER(PARTITION BY 그룹화칼럼)와 집계함수
--  그룹화 작업을 수행하므로 집계함수(그룹함수)와 함께 사용이 가능함
-- 해석 : 그루핑한 부서번호의 개수와 맞게 집계함수들의 값을 배치
SELECT      
       DEPARTMENT_ID
        -- * 그저 같은값 중복제거 처리(있든 없든 아래 값에 변화X)
      ,SUM(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별연봉합계  -- * 동일한 부서번호를 지닌 SALARY들의 합계 구함 
      ,FLOOR(AVG(SALARY) OVER(PARTITION BY DEPARTMENT_ID)) AS 부서별연봉평균  -- * 정수내림        
      ,MAX(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최대연봉
      ,MIN(SALARY) OVER(PARTITION BY DEPARTMENT_ID) AS 부서별최저연봉
      ,COUNT(*)    OVER(PARTITION BY DEPARTMENT_ID) AS 부서별사원수
  FROM 
       EMPLOYEES
 WHERE DEPARTMENT_ID IS NOT NULL;
-- WHERE DEPARTMENT_ID != NULL;    -- * 자바에서만 가능
        
--  2) RANK()함수와 PARTITION BY을 함께 사용해 그룹내 순위 구하기 ***
SELECT 
       RANK() OVER(PARTITION BY DEPARTMENT_ID ORDER BY SALARY DESC) AS 부서내연봉순위  -- 부서별로 연봉 높은순위
      ,EMPLOYEE_ID
      ,FIRST_NAME
      ,LAST_NAME
      ,SALARY
      ,DEPARTMENT_ID
     
 FROM
       EMPLOYEES
 ORDER BY 
       DEPARTMENT_ID;     

--------------------------------------------------------------------------------

-- 3. 분기처리 함수(IF,SWITCH)
--  1) DECODE(표현식, 값1, 결과1, 값2, 결과2, 값3, 결과3.....)
--  2) 표현식의 결과가 값1이면 결과1 반환 값2면 결과2 반환
--  3) 표현식의 결과와 값의 비교는 동등 비교(=)만 가능함

-- * 두개 이상의 테이블에서 값을 출력할 경우 JOIN , DECODE 두가지 방식이 있다
-- 다만 JOIN은 테이블이 많을수록 성능이 떨어지기 때문에, DECODE가 사용된다
-- (JOIN은 테이블 전부를 사용, DECODE는 테이블 하나만 사용)

--  1) JOIN없이 EMPLOYEE 테이블만 이용하여, EMPLOYEE_ID, DEPARTMENT_NAME 조회하기
SELECT
       EMPLOYEE_ID
      ,DECODE(DEPARTMENT_ID
        , 10, 'Administration'
        , 20, 'Marketing'
        , 30, 'Purchasing'
        , 40, 'Human Resources'         
        , 50, 'Shipping'
        , 60, 'IT') AS 부서명
  FROM 
        EMPLOYEES;              -- * JOIN으로 테이블을 연결해 정보를 열람하면 성능이 떨어진다
                                -- * 컴퓨터가 할 일을 직접 입력해서 성능을 높일 수 있음


--  2) PHONE_NUMBER
--      011(시작번호)   MOBILE
--      515             EAST
--      590             WEST
--      603             SOUTH    
--      650             NORTH

SELECT
       EMPLOYEE_ID, PHONE_NUMBER
      ,DECODE(SUBSTR(PHONE_NUMBER, 1, 3)    -- * 휴대폰 번호 앞에 3글자(문자열함수)
      ,'011', 'MOBILE'
      ,'515', 'EAST'
      ,'590', 'WEST'
      ,'603', 'SOUTH'
      ,'650', 'NORTH'
      ) AS REGION
 FROM     
      EMPLOYEES;  


-- 4. 분기표현식(IF)
--  1) CASE END문(함수는 아님)
--  CASE
--      WHEN 조건식1 THEN 결과값1
--      WHEN 조건식2 TEHN 결과값2
--      WHEN 조건식3 THEN 결과값3....
--      ELSE 나머지 결과값
--   END

-- ex) 연봉에 따른 크기 비교
--      SALARY가 < 10000 : C그룹, SALARY가 < 20000 : B그룹, SALARY가 >= : A
SELECT 
       EMPLOYEE_ID
      ,SALARY 
      ,CASE
            WHEN SALARY < 10000 THEN 'C'
            WHEN SALARY < 20000 THEN 'B'
            ELSE 'A'
        END AS 구분
 FROM
      EMPLOYEES;  
      
      
-- EMPLOYEE_ID, HIRE_DATE(YYYY-MM-DD), 근무개월수 계산, 퇴직금 정산대상유무 조회하기
--(근무개월수가 240개월 이상이면 정산대상, 아니면 빈문자열)

SELECT
       EMPLOYEE_ID
      ,TO_CHAR(HIRE_DATE, 'YYYY-MM-DD')
      ,FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) AS 근무개월수
      ,CASE
             WHEN MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= 240 THEN '정산대상'
             ELSE ' '
       END AS 퇴직금정산대상     
 FROM   
      EMPLOYEES;  
        
        -- * 복습
        --  ) 날짜를 형식 타입으로       : TO_CHAR
        --  ) 현재~특정기간 사이 개월수  : MONTHS_BETWEEN(SYSDATE, 원하는 기간칼럼)
        --     * ADD_MONTHS             : 몇개월 이전, 이후 구하기
                        
        --  ) 원하는 자리 절삭       :TRUNC(칼럼, 0)
        --  ) 분기표현식(SWITCH)     : CASE END 문(WHEN THEN, ELSE)



 SELECT DEPARTMENT_NAME
	  FROM DEPARTMENTS 
	WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID
			  FROM EMPLOYEES
			WHERE MAX(SALARY));





