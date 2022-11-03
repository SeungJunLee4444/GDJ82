/* 테이블 관계
    1) 실선이면 NOT NULL, 점선이면 NULL
    2) O가 들어가면 PK가 사라졌을때, FK값이 NULL로 변경     ON DELETE SETNULL
       X가 들어가면 PK가 사라졌을 때, FK값이 DELETE됨      ON DELETE CASCADE
    3) PK는 개체무결성으로 인해 NOTNULL, UNIQUE하지만 FK는 NULL도 가능하고 중복될수도있다

*/
/* ???????????????????
-- * 계정, 게시판 예시
-- > 만약 계정을 삭제하는데(PK), 게시판의 내용(일반칼럼)때문에 삭제되지 않는다면 게시판글을 전부 지워야 할 것이다
-- > PK가 삭제될 때 FK도 같이 삭제되는 식으로 쿼리문을 작성해야한다
-- ex) ON DELETE SET NULL, CASCADE;

*/

-- # 은행, 고객 테이블

-- 삭제
DROP TABLE CUSTOMER CASCADE CONSTRAINTS;
DROP TABLE BANK CASCADE CONSTRAINTS;

-- 생성
CREATE TABLE BANK(
    BANK_CODE VARCHAR2(20 BYTE) NOT NULL,
    BANK_NAME VARCHAR2(30 BYTE) NOT NULL
);

CREATE TABLE CUSTOMER(
    NO NUMBER               NOT NULL,       -- * NULL은 생략가능
    NAME VARCHAR2(30 BYTE)  NOT NULL,
    PHONE VARCHAR2(30 BYTE) UNIQUE,
    AGE NUMBER CHECK(AGE BETWEEN 0 AND 100),
    BANK_CODE VARCHAR2(20 BYTE)
);

-- * NULL은 생략가능
-- * 제약사항 중, UNIQUE와 CHECK는 바로쓰는것이 편리하나 문장이 길어지면 따로쓸것
-- * F5키 누르면 전체실행


-- 기본키 추가
ALTER TABLE BANK
    ADD CONSTRAINT PK_BANK PRIMARY KEY(BANK_CODE);

ALTER TABLE CUSTOMER
    ADD CONSTRAINT PK_CUSTOMER PRIMARY KEY(NO);
    
-- 외래키 추가(기본키 쿼리문에 참조하는 테이블과 칼럼이 추가된다) *
ALTER TABLE CUSTOMER
    ADD CONSTRAINT FK_CUSTOMER_BANK FOREIGN KEY(BANK_CODE) 
        REFERENCES BANK(BANK_CODE);
--            ON DELETE CASCADE;
            
            
-- 테이블 변경하기(ALTER TABLE)
-- 1) 칼럼추가 : ALTER TABLE 테이블명 ADD 칼럼명 데이터타입 (제약조건);
-- 2) 칼럼수정 : ALTER TABLE 테이블명 MODIFY 칼럼명 데이터타입 (제약조건);
    
    -- * 삭제와 이름변경은 COLUMN을 적어줘야한다
-- 3) 칼럼삭제 ALTER TABLE 테이블명 DROP 'COLUMN' 칼럼명 (이후생략);
-- 4) 칼럼 이름 변경: ALTER TABLE 테이블명 RENAME COLUMN OLD이름 TO NEW이름
-- 5) 테이블명 변경 ALTER TABLE 테이블명(OLD이름) RENAME TABLE NEW이름;

-- * CHECK(GRADE IN('VIP', 'GOLD', 'SILVER')); 셋중 하나 제약조건

-- 1. BANK 테이블에 BANK_PHONE 칼럼을 추가하시오.

ALTER TABLE BANK 
    ADD BANK_PHONE VARCHAR2(20 BYTE) NULL;

-- 2. CUSTOMER 테이블에 GRADE 칼럼을 추가하시오. ('VIP', 'GOLD', 'SILVER' 중 하나의 값만 가진다.)
                                             
ALTER TABLE CUSTOMER 
    ADD GRADE VARCHAR2(6 BYTE) CHECK(GRADE IN('VIP', 'GOLD', 'SILVER'));         
    
--    * DB에서 텍스트는 ''
--    * CHECK(칼럼명 IN('텍스트')) : 텍스트 중에서 하나 선택 **

-- 3. BANK 테이블의 BANK_NAME 칼럼을 VARCHAR2(15 BYTE)로 수정하시오.
ALTER TABLE BANK
    MODIFY BANK_NAME VARCHAR2(15 BYTE); -- NULL 여부를 안쓰면 기존의 NULL여부 상속


-- 4. BANK 테이블의 BANK_NAME 칼럼을 NOT NULL로 수정하시오.
ALTER TABLE BANK
    MODIFY BANK_NAME VARCHAR2(15 BYTE) NOT NULL;


-- 5. CUSTOMER 테이블의 AGE 칼럼을 삭제하시오.

ALTER TABLE CUSTOMER
    DROP COLUMN AGE;

-- 6. CUSTOMER 테이블의 NO 칼럼과 NAME 칼럼이름을 CUST_NO와 CUST_NAME으로 수정하시오.
ALTER TABLE CUSTOMER
    RENAME COLUMN NO TO CUST_NO;
ALTER TABLE CUSTOMER
    RENAME COLUMN NAME TO CUST_NAME;


-- 7. BANK 테이블의 이름을 BANK_TBL로 수정하시오.
ALTER TABLE BANK RENAME TO BANK_TBL;


    
    
    
    
                
        
        
        
        
        
        