package ex01_connection_x;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		// [27-1] JDBC
		// 1. OracleDriver 열기
		// - Oracle.jdbc.OracleDriver / oracle.jdbc.driver.OracleDriver 둘다 사용가능
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// - Class.forName(클래스명을 입력하면 해당 클래스를 호출)
			// - 실행시 예외처리를 꼭 해줘야함															&
			//          

		} catch (ClassNotFoundException e) {
			// - ClassNotFoundException : 클래스 못찾음 예외문
			System.out.println("OracleDriver 로드 실패");
		}
		
		Connection con = null;
		
		// 2. DriverManager로부터 Connection 받아오기
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		// (Oracle XE버전 기준))
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("DB접속 성공");
			// - Connection 인터페이스 선택
			// - Connection 연결에 필요한 url, user, password가 있는  getConnection 선택
			// - DB마다, 오라클 마다도 URL은 다르다(구글링으로 확인가능)
			// - 아이디 : 대소문자 상관없음
			// - 비밀번호 : 대소문자 구분
			
			
		} catch (SQLException e) {
			// - SQLException : DB접속 정보인 url, user, password에 이상이 있다
			System.out.println("DB접속정보 오류");
		}
		
		
		// 3. Connection 종료
		// - JDBC에서는 Connection을 닫는것이 굉장히 중요
		try {
			if(con != null)
				con.close();
			// - Connection이 닫히지 않으면 닫아라
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		
		
		
		
		
		
	}

}
