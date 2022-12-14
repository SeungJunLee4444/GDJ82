DROP SEQUENCE CONTACT_SEQ;
CREATE SEQUENCE CONTACT_SEQ NOCACHE;

DROP TABLE CONTACT;
CREATE TABLE CONTACT(
    NO    NUMBER             NOT NULL,
    NAME  VARCHAR2(100 BYTE) NOT NULL,
    TEL   VARCHAR2(100 BYTE),
    ADDR  VARCHAR2(100 BYTE) NOT NULL,
    EMAIL VARCHAR2(100 BYTE),
    NOTE  VARCHAR2(100 BYTE),
    CONSTRAINT PK_CONTACT PRIMARY KEY(NO)
);

INSERT INTO CONTACT VALUES(CONTACT_SEQ.NEXTVAL, '관리자', '1', '가', '@', '메모');