/**

    1. 뷰(자주쓰는 쿼리문 만들어서 조회하기)
    
    - 가상 테이블
    - 디스크에 저장된 테이블이 아님(쿼리만 저장해둔 상태)
    - 자주 사용하는 복잡한 쿼리가 있으면 뷰로 저장해둠
    - 저장된 뷰는 테이블처럼 SELECT문을 활용하면 됨
    - 뷰로 인한 성능상 이점은 없으나, 보안상 이점은 있음
    - * 과도한 조인이 걸릴때 사용
    - 뷰 생성
        CREATE VIVE 뷰_이름 AS (쿼리문)
        
    * 데이터사전 
        (1) USER_VIEWS : 사용자가 작성한 VIEW 확인
*/

-- 뷰 생성
CREATE VIEW VIEW_EMP 
    AS (SELECT E.EMP_NO, E.NAME, E.DEPART, D.DEPT_NAME, E.GENDER, E.POSITION, E.HIRE_DATE, E.SALARY
          FROM DEPARTMENT D INNER JOIN EMPLOYEE E
            ON D.DEPT_NO = E.DEPART); 
            
-- 두개의 테이블을 뷰로 생성한 경우
-- => 한개의 테이블로도 만들 수 있음
            
GRANT DBA TO SCOTT;            
            
            
-- 뷰 확인
SELECT * FROM VIEW_EMP;


-- 뷰 삭제
DROP VIEW VIEW_EMP;

-- 사용자가 작성한 VIEW 확인하려면 USER_VIEWS 데이터 사전
DESC USER_VIEWS;
SELECT VIEW_NAME, TEXT
  FROM USER_VIEWS;
  
  
-- INDEXT, VIEW 전부 문법이 CREATE AS형태












