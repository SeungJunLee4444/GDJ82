package ex02_create_o;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DropTableMain {
	
	public static void main(String[] args) {

	Connection con = null;
	try {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:xe";		
		String user = "scott";
		String password = "TIGER";
		con = DriverManager.getConnection(url, user, password);
		
		
	} catch (ClassNotFoundException e) {
		System.out.println("OracleDriver 로드 실패");
	} catch (SQLException e) {
		System.out.println("DB접속정보 오류");
	}
	
	// 2. CREATE TABLE 실행(원래는 자바로 안함)
	PreparedStatement ps = null;
	
	
	try {
		
		// 1) 쿼리문 작성
		// - String 타입으로 작성
		// * 쿼리문의 마지막 세미콜론(;)는 JDBC에서 사용X						
		String sql = "Drop TABLE BOARD";
		
		// 2) PreparedStatement 객체 생성(쿼리문 실행)
		// - Statement의 뜻은 쿼리문
		// - 역할 : 쿼리문을 실행을 담당
		ps = con.prepareStatement(sql);												// 1)에서 작성한 쿼리문을 실행시킴
		
		// 3) 쿼리문 실행
		ps.execute();
		// - preparestatement 패키지 것만 선택, statement 패키지는 거르기
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	// 3. Connection, PreparedStatement 닫기
	
	try {
		if(ps != null)
			ps.close();
		if(con != null)
			con.close();
			
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	// => 성공시, SQL DEVELOPER에 테이블이 생성되있음

}

}
