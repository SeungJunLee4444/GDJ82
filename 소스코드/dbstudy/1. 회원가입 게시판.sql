
DROP SEQUENCE USERS_SEQ;
CREATE SEQUENCE USERS_SEQ NOCACHE;



DROP TABLE USERS CASCADE CONSTRAINTS;
CREATE TABLE USERS (
	USER_NO	                NUMBER	            NOT NULL,
	ID	                    VARCHAR2(45 BYTE)	UNIQUE NOT NULL,
	PW	                    VARCHAR2(64 BYTE)	NOT NULL,
	NAME	                VARCHAR2(50 BYTE)	NOT NULL,
	GENDER	                VARCHAR2(2 BYTE)    NOT NULL,
	EMAIL	                VARCHAR2(50 BYTE)	NOT NULL,
	MOBILE	                VARCHAR2(11 BYTE)	NOT NULL,
	BIRTHYEAR	            VARCHAR2(4 BYTE)	NOT NULL,
	BIRTHDAY	                VARCHAR2(4 BYTE)	NOT NULL,
	POSTCODE                	VARCHAR2(5 BYTE)	NULL,
	ROAD_ADDRESS	            VARCHAR2(100 BYTE)	NULL,
	JIBUN_ADDRESS	        VARCHAR2(100 BYTE)	NULL,
	DETAIL_ADDRESS	        VARCHAR2(100 BYTE)	NULL,
	EXTRA_ADDRESS	        VARCHAR2(100 BYTE)	NULL,
	AGREE_CODE	            NUMBER	            NOT NULL,
	SNS_TYPE	                VARCHAR2(10 BYTE)	NULL,
	JOIN_DATE	            DATE	            NOT NULL,
	PW_MODIFY_DATE	        DATE	            NULL,
	INFO_MODIFY_DATE        	DATE	            NULL,
	SESSION_ID	            VARCHAR2(32 BYTE)	NULL,
	SESSION_LIMIT_DATE      	DATE	            NULL,
	POINT	                NUMBER	            NULL
);

ALTER TABLE USERS ADD CONSTRAINT PK_USERS PRIMARY KEY (
	USER_NO
);

DROP SEQUENCE RETIRE_USERS_SEQ;
CREATE SEQUENCE RETIRE_USERS_SEQ NOCACHE;

DROP TABLE RETIRE_USERS;
CREATE TABLE RETIRE_USERS (
	RETIRE_NO	NUMBER	NOT NULL,
	JOIN_DATE	DATE	NULL,
	RETIRE_DATE	DATE	NULL,
	ID	VARCHAR2(30 BYTE)	NULL
);

ALTER TABLE RETIRE_USERS ADD CONSTRAINT PK_RETIRE_USERS PRIMARY KEY (
	RETIRE_NO
);

DROP SEQUENCE SLEEP_USERS_SEQ;
CREATE SEQUENCE SLEEP_USERS_SEQ NOCACHE;


DROP TABLE SLEEP_USERS;

CREATE TABLE SLEEP_USERS (
	SLEEP_NO	NUMBER	NOT NULL,
	ID	VARCHAR2(45 BYTE)	NULL,
	PW	VARCHAR2(64 BYTE)	NULL,
	NAME	VARCHAR2(50 BYTE)	NULL,
	GENDER	VARCHAR2(2 BYTE)	NULL,
	EMAIL	VARCHAR2(45 BYTE)	NULL,
	MOBILE	VARCHAR2(11 BYTE)	NULL,
	BIRTHYEAR	VARCHAR2(4 BYTE)	NULL,
	BIRTHDAY	VARCHAR2(4 BYTE)	NULL,
	POSTCODE	VARCHAR2(5 BYTE)	NULL,
	ROAD_ADDRESS	VARCHAR2(100 BYTE)	NULL,
	JIBUN_ADDRESS	VARCHAR2(100 BYTE)	NULL,
	DETAIL_ADDRESS	VARCHAR2(100 BYTE)	NULL,
	EXTRA_ADDRESS 	VARCHAR2(100 BYTE),  
	AGREE_CODE	NUMBER	NULL,
	SNS_TYPE	VARCHAR2(10 BYTE)	NULL,
	JOIN_DATE	DATE	NULL,
	PW_MODIFY_DATE	DATE	NULL,
	LAST_LOGIN_DATE	DATE	NULL,
	SLEEP_DATE 	DATE 	NULL,
	POINT		NUMBER	NULL
);

ALTER TABLE SLEEP_USERS ADD CONSTRAINT PK_SLEEP_USERS PRIMARY KEY (
	SLEEP_NO
);


-- ID : ???????????? 
-- * ??? ???????????? PK??? ?????? ????????? ??? ??? ?????????, PK??? UK??? ???????????? ????????? PK??? ???????????? ????????? UK??? ?????? ??? ??????
-- ALTER TABLE USERS ADD CONSTRAINT UK_USERS UNIQUE(ID);
DELETE USERS
WHERE ID = 'admin';
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, 'admin', '1111', '??????', '1', '?????????', '010', '1996', '82', 'CO', '??????1' , '??????2', '????????????', '????????????', 'code', NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);

COMMIT;
