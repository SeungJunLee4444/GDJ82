-- 다중 첨부

DROP TABLE ATTACH;
CREATE TABLE ATTACH
(
    ATTACH_NO NUMBER NOT NULL,  		-- PK
    PATH VARCHAR2(300 BYTE),			-- 첨부된 파일의 경로
    ORIGIN VARCHAR2(300 BYTE),			-- 파일의 원래 이름
    FILESYSTEM VARCHAR2(40 BYTE),		-- 파일의 저장된 이름
    DOWNLOAD_CNT NUMBER,				-- 다운로드 횟수
    UPLOAD_NO NUMBER  					-- FK	->
);

DROP TABLE UPLOAD;
CREATE TABLE UPLOAD
(
    UPLOAD_NO NUMBER NOT NULL, 			 -- PK(FK : UPLOAD_NO)	->
    TITLE VARCHAR2(100 BYTE),			 -- 제목
    CONTENT VARCHAR2(100 BYTE),			 -- 내용
    CREATE_DATE TIMESTAMP,				 -- 날짜		* timestamp 타입 - 자바도 timestamp
    MODIFY_DATE TIMESTAMP				 -- 수정 날짜
);
-- 기본키(PK)
ALTER TABLE ATTACH
    ADD CONSTRAINT ATTACH_PK
        PRIMARY KEY(ATTACH_NO);
ALTER TABLE UPLOAD
    ADD CONSTRAINT UPLOAD_PK
        PRIMARY KEY(UPLOAD_NO);

-- 외래키
ALTER TABLE ATTACH
    ADD CONSTRAINT ATTACH_UPLOAD_FK
        FOREIGN KEY(UPLOAD_NO) REFERENCES UPLOAD(UPLOAD_NO)
            ON DELETE CASCADE;  -- 업로드 내역을 삭제하면 첨부 내역이 함께 삭제되는 옵션
            SET NULL;

-- 시퀀스
DROP SEQUENCE UPLOAD_SEQ;
CREATE SEQUENCE UPLOAD_SEQ NOCACHE;
DROP SEQUENCE ATTACH_SEQ;
CREATE SEQUENCE ATTACH_SEQ NOCACHE;