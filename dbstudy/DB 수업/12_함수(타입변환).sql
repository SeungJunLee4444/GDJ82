-- * SELECT의 특징 : FROM이 필수(오라클 한정)
SELECT 1+2
 FROM EMPLOYEE;
-- 테이블이 있다해서 꼭 테이블을 참고하지 않고 상관없는 값이 나올 수 있다
--> MYSQL과 달리 오라클은 FROM이 필수기 때문에 단순한 연산을 위한 DUAL 테이블이 존재한다
SELECT DUMMY
 FROM DUAL;       --값 : X만 나옴
--> DUAL, 테이블을 참고할 필요없는 그냥 이름만 빌려주는 테이블

DESC DUAL; -- * 내림차순과 혼동X


/*
    1. 타입변환 함수
     1) TO_NUMBER('문자열');       : 문자열 형식의 '숫자'를 숫자형식으로 변환
     2) TO_CHAR(데이터, ['형식'])    : 지정된 데이터(숫자, 날짜)를 형식에 맞는 문자열로 변화
     3) TO_DATE('문자열', '형식')  : 지정된 문자열을 날짜 형식으로 변환 
*/

-- 1) TO_NUMBER
SELECT '100', TO_NUMBER('100')
  FROM DUAL;
  --=> 100(문자), 100(숫자)로 조회가능
  -- * 문자는 왼쪽, 숫자는 오른쪽으로 정렬됨

SELECT '1.5', TO_NUMBER('1.5')
  FROM DUAL;
-- > 위에 쓴 것들은 칼럼으로, 연산값은 칼럼값으로 나온다 ***


-- 2) 숫자 + '문자' / '문자' + '문자'
-- 숫자 형태의 '문자'는 전부 연산시 숫자타입으로 정렬된다
-- '문자' -> TO_NUMBER('문자') 방식으로 자동처리한다
SELECT 1 + '1'
  FROM DUAL;
-- => 2(숫자타입으로 변환)
SELECT '1' + '1'        -- = SELECT TO_NUMBER('1') + TO_NUMBER('1')
  FROM DUAL;
  
SELECT *
  FROM EMPLOYEE
 WHERE EMP_NO = '1001'; --> '1001'을 문자로 입력해도 숫자로 알아서 변환하기 때문에,
                        --  EMP_NO의 타입 NUMBER에 맞음

-- 3) TO_CHAR, 문자로 변환
SELECT 
    TO_CHAR(1234)
    ,TO_CHAR(1234, '999999')     --> 빈자리에 공백을 채움(왼쪽)    -   1234
    ,TO_CHAR(1234, '000000')     --> 빈자리에 0을 채움(왼쪽)       - 001234
    ,TO_CHAR(1234, '9,999')      --> 네자릿수로 표시               - 1,234
    ,TO_CHAR(12345, '99,999')     --> 자릿수가 부족해 #####로 표시  - #####
    ,TO_CHAR(12345, '99,999')    --> 다섯자리수로 표시             - 12,345
    ,TO_CHAR(1.6, '9')           --> 형식은 정수 1자리 표기(소수점포기, 반올림처리) -1
    ,TO_CHAR(1.5, '9')                                            -- 2
    ,TO_CHAR(0.123, '0.00')      --> 소수점 이하 두자리 표시         - 0.12
    ,TO_CHAR(0.129,'0.00')       --> 소수점 이하 두자리표기(반올림)  -0.13
    FROM DUAL;                                      

&&&&&&&&&&&&&&&&&&&&&--

-- 4) 날짜 -> 문자로 변환
-- * 현재 날짜와 시간
-- DATE 타입의 SYSDATE
-- TIMESTAMP 타입의 SYSTIMESTAMP
SELECT 
        SYSDATE            --22/08/26 년,월,일 형식만 나오지만 시간 데이터도 가지고있음
       ,SYSTIMESTAMP       --22/08/26 15:12:41 시,분,초까지 나옴
    FROM DUAL;
    -- * +9:00 UTC 기준 9시간 빠르다는 의미
    
SELECT  
        TO_CHAR(SYSDATE, 'YYYY-MM-DD')  --년, 월, 일
       ,TO_CHAR(SYSDATE, 'HH:MI:SS')    -- 시, 분, 초
    FROM DUAL; 
    
    
-- 5) 날짜로 변환(TO_DATE)

-- '05/06/07'날짜는 언제인가?
-- => 알려주기 전까지는 모름
-- => '지정된 형식'으로 해석해야한다  
-- 예시) 'YY/MM/DD' : 05년 06월 07일
-- 예시2) 'MM/DD/YY': 07년 05월 06일
-- * TODATE는 어떤 날짜를 어떻게 해석해야 하는지를 알려주는 함수 **

SELECT  
        TO_DATE('05/06/07', 'YY/MM/DD')
       ,TO_DATE('05/06/07', 'MM/DD/YY')
    FROM
        DUAL;
-- ex) 현재 날짜를 YYYY-MM-DD 형식으로 조회
-- * TODATE로 푸는 문제가 아님, 

SELECT
        TO_DATE(SYSDATE, 'YYYY/MM/DD')  --* 날짜인데 날짜로 바꾸겠다? XX, 잘못된 사용법
    FROM
        DUAL;
        -- 상관없는 숫자를 날짜형식을 입혀 날짜값으로 바꿀때 TODATE
        -- 날짜값에 특정한 형식을 입히는 것을 TOCHAR
        -- * 이 차이를 구분할 것

-- ex) 사원 테이블에서 90년 1월 1일 ~ 99년 12월 31일 사이에 입사한 사원 조회하기
  SELECT EMP_NO , NAME, DEPART, POSITION, GENDER, HIRE_DATE, SALARY     -- EMPLOYEE 테이블 전체내용
    FROM EMPLOYEE
   WHERE TO_DATE(HIRE_DATE, 'YY/MM/DD') BETWEEN TO_DATE('90/01/01', 'YY/MM/DD') AND
   TO_DATE('31/12/99', 'DD/MM/YY');
   
DROP TABLE SAMPLE; 
CREATE TABLE SAMPLE(
    DT1 DATE,
    DT2 TIMESTAMP,
    DT3 VARCHAR2(10 BYTE)       -- * 실무에서는 날짜값이 성능상 이유로 VARCAHR2타입에 저장된 경우가 많다
); 

-- DT1과 DT2에 오늘 날짜넣기
INSERT INTO SAMPLE(DT1, DT2, DT3) VALUES(SYSDATE, SYSTIMESTAMP, TO_CHAR(SYSDATE, 'YYYY-MM-DD'));
-- * DATE타입 칼럼에서 값은 SYSDATE
SELECT DT1, DT2, DT3 FROM SAMPLE;

-- * 날짜 비교연산은 TO_DATE가 꼭 필요하다
--(1) 실패

SELECT DT1, DT2, DT3 
    FROM SAMPLE
   WHERE DT1 = TO_DATE('22/08/26', 'YY/MM/DD'); 

--(2) 실패
SELECT DT1, DT2, DT3 
    FROM SAMPLE
   WHERE DT1 = TO_DATE(DT1, 'YY/MM/DD'); 
   
--(3) 성공
SELECT DT1, DT2, DT3 
    FROM SAMPLE
   WHERE TO_DATE(DT1, 'YY/MM/DD') = TO_DATE('22/08/26', 'YY/MM/DD');  
   -- * 날짜를 비교할 때는 TO_DATE를 꼭 사용해야 깔끔하게 비교할 수 있다

