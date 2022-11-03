package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import domain.Board;
import repository.BoardDao;

/*
	DBCP 설정은 context.xml에 작성되어 있는데, 해당 내용은 Tomcat이 처리한다.
	JUnit 단위 테스트는 Tomcat 없이 실행되므로, BoardDao의 DataSource가 생성되지 않아
	정상적인 테스트가 어렵다.
	
	테스트 수행할 때 Tomcat이 참여할 수 있도록 URL을 통해 접근하는 별도 테스트 코드를 작성한다.
	(테스트를 위해 json 라이브러리를 추가하였다.) 
*/
public class BoardTest {

	@Test
	public void 목록테스트() {  // 메소드명으로 한글을 많이 사용한다.
		
		// 목록의 개수가 5개이면 테스트 성공, 아니면 실패
		List<Board> boards = BoardDao.getInstance().selectAllBoards();
		assertEquals(5, boards.size());
		
	}

}
