
DROP TABLE DEPARTMENT CASCADE CONSTRAINTS; 
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;

CREATE TABLE DEPARTMENT(
    DEPT_NO NUMBER              NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION VARCHAR2(15 BYTE)  NOT NULL
);

CREATE TABLE EMPLOYEE(
    EMP_NO NUMBER  NOT NULL,
    NAME VARCHAR2(20 BYTE)      NOT NULL,
    DEPART NUMBER,
    POSITION VARCHAR2(20 BYTE),
    GENDER CHAR(2),
    HIRE_DATE DATE,
    SALARY NUMBER
);

ALTER TABLE DEPARTMENT ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);
ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) REFERENCES DEPARTMENT(DEPT_NO) ON DELETE SET NULL;

/* 
1. 시퀀스(SEQUENCE)
    => 번호표를 생성해주는 DB 객체로, 자동으로 증가하는 번호를 생성해줌    
    => 자동으로 증가하는 번호를 생성                          * DB객체 : DB 내에 존재하는 논리적 저장구조        
    * 주로 PK, 인공키에 자주 사용됨                           ex) 테이블, 뷰, 인덱스, 시노님 등등
    (1) NEXTVAL를 이용하면 새로운 번호가 생성
    (2) CURRVAL를 이용하면 현재 번호를 확인 
    ex) INSERT INTO SAMPLE(NO1, NO2) VALUES(SAMPLE_SEQ.NEXTVAL, SAMPLE_SEQ.CURRVAL);
    => NO1, NO2 칼럼에 SAMPLE_SEQ 시퀸스의 값을 NO1에 저장, 확인된 값을 NO2에 저장 *


2. 시퀸스 생성 형식(순서는 상관없으나 그냥 아래 순서대로 작성하자)
    => CREATE SEQUENCE 시퀀스_이름
          START WITH 시작값           -> 생략하면 1, 생성 이후 유일하게 수정이 안됨 *
          INCREMENT BY 증가값         -> 생략하면 1, 
          MINVALUE 최소값             -> 기본적으로는 시작값과 동일
          MAXVALUE 최대값             -> MIN, MAX : 역순으로 100부터 1까지 값을 구하는 경우에 필요, NOMAXVALUE(최대값무한)
          CACHE사용유무               -> NO CACHE 권장 
          CYCLE사용유무               -> PK에서 사용한다면 NO CYCLE(중복불가능이기 때문), 생략하면 NO CYCLE(생략가능)
       
    => 
*/

-- 부서 테이블에서 사용할 부서 시퀀스
DROP SEQUENCE DEPARTMENT_SEQ;
CREATE SEQUENCE DEPARTMENT_SEQ
    START WITH 1
    INCREMENT BY 1  -- * BY 빼먹음
    MINVALUE 1
    MAXVALUE 100
    NOCACHE
    NOCYCLE;


-- INSERT문에서 시퀀스를 사용할 때, 사용하려는 위치에 시퀀스명을 집어넣으면 된다


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
    
    -- 변수명 두개를 제외한 나머지는 고정된 쿼리문을 사용가능해진다

COMMIT;

-- 사원 시퀀스
DROP SEQUENCE EMPLOYEE_SEQ;
CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1001
      NOCACHE;

-- > START WITH와 NOCACHE는 항상 쓸것


INSERT INTO 
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL , '구창민', 1 , '과장', 'M', '95/05/01', 5000000);    
INSERT INTO 
    EMPLOYEE 
VALUES
    (EMPLOYEE_SEQ.NEXTVAL , '김민서', 1 , '사원', 'F', '17/09/01', 2000000);
INSERT INTO
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL , '이은영', 2 ,'부장', NULL , '90-09-01', 5500000);       
INSERT INTO 
    EMPLOYEE
VALUES 
    (EMPLOYEE_SEQ.NEXTVAL , '한성일', 2, '과장', 'M', '93-04-01', 5000000);
    
INSERT INTO 
    EMPLOYEE
VALUES 
    (EMPLOYEE_SEQ.NEXTVAL , '신현준', 5, '대리사원', 'M', '98-12-01', 3500000);
    
 -- 부서번호 5번이 없어 실패
 -- INSERT에 실패했으나 시퀀스의 번호는 사용한 것
INSERT INTO 
    EMPLOYEE
VALUES
    (EMPLOYEE_SEQ.NEXTVAL , '신현준', 5, '대리', 'M', '98-12-01', 3500000);
    -- 부서번호 5번이라는 값이 없어서 중간에 해당 칸을 띄운채 표시될 수 있음 1001,1002,1003,1004,1006
    -- 시퀀스 지우고 새로만드는 방법말곤 없음
    
    -- * 5라는 부서번호는 PK를 참조하는 FK값(PK가 가지지않은 값을 쓸수없음)

    
    -- * 행에 나오는 0, 1 : 삽입메서드에 데이터가 입력되면 1, 또는 0의 형태로 반환

COMMIT;

------------------------------------------------------------------------------------------------------

-- * 2. 시퀸스 수정

DROP TABLE SAMPLE;
CREATE TABLE SAMPLE(
    NO1 NUMBER,
    NO2 NUMBER
);

DROP SEQUENCE SAMPLE_SEQ;
CREATE SEQUENCE SAMPLE_SEQ NOCACHE; --* STARTWITH는 써주는게 좋으나 생략도 가능

-- * CURRVAL을 통해 번호확인을 하려해도, 기존에 번호가 저장되있지 않기 때문에 오류발생
INSERT INTO SAMPLE(NO1) VALUES(SAMPLE_SEO.CURRVAL);  

-- NEXTVAL(번호입력), CURRVAL(번호확인)을 함께 사용
INSERT INTO SAMPLE(NO1, NO2) VALUES(SAMPLE_SEQ.NEXTVAL, SAMPLE_SEQ.CURRVAL);

COMMIT;


