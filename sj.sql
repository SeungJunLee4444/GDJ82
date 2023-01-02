-- MY MUSIC
CREATE TABLE MY_MUSIC 
(
	MY_MUSIC_NO INT 			NOT NULL AUTO_INCREMENT,
    LIST_NO		INT,
    MUSIC_NO	INT
);

-- ACTIVE_LOG
CREATE TABLE ACTIVE_LOG
(
	ACTIVE_NO 	INT 			NOT NULL AUTO_INCREMENT,
    EMAIL 		VARCHAR(100) 	NOT NULL,
    MUSIC_NO	INT				NOT NULL,
    LAST_LISTEN_DATE DATE,
    LISTEN_COUNT INT
);

-- PLAYLIST
CREATE TABLE PLAYLIST 
(
	LIST_NO 	INT 			NOT NULL AUTO_INCREMENT,
	EMAIL 		VARCHAR(100) 	NOT NULL,
	LIST_NAME VARCHAR (40)
);

CREATE TABLE MUSIC_COMMENT
(
	COMMENT_NO	INT				NOT NULL AUTO_INCREMENT,
	MUSIC_NO	INT				NOT NULL,
	EMAIL 		VARCHAR(100) 	NOT NULL,
    COMMENT_CONTENT VARCHAR(1000),
    CREATE_DATE	DATE
);

CREATE TABLE MUSIC_LIKE 
(
	LIKE_NO 	INT 			NOT NULL AUTO_INCREMENT,
	EMAIL 		VARCHAR(100) 	NOT NULL,
    MUSIC_NO	INT				NOT NULL
);

-- 기본키
ALTER TABLE MY_MUSIC
	ADD CONSTRAINT PK_MY_MUSIC
    PRIMARY KEY(MY_MUSIC_NO);

ALTER TABLE ACTIVE_LOG
	ADD CONSTRAINT PK_ACTIVE_LOG
    PRIMARY KEY(ACTIVE_NO);
    
ALTER TABLE PLAYLIST
	ADD CONSTRAINT PK_PLAYLIST
    PRIMARY KEY(LIST_NO);
    
ALTER TABLE MUSIC_COMMENT
	ADD CONSTRAINT PK_MUSIC_COMMENT
    PRIMARY KEY(COMMENT_NO);
    
ALTER TABLE MUSIC_LIKE
	ADD CONSTRAINT PK_MUSIC_LIKE
    PRIMARY KEY(LIKE_NO);


-- 외래키
-- ACTIVE_LOG - USERS
ALTER TABLE ACTIVE_LOG
	ADD CONSTRAINT FK_ACTIVE_LOG_USERS
		FOREIGN KEY(EMAIL) REFERENCES USERS(EMAIL)
			ON DELETE CASCADE;
            
-- ACTIVE_LOG - MUSIC
ALTER TABLE ACTIVE_LOG
	ADD CONSTRAINT FK_ACTIVE_LOG_MUSIC
		FOREIGN KEY(MUSIC_NO) REFERENCES MUSIC(MUSIC_NO)
			ON DELETE CASCADE;

-- PLAYLIST - USERS
ALTER TABLE PLAYLIST
	ADD CONSTRAINT FK_PLAYLIST_USERS
		FOREIGN KEY(EMAIL) REFERENCES USERS(EMAIL)
			ON DELETE CASCADE;

-- MY_MUSIC - MUSIC
ALTER TABLE MY_MUSIC
	ADD CONSTRAINT FK_MY_MUSIC_MUSIC
		FOREIGN KEY(MUSIC_NO) REFERENCES MUSIC(MUSIC_NO)
			ON DELETE CASCADE;
            
-- MY_MUSIC - PLAYLIST
ALTER TABLE MY_MUSIC
	ADD CONSTRAINT FK_MY_MUSIC_PLAYLIST
		FOREIGN KEY(LIST_NO) REFERENCES PLAYLIST(LIST_NO)
			ON DELETE CASCADE;

-- MUSIC_COMMENT - USERS
ALTER TABLE MUSIC_COMMENT
	ADD CONSTRAINT FK_MUSIC_COMMENT_USERS
		FOREIGN KEY(EMAIL) REFERENCES USERS(EMAIL)
			ON DELETE CASCADE;
            
-- MUSIC_COMMENT - MUSIC
ALTER TABLE MUSIC_COMMENT
	ADD CONSTRAINT FK_MUSIC_COMMENT_MUSIC
		FOREIGN KEY(MUSIC_NO) REFERENCES MUSIC(MUSIC_NO)
			ON DELETE CASCADE;
            
-- MUSIC_LIKE - USERS
ALTER TABLE MUSIC_LIKE
	ADD CONSTRAINT FK_MUSIC_LIKE_USERS
		FOREIGN KEY(EMAIL) REFERENCES USERS(EMAIL)
			ON DELETE CASCADE;
            
-- MUSIC_LIKE - MUSIC
ALTER TABLE MUSIC_LIKE
	ADD CONSTRAINT FK_MUSIC_LIKE_MUSIC
		FOREIGN KEY(MUSIC_NO) REFERENCES MUSIC(MUSIC_NO)
			ON DELETE CASCADE;

-- 기초데이터
-- 계정 
INSERT INTO USERS (USER_NO, EMAIL, ARTIST, NAME, PW, MOBILE, JOIN_DATE) VALUES (USERS_SEQ.NEXTVAL, 'practice@web.com', '연습1', '연습1', ' FFE1ABD1A 8215353C233D6E0 9613E95EEC4253832A761AF28FF37AC5A15 C', '01011111111', SYSDATE);
INSERT INTO USERS (USER_NO, EMAIL, ARTIST, NAME, PW, MOBILE, JOIN_DATE) VALUES (USERS_SEQ.NEXTVAL, 'music1@web.com', '음악테스트계정', '김김김', ' FFE1ABD1A 8215353C233D6E0 9613E95EEC4253832A761AF28FF37AC5A15 C', '01011111111', SYSDATE);

-- 음악 게시물 기초 데이터
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'practice@web.com', '관리제목1', '내용1', '앨범1' , '락', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'practice@web.com', '관리제목2', '내용1', '앨범2' , '팝송', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목3', '내용1', '앨범3' , '발라드', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목4', '내용1', '앨범4' , '클래식', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'practice@web.com', '관리제목5', '내용1', '앨범5' , '락', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목6', '내용1', '앨범1' , '팝송', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목7', '내용1', '앨범2' , '발라드', SYSDATE, SYSDATE, '00000001', '이미지1', '', '음악1', 'DB음악1', 0, '', '', 0);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목8', '내용1', '앨범3' , '팝송', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목9', '내용1', '앨범4' , '클래식', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목10', '내용1', '앨범5' , '팝송', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'practice@web.com', '관리제목11', '내용1', '앨범1' , '트로트', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목12', '내용1', '앨범2' , '클래식', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'practice@web.com', '관리제목13', '내용1', '앨범3' , '트로트', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목14', '내용1', '앨범4' , '발라드', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목15', '내용1', '앨범5' , '락', SYSDATE, SYSDATE, '00000001', '이미지1', '', '음악1', 'DB음악1', 0, '', '', 0);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목16', '내용1', '앨범1' , '트로트', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목17', '내용1', '앨범2' , '발라드', SYSDATE, SYSDATE, '00000001', '이미지1', '', '음악1', 'DB음악1', 0, '', '', 0);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목18', '내용1', '앨범3' , '트로트', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목19', '내용1', '앨범4' , '발라드', SYSDATE, SYSDATE, '00000001', '이미지1', '', '음악1', 'DB음악1', 0, '', '', 0);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목20', '내용1', '앨범5' , '클래식', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목21', '내용1', '앨범5' , '클래식', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);
INSERT INTO MUSIC (MUSIC_NO, EMAIL, MUSIC_TITLE, MUSIC_CONTENT, MUSIC_ALBUM, MUSIC_GENRE, MUSIC_UPLOAD_DATE, MUSIC_MODIFY_DATE, IP, IMG_ORIGIN, IMG_FILESYSTEM, MUSIC_ORIGIN, MUSIC_FILESYSTEM, DOWNLOAD_CNT, IMG_PATH, MUSIC_PATH, HAS_THUMBNAIL )
VALUES 
(MUSIC_SEQ.NEXTVAL,'admin@web.com', '관리제목22', '내용1', '앨범5' , '클래식', SYSDATE, SYSDATE, '00000001', '이미지1', 'animal1.jpg', '음악1', 'DB음악1', 0, 'storage\\2022\\12\\22', 'storage\\2022\\12\\22', 1);


-- 로그 기초데이터 : USER 테이블의 EMAIL과  MUSIC 테이블의 MUSIC_NO를 복수의 FK로 받는다
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'practice@web.com', '1', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'practice@web.com', '2', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '3', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '4', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'practice@web.com', '5', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '6', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '7', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '8', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '9', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '10', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'practice@web.com', '11', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '12', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'practice@web.com', '13', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '14', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '15', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '16', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '17', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '18', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '19', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '20', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '21', SYSDATE, 0);
INSERT INTO ACTIVE_LOG (ACTIVE_NO, EMAIL, MUSIC_NO, LAST_LISTEN_DATE, LISTEN_COUNT ) VALUES (ACTIVE_LOG_SEQ.NEXTVAL, 'admin@web.com', '22', SYSDATE, 0);

-- 좋아요 수 기초데이터
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 1);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 2);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'practice@web.com', 11);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 4);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'practice@web.com', 15);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 9);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'practice@web.com', 15);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 8);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'practice@web.com', 11);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'practice@web.com', 7);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 19);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 19);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'admin@web.com', 19);
INSERT INTO MUSIC_LIKE (LIKE_NO, EMAIL, MUSIC_NO) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 'music1@web.com', 3);

-- 댓글수 기초데이터
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 3, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 3, 'practice@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 3, 'practice@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 4, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 5, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 7, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 8, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 13, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 13, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 2, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 2, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 1, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 2, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 7, 'admin@web.com', '댓글1', SYSDATE);
INSERT INTO MUSIC_COMMENT (COMMENT_NO, MUSIC_NO, EMAIL, COMMENT_CONTENT, CREATE_DATE) VALUES (MUSIC_LIKE_SEQ.NEXTVAL, 8, 'admin@web.com', '댓글1', SYSDATE);

-- 플레이리스트 기초데이터   
INSERT INTO PLAYLIST(LIST_NO, EMAIL, LIST_NAME) VALUES (PLAYLIST_SEQ.NEXTVAL, 'music1@web.com', '연습용리스트1');
INSERT INTO PLAYLIST(LIST_NO, EMAIL, LIST_NAME) VALUES (PLAYLIST_SEQ.NEXTVAL, 'music1@web.com', '연습용리스트2');


UPDATE PLAYLIST SET LIST_NAME = '알랑알랑' WHERE LIST_NO = 1 AND EMAIL = 'music1@web.com';
   

-- 플레이리스트에 담긴 음악 기초데이터
INSERT INTO MY_MUSIC(MY_MUSIC_NO, LIST_NO, MUSIC_NO) VALUES (MY_MUSIC_SEQ.NEXTVAL, 1, '1');
INSERT INTO MY_MUSIC(MY_MUSIC_NO, LIST_NO, MUSIC_NO) VALUES (MY_MUSIC_SEQ.NEXTVAL, 1, '2');
INSERT INTO MY_MUSIC(MY_MUSIC_NO, LIST_NO, MUSIC_NO) VALUES (MY_MUSIC_SEQ.NEXTVAL, 1, '3');
INSERT INTO MY_MUSIC(MY_MUSIC_NO, LIST_NO, MUSIC_NO) VALUES (MY_MUSIC_SEQ.NEXTVAL, 2, '7');
INSERT INTO MY_MUSIC(MY_MUSIC_NO, LIST_NO, MUSIC_NO) VALUES (MY_MUSIC_SEQ.NEXTVAL, 2, '9');
