package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.Board;
import repository.BoardDao;

// [JUNIT TEST 클래스]
// - import static org.junit.Assert.*;, import org.junit.Test; 가 import된 자바 클래스(클래스와 차이x)


/* 
 * [JUNIT 단위 테스트]
 * - 기능 : DAO의 메서드 단위로 단위 테스트를 수행하여, 서버(톰캣)을 작동시키지 않고 db의 데이터 여부를 테스트할 수 있다
 * & DAO = BEAN
 * 
 * => 서버(톰캣)가 돌지 않는 상태(controller 작동x)로 db에 접속
 * - Service 실행 결과가 "특정값인 경우" Service 대상으로 단위테스트를 수행할 수 있다
 * - 세팅 : BuildPath - AddLibrary - JUNIT4/5
 * 
 * - 테스트 수행 : 프로젝트 실행 : Run - JUNIT
 * - 주요 에너테이션
 * 	(1) @Test	: 단위 테스트를 수행하는 메서드
 * 	(2) @Before : 단위 테스트 수행 이전에 실행하는 메서드
 * 	(3) @After  : 단위 테스트 수행 이후에 실행되는 메서드
 * */



public class BoardTest {

	// & 보트에 테스트계정이 맨 첫번째로 들어간다(주의)
	// 데이터베이스 상으로는 번호는 4번
	
	@BeforeClass
	public static void 게시글삽입테스트() {
		// 제목 : 테스트, 내용 : 테스트내용
		Board board3 = new Board();
		board3.setTitle("테스트");
		board3.setContent("테스트내용");
		int result = BoardDao.getInstance().insertBoard(board3);
		assertEquals(1, result);	// => 성공시 반환값 1
	}
	@Test
	public void 목록테스트() {	// # 메서드명은 한글을 많이 쓴다
		
		// 목록의 개수가 4개이면 성공, 아니면 실패
		List<Board> boards = BoardDao.getInstance().selectAllBoards();
		assertEquals(4, boards.size());	// => 목록의 개수 : 기존 3개 + 테스트계정1 = 4
		
	}
	
	@Test
	public void 상세조회테스트() {
		
		// 1번 목록의 타이틀이 "공지"인가?
		Board board = BoardDao.getInstance().selectBoardByNo(1);
		assertEquals("공지", board.getTitle());
		
		// 4번 테스트계정이 존재하면 성공
		Board board2 = BoardDao.getInstance().selectBoardByNo(4);
		assertNotNull(board2);	// null이 아니면 성공
	}
	
	@Test
	public void 게시글수정테스트() {
		// 위에서 삽입한 내용을 변경
		// 제목 : 테스트2, 내용 : 테스트내용2
		Board board4 = new Board();
		board4.setTitle("테스트2");
		board4.setContent("테스트내용2");
		board4.setBoardNo(4);	// => 수정할 학생의 번호는 4번
		int result2 = BoardDao.getInstance().updateBoard(board4);
		assertEquals(1, result2);
		// => 수정을 성공하면 1반환
	}
	
	@AfterClass
	public static void 게시글삭제테스트() {
		// 위에서 수정한 내용을 삭제
		// 제목 : 테스트2, 내용 : 테스트내용2
		int result3 = BoardDao.getInstance().deleteBoard(4);
		assertEquals(1, result3);
		// => 4번 테스트2계정을 반환, 성공시 1을 반환
	}
	
	

}
