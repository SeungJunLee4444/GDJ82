/* 
1. 기존의 SCOTT 계정 테이블을 전부 삭제하는법
1) SYS, SYSTEM 계정에 접속
2) DROP USER SCOTT CASCADE; 입력

*/

-- 1) 계정 삭제
DROP USER SCOTT CASCADE;

-- 2) 계정 생성
CREATE USER SCOTT IDENTIFIED BY TIGER;

-- 3) 권한 부여
GRANT CONNECT, RESOURCE TO SCOTT;
-- * RESOURCE : 테이블 수정, 관리할 수 있는 권한

-- * SYS로 접속해 기존 계정의 테이블을 리셋시키는 용도로 사용할 것

GRANT DBA TO SCOTT; -- 이러면 SCOTT로 못하는 작업이없음





