package ex03_insert_x;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class InsertMain {

	public static void main(String[] args) {
		
		// 예시. 게시판 정보를 입력받아서 BOARD 테이블에 저장
		//	- Scanner 클래스로 정보 입력받기
		// 	- Board 클래스 타입의 객체에 입력받은 정보 저장하기
		
		// 2. 인서트 사용
		// => 기존과 달리 실행시 excute 대신 excuteUpdate() 사용
		// => 여러번 실행가능(실행될떄마다 값이 추가)
		// => scanner로 입력할 값은 INSERT문에서 ?로 표시하고,
		// 
		
		// 1) 제목과 내용 입력받기
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("제목 >>> ");
		String title = sc.nextLine();
		
		System.out.print("내용 >>> ");
		String content = sc.nextLine();
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
		
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		String user = "scott";
		String password = "TIGER";
		con = DriverManager.getConnection(url, user, password);
		
		String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, 0, SYSDATE)";		// 차이점1 : scanner로 입력할 값은 ?표시
		
		ps = con.prepareStatement(sql);	// 쿼리문 실행(Preparestatement와 excute)
		
		// 2) 쿼리문에 포함된 ?에 변수전달
		// 쿼리문에 작성된 ?의 순서대로 명시하며 채워줌
		
		ps.setString(1, board.getTitle());		// 첫번째 ?에 board에 들어있는 gettitle 전달하기(title이 string이니 setstring)			// 차이점1: 
		ps.setString(2, board.getContent());  	// 2번째 ?에 board에 들어있는 getcontent 전달하기
		int result = ps.executeUpdate();		// 차이점2 : insert 실행은 반환값이 int 타입
		if(result > 0) {				// (두개가 insert되면 2가 나올수도 있음)
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
		
		// 3) INSERT문의 실행
		// => excuteUpdate() 메서드 사용, 반환값은 INT(0 또는 1)
		
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) 
					ps.close();
				if(con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
