package ex05_delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import domain.Board;

public class DeleteMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Scanner sc = new Scanner(System.in);
			
			// 1) 삭제할 게시글 번호 입력받기
			System.out.println("삭제할 게시글 번호 >>>");
			int board_no = sc.nextInt();
			sc.nextLine();
			
			// 2) connection 생성
			// (1) DB 접속
			
			Board board = new Board();
			board.setBoard_no(board_no);
			
			
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);		// &
			
			// 3) 쿼리문 생성
			
			String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			
			// 4) preparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5) 쿼리문 ?에 변수 전달하기
			ps.setInt(1, board.getBoard_no());
			
			// 6) 쿼리문 실행
			int result = ps.executeUpdate();
			
			// 7) 실행결과
			// 8) 삭제성공, 삭제 실패
			if(result > 0) {
				System.out.println("성공");
			} else {
				System.out.println("실패");
			}

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
