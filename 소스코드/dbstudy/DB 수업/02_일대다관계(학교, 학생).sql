-- & 학교 테이블과 학생테이블 1대 다 관계 형성하기
-- 1. 학교 테이블(부모)

-- 1) 제약조건에 이름을 주지않는 방법
DROP TABLE SCHOOL;                  -- * 자바와 달리 오류가 나면 건너뛰고 다음코드를 실행함
CREATE TABLE SCHOOL(                                        
    SCH_CODE NUMBER(1) NOT NULL PRIMARY KEY,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL
);

-- 2) 제약조건을 몰아서 작성하는 방법
DROP TABLE SCHOOL;                 
CREATE TABLE SCHOOL(                                        
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL,
    PRIMARY KEY(SCH_CODE)            -- 제약조건(칼럼명)
);

-- 3) 제약조건에 이름 부여하며 테이블 생성  * 대체로 PK,FK에 이름이 들어감
DROP TABLE SCHOOL;                 
CREATE TABLE SCHOOL(                                        
    SCH_CODE NUMBER(1) NOT NULL CONSTRAINT PK_SCHOOL PRIMARY KEY,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL
);

-- 4) 2)번에 제약조건 이름 부여하며 생성 * 테이블 생성 최종버전 ***********************************
DROP TABLE SCHOOL;                 
CREATE TABLE SCHOOL(                                        
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL,
    CONSTRAINT PK_SCH PRIMARY KEY(SCH_CODE)            -- 제약조건(칼럼명)
);


--==========================================================================

--2. 학생테이블(자식)

-- * PK, FK 간에는 타입이 동일해야한다(되도록 이름도 동일하게)
-- * 자식테이블 : FK를 가진 테이블을 자식테이블이라 한다
-- 1) 
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL PRIMARY KEY,
    SCH_CODE NUMBER(1) NOT NULL REFERENCES SCHOOL(SCH_CODE),    -- 부모테이블 참조코드(REFERENCES 부모테이블명(부모칼럼)
    STU_NAME VARCHAR2(20 BYTE) NULL 
);

-- 2) 제약사항 별도로
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL,
    STU_NAME VARCHAR2(20 BYTE) NULL, 
    PRIMARY KEY(STU_NO),
    FOREIGN KEY(SCH_CODE) REFERENCES SCHOOL(SCH_CODE)
);

-- 3) 제약조건 밑에 정리 + 이름붙이기 * 테이블 생성 최종버전 ***********************************
DROP TABLE STUDENT;
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL,
    STU_NAME VARCHAR2(20 BYTE) NULL, 
    CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO),
    CONSTRAINT FK_STUDENT_SCHOOL FOREIGN KEY(SCH_CODE) REFERENCES SCHOOL(SCH_CODE)
); 

/*
* 1대 다 관계를 생성할 때 고려해야하는 사항
1. 명칭
    1) 부모테이블 : 일(1), 중복이 없는 PK를 가진 테이블
    2) 자식테이블 : 다(M), 중복이 있는 FK를 가진 테이블
2. 생성
    => 부모테이블을 먼저 생성하고, 자식테이블을 나중에 생성
3. 삭제
    => 자식 테이블을 먼저 삭제하고, 부모 테이블을 나중에 삭제 
    * 참조하고있는 부모 테이블이 사라지면, 자식테이블이 오류가 발생

*/

-- 최종정리(테이블 생성도 한가지 모습으로 정리)
-- 생성 : 부모 먼저, 자식 나중
-- 삭제 : 자식 먼저 부모 나중
DROP TABLE STUDENT;
DROP TABLE SCHOOL;
CREATE TABLE SCHOOL(
    SCH_CODE NUMBER(1) NOT NULL,
    SCH_NAME VARCHAR2(20 BYTE) NOT NULL,
    CONSTRAINT PK_SCHOOL PRIMARY KEY(SCH_CODE)
);
CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE) NOT NULL,
    SCH_CODE NUMBER(1) NOT NULL,
    STU_NAME VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_STUDENT PRIMARY KEY(STU_NO),
    CONSTRAINT FK_STUDENT_SCHOOL FOREIGN KEY(SCH_CODE) REFERENCES SCHOOL(SCH_CODE)
);
    
    
    
    