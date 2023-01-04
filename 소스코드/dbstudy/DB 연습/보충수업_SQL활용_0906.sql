2.

현재 계정(USER)이 소유한 인덱스의 정보를 학인할 수 있는 테이블 이름은 무엇인가? (5점)

-- 데이터 사전(시스템 카탈로그) : 테이블
DBA_INDEXES
               USER_INDEXES
ALL_INDEXES

 

 

3.

다음 지시사항을 만족하는 인덱스(INDEX)를 생성하는 DDL 을 작성하시오. (10점)

    1) 인덱스를 설정할 테이블 : ORDERS

    2) 인덱스를 설정할 칼럼 : ORDER_NO

    3) 생성할 인덱스의 이름 : IDX_NO

    4) 고유/비고유 유무 : 고유 인덱스

 CREATE UNIQUE INDEX IDX_NO ON ORDERS(ORDER_NO)

 

 

4.

ORDERS 테이블과 동일한 필드와 레코드를 가지는 ORDERS_COPY 테이블을 생성하는 DDL 을 작성하시오. (10점)

 CREATE TABLE 새테이블 AS 서브쿼리;
 
 CREATE TABLE ORDERS_COPY AS (SELECT * FROM ORDERS);

 

 

5.

다음 지시사항을 참고하여 뷰(VIEW)를 생성하는 DDL 을 작성하시오. (10점)

    1) 뷰를 생성할 원본 테이블 : ORDERS

    2) 뷰에 포함할 칼럼 : ORDER_NO, ORDER_DATE

    3) 뷰의 이름 : ORDER_VIEW

    4) 조건 : ORDER_DATE가 '2020-12-31'인 주문만 조회
    
- 테이블 복사와 거의 같은 쿼리문

 CREATE VIEW 뷰이름 AS (서브쿼리);
 
 CREATE VIEW ORDER_VIEW AS (SELECT ORDER_NO, ORDER_DATE FROM ORDERS WHERE ORDER_DATE = '2020-12-31');

 CREATE VIEW ORDER_VIEW AS (SELECT ORDER_NO, ORDER_DATE FROM ORDERS WHERE ORDER_DATE = '20/12/31');

 CREATE VIEW ORDER_VIEW AS (SELECT ORDER_NO, ORDER_DATE FROM ORDERS WHERE TO_DATE(ORDER_DATE) = TO_DATE('20/12/31', 'YY/MM/DD'));
 CREATE VIEW ORDER_VIEW AS (SELECT ORDER_NO, ORDER_DATE FROM ORDERS WHERE TO_DATE(ORDER_DATE) = TO_DATE('2020-12-31', 'YYYY-MM-DD'));
 CREATE VIEW ORDER_VIEW AS (SELECT ORDER_NO, ORDER_DATE FROM ORDERS WHERE TO_DATE(ORDER_DATE) = TO_DATE('12/31-2020', 'MM/DD-YYYY'));
 

6.

현재 사용자 계정(USER)에 작성되어 있는 제약조건들의 목록을 조회할 수 있는 쿼리문을 작성하시오. (5점)

 
SELECT * FROM USER_CONSTRAINTS;
 

 

7.

현재 사용자 계정(USER)에 작성된 테이블의 목록을 조회할 수 있는 쿼리문을 작성하시오. (5점)

 
SELECT * FROM USER_TABLES;
 

 

8.

다음 데이터베이스 구조를 가지고 있는 테이블이 있다고 가정하고 문제에서 요구하는 올바른 DML 2개를 모두 작성하시오. (20점)

 

 

<< 데이터베이스 구조 >>

 

    1) 회원테이블

        MEMBER (MEMBER_ID, NAME, ADDRESS)

 

    2) 회원로그테이블

        MEMBERLOG (MEMBER_ID, LOGIN_DATE)

 

    3) MEMBER 테이블의 MEMBER_ID 칼럼은 기본키(PRIMARY KEY)이다.

 

    4) MEMBERLOG 테이블의 MEMBER_ID 칼럼은 MEMBER 테이블의 MEMBER_ID 를 참조하는 외래키(FOREIGN KEY)이다.

 

 

    <<< 문제1 >>>

        로그인 한 이력은 MEMBERLOG 테이블에 남는다. 로그인 한 이력이 남은 회원들의 "이름(NAME)"과 "로그인일시(LOGIN_DATE)"를 조회하는 내부 조인 쿼리문을 작성하시오.

    SELECT M.NAME
         , L.LOGIN_DATE
      FROM MEMBER M INNER JOIN MEMBERLOG L
        ON M.MEMBER_ID = L.MEMBER_ID;

 

    <<< 문제2 >>>

        "이름(NAME)"과 "로그인일시(LOGIN_DATE)"를 조회하는 외부 조인 쿼리문을 작성하시오. 로그인 이력이 없는 회원들의 정보도 함께 출력하시오.
   
     1   회원1
     2   회원2
     3   회원3
     
     1   22/09/01
     1   22/09/02
     1   22/90/03

    내부조인 - 로그인 이력이 있어야만 결과에 포함(회원에도 있고, 회원로그에도 있는 것만 조인)
     SELECT M.NAME
         , L.LOGIN_DATE
      FROM MEMBER M INNER JOIN MEMBERLOG L
        ON M.MEMBER_ID = L.MEMBER_ID;

    회원1  22/09/01
    회원1  22/09/02
    회원1  22/09/03
    
    외부조인 - 로그인 이력이 없어도 결과에 포함(회원 또는 회원로그 중 하나는 다 포함, 어떤걸 다 포함할 건지 LEFT, RIGHT로 지정)
      SELECT M.NAME
         , L.LOGIN_DATE
      FROM MEMBER M LEFT OUTER JOIN MEMBERLOG L
        ON M.MEMBER_ID = L.MEMBER_ID;
        
    회원1  22/09/01
    회원1  22/09/02
    회원1  22/09/03
    회원2  NULL
    회원3  NULL
        
        

9.

다음 칼럼 정보를 참고하여 BOOKS 테이블을 생성하는 DDL 을 작성하시오. (10점)

 

<< 칼럼 정보 >>

    1) BOOK_ID : 책아이디, 숫자 최대 4자리, 기본키

    2) BOOK_NAME : 책이름, 가변길이문자열 최대 20바이트, 필수

    3) BOOK_ISBN : 책ISBN, 가변길이문자열 최대 20바이트, 중복 불가

    4) BOOK_PRICE : 책가격, 숫자

    5) PUB_DATE : 출판일, 날짜
    
    CREATE TABLE BOOKS(
        BOOK_ID NUMBER(4) NOT NULL PRIMARY KEY,
        BOOK_NAME VARCHAR2(20 BYTE) NOT NULL,
        BOOK_ISBN VARCHAR2(20 BYTE) UNIQUE,
        BOOK_PRICE NUMBER,
        PUB_DATE DATE
    );
    
    
    
    
    
    
    