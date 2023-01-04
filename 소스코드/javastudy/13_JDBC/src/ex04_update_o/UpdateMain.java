package ex04_update_o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class UpdateMain {

	public static void main(String[] args) {
		
		// 3. UPDATE 사용
		
			// 예시
			
			// Connection 생성
			Connection con = null;
			PreparedStatement ps = null;
			
			try {
				
				Scanner sc = new Scanner(System.in);
				
				// UPDATE할 게시글의 번호 입력받기
				System.out.println("수정할 게시글 번호 >>>");
				int board_no = sc.nextInt();
				sc.nextLine();
				
				
				// UPDATE할 게시글의 내용(CONTENT) 입력받기
				System.out.println("수정할 게시글 내용 >>>");
				String content = sc.nextLine();
				
				
				// UPDATE할 번호 + 내용을 가진 Board 객체 만들기
				Board board = new Board();
				board.setBoard_no(board_no);
				board.setContent(content);
				
		
			// DB 접속 후 쿼리문 작성(CONNECTION 인터페이스 사용)
				
				Class.forName("oracle.jdbc.OracleDriver");
				String url = "jdbc:oracle:thin:@localhost:1521:xe";		
				String user = "scott";
				String password = "TIGER";
				con = DriverManager.getConnection(url, user, password);	// drivermanager은 자바자체 클래
			     
				String sql = "UPDATE BOARD SET CONTENT = ? WHERE BOARD_NO = ?";
				
						
			// PreparedStatement 객체 생성
				// PREPAREDSTATEMENT의 EXECUTE 메서드 => 쿼리문 실행
				
				ps = con.prepareStatement(sql);
			
			// 쿼리문의 ?에 변수 전달
				
				ps.setString(1, board.getContent());
				ps.setInt(2, board.getBoard_no());
				
				
			// 쿼리문 실행	
				int result = ps.executeUpdate();
				
			// 실행결과(업데이트 성공 또는 실패)
				if(result > 0) {
					System.out.println("성공");
				} else {
					System.out.println("실패");
				}
				
				// con, ps 닫기
				
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
