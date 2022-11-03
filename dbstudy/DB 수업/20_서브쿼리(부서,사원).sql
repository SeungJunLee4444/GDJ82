DROP TABLE EMPLOYEE;
DROP TABLE DEPARTMENT;

-- DEPARTMENT 테이블 생성
CREATE TABLE DEPARTMENT(
    DEPT_NO   NUMBER            NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION  VARCHAR2(15 BYTE) NOT NULL
);

-- EMPLOYEE 테이블 생성
CREATE TABLE EMPLOYEE(
    EMP_NO    NUMBER            NOT NULL,
    NAME      VARCHAR2(20 BYTE) NOT NULL,
    DEPART    NUMBER            NULL,
    POSITION  VARCHAR2(20 BYTE) NULL,
    GENDER    CHAR(2)           NULL,
    HIRE_DATE DATE              NULL, 
    SALARY    NUMBER            NULL
);

-- 기본키
ALTER TABLE DEPARTMENT 
    ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

-- 외래키
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) 
        REFERENCES DEPARTMENT(DEPT_NO)
            ON DELETE SET NULL;
            
            
SELECT DEPARTMENT_NAME
	  FROM DEPARTMENTS 
	WHERE DEPARTMENT_ID = (SELECT DEPARTMENT_ID
			  FROM EMPLOYEES
			WHERE MAX(SALARY));


-- 부서 테이블에서 사용할 부서_시퀀스
DROP SEQUENCE DEPARTMENT_SEQ;
CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 100
    NOCACHE
    NOCYCLE;

-- 부서 테이블에 행(Row) 삽입
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '영업부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '총무부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (DEPARTMENT_SEQ.NEXTVAL, '기획부', '서울');

-- 작업의 완료
COMMIT;


-- 사원 테이블에서 사용할 사원_시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
    NOCACHE;

-- 사원 테이블에 행(Row) 삽입
INSERT INTO 
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '구창민', 1, '과장', 'M', '95/05/01', 5000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '김민서', 1, '사원', 'F', '17/09/01', 2000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '이은영', 2, '부장', NULL, '90-09-01', 5500000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL, '한성일', 2, '과장', 'M', '93-04-01', 5000000);
    
--******************************************************************************
/*

    [20] 서브쿼리
    - 정의        : SELECT 안에 SELECT가 있는것
    - 시행순서    : 서브쿼리가 먼저 실행되고, 이후 메인쿼리가 실행
    - 형태        : 서브쿼리는 메인 쿼리에 괄호()를 이용해서 포함시킴
    - 사용되는 절에 따른 구분
        1) SELECT절  : 스칼라 서브쿼리  결과 : (단일)
        2) FROM절    : 인라인뷰         결과 : (다중)
        3) WHERE절   : 서브쿼리         결과 : (단일, 다중)
    - 서브쿼리 결과에 따른 구분
        1) 단일 행 서브쿼리 
            의미 : 서브쿼리 결과가 하나
            경우 : PK나 UNIQUE 칼럼에 동등비교결과, 함수의 결과      -- 서브쿼리 조건이 PK/UNIQUE가 아니면 다중행 서브쿼리 **
            특징 : 단일행 연산자를 사용(=, !=, >, >=, <, <=) **************
        2) 다중 행 서브쿼리
            서브쿼리 결과가 2개 이상      -- "single-row subquery returns more than one row" 오류 발생시
            FROM절에나 WHERE절에서 사용
            다중행 연산자를 사용(IN, ANY, ALL 등)
      
          
*/      
-- 1. 사원번호가 1001인 사원과 같은 직급(POSITION)을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE POSITION = (SELECT POSITION                      -- * 단일행 서브쿼리는 = 사용
                     FROM EMPLOYEE                      -- * 하위쿼리는 메인쿼리와 동등비교(=) 반드시 POSITION을 반환
                    WHERE EMP_NO = 1001);               -- * EMP_NO는 PK이므로 단일행 서브쿼리(어디쿼리인지 알수있는)
            
            
--2. 급여(SALARY)가 가장 높은 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY)
                   FROM EMPLOYEE);      -- 서브쿼리가 함수이므로 단일행 서브쿼리 
                   
                   
-- 3. 부서번호가 1인 부서와 같은 지역에 있는 부서정보를 조회하기
SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE LOCATION IN (SELECT LOCATION
                    FROM DEPARTMENT
                   WHERE DEPT_NO = 1); 


-- 4. 평균급여 이상을 받는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE SALARY >= (SELECT AVG(SALARY)
                   FROM EMPLOYEE
                  ); 

-- 5. 평균근속기간 이상을 근무한 사원 조회하기
--  1) 일수 계산 : SYSDATE - HIRE_DATE
--  2) 개월 계산 : MONTHS_BETWEEN(SYSDATE, HIRE_DATE) => 개월수가 나올 뿐 둘의 용도와 의미는 같다
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
  FROM EMPLOYEE 
 WHERE MONTHS_BETWEEN(SYSDATE, HIRE_DATE) >= (SELECT AVG(MONTHS_BETWEEN(SYSDATE, HIRE_DATE))
                   FROM EMPLOYEE
                   ); 
                   
            

-- 6. 부서번호가 2인 부서 근무하는 사원들의 직급과 일치하는 직급을 가진 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY 
  FROM EMPLOYEE
 WHERE POSITION IN (SELECT POSITION 
                     FROM EMPLOYEE
                    WHERE DEPART = 2); -- "single-row subquery returns more than one row"(반환값이 복수, PK값이 아님)
   
    -- * 조건으로 사용할 칼럼이 PK, UNIQUE가 아닐때 : 다중서브쿼리
    -- * 서브쿼리문의 반환값이 2개 이상이므로 단일연산자 = 이 아닌 다중연산자 IN을 사용
    --(다중행서브쿼리의 IN사용)
    -- * 반환되는 행의 개수가 1개면 단일행 서브쿼리, 행의 개수가 2개 이상이면 다중행 서브쿼리
    
-- TIP) 단일행/다중행 상관없이 동등비교는 IN 연산으로 수행가능

--------------------------------------------------------------------------------


-- 7. 부서명이 '영업부'인 부서에 근무하는 사원 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY 
  FROM EMPLOYEE
 WHERE DEPART IN (SELECT DEPT_NO            -- * 사원 비교니까 직원번호 비교
                 FROM DEPARTMENT
                WHERE DEPT_NAME = '영업부'); -- DEPT_NAME이 PK가 아니기 때문에 (실제 행의 개수가 1개인건 상관없이) 다중행취급

SELECT E.EMP_NO, E.NAME, E.DEPART, E.GENDER, E.POSITION, E.HIRE_DATE, E.SALARY 
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.DEPT_NAME = '영업부';   


--------------------------------------------------------------------------------


-- 8. 직급이 '과장'인 사원들이 근무하는 부서 조회하기

SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT
 WHERE DEPT_NO IN (SELECT DEPART        -- PK가 들어가야한다 이런거 상관없음!
                     FROM EMPLOYEE   
                    WHERE POSITION = '과장'); 

SELECT DEPT_NO, DEPT_NAME, LOCATION
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE POSITION = '과장';
 
--------------------------------------------------------------------------------
 
-- 9. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- 2000000, 5000000 둘중 하나의 급여보다 많이 받으면 조회하기
SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY 
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MIN(SALARY)
                    FROM EMPLOYEE
                   WHERE DEPART = 1);
                   -- * WHERE SALARY > ANY(2000000. 5000000)
                   -- SALARY가 200만보다 크거나 500만보다 크면됨(OR개념)


-- 10. 부서번호가 1인 부서에 근무하는 사원들의 급여보다 더 많은 급여를 받는 사원 조회하기
-- (모든급여(2000000, 5000000) 와 비교해서 많이 받으면 조회하기

SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY 
  FROM EMPLOYEE
 WHERE SALARY > (SELECT MAX(SALARY)
                    FROM EMPLOYEE
                   WHERE DEPART = 1);
                   -- * WHERE SALARY > ALL(2000000. 5000000)
                   -- SALARY가 200만보다 크고 500만보다 크면됨(AND개념)
                   
                   
                   -- * ANY는 OR, ALL은 AND개념
                   -- * ANY, ALL이 잘 안쓰이는 이유(몰라도 무관)
                   -- 1) WHERE SALARY > ANY(2000000. 5000000)           * 2, 5000000 둘중 하나보다 커야함
                   -- =>       SALARY > (SELECT MIN(2000000, 5000000)   = 2000000만 있어도된다
                   -- 2) WHERE SALARY > ALL(2000000. 5000000)           * 2, 5000000 둘다보다 커야함
                   -- =>       SALARY > (SELECT MAX(2000000, 5000000)   = 5000000만 있어도된다    
                   
                   
--------------------------------------------------------------------------------

/*WHERE 절의 서브쿼리*/

-- 1. 전체 사원수의 인원수, 급여합계/평균/최대/최소 조회하기
SELECT
         (SELECT COUNT(*) FROM EMPLOYEE)
       , (SELECT SUM(SALARY) FROM EMPLOYEE)
       , (SELECT AVG(SALARY) FROM EMPLOYEE) 
       , (SELECT MAX(SALARY) FROM EMPLOYEE)
       , (SELECT MIN(SALARY) FROM EMPLOYEE)
   FROM DUAL;                                   -- EMPLOYEE에서 전부 꺼내써서 DUAL   

-- 2. 부서번호가 1인 부서와 같은 지역에서 근무하는 사원 조회하기

SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.LOCATION = (SELECT LOCATION
                        FROM DEPARTMENT
                       WHERE DEPT_NO = 1); 
                       
-- 스칼라 서브쿼리 접근(조인의 문법과 유사) -----------------------------------------중요X, JOIN으로 해결가능
-- => 스칼라 서브쿼리는 일치하지않는 정보도 NULL로 처리해서 가져옴(OUTER JOIN)
-- => 스칼라 서브쿼리와 동일한 방식의 조인은 '외부조인'임

SELECT 
        E.EMP_NO
       ,E.NAME
       ,E.DEPART
       ,(SELECT D.DEPT_NAME
           FROM DEPARTMENT D
          WHERE D.DEPT_NO = E.DEPART          -- 다른 테이블에서 가져올 것만 별도로 작성
            AND D.DEPT_NO = 1)
  FROM 
        EMPLOYEE E;
        
       
SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART
 WHERE D.LOCATION = (SELECT LOCATION
                        FROM DEPARTMENT
                       WHERE DEPT_NO = 1); 
       




----------------------------------------------------------------------------- 

-- * 서브쿼리 종류 정리
--  1) WHERE절(단일, 다중), 2) SELECT절, 3) FROM절(다중)



-- 4. FROM 절의 서브쿼리 : 인라인뷰 (다중)
/*
    * 인라인뷰( * 뷰는 테이블과 동의어라 생각)
    - FROM절에서 사용하는 서브쿼리를 의미
    - 인라인뷰는 주로 테이블 형식
    - 인라인뷰에 별명을 주고 사용함
    - 인라인뷰에서 조회한 컬럼만 메인 쿼리에서 조회할 수 있음 *
    - SELECT문의 실행순서를 바꿀때 사용(인라인뷰를 쓰는 이유)
    (서브쿼리 안에서 ORDER BY를 쓰면 메인 쿼리의 SELECT보다 먼저 쓸 수 있다)
*/
-- 1) 인라인뷰 원리(매우 중요, 목록보기 구현 ************************************************************************)

SELECT A.EMP_NO, A.NAME, A.POSITION     -- * (2) 따라서 메인 쿼리에도 동일한 칼럼 작성
  FROM (SELECT EMP_NO, NAME, POSITION   -- * (1) 인라인 뷰가 조회한 칼럼만 작성가능
          FROM EMPLOYEE
         WHERE DEPART = 1) A;           -- * 인라인 뷰의 별명이 A로 지음
         
/*
    * 가상칼럼
    
    5. PSEUDO(수도, 가상칼럼)
    - 존재하나 저장되어 있지 않은 칼럼을 수도칼럼이라 부른다
    - 사용할 수 있으나 일부 제약이 있음
    - 종류
        (1) ROWID : 행의 아이디, 어떤 행의 물리적 저장 위치
        (2) ROWNUM : 행의 NUMBER, 어떤 행의 번호
        
        -- * 정리
        1) 수도칼럼 : 존재하나 저장되지 않은 칼럼
            종류  (1) ROWID     : 행의 아이디  
                  (2) ROWNUM    : 행의 비밀번호
        
        2) ROWNUM : 기본적으로 1을 포함하는 범위에만 사용가능
        3) 1외의 범위에도 사용하는법 : ROWNUM에 별명을 지정하고 사용하면됨
        => 이를 위해 인라인뷰 서브쿼리 사용
        

*/

-- 1) ROWID(행의 아이디, 저장위치), ROWNUM(행의 번호)
SELECT ROWID, ROWNUM, EMP_NO, NAME
  FROM EMPLOYEE
 WHERE ROWID = 'AAAFEOAABAAALDBAAC'; 
-- => ROWID 값을 알고 조회하는건 가장 빠른 데이터 조회법]
-- => 다만 ROWID를 직접 사용하는 것은 어렵기 때문에, '인덱스'라는 개념을 도입

-- 2) ROWNUM 사용법
-- => ROWNUM은 1을 포함하는 범위만 조건으로 사용할 수 있다(무조건 1 포함된 범위만 *)
SELECT EMP_NO, NAME
  FROM EMPLOYEE
 WHERE ROWNUM BETWEEN 1 AND 2;  -- => 성공
 
SELECT EMP_NO, NAME
  FROM EMPLOYEE
 WHERE ROWNUM = 2;              -- => 오류(1이 포함되지 않았기 때문)

-- 3) ROWNUM을 1 이외의 범위를 조건으로 사용하는 방법
-- ROWNUM에 별명을 지정하고 해당 별명을 사용하면 됨
-- * SELECT 실행순서극복
-- 3 : SELECT
-- 1 : FROM(인라인뷰): ROWNUM의 별명 지정하기
-- 2 : WHERE         : ROWNUM의 별명 사용하기

-- (1) 오답
SELECT R.ROWNUM, R.EMP_NO, R.NAME
  FROM (SELECT ROWNUM, EMP_NO, NAME
          FROM EMPLOYEE
         WHERE ROWNUM BETWEEN 2 AND 3) R; 

-- (2) 성공
SELECT ROW_NUM, A.EMP_NO, A.NAME
  FROM (SELECT ROWNUM AS ROW_NUM, EMP_NO, NAME
          FROM EMPLOYEE) A
 WHERE A.ROW_NUM = 2; 
 
--------------------------------------------------------------------------------

-- 문제 1. 연봉 기준으로 가장 높은 연봉을 받는 사원 조회하기 
-- 1) 서브쿼리
SELECT EMP_NO, NAME, SALARY
  FROM EMPLOYEE
 WHERE SALARY = (SELECT MAX(SALARY)
                    FROM EMPLOYEE);
                                                -- * ALIAS(AS)는 SELECT에서만 사용가능?

-- 2) 정렬과 ROWNUM 이용
    --(1) 연봉의 내림차순 정렬(가장높은 연봉이 위에)
    --(2) 정렬 결과에 ROWNUM 이 1인 행을 조회
    --(정렬을 먼저하는 방법은 인라인뷰)
SELECT ROWNUM, A.EMP_NO, A.NAME, A.SALARY
  FROM (SELECT EMP_NO, NAME, SALARY     --3
          FROM EMPLOYEE                 --1
         ORDER BY SALARY DESC) A        --2   
 WHERE ROWNUM = 1;
 

--------------------------------------------------------------------------------

-- 문제2. 2번째로 높은 연봉을 받는 사원 조회하기
-- 1) ROWNUM 칼럼(오리지널)
-- 정렬(ORDERBY), 행번호 붙이기(ROWNUM), 열람(WHERE)
SELECT B.EMP_NO, B.NAME, B.SALARY
  FROM (SELECT ROWNUM AS ROW_NUM, A.EMP_NO, A.NAME, A.SALARY                    --*
          FROM (SELECT EMP_NO, NAME, SALARY
                  FROM EMPLOYEE
                 ORDER BY SALARY DESC) A ) B
 WHERE B.ROW_NUM BETWEEN 2 AND 10;                                              -- 목록 *
 -- 인라인뷰 A : 연봉으로 정렬한 테이블
 -- 인라인뷰 B : 정렬이 끝난 A 테이블에 별명을 붙인 테이블
 
 -- (1) ROWNUM은 1외의 숫자를 쓰려면 별도의 별명을 지정하고, 사용해야만 가능  
 -- (2) SELECT에 별명을 붙이고, WHERE보다 먼저 사용하기 위해 서브쿼리 사용
 -- * WHERE 조건에서 1,2를 사용하기 위해 그 이전에 별명이 지정되어야함
 
 
 
 -- 2) ROW_NUMBER(함수) 사용( 1) 번을 간편히)
 -- => 정렬과 행번호 추가를 한번에 해줌, 열람
SELECT A.EMP_NO, A.NAME, A.SALARY
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY SALARY DESC) AS ROW_NUM, EMP_NO, NAME, SALARY  -- 정렬과 행번호 붙이기를 동시에
          FROM EMPLOYEE) A
 WHERE A.ROW_NUM BETWEEN 2 AND 2;         
 
 -- * ROW_NUMBER과 RANK는 해당 단어를 제외하면 형태가 같다(ROW_NUMBER 사용할것)

 -- 3) RANK함수(목록에 사용하기에는 부적절, 동점자도 중복해서 처리하기 때문에 목록의 수가 매번 달라짐)
SELECT A.EMP_NO, A.NAME, A.SALARY
  FROM (SELECT RANK() OVER(ORDER BY SALARY DESC) AS 순위, EMP_NO, NAME, SALARY
          FROM EMPLOYEE) A
 WHERE A.순위 BETWEEN 2 AND 10;         

--------------------------------------------------------------------------------

-- 문제 3. 3~4번째로 입사한 사원 조회하기
SELECT A.EMP_NO, A.NAME, A.SALARY
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY HIRE_DATE) AS 순위, EMP_NO, NAME, SALARY
          FROM EMPLOYEE) A
 WHERE A.순위 BETWEEN 3 AND 4; 
 

SELECT B.EMP_NO, B.NAME, B.SALARY
  FROM (SELECT ROWNUM AS ROW_NUM, A.EMP_NO, A.NAME, A.SALARY
          FROM (SELECT EMP_NO, NAME, SALARY
                  FROM EMPLOYEE
                 ORDER BY HIRE_DATE) A ) B 
 WHERE B.ROW_NUM BETWEEN 3 AND 4;                


--------------------------------------------------------------------------------


-- 6. 기타 서브쿼리 : CREATE, UPDATE, DELETE에서 활용

-- 1. CREATE와 서브쿼리
--  1) 용도 : 새로운 테이블에 기존의 테이블 값들을 복사
--  2) NOT NULL을 제외하면 제약조건은 복사되지 않는다
--  3) 사용법 : CREATE TABLE 테이블명 AS (서브쿼리)


-- 문제1. EMPLOYEE 테이블 복사하기
DROP TABLE EMPLOYEE2;
CREATE TABLE EMPLOYEE2 
    AS (SELECT EMP_NO, NAME, DEPART, GENDER, POSITION, HIRE_DATE, SALARY
          FROM EMPLOYEE);
-- => NOT NULL을 제외한 모든 제약조건이 삭제
          
----------------------------          
-- 2)번 점검작업          
-- USER_CONSTRAINTS : USER 사용자가 사용가능한 모든 제약조건이 담겨있음
DESC USER_CONSTRAINTS;

SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'EMPLOYEE2';
-- => 값 : NOT NULL들(EMPLOYEE2에 복사된 제약조건은 오직 NOTNULL 뿐이기 때문)
----------------------------

-- 4) 별도의 제약조건을 추가
-- ex) EMPLOYEE2 테이블에 PK 제약조건을 추가하고 싶다면?
ALTER TABLE EMPLOYEE2
    ADD CONSTRAINT PK_EMPLOYEE2 PRIMARY KEY(EMP_NO);
-- 해결 : ALTER 추가문 작성


--------------------------------------------------------------------------------

-- 문제2. DEPARTMENT 테이블의 구조만 복사하기(모든 행을 제외하고 복사하기)

CREATE TABLE DEPARTMENT2
    AS (SELECT DEPT_NO, DEPT_NAME, LOCATION
          FROM DEPARTMENT
--         WHERE DEPT_NO = 1); -- (1: 부서번호 1번만 복사, 0: 안들어감) 
           WHERE 1 = 2);       -- 절대 만족하지 않을조건
      
-- 2) 행 제외하고 복제되었는지 확인           
SELECT DEPT_NO, DEPT_NAME, LOCATION FROM DEPARTMENT2;
-- 빈값

-- & AS 서브쿼리 는 전체 복사, 그중에서 1과 2는 같지않음을 이용해
-- 데이터만 누락시킴

--------------------------------------------------------------------------------

-- 2. INSERT와 서브쿼리
--  1) VALUES 절 대신 서브쿼리를 이용
--  2) 서브쿼리의 결과가 INSERT됨
--  형태 : INSERT INTO 테이블_이름(칼럼1, 칼럼2) (서브쿼리)

--  문제3. DEPARTMENT 테이블의 모든 행(ROW)을 DEPARTMENT2 테이블에 삽입
INSERT INTO DEPARTMENT2(DEPT_NO, DEPT_NAME, LOCATION) 
    (SELECT DEPT_NO, DEPT_NAME, LOCATION
       FROM DEPARTMENT);
       
SELECT DEPT_NO, DEPT_NAME, LOCATION FROM DEPARTMENT2;  

--------------------------------------------------------------------------------

-- 3. UPDATE 서브쿼리
--  1) SET, WHERE절에서 서브쿼리 활용

-- (1) 일반수정
UPDATE EMPLOYEE2 
    SET NAME = '이승준'        -- 한개를 하든 두개를 하든 이렇게 수정
       ,GENDER = 'M'
 WHERE EMP_NO = 1001; 
 
 
-- (2) 서브쿼리로 데이터 수정
--UPDATE EMPLOYEE2 
--    SET (NAME,GENDER) = (서브쿼리)       
-- WHERE EMP_NO = 1001;
--
---- ex) 1004번의 직원의 이름과 성별을 EMPLOYEE2의 1001번 직원정보에 저장
--UPDATE EMPLOYEE2 
--    SET NAME = (SELECT NAME FROM EMPLOYEE WHERE EMP_NO = 1004)      
--       ,GENDER = (SELECT GENDER FROM EMPLOYEE WHERE EMP_NO = 1004)
-- WHERE EMP_NO = 1001;
 

-- 서브쿼리 수정 최종형 
UPDATE EMPLOYEE2
    SET(NAME, GENDER) = (SELECT NAME, GENDER
                          FROM EMPLOYEE
                         WHERE EMP_NO = 1003)
   WHERE EMP_NO = 1001;                      



SELECT EMP_NO, NAME, GENDER FROM EMPLOYEE2;


-- 4. DELETE와 서브쿼리
--  1) WHERE절에서 주로 사용

DELETE 
  FROM EMPLOYEE2
 WHERE DEPART IN (SELECT DEPT_NO
                     FROM DEPARTMENT2
                    WHERE DEPT_NAME = '영업부');
COMMIT;

SELECT E.EMP_NO, D.DEPT_NAME
  FROM DEPARTMENT2 D INNER JOIN EMPLOYEE2 E
    ON D.DEPT_NO = E.DEPART;
-- 영업부의 부서번호는 PK가 아님, 둘 이상일 수 있기 때문에 다중행 서브쿼리




















