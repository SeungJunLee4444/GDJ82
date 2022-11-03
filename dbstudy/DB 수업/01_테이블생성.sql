/*
1. 테이블 데이터타입
    1) char         : 용량이 고정된 문자타입(1~2000바이트)
    2) varchar2     : 용량이 가변되는 문자타입(1~4000바이트)
    * 대체로 char보다는 varchar2를 사용
     * varchar2이 유용해 보이지만 바이트 타입이 고정되지 않아 처리에 시간이 지연될 수 있다
    => 글자의 개수가 고정된 경우라면 char이 유리
    3) number(p,s)  : 숫자타입 (전체 글자수, 소수점 자리수)
     * number(3) : 3자리 정수, number(5, 2) : 최대 5자리, 소수점 2자리 수, number 최대 38자리 숫자(22바이트 할당), number(0) : 0은 불가능
    4) date         : 날짜타입
    

2. 테이블 제약조건
    => 테이블 생성시 함께 지정하거나, 테이블 생성후 테이블 수정하면서 지정(ALTER)
    
3. 테이블 조회 *

    * 데이터 사전의 종류
    1) DBA_     : 데이터 베이스 전체 모든 객체에 대한 정보확인
    2) USER_    : 자신이 생성한 모든 객체
    3) ALL_     : 자신 + 다른 사용자가 생성한 객체
    
    * 사용자 목록확인
    1) DBA_USERS    : 데이터베이스 전체 사용자 정보
    2) USER_USERS   : 자신이 생성한 사용자 정보
    3) ALL_USERS    : 자신 + 다른 사용자가 생성한 사용자 정보
    
    * 제약조건을 가진 데이터사전
    1) DBA_CONSTRAINTS
    2) USER CONSTRAINTS : 현재 접속한 사용자가 만든 제약조건 확인가능
    3) ALL_CONSTRAINTS

*/

-- USER_CONSTRAINTS 테이블의 구조를 확인
DESCRIBE SYS.USER_CONSTRAINTS
-- (SYS는 생략가능)



--USER_CONSTRAINTS 테이블의 CONSTRAINTS_NAME 칼럼 확인하기 : 조회문실습
SELECT CONSTRAINT_NAME FROM USER_CONSTRAINTS;



-- 1. 제약조건 이름 없이 테이블 만들기
DROP TABLE USER_TBL;    --TABLE 삭제 * 테이블은 한번 돌리면 수정이 불가하나, DROP까지 블록잡고 돌리면 수정할 수 있다
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL PRIMARY KEY,
    USER_PW VARCHAR2(30 BYTE) NOT NULL,
    USER_NAME VARCHAR2(30 BYTE) NULL,                --NULL : 빈칸이여도 문제없다
    USER_AGE NUMBER(3) NULL CHECK(USER_AGE BETWEEN 0 AND 100),    --NUMBER : 최대 세자리수(0과 100사이 CHECK 0, 100포함)
    USER_ADDR VARCHAR2(30 BYTE) NULL,                             --
    USER_TEL CHAR(13 BYTE) NULL UNIQUE,              -- -(하이푼 포함 13바이트)
    USER_GEN CHAR(1 BYTE) NULL                       -- UNIQUE : 중복허가X
); 
--> 두번 실행하면 실패 : 이미 있어서

-- 2. 제약조건 이름없이 테이블 만들기 2번쨰 (NULL을 제외한 나머지 제약조건을 아래로 빼기)

DROP TABLE USER_TBL;   
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL,
    USER_PW VARCHAR2(30 BYTE) NOT NULL,
    USER_NAME VARCHAR2(30 BYTE) NULL,             
    USER_AGE NUMBER(3) NULL,
    USER_ADDR VARCHAR2(30 BYTE) NULL,               
    USER_TEL CHAR(13 BYTE) NULL,            
    USER_GEN CHAR(1 BYTE) NULL,                     
    PRIMARY KEY(USER_ID),
    CHECK(USER_AGE BETWEEN 0 AND 100),
    UNIQUE(USER_TEL)
); 

-- 3. 제약조건이름 지정하며 테이블만들기
DROP TABLE USER_TBL;    
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL CONSTRAINT PK_USER_TBL PRIMARY KEY,
    USER_PW VARCHAR2(30 BYTE) NOT NULL,
    USER_NAME VARCHAR2(30 BYTE) NULL,               
    USER_AGE NUMBER(3) NULL CONSTRAINT CK_USER_AGE CHECK(USER_AGE BETWEEN 0 AND 100),   
    USER_ADDR VARCHAR2(30 BYTE) NULL,                            
    USER_TEL CHAR(13 BYTE) NULL CONSTRAINT UQ_USER_TEL UNIQUE,             
    USER_GEN CHAR(1 BYTE) NULL                      
); 

-- 4. 제약조건이름 지정하며 테이블 만들기 2번쨰 **
DROP TABLE USER_TBL;   
CREATE TABLE USER_TBL(
    USER_ID VARCHAR2(30 BYTE) NOT NULL, -- 칼럼들
    USER_PW VARCHAR2(30 BYTE) NOT NULL,
    USER_NAME VARCHAR2(30 BYTE) NULL,             
    USER_AGE NUMBER(3) NULL,
    USER_ADDR VARCHAR2(30 BYTE) NULL,               
    USER_TEL CHAR(13 BYTE) NULL,            
    USER_GEN CHAR(1 BYTE) NULL,                     
    CONSTRAINT PK_USER_TBL PRIMARY KEY(USER_ID),                -- 제약조건이 기본키(기본키)
    CONSTRAINT CK_USER_AGE CHECK(USER_AGE BETWEEN 0 AND 100),   -- 제약조건이 CHECK(칼럼)
    CONSTRAINT UQ_USER_TEL UNIQUE(USER_TEL)                     -- 제약조건이 중복X(칼럼)
    
); 


