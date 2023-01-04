/*
 모델링

    1. [보기] - [Data Modeler] - [브라우저]
    2. [브라우저] 창 - [제목 없음_1] - [관계형 모델 []] - 우클릭 - [새 관계형 모델]
    3. 상단 메뉴 [새 테이블] 클릭 후 [Relational_1] 창에 그리기(클릭 or 드래그)
        1) 일반 : 테이블 이름, DDL에 생성 체크
        2) 열   : 열 이름, 논리적, 타입, 옵션 체크(PK, 필수(NOT NULL))
        3) 기본키 : 기본키 변경 및 제약조건 이름 변경
        4) 고유 제약 조건 : UNIQUE
        5) 외래키
            (1) 필수 선택하면 실선으로 관계 생성, 필수 해제하면 점선으로 관계 생성
            (2) 연관된 열
                참조된 열  |  열
                PK 등록    |  FK 등록
    4. 상단 메뉴 [DDL 생성] 클릭 후 [생성] 버튼 클릭하면 자동으로 생성되는 DDL 확인 가능
*/

-- 생성자 Oracle SQL Developer Data Modeler 20.4.0.374.0801
--   위치:        2022-08-24 15:58:29 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE orders (
    ord_no    NUMBER NOT NULL,
    ord_user  VARCHAR2(20 BYTE) NOT NULL,
    prod_no   NUMBER(3) NOT NULL,
    ord_date  DATE
);

ALTER TABLE orders ADD CONSTRAINT pk_orders PRIMARY KEY ( ord_no );

CREATE TABLE product (
    prod_no    NUMBER(3) NOT NULL,
    prod_name  VARCHAR2(20 BYTE) NOT NULL,
    price      NUMBER(6) NOT NULL,
    stock      NUMBER
);

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( prod_no );

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_product FOREIGN KEY ( prod_no )
        REFERENCES product ( prod_no );



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             2
-- CREATE INDEX                             0
-- ALTER TABLE                              3
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
