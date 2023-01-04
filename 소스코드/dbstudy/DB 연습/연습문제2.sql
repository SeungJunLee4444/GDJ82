-- 다음 쿼리문을 이용해서 사용자 테이블과 구매 테이블을 작성하시오.

-- 테이블 삭제
DROP TABLE BUYS;
DROP TABLE USERS;

-- 사용자 테이블
CREATE TABLE USERS (
    USER_NO NUMBER NOT NULL,                    -- 사용자번호(기본키)
    USER_ID VARCHAR2(20 BYTE) NOT NULL UNIQUE,  -- 사용자아이디
    USER_NAME VARCHAR2(20 BYTE),                -- 사용자명
    USER_YEAR NUMBER(4),                        -- 태어난년도
    USER_ADDR VARCHAR2(100 BYTE),               -- 주소
    USER_MOBILE1 VARCHAR2(3 BYTE),              -- 연락처1(010, 011 등)
    USER_MOBILE2 VARCHAR2(8 BYTE),              -- 연락처2(12345678, 11111111 등)
    USER_REGDATE DATE                           -- 등록일
);
-- 사용자 테이블 기본키
ALTER TABLE USERS
    ADD CONSTRAINT PK_USERS PRIMARY KEY(USER_NO);


-- 구매 테이블
CREATE TABLE BUYS (
    BUY_NO NUMBER NOT NULL,           -- 구매번호
    USER_ID VARCHAR2(20 BYTE) ,       -- 구매자
    PROD_NAME VARCHAR2(20 BYTE),      -- 제품명
    PROD_CATEGORY VARCHAR2(20 BYTE),  -- 제품카테고리
    PROD_PRICE NUMBER,                -- 제품가격
    BUY_AMOUNT NUMBER                 -- 구매수량
);
-- 구매 테이블 기본키
ALTER TABLE BUYS
    ADD CONSTRAINT PK_BUYS PRIMARY KEY(BUY_NO);
-- 구매-사용자 외래키
ALTER TABLE BUYS
    ADD CONSTRAINT FK_BUYS_USERS FOREIGN KEY(USER_ID)
        REFERENCES USERS(USER_ID);


-- 문제.
-- BUYS 테이블의 종속 관계를 확인하고 정규화를 수행하시오.
