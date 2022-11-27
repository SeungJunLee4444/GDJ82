
DROP SEQUENCE USERS_SEQ;
CREATE SEQUENCE USERS_SEQ NOCACHE;

DROP TABLE USERS;
CREATE TABLE USERS (
	USER_NO	                NUMBER	            NOT NULL,
	ID	                    VARCHAR2(30 BYTE)	UNIQUE NOT NULL,
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

-- ID : 유니크키 
-- * 한 테이블에 PK는 오직 하나만 줄 수 있으며, PK는 UK의 일종이기 때문에 PK가 존재하는 상황에 UK를 만들 수 없다
ALTER TABLE USERS ADD CONSTRAINT UK_USERS UNIQUE(ID);

INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디1', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디2', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디3', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디4', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디5', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디6', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디7', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디8', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디9', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디10', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디11', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디12', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디13', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디14', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);
INSERT INTO USERS VALUES (USERS_SEQ.NEXTVAL, '아이디15', '비밀번호', '이름', '1', '이메일', '010', '1996', '82', 'CO', '주소1' , '주소2', '상세주소', '추가주소', 0, NULL, SYSDATE, SYSDATE, SYSDATE, NULL, NULL, 0);

COMMIT;
