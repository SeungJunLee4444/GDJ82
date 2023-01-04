package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board;

public class BoardDao {
	// => dao는 기본적으로 싱글턴패턴
	// & dto와 dao와 구분
	// dao는 실제로 db에 접근하는 객체
	// dto는 계층간(controller, service, repository) 데이터 교환을 위한 것
	
	// SqlSessionFactory 빌드
	private SqlSessionFactory factory;
	
	
	
	// 1. DAO 싱글턴패턴
	// 1) 내부에 생성자 호출
	private static BoardDao dao = new BoardDao();
	
	// 2) 외부에서 호출못하게 만들기
	private BoardDao() {
		// (1) dao 호출시 factory로 생성(mybatis 사이트 : XML에서 SqlSessionFactory 빌드하기)
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 3) 인스턴스를 호출
	public static BoardDao getInstance() {
		return dao;
	}
	
	// 3. 모든 메서드는 SqlSessionFactory로부터 SqlSession을 얻어서 사용
	// method
	
	
	// 4. 게시글 목록
	// => 메서드 이름을 실행할 쿼리문에 맞추기
	public List<Board> selectAllBoards() {
		SqlSession ss = factory.openSession();	// select(커밋이 필요없는 경우, jdbc는 오토 커밋)
		List<Board> boards = ss.selectList("mybatis.mapper.board.selectAllBoards");	// mybatis.mapper.board 매퍼이름과 selectAllBoards 아이디를 가진 쿼리문 실행
		// => select의 결과가 한개면 selectOne(), 여러개면 selectList();
		ss.close();	// 메서드마다 닫아주어야한다
		return boards;
	}
	
	
	
	
	
	// 2. 게시글 상세보기
	public Board selectBoardByNo(int boardNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne("mybatis.mapper.board.selectBoardByNo", boardNo);
		ss.close();
		return board;
	}
	
	// 3. 게시글 삽입(mapper의 쿼리문에서 board를 전달 int를 반환)
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false);	// insert(커밋이 필요한 경우)
		int result = ss.insert("mybatis.mapper.board.insertBoard", board);
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	// 4. 게시글 삭제
	public int deleteBoard(int boardNo) {
		SqlSession ss = factory.openSession(false);	// 수동커밋 : false, 자동커밋: true
		int result = ss.delete("mybatis.mapper.board.deleteBoard", boardNo);
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	// 5. 게시글 수정
	public int updateBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("mybatis.mapper.board.updateBoard", board);	// => 실행할 쿼리문을 특정
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
