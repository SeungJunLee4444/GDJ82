DROP TABLE APLI;
DROP TABLE SUBJECT;
DROP TABLE STUDENT;

-- * CHAR과 VARCHAR2의 차이
-- 1) CHAR      : 생성시 문자열의 길이를 할당하는 타입으로, 지정한 용량보다 문자의 수가 적어도 정해진 용량만큼 차지
-- 2) VARCHAR2  : 생성후 문자열의 길이에 따라 바이트값이 변하는 타입
-- => 바이트값에 따라 용량을 산정하는 VARCHAR2는 작동시 성능이 저하될 수 있다는 단점이 있다
-- => 지정한 길이의 변동이 심한 데이터(ex 주소)는 varchar2타입, 학번, 이름 등 길이가 고정된 타입은 char을 쓰기도 하나
-- => 대체로 varchar2를 사용한다

-- CHAR(5) 'SQL' != VARCHAR2(3) 'SQL' : CHAR은 SQL 뒤에 NULL이 두개 있기때문 

-- * NULL : 없어도 되는 값은 NULL, 값이 없으면 안되는 경우(EX 기본키)는 NOT NULL이 기본

/*
* 제약조건을 무시하고 테이블 삭제하는 방법(순서 상관없이 삭제가능)
DROP TABLE STUDENT CASCADE CONSTRAINTS;
DROP TABLE SUBJECT CASCADE CONSTRAINTS;
DROP TABLE APLI CASCADE CONSTRAINTS;

*/


/*
    수강신청
    1. 여러 학생이 여러 과목을 신청하는 다대다관계
    2. 학생-과목의 직접적인 다대다관계 설정은 불가능
    3. 학생-수강신청-과목으로 일대다관계 2개 설정으로 진행
*/

CREATE TABLE STUDENT(
    STU_NO CHAR(5 BYTE)         NOT NULL,           -- * 칼럼명은 축약해도 상관없다
    STU_NAME VARCHAR2(15 BYTE)  NULL,
    STU_AGE NUMBER(3)           NULL,
    CONSTRAINT PK_STU_NO PRIMARY KEY(STU_NO),
    CONSTRAINT CK_STU_AGE CHECK(STU_AGE BETWEEN 0 AND 100)
);

CREATE TABLE SUBJECT(
    SUB_CODE CHAR(1 BYTE)              NOT NULL,
    SUB_NAME VARCHAR2(10 BYTE)         NULL,
    SUB_TEACHER_NAME VARCHAR2(20 BYTE) NULL,
    CONSTRAINT PK_SUB_CODE PRIMARY KEY(SUB_CODE)
);

CREATE TABLE APLI(
    APLI_NO NUMBER(1) NOT NULL,
    STU_NO CHAR(5 BYTE) NOT NULL,
    SUB_CODE CHAR(1 BYTE) NOT NULL,
    CONSTRAINT PK_APLI_NO PRIMARY KEY(APLI_NO),
    CONSTRAINT FK_APLI_NO FOREIGN KEY(STU_NO) REFERENCES STUDENT(STU_NO),
    CONSTRAINT FK_SUB_CODE FOREIGN KEY(SUB_CODE) REFERENCES SUBJECT(SUB_CODE)
);


