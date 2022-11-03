-- 테이블 삭제
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
    
    
--참조무결성 위배 데이터 삽입을 위해서 외래키 일시중지
ALTER TABLE EMPLOYEE
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;
    
--참조무결성 위배 데이터 삽입    
INSERT INTO EMPLOYEE VALUES(EMPLOYEE_SEQ.NEXTVAL, '신현준', 5, '대리', 'M', '98-12-01', 3500000);
COMMIT;

-- => 이러면 부모테이블 기본키에 없는 값을 자식테이블에서 지닐 수 있게 된다 (5번 신현준)                  

--외래키 재시작    
--ALTER TABLE EMPLOYEE 
--    ENABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT;

SELECT EMP_NO, NAME, DEPT_NAME
  FROM EMPLOYEE INNER JOIN DEPARTMENT   -- * EMPLOYEE와 DEPARTMENT를 조인(합침)
    ON DEPART = DEPT_NO;                -- * JOIN 조건(연결)
    



-- [19] JOIN
/*
    1. JOIN
    => 두개 이상의 테이블을 조회하는 방법
    => 조회할 테이블들은 관계를 가지고 줄 수 있어야함
    => 종류 
        1) 크로스 조인(CROSS JOIN) : 카테젼 곱, 각 테이블의 모든 행을 조인
            * 행: 각 테이블의 행의 곱, 열은 A와 B의 열(합)
            => JOIN 조건이 없으면 됨 *
            => 많은 행을 순식간에 만들 수 있음(기초데이터 작성용)
            => 조인 조건을 잘 못 지정한 경우
            
        2) 내부 조인(INNER JOIN) : 
            => 각 테이블에서 일치하는 모든 행을 조인
        
        3) 외부 조인(OUTER JOIN) : 
            => 한 테이블은 일치하는 행을 조인, 한 테이블 일치하지 않아도 조인
        
        4) 셀프 조인
            => 한 테이블에 참조관계가 있는 경우 
            => 한 테이블에 특정 칼럼과 다른 특정 칼럼을 조인
    
    5. 형식
        1) JOIN 문법
            SELECT 칼럼
              FROM 테이블1 JOIN 테이블2
                ON 조인조건
        2) 콤마(,) 문법
            SELECT 칼럽
              FROM 테이블1, 테이블2
             WHERE 조인조건 

*/

    /*
        * 드라이브 테이블과 드리븐 테이블
         1. 드라이브 테이블 
            => 조인에서 검색할 때 사용하는 테이블
            => 관계에서 PK를 가진 테이블
            => 대체로 행 개수가 적은 테이블
            
            
         2. 드리븐 테이블   : 관계에서 PK를 가진 테이블
            => 관계에서 FK를 가진 테이블
            => 대부분 행의 개수가 많은 테이블
         3. 조인할 때 1) 드라이블 테이블, 2) 드리븐 테이블 순으로 작성 **   
         
         * 조인작성시 행이 작은 테이블을 JOIN 선언과 JOIN조건에서 앞에 작성하기
    
    */






-- 1. 크로스 조인 
--  => JOIN 조건이 없으면 크로스 조인(안중요)
SELECT E.EMP_NO, E.NAME, E.SALARY, D.DEPT_NO, D.DEPT_NAME
  FROM DEPARTMENT D  CROSS JOIN EMPLOYEE E;              -- * 부서수만큼 나머지 데이터가 반복해서 채워짐
                                                        -- * E, D 별명
  
-- 2. 내부조인
--  => 각 테이블에서 일치하는 값인 모든 행을 조인 (1,1,2,2,5)  (1,2,3,4) -> 1,2 
--  => ex)사원번호, 사원명, 부서명 조회하기
--  => 양쪽에 다 존재하는 데이터만 반영됨
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME
  FROM DEPARTMENT D INNER JOIN EMPLOYEE E
    ON D.DEPT_NO = E.DEPART;                -- * 부서간 번호가 일치하면 INNTER JOIN

-- 3. 외부조인
--  1) 왼쪽 외부조인(LEFT OUTER JOIN) 
--           사원    -     부서
--       모두포함  일치하는 부서만 포함
--  2) 오른쪽 외부조인(RIGHT OUTER JOIN)
--           사원    -     부서
--    일치하는 부서만 포함  모두포함

-- 모두 포함시킬 사원테이블을 OUTER JOIN의 왼쪽/ 오른쪽에 두느냐에 따라 

    -- DRIVE/DRIVEN 테이블이 잘못 지정된 조인
    -- 1) 느림
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME 
  FROM EMPLOYEE E LEFT OUTER JOIN DEPARTMENT D  -- 왼쪽의 EMPLOYEE 테이블은 모두 조회(일치하는 번호가 없어도 조회)
    ON E.DEPART = D.DEPT_NO; 
    
        -- DRIVE/DRIVEN 테이블이 잘지정된 조인    : DEPART 테이블의 행이 더 작기 때문
    -- 2) 이게 더 빠름
SELECT E.EMP_NO, E.NAME, D.DEPT_NAME 
  FROM DEPARTMENT D RIGHT OUTER JOIN EMPLOYEE E  -- 오른쪽의 EMPLOYEE 테이블은 모두 조회
    ON D.DEPT_NO = E.DEPART; 
    

    

    
    
SELECT * FROM DEPARTMENT;
SELECT * FROM EMPLOYEE;


--==============================================================================


-- 연습문제

/* INNER JOIN */

-- 1. 부서위치(LOCATION_ID)가 1700인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
--) DEPARTMENTS 테이블 : LOCATION_ID, DEPARTMENT_NAME
--) EMPLOYEES 테이블 : EMPLOYEE_ID, LAST_NAME

--1) 조인을 쓰는 문법

SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME    -- (두개 이상의 테이블에서 조회할 때 JOIN을 사용)
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.EMPLOYEE_ID
 WHERE LOCATION_ID = 1700;                                -- 조인조건이 없으면 크로스 조인 발생

-- 앞에는 PK를 가진 드라이브 테이블, 뒤에는 FK를 가진 드리븐 테이블(성능에 유리)

--2) 조인을 안쓰는 문법
--(1) INNER JOIN 대신 , (2)JOIN 조건을 WHERE절로 

SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME    -- (두개 이상의 테이블에서 조회할 때 JOIN을 사용)
  FROM DEPARTMENTS D , EMPLOYEES E
 WHERE D.DEPARTMENT_ID = E.EMPLOYEE_ID
   AND LOCATION_ID = 1700; 
   
   
-- * 둘의 차이는 없으니 편한걸 선택해 공부할것   


--------------------------------------------------------------------------------

-- 2. 부서명이 'Executive'인 부서에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
-- 1) DEAPRTMENTS 테이블 : EMPLOYEE_ID, LAST_NAME, 부서명
-- 2) EMPLOYEES 테이블 : DEPARTMENT_NAME

SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 WHERE D.DEPARTMENT_NAME = 'Executive'; 
 
SELECT  E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME
  FROM DEPARTMENTS D, EMPLOYEES E
 WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID
   AND D.DEPARTMENT_NAME = 'Executive'; 
 

--------------------------------------------------------------------------------


-- 3. 직업아이디(JOB_ID)가 변하지 않은 사원들의 EMPLOYEE_ID, LAST_NAME, JOB_ID를 조회하시오.  -- * 두 테이블의 일치값 조회(INNER JOIN)
--    현재 JOB_ID(EMPLOYEES)와 과거 JOB_ID(JOB_HISTORY)가 일치하는 사원을 조회하시오.         

SELECT E.EMPLOYEE_ID, E.LAST_NAME, JH.JOB_ID
  FROM JOB_HISTORY JH INNER JOIN EMPLOYEES E
    ON JH.JOB_ID = E.JOB_ID;

SELECT E.EMPLOYEE_ID, E.LAST_NAME, JH.JOB_ID
  FROM JOB_HISTORY JH, EMPLOYEES E
 WHERE JH.JOB_ID = E.JOB_ID;



--------------------------------------------------------------------------------
 

-- 4. 부서별로 사원수와 평균연봉을 DEPARTMENT_NAME과 함께 조회하시오.               -- * 집계함수를 조회하니 그룹핑
--    평균연봉은 정수로 절사하고, 사원수의 오름차순 정렬하시오.                     -- * TRUNC 정수 절사


SELECT D.DEPARTMENT_NAME AS 부서명, COUNT(*) AS 사원수, TRUNC(AVG(E.SALARY)) AS 평균연봉
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID             
 GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME                                     -- * 조인에 그룹핑 사용시 PK값도 같이 써주기---?
 
 ORDER BY 사원수;  

SELECT * FROM DEPARTMENTS;

--------------------------------------------------------------------------------


-- 5. CITY가 'S'로 시작하는 지역에 근무하는 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY를 조회하시오.
--    EMPLOYEE_ID의 오름차순 정렬로 조회하시오.
-- DEPARTMENTS 테이블 : DEPARTMENT_NAME
-- EMPLOYEES 테이블 : EMPLOYEE_ID, LAST_NAME
-- LOCATION 테이블 : CITY

--1) 조인을 쓰는 방법
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY
  FROM DEPARTMENTS D INNER JOIN EMPLOYEES E                      -- 1) DEPARTMENTS와 EMPLOYEES의 조인
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID INNER JOIN LOCATIONS L
    ON L.LOCATION_ID = D.LOCATION_ID                            -- 2) CITY 테이블을 추가 조인
 WHERE L.CITY LIKE 'S%';
 
-- * 3단조인: 테이블 세 개를 조인할 때, 2개씩 하나하나 조인해야함
-- PK를 가진 테이블이 먼저니 1) DEAPRTMENTS 2) LOCATIONS 테이블이 우선 나와야한다

--2) 조인을 쓰는 방법
SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY
  FROM DEPARTMENTS D, EMPLOYEES E, LOCATIONS L                      -- 1) DEPARTMENTS와 EMPLOYEES의 조인
 WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID 
   AND L.LOCATION_ID = D.LOCATION_ID                            -- 2) CITY 테이블을 추가 조인
   AND L.CITY LIKE 'S%';



--------------------------------------------------------------------------------


-- 6. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME, CITY, ROWRY_NAME을 조회하시오.
--    단, DEPARTMENT_ID가 없는 사원은 조회하지 마시오.

SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY, C.ROWNUMRY_NAME
 FROM COUNTRIES C INNER JOIN LOCATIONS L
   ON C.COUNTRY_ID = L.COUNTRY_ID INNER JOIN DEPARTMENTS D
   ON L.LOCATION_ID = D.LOCATION_ID INNER JOIN EMPLOYEES E
   ON D.DEPARTMENT_ID = E.DEPARTMENT_ID;                -- 부서테이블엔 NULL없음 , 사원 테이블엔 NULL있음
--   WHERE D.DEPARTMENT_ID IS NOT NULL;                 => INNERJOIN이기 때문에 애초에 같은 값만 조건으로서 기능(NULL 제외됨)
      
   -- * 테이블 관계를 앞에서 하든 뒤에서 하든 어느 방향이든 상관없음

SELECT E.EMPLOYEE_ID, E.LAST_NAME, D.DEPARTMENT_NAME, L.CITY, C.COUNTRY_NAME
  FROM COUNTRIES C,LOCATIONS L, DEPARTMENTS D, EMPLOYEES E
 WHERE C.COUNTRY_ID = L.COUNTRY_ID 
   AND L.LOCATION_ID = D.LOCATION_ID 
   AND D.DEPARTMENT_ID = E.DEPARTMENT_ID;     


SELECT * FROM EMPLOYEES;
SELECT * FROM DEPARTMENTS;
--==============================================================================


/* OUTER JOIN */

-- * INNER JOIN은 해당 키값이 일치하는 값만 조인
-- * OUTER JOIN은 해당 키값이 서로 일치하지 않는 경우에도 조인
-- (테이블 한쪽의 정보를 서로 키값이 일치하든 말든 한쪽 데이터를 무조건 조인한다)

--      부서(P)          -      사원(F)
-- 일치하는정보포함        모든정보 포함
-- 오른쪽에 있는 사원 테이블의 모든 정보 포함을 위해 오른쪽 외부조인 실시


-- 7. 모든 사원들의 EMPLOYEE_ID, LAST_NAME, DEPARTMENT_NAME을 조회하시오.
--    부서번호(DEPARTMENT_ID)가 없는 사원도 조회하고, EMPLOYEE_ID순으로 오름차순 정렬하시오.
--    부서번호(DEPARTMENT_ID)가 없는 사원의 부서명(DEPARTMENT_NAME)은 'None'으로 조회하시오.

SELECT E.EMPLOYEE_ID, E.LAST_NAME, NVL(D.DEPARTMENT_NAME, 'None')
  FROM DEPARTMENTS D RIGHT OUTER JOIN EMPLOYEES E
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID
 ORDER BY E.EMPLOYEE_ID;   
    -- * 오른쪽(EMPLOYEE DEPARTMENT_ID 행수)를 기준으로 조회 107 
    -- * 테이블 이름 명시 : 동일한 이름의 칼럼을 병행해 사용할 때
    
SELECT E.EMPLOYEE_ID, E.LAST_NAME, NVL(D.DEPARTMENT_NAME, 'None')
  FROM DEPARTMENTS D, EMPLOYEES E
 WHERE D.DEPARTMENT_ID(+) = E.DEPARTMENT_ID         -- RIGHT면 왼쪽에 (+)를 넣어줘야함(OUTER JOIN)
 ORDER BY E.EMPLOYEE_ID;   


-- 8. 부서별 근무하는 사원수를 조회하시오.
--    단, 근무하는 사원이 없으면 0으로 조회하시오.

SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME, COUNT(E.DEPARTMENT_ID) AS 사원수
  FROM DEPARTMENTS D LEFT OUTER JOIN EMPLOYEES E                    -- 빈부서도 조회하기 위해 DEPARTMENT 기준 조인
    ON D.DEPARTMENT_ID = E.DEPARTMENT_ID 
 GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME
 ORDER BY D.DEPARTMENT_ID;      


SELECT D.DEPARTMENT_ID, D.DEPARTMENT_NAME, COUNT(E.DEPARTMENT_ID) AS 사원수
  FROM DEPARTMENTS D, EMPLOYEES E                    -- 빈부서도 조회하기 위해 DEPARTMENT 기준 조인
 WHERE D.DEPARTMENT_ID = E.DEPARTMENT_ID(+) 
 GROUP BY D.DEPARTMENT_ID, D.DEPARTMENT_NAME
 ORDER BY D.DEPARTMENT_ID;
 



/* SELF JOIN */
-- => 한 테이블 내의 칼럼들을 내에서 조인이 이루어짐, 한 테이블을 두개의 테이블이 있는것처럼 작성



-- 9. MANAGER보다 먼저 입사한 사원들의 EMPLOYEE_ID, LAST_NAME, HIRE_DATE과 MANAGER의 HIRE_DATE를 조회하시오.
--    사원의 HIRE_DATE가 MANAGER의 HIRE_DATE보다 작은 사원을 조회하시오. (MANAGER보다 먼저 입사한 사원)
-- => 모든 사원 정보 : EMPLOYEE E
-- => MANAGER의 정보 : EMPLOYEE M

-- 관계
-- PK EMPLOYEE_ID
-- FK MANAGER_ID

-- 조인조건
-- 사원들의 매니저 번호 = 매니저의 사원번호
-- E.MANAGER_ID = M.EMPLOYEE_ID

SELECT E.EMPLOYEE_ID, E.LAST_NAME, E.HIRE_DATE, M.LAST_NAME, M.HIRE_DATE
  FROM EMPLOYEES E INNER JOIN EMPLOYEES M
    ON E.MANAGER_ID = M.EMPLOYEE_ID
 WHERE TO_DATE(E.HIRE_DATE) < TO_DATE(M.HIRE_DATE);    -- TO DATE를 쓰면 날짜타입이라도 더 안전히 비교가능(날짜타입은 숫자비교가능)
 


-- 10. 같은 부서의 사원들 중에서 나보다 늦게 입사하였으나 연봉을 더 많이 받는 사원이 있는 사원들의
--     DEPARTMENT_ID, LAST_NAME, SALARY, HIRE_DATE와 높은 연봉을 받는 사원의 LAST_NAME, SALARY, HIRE_DATE를 조회하시오.

SELECT 나.DEPARTMENT_ID, 나.LAST_NAME, 나.SALARY, 나.HIRE_DATE, 너.LAST_NAME, 너.SALARY, 너.HIRE_DATE
  FROM EMPLOYEES 나 INNER JOIN EMPLOYEES 너
    ON 나.DEPARTMENT_ID = 너.DEPARTMENT_ID
 WHERE 나.HIRE_DATE < 너.HIRE_DATE            -- 날짜가 크다-> 숫자비교(최근이다)**
   AND 나.SALARY < 너.SALARY;










