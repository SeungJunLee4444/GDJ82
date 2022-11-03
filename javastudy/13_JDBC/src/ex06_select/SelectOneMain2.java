package ex06_select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectOneMain2 {

	public static void main(String[] args) {


		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			String sql = "SELECT COUNT(*) AS 총개수 FROM BOARD";	
			
			// & COUNT는 NULL값을 포함한 테이블 전체의 행의 개수를 구한다
	
			
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();			// select 문 실행( 칼럼 : 총개수, 값 3)
			
			if(rs.next()) { // -> ( 칼럼 : 총개수, 값 3)
				
				// 방법 1: rs.getInt("총개수")												& 
				// 방법 2: rs.getInt(1)
				int count = rs.getInt("총개수");
				System.out.println(count);
				
				// COUNT(*) 집계함수의 결과 : ELSE처리가 필요없음
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null)
					rs.close();
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
