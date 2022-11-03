package ex06_select;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import domain.Board;

public class SelectOneMain {

	public static void main(String[] args) {
		
		// [3] SELECT문 실행
		// - executeQuery() 메서드 사용
		// - ResultSet 객체 사용
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			// 1) DB 접속 및 연결
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";		
			String user = "scott";
			String password = "TIGER";
			con = DriverManager.getConnection(url, user, password);
			
			// 2) 쿼리문 작성
			// 	SELECT문의 결과가 단일행인 경우(행이 하나만 조회되는경우)
			// 	(1) WHERE절에서 PK 또는 UNIQUE 칼럼과 동등비교(=)를 수행
			// 	(2) 집계함수
			
			String sql = "SELECT BOARD_NO, TITLE, CONTENT, HIT, CREATE_DATE FROM BOARD WHERE BOARD_NO = 2";
			// * 조회결과가 1개인 경우
			
			// 3) 쿼리문 사전생성
			
			ps = con.prepareStatement(sql);
			
			// 4) 쿼리문 실행
			rs = ps.executeQuery();
			
			// 5) 조회결과를 행 단위로 스캔하는 rs 객체
			// => rs의 next()로 한행씩 스캔하여, 조회결과를 스캔할 수 있음
			// => rs.next() 메서드 호출 1건 = 1행 스캔가능
			// => rs.next() 메서드 스캔 성공시 true, 스캔 실패 시 faluse 반환
			
			// * 조회결과가 한개뿐인 쿼리문이기 때문에 next()는 한번만 출력
			
			if(rs.next()) {	// * 스캔 성공하면
				
				/**
				 rs 객체는 행 전체를 가리키는 포인터역할
				 rs 객체를 통해서 행을 구성하는 열(칼럼) 정보를 가져올 수 있음
				 
				 | BOARD_NO |  TITLE  | CONTENT | HIT | CREATE_DATE |
				 |     2	|유토피아 |  나나나 |  0  |   22/09/07  | <= rs.next() 호출시 rs 포인터의 위치 
				 
				 rs 객체의 칼럼 정보 가져오기
				 	(1) 칼럼의 이름
				 	-- rs.getInt("BOARD_NO")
				 	-- rs.getString("TITLE")
				 	-- rs.getString("CONTENT")
				 	-- rs.getInt("HIT")
				 	-- rs.getDate("CREATE_DATE")
				 	
				 	(2) 칼럼의 순서(번호)(칼럼의 순서는 1부터 시작)
				 	-- rs.getInt(1)
				 	-- rs.getString(2)
				 	-- rs.getString(3)
				 	-- rs.getInt(4)
				 	-- rs.getDate(5)
				 	
				*/
				
				// 각 칼럼의 정보
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				int hit = rs.getInt("HIT");
				Date create_date = rs.getDate("CREATE_DATE");
				
				// 모든 칼럼의 정보를 하나의 Board 객체로 만듬
				// * DTO에 메서드 또는 생성자를 이용해 정보를 저장
				
				Board board = new Board();
				board.setBoard_no(board_no);
				board.setTitle(title);
				board.setContent(content);
				board.setHit(hit);
				board.setCreate_date(create_date);
				
				// 확인
				System.out.println(board.toString());
				
				
				
				
				
			} else {
				System.out.println("조회결과 없음");	// false
			}
			
			
			
			
			// rs.getint, getString("칼럼명");
			
		
			
			
			
			
			
			
			
			
			
			
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
