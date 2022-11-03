DROP TABLE PROCEEDING CASCADE CONSTRAINTS;
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;
DROP TABLE PROJECT CASCADE CONSTRAINTS;
DROP TABLE DEPARTMENT CASCADE CONSTRAINTS;
-- * CASCADE CONSTRAINTS는 순서, 제약조건 관계 상관없이 DROP이 가능해진다

/* 참고사항
-- * 외래키 이름은 현재 테이블-참조해온 테이블 명으로 작성한다
-- * NUMBER의 크기가 지정되지 않을때는 그냥 NUMBER만 써도 된다
-- * 컬럼명을 더블클릭하면 같은 값을 지닌 칼럼을 색으로 표시해준다
*/

--부서 - 직원 일대다 관계
--프로젝트 - 직원은 다대다 관계 -> 중간에 새로운 테이블 형성

-- * TRUNBCATE TABLE 테이블이름;
-- => 테이블의 구조만을 남김(열은 남기고, 행은 없어짐)


TRUNCATE TABLE DEPARTMENT;

CREATE TABLE DEPARTMENT(
    DEPT_NO VARCHAR2(15 BYTE)       NOT NULL,
    DEPT_NAME VARCHAR2(30 BYTE)     NULL,
    DEPT_LOCATION VARCHAR2(50 BYTE) NULL
);

CREATE TABLE PROJECT(
    PJT_NO NUMBER              NOT NULL,
    PJT_NAME VARCHAR2(30 BYTE)         NULL,
    BEGIN_DATE DATE             NULL,
    END_DATE DATE    
);

CREATE TABLE EMPLOYEE(
    EMP_NO NUMBER               NOT NULL,
    DEPT_NO VARCHAR2(15 BYTE)   NOT NULL,
    POSITION CHAR(10 BYTE)      NULL,
    NAME VARCHAR2(15 BYTE)      NULL,
    HIRE_DATE DATE              NULL,
    SALARY NUMBER               NULL
);

CREATE TABLE PROCEEDING(
    PCD_NO NUMBER NOT NULL,
    EMP_NO NUMBER NOT NULL,
    PJT_NO NUMBER NOT NULL,
    PJT_STATE NUMBER NOT NULL
);

-- 1. 키 추가 * 한줄로 작성해야한다
--1) 기본키 추가 * ALTER : 수정
ALTER TABLE DEPARTMENT ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
    
ALTER TABLE PROJECT ADD CONSTRAINT PK_PROJECT PRIMARY KEY(PJT_NO);
    
ALTER TABLE EMPLOYEE
    ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);
    
ALTER TABLE PROCEEDING
    ADD CONSTRAINT PK_PROCEEDING PRIMARY KEY(PCD_NO);
    
    
-- 2) 외래키 추가
ALTER TABLE EMPLOYEE 
    ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPT_NO) 
    REFERENCES DEPARTMENT(DEPT_NO);
    
ALTER TABLE PROCEEDING
    ADD CONSTRAINT FK_PROCEEDING_EMPLOYEE FOREIGN KEY(EMP_NO)
    REFERENCES EMPLOYEE(EMP_NO);
   
ALTER TABLE PROCEEDING
    ADD CONSTRAINT FK_PROCEEDING_PROJECT FOREIGN KEY(PJT_NO)
    REFERENCES PROJECT(PJT_NO);

-- 2. 제거

-- 1) 외래키 제거
-- > DEPARTMENT 테이블의 DEPT_NO 칼럼을 참조하는 외래키 제약조건
-- > DEPARTMENT 테이블의 DEPT_NO 칼럼에 추가된 기본키 제약조건을 제거할 수 있다
ALTER TABLE EMPLOYEE
    DROP CONSTRAINT FK_EMPLOYEE_DEPARTMENT; -- * 자식 테이블(EMPLOYEE)에 있는 FK를 먼저 제거한후, 부모 테이블(DEPARTMENT)의 PK를 제거

ALTER TABLE EMPLOYEE
    DROP FOREIGN KEY;

SELECT


-- 2) 기본키 제거
--  * FK에 의해 참조되고 있는 PK는 제거할 수 없음, 먼저 FK를 제거해야함
--(자식 테이블에 꼳혀있는 FK를 제거하고, 부모테이블의 PK를 제거)
ALTER TABLE DEPARTMENT 
    DROP CONSTRAINT PK_DEPARTMENT;
    -- => 부모 테이블인 부서의 PK기 때문에, 자식 테이블인 사원
    
-- * 제약조건의 이름이 없는 경우
--ALTER TABLE DEPARTMENT
--    DROP PRIMARY KEY;   

ALTER TABLE PROCEEDING
    DROP CONSTRAINT PK_PROCEEDING;
    
-- * SCOTT이 가진 모든 테이블 없애는법
DROP USER SCOTT CASCADE;






