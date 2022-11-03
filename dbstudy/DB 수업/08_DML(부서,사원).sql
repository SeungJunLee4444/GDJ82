/*
1. DML 활용
=> DML은 ROW, 레코드를 관리하는 쿼리문이다
    1) INSERT   : 행 추가
    => INSERT INTO 테이블명(칼럼명) VARLUES(VALUE);
    ex) INSERT INTO NATION(CODE, NAME, NUMBER)
            VALUE(1, 'KOREA', 50);
    
    2) UPDATE   : 행 갱신
    => UPDATE 테이블명 SET CONTENT(칼럼1 = 값1, 칼럼2 = 값2...) WHERE 조건식;    - * WHERE절은 반드시 작성되어야함
    ex) UPTATE NATION SET NUMBER = 40 WHERE CODE = 1;
    (NATION 테이블을 업데이트하라, NUMBER 칼럼의 값을 40으로, 칼럼 CODE가 1인
      * WHERE절을 생략하면 전체를 같은 내용으로 수정하겠다는 뜻?
    
    3) DELETE   : 행 삭제
    => DELETE FROM TABLE WHERE(CONDITION);
      * WHERE절을 생략하면 전체를 지우겠다는 뜻

2. 트렌잭션
=> 여러개의 DML을 여러 작업을 처리할때 하나의 작업 단위로 처리하는 것을 말한다
=> 작업이 시작되면 반드시 종료해야 하는 작업단위다
=> 중간에 멈추면 아무것도 안된채로 되돌아감

* 거래를 예시로 들때, 특정 누군가에게 입금하면 누군가가 입금받을 수 있는 두개의 과정은 뗄수없는 거래의 최소단위인데 이를 트랜젝션

1) 제어명령
  (1) COMMIT : 지금까지 변경한 내용을 영구적으로 저장하고 종료
  (2) ROLBACK : 지금까지 변경한 내용을 취소하고 트랜잭션 종료

* DCL(DATACONTROLLANG) : GRANT, REVOKE

3. DDL과 DML의 차이
1) 대상    : DB OBJECT       /    ROW
            (테이블, 유저..)         
2) 커밋유무 : X              /     O 
3) 

* 엔티티 : map의 키와 값을 엔티티라 부르듯이 해당 칼럼과 로우의 값 하나를 엔티티라한다 


*/

DROP TABLE DEPARTMENT CASCADE CONSTRAINTS; 
DROP TABLE EMPLOYEE CASCADE CONSTRAINTS;

CREATE TABLE DEPARTMENT(
    DEPT_NO NUMBER              NOT NULL,
    DEPT_NAME VARCHAR2(15 BYTE) NOT NULL,
    LOCATION VARCHAR2(15 BYTE)  NOT NULL
);

CREATE TABLE EMPLOYEE(
    EMP_NO NUMBER DEFAULT 1001 NOT NULL,    -- * 초기값 : DEFAULT
    NAME VARCHAR2(20 BYTE) NOT NULL,
    DEPART NUMBER,
    POSITION VARCHAR2(20 BYTE),
    GENDER CHAR(2),
    HIRE_DATE DATE,
    SALARY NUMBER
);

ALTER TABLE DEPARTMENT ADD CONSTRAINT PK_DEPARTMENT PRIMARY KEY(DEPT_NO);
ALTER TABLE EMPLOYEE ADD CONSTRAINT PK_EMPLOYEE PRIMARY KEY(EMP_NO);

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_DEPARTMENT FOREIGN KEY(DEPART) REFERENCES DEPARTMENT(DEPT_NO) ON DELETE CASCADE;


-------------------------------------------------------------------------------

/*
1. DML 
    => 데이터 조작어
    => 행(ROW, RECORD, TUPLE) 단위 삽입, 수정, 삭제
    => 트랜잭션 작업완료를 위해 COMMIT이 필요하고,
    => 트랜잭션의 취소를 위해 ROLLBACK 사용이 가능하다
    
    1) 종류
    (1) INSERT INTO VALUES
    (2) UPDATE SET WHERE
    (3) DELETE FROM WHERE
*/

--   1. 부서 테이블에 행 삽입
--    1) 지정한 칼럼에 삽입하는 방법
-->    INSERT INTO 테이블(칼럼1, 칼럼2) VALUES(값1, 값2);
--    2) 모든 칼럼에 삽입하는 방법
-->    INSERT INTO 테이블 VALUES(값1, 값2);

--    * 부모 테이블에 먼저 삽입해야함
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(1, '영업부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION) 
VALUES
    (2, '인사부', '서울');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (3, '총무부', '대구');
INSERT INTO DEPARTMENT
    (DEPT_NO, DEPT_NAME, LOCATION)
VALUES
    (4, '기획부', '서울');

--      작업의 완료
COMMIT;

-- * 오류체크하기
INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME) VALUES(5,'개발부');
-- * 에러, LOCATION 칼럼이 NOT NULL이기 떄문에 NULL값을 넣을 수 없다

INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(5,'개발부', '부에노스아이레스');
-- * 에러, LOCATION 칼럼의 용량이 15바이트라 용량초과로 오류(한글은 하나에 3바이트)

INSERT INTO DEPARTMENT(DEPT_NO, DEPT_NAME, LOCATION) VALUES(4,'개발부', '인천');
-- * 에러, DEPT_NO는 PK로 중복불가(UNIQUE)

--    2. 사원 테이블에 행 삽입
INSERT INTO 
    EMPLOYEE
VALUES
    (1001 , '구창민', 1 , '과장', 'M', '95/05/01', 5000000);        -- * 날짜표기는 /, - 중 하나를 사용
    -- * 칼럼명을 생략하면 모든 값을 집어넣어야함

INSERT INTO 
    EMPLOYEE 
VALUES
    (1002 , '김민서', 1 , '사원', 'F', '17/09/01', 2000000);

INSERT INTO
    EMPLOYEE
VALUES
    (1003 , '이은영', 2 ,'부장', NULL , '90-09-01', 5500000);       -- * NULL값도 가능한 위치는 NULL 입력
INSERT INTO 
    EMPLOYEE
VALUES 
    (1004 , '한성일', 2, '과장', 'M', '93-04-01', 5000000);
COMMIT;

INSERT INTO
    EMPLOYEE(EMP_NO, NAME, DEPART)
VALUES (1005, '아무개', 5);   -- * FOREIGN 키인 5가 실제로 1~4까지의 데이터값만을 지니고있음



--   3. 외부데이터 IMPORT (ex 엑셀)
--   엑셀 데이터(시트마다 테이블 1개)
--   테이블 선택후 우클릭 - 데이터 임포트 (2003으로 꼭 바꾸기)

----------------------------------------------------

--   4. 부서테이블 수정
--  * 줄맞춤 주의
--   ex) 부서번호가 1인 부서의 지역을 '인천'으로 수정 (수정할때는 모든 칼럼의 값을 집어넣지 않아도 문제없다 **)
UPDATE DEPARTMENT
   SET LOCATION = '인천'
 WHERE(DEPT_NO = 1);
 
--   ex) 부서번호가 3인 부서명을 '전략부', 지역을 부산으로 수정
UPDATE DEPARTMENT
   SET DEPT_NAME = '전략부'
     , LOCATION = '부산';
     
 WHERE 1 = 1            --> 둘중 하나가 사라지는경우를 방지, 성능이 떨어져서 비추천
    OR DEPT_NO = 3,
    OR DEPT_NO = 4; -- * 3 또는 4중 하나 (이러면 둘중 하나가 사라지는데, WHERE이 사라지면 곤란한 상황이 된다

/*
    ex) 부서번호가 3인 부서의 부서번호를 6으로 수정
    * 부서에 6이란 숫자가 없기 때문에 6을 만들수는 있으나 fk입장에서 3이 참조무결성이 위배된다

        부서 - 사원
        1       1       
        2       1
        3       2
        4       2
        5       3
        
        * 해결책
        & DEPARTMENT의 부서번호를 EMPLOYEE가 참조중이라 수정못함
        => FK를 일시중지 후 DEPARTMENT 수정후 외래키 재작동
*/

ALTER TABLE EMPLOYEE 
    DISABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT; -- DISABLE : 외래키 일시중지

--ALTER EMPLOYEE 
--    DROP CONSTRAINT FK_EMPLOYEE_DEPARTMENT; --> 삭제는 안됨?

UPDATE EMPLOYEE
   SET DEPART = 6
 WHERE DEPART = 3;

UPDATE DEPARTMENT
   SET DEPT_NO = 6
 WHERE DEPT_NO = 3;
 
ALTER TABLE EMPLOYEE 
    ENABLE CONSTRAINT FK_EMPLOYEE_DEPARTMENT; -- ENABLE : 외래키 재작동
    
    
    
    -- ex ) 부서번호가 1인 사원들의 월급을 100000 인상
UPDATE EMPLOYEE 
   SET SALARY = SALARY + 100000
 WHERE DEPART = 1;
   

    -- ex ) 직급이 '과장'인 사원들의 월급을 10% 인상

UPDATE EMPLOYEE
   SET SALARY = SALARY * 1.1
 WHERE POSITION = '과장';
 
 -- => DML로 사칙연산도 가능하다 *


-------------------------------------------------

--  5. 테이블 삭제
--      1) 부서번호가 4인 부서 삭제
--      * 부서번호 4인 행을 참조하는 사원이 없으므로 정상 삭제
--      => 외래키에 ON DELETE CASCADE 처리를 했기 때문 ***
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 4; 
 
--2) 부서번호가 1인 부서를 삭제하기 * DEPT_NO를 참조하는 1
--   * 부서번호 1의 소속 부서가 NULL값으로 함께 변경
--   => 외래키에 ON DELETE SET NULL처리
DELETE
  FROM DEPARTMENT
 WHERE DEPT_NO = 1;
 
 
 /*
 
 DDL : 명령어, CREATE, ALTER, DROP
 DML : INSERT, UPTATE, DELETE
 DQL : 조회, 검색언어
 FUNCTION : 함수(쿼리문 안에 내장된)
 조인 : 테이블 2개 이상
 서브쿼리 : 쿼리 중첩
 시퀸스 : 번호증가
 */











