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

-- 해설
-- 제품명(PROD_NAME)에 따라 제품카테고리(PROD_CATEGORY), 제품가격(PROD_PRICE)이 정해지는 종속 관계가 있음
-- 종속 관계의 칼럼들을 다른 칼럼들과 함께 저장하면 데이터의 일관성에 문제가 발생할 수 있음
-- 종속 관계의 칼럼들은 별도의 테이블로 분리해야 함
-- 별도로 분리한 테이블에는 기본키를 추가해야 함

-- 1) 제품 테이블 생성
DROP TABLE PRODUCTS;
CREATE TABLE PRODUCTS (
    PROD_CODE NUMBER NOT NULL,
    PROD_NAME VARCHAR2(20 BYTE),
    PROD_CATEGORY VARCHAR2(20 BYTE),
    PROD_PRICE NUMBER
);
-- 2) 제품 테이블 기본키
ALTER TABLE PRODUCTS
    ADD CONSTRAINT PK_PRODUCTS PRIMARY KEY(PROD_CODE);

-- 3) 구매 테이블 생성
DROP TABLE BUYS;
CREATE TABLE BUYS (
    BUY_NO NUMBER NOT NULL,
    USER_ID VARCHAR2(20 BYTE),
    PROD_CODE NUMBER,
    BUY_AMOUNT NUMBER
);
-- 4) 구매 테이블 기본키
ALTER TABLE BUYS
    ADD CONSTRAINT PK_BUYS PRIMARY KEY(BUY_NO);
-- 5) 구매-사용자 외래키
ALTER TABLE BUYS
    ADD CONSTRAINT FK_BUYS_USERS FOREIGN KEY(USER_ID)
        REFERENCES USERS(USER_ID);
-- 6) 구매-제품 외래키
ALTER TABLE BUYS
    ADD CONSTRAINT FK_BUYS_PRODUCTS FOREIGN KEY(PROD_CODE)
        REFERENCES PRODUCTS(PROD_CODE);