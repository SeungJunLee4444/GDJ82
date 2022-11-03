package ex02_create_o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class CreateSequenceMain {

	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			// drivermanager은 java.lang 기본 클래스
			
			String sql = "CREATE SEQUENCE BOARD_SEQ NOCACHE";
			ps = con.prepareStatement(sql);
			// connection이 preparestatement 
			ps.execute();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if(ps != null) 
				ps.close(); 
			if(con != null) 
				con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	

	}

}
