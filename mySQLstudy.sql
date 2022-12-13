-- 스프링부트에서 적용 : application.properties 수정

-- 1. 사용자만들기 : 사용자를 SKIMA라고 함
-- 방법 : 좌측 SCHEMAS 탭 - 우클릭 - create scima
-- 설정 		(1) 계정이름 
-- 			(2) utf-8, utf-8_bin

-- 2. team 스키마 사용
USE team;

-- 3. 쿼리문 실행
-- 1) 단독실행 : ctrl + enter
-- 2) 블록실행 : 블록잡고 ctrl + shift + enter
-- 3) 전체실행 : ctrl + shift + enter
 
 -- 3. 오라클 -> mysql로 수정하기
 -- 예시 : mysql 프로젝트.sql(14장)
 
 
 
 -- 다중 첨부(게시글 하나에 여러 개의 첨부가 가능)
-- 파일 첨부 정보
-- 1. DROP TABLE
DROP TABLE IF EXISTS ATTACH;
DROP TABLE IF EXISTS UPLOAD;


-- 2. CREATE TABLE

-- (1) NUMBER -> INT
-- (2) AUTO_INCREMENT : 시퀀스대체, 자동증가	
-- * 사용조건 : 해당 칼럼을 PK로 등록해야 사용 가능
-- (3) VARCHAR2(숫자 타입) -> VARCHAR(숫자)
-- (4) DATETIME : 날짜 + 시간 전부 계산
CREATE TABLE UPLOAD
(
    UPLOAD_NO 	INT NOT NULL AUTO_INCREMENT ,  	
    TITLE VARCHAR(100),   -- 제목
    CONTENT VARCHAR(100), -- 내용
    CREATE_DATE DATETIME,      -- 작성일			-- * mysql의 DATETIME은 DATE로 변환
    MODIFY_DATE DATETIME,       -- 수정일			-- * mysql의 TIMESTAMP는 TIMESTAMP로 변환
    CONSTRAINT PK_UPLOAD PRIMARY KEY(UPLOAD_NO)	-- * PK 적용 : AUTO_INCREMENT 사용을 위해 바로 PK 등록
);

CREATE TABLE ATTACH
(
    ATTACH_NO INT NOT NULL AUTO_INCREMENT,     -- PK
    PATH VARCHAR(300),       -- 파일의 경로
    ORIGIN VARCHAR(300),     -- 파일의 원래 이름
    FILESYSTEM VARCHAR(42),  -- 파일의 저장된 이름
    DOWNLOAD_CNT INT,           -- 다운로드 횟수
    HAS_THUMBNAIL INT,          -- 썸네일이 있으면 1, 없으면 0
    UPLOAD_NO INT,              -- 게시글번호, FK
    PRIMARY KEY(ATTACH_NO)						-- * PK적용 : 오라클과 마찬가지로 CONSTRAINT 생략가능
	CONSTRAINT FK_ATTACH_UPLOAD FOREIGN KEY(UPLOAD_NO) REFERENCE UPLOAD(UPLOAD_NO) ON DELETE CASCADE;
);
   

-- 게시판
DROP TABLE UPLOAD;


-- 기본키
ALTER TABLE ATTACH
    ADD CONSTRAINT PK_ATTACH
        PRIMARY KEY(ATTACH_NO);
ALTER TABLE UPLOAD
    ADD CONSTRAINT PK_UPLOAD
        PRIMARY KEY(UPLOAD_NO);

-- 외래키
ALTER TABLE ATTACH
    ADD CONSTRAINT FK_ATTACH_UPLOAD
        FOREIGN KEY(UPLOAD_NO) REFERENCES UPLOAD(UPLOAD_NO)
            ON DELETE CASCADE;  -- 업로드 내역을 삭제하면 첨부 내역이 함께 삭제되는 옵션





-- 페이징 처리한 목록 보기 쿼리
SELECT U.UPLOAD_NO, U.TITLE, U.CONTENT, U.CREATE_DATE, U.MODIFY_DATE, (SELECT COUNT(*) FROM ATTACH A WHERE U.UPLOAD_NO = A.UPLOAD_NO) AS ATTACH_CNT
  FROM (SELECT ROW_NUMBER() OVER(ORDER BY UPLOAD_NO DESC) AS ROW_NUM, UPLOAD_NO, TITLE, CONTENT, CREATE_DATE, MODIFY_DATE
          FROM UPLOAD) U
 WHERE U.ROW_NUM BETWEEN 1 AND 5;
