package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board;

public class BoardDao {
	
	// [DAO]
	
	// - 개념 : db에 직접 접근해서 데이터를 가져오는 클래스
	// - 특징 : dao는 싱글턴 패턴이어야한다
	
	// * mybatis를 이용해서 dao를 구현하려면 sqlsession 객체가 필요하다
	// * 모든 쿼리문 method는 sqlsessionfactory의 sqlsession을 얻어서 사용
	// * factory 객체는 mybatis의 config.xml문서를 바탕으로 생성된다 
	// & 다만 spring에서는 알아서 처리해주기 때문에 코드를 외울 필요는없음--------------------&
	
	
	// # mybatis의 config.xml을 바탕으로 한 factory 객체 얻기
	// #) 1 필드 sqlsessionfactory 
	private SqlSessionFactory factory;
	
	// & mybatis + dao 클래스 작동원리 정리---------------------------------------------------&
	// (1) dao 클래스는 싱글턴 패턴 적용
	// (2) seqlsessionfactory를 통해 sqlsession 객체 획득
	// (3) sqlsession 객체로 sql 쿼리문 실행 메서드 사용
	//----------------------------------------------------------------------------------------&
	
	// ## 싱글턴 패턴
	// ##) 1 필드에 클래스 선언 및 생성
	private static BoardDao dao = new BoardDao();	// 내부에서 클래스 생성
	// ##) 2 생성자 만들기
	private BoardDao() {
		try {
			// #) 2 SqlSessionFactory 빌드 : factory 객체를 얻기 위해서
			String resource = "mybatis/config/mybatis-config.xml";		// mybatis의 config.xml문서
			InputStream in = Resources.getResourceAsStream(resource);	// 
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// ##) 3 인스턴스 메서드 생성 : 반환값이 클래스의 객체값
	public static BoardDao getInstance() {
		return dao;
	}
	
	//========================================================================================
	
	// [쿼리문을 처리할 메서드]
	
	String mapper = "mybatis.mapper.board.";
	// 해석 : mapper의 위치를 변수화 : mybatis 폴더의 mapper폴더의 board.xml파일을 의미
	
	// # 서비스 목록 메서드 : read,select 영역
	// - 매개변수 : x
	// - 반환타입 : Board 타입 List(복수의 board기 때문)
	// - 메서드 : selectList()
	
	public List<Board> selectAllBoards() {
		SqlSession ss = factory.openSession();
		List<Board> boards = ss.selectList(mapper + "selectAllBoards");	// mapper 쿼리문의 id와 동일해야한다
		ss.close();	// * sqlsession은 사용후에 닫아줘야한다
		
		return boards;
	} 
	
	// # 서비스 상세보기 메서드 : 
	// - 매개변수 boardNo
	// - 반환타입 : Board 타입 하나
	// - 메서드 selectOne
	public Board selectBoardByNo(int boardNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne(mapper + "selectBoardByNo", boardNo);
		ss.close();
		return board;
	}
	
	
	// * 추가, 수정, 삭제는 메서드 작성시, commit()를 한다-------------------------------------------------------------*
	// * 추가 ,수정, 삭제 메서드는 commit 하기 위해서 openSession(false); false를 넣어줘야한다
	
	// # 서비스 추가 메서드 
	// - 매개변수 	: Board(title과 content가 저장된)
	// - 반환타입	: int
	// - 메서드 	: insert()
	
	
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertBoard", board);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// # 서비스 수정 메서드
	// - 매개변수	: Board(boardNo, title, content) * 날짜는 수정날짜 기준으로 받아야하니 파라미터로 전송할필요가없다
	// - 반환타입	: int(성공은 1, 실패는 0)
	// - 메서드		: update()
	// * insert와 코드가 동일하다 보면된다(boardNo를 파라미터로 전달하는 것의 차이일뿐)
	
	public int updateBoard(Board board) {
		SqlSession ss = factory.openSession(false);  // UPDATE(커밋이 필요한 경우)
		int result = ss.update("mybatis.mapper.board.updateBoard", board);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// # 게시글 삭제 메서드
	// - 매개변수	: boardNo
	// - 반환타입	: int
	// 메서드 		: delete()
	
	public int deleteBoard(int boardNo) {
		SqlSession ss = factory.openSession(false);  // DELETE(커밋이 필요한 경우)
		int result = ss.delete("mybatis.mapper.board.deleteBoard", boardNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	

}
