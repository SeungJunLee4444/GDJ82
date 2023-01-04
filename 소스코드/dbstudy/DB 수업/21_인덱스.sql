/**

    1. 인덱스
    
        - 빠른 검색을 위해 특정 칼럼에 인덱스 부여
        - 인덱스가 등록된 칼럼은 검색이 빠름
        
        - 인덱스가 자동으로 등록된 경우
            (1) PK로 지정한 칼럼
            (2) UNIQUE 제약조건을 가진 칼럼
        - 수정(갱신)이 자주 발생하는 칼럼에 부여하면 성능이 떨어짐
          (데이터 수정시 인덱스도 같이 수정해줘야함) 
        - 인덱스 생성
            (1) 비고유 인덱스(칼럼 내용에 중복이 있는경우)                         * 그냥 비고유 인덱스 만든다 생각
               => CREATE INDEX 인덱스_이름 ON 테이블_이름(칼럼);                      
            (2) 고유 인덱스(칼럼 내용에 중복이 없는 경우)
               => CREATE UNIQUE INDEX 인덱스_이름 ON 테이블_이름(칼럼);
        
        - 인덱스 여부는 데이터모델링(BAKER), 인덱스란에서 확인가능
        - 인덱스 데이터 사전
            (1) USER_INDEXES : 유저가 만든 인덱스 조회
            (2) USER_IND_COLUMNS : 유저가 만든 인덱스가 동작하는 칼럼 확인
            
            
        - * 특정 칼럼에 함수를 쓰게되면 인덱스를 쓸 수 없음                         * WHERE절 작성시, 가급적 등호 왼쪽은 가공안하는쪽이 좋다(인덱스활용을 위함)
            => 빠르게 조회를 해야하는데, 칼럼에 함수가 개입하면서 인덱스를 쓸 수 없는 상황이 발생함     
            ex) WHERE CODE = 1 (CODE는 PK, VARCHAR2 타입)
            => 문자타입이라 1과 비교하기위해서 TO_NUMBER이 개입하게됨
            
*/


-- 인덱스 조회
-- => 사용자가 작성한 인덱스는 USER_INDEXES 데이터 사전에서 확인
DESC USER_INDEXES;
SELECT INDEX_NAME, TABLE_NAME FROM USER_INDEXES
WHERE TABLE_NAME = 'STUDENT';
-- 인덱스 칼럼 조회
-- => 사용자가 작성한 인덱스가 동작하는 칼럼 확인 USER_IDN_COLUMNS 데이터 사전

DESC USER_IND_COLUMNS;

-- 인덱스 생성
CREATE INDEX IDX_NAME ON EMPLOYEE2(NAME);

-- 인덱스 삭제
DROP INDEX IDX_NAME;










