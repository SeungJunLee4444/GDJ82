package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.Board;

public class SelectListMain {

	public static void main(String[] args) {
			
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			
			// 1. DB접속
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			// 2. 쿼리문 작성
			String sql = "SELECT BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC ";
			
			// 3. 쿼리문 사전실행 
			ps = con.prepareStatement(sql);
			
			// 4. 쿼리문 실행
			rs = ps.executeQuery();
			
			// * 모든 조회결과를 저장할 Arraylist
			List<Board> boards = new ArrayList<>();	// arraylist에 추가는 add();
			
			
			// 5. 다중행 조회(while)문
			// - 다중행이니 while문을 사용해 순차적으로 스캔
			while(rs.next()) {
				
				Board board = new Board();
				board.setBoard_no(rs.getInt("BOARD_NO"));
				board.setTitle(rs.getString("TITLE"));
				board.setContent(rs.getString("CONTENT"));
				board.setHit(rs.getInt("HIT"));
				board.setCreate_date(rs.getDate("CREATE_DATE"));
				
				boards.add(board);

			}
			// 6. arraylist에 저장된 board 확인
			for(int i = 0; i < boards.size(); i++) {
				System.out.println(boards.get(i));
			}
			
			/*
			  for(Board board : boards)
			  	System.out.println(board);
			 */
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null)
					rs.close();
				if( ps != null)
					ps.close();
				if( con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
