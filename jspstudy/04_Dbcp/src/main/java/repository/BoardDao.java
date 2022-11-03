package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.Board;

public class BoardDao {

	// field
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	
	// & DataSource : 커넥션 풀 관리 인터페이스---------------------------------
	// => dbcp는 여러개의 db connection을 하나의 pool에 모아놓고관리 
	private DataSource dataSource;
	//--------------------------------------------------------------------------
	
	// singleton - pattern
	private static BoardDao dao = new BoardDao();
	private BoardDao() {
		try {
			// DataSource 객체 생성
			// context.xml에서 name="jdbc/oracle11g"인 Resource를 찾아서 생성(JNDI)
			
			// & JNDI  -----------------------------------------------------------
			// - 서버의 특정 resource를 찾는 방식
			// - java EE서버에서 resource를 찾는경우 정해진 기본이름이 있음
			 
			
			Context ctx = new InitialContext();
//			Context envCtx = (Context)ctx.lookup("java:comp/env");
//			dataSource = (DataSource)envCtx.lookup("jdbc/oracle11g");
			 dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle11g");
			 // & dbcp 설정이 저장된 resource를 찾는 경우 사용해야하는 jndl
			 //--------------------------------------------------------------------
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static BoardDao getInstance() {
		return dao;
	}
	
	// method
	
	// 1. 접속/자원 해제
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) { rs.close(); }
			if(ps != null) { ps.close(); }
			if(con != null) { con.close(); }  // Connection Pool의 close()는 Connection 종료가 아닌 Connection 반환으로 동작
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 2. 목록 반환하기
	public List<Board> selectAllBoards() {
		List<Board> boards = new ArrayList<Board>();
		try {
			con = dataSource.getConnection();  // CP로부터 Connection 대여
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();  // SELECT문은 executeQuery() 사용
			while(rs.next()) {  // 목록보기는 while문
				// Board board는 한 개의 게시글을 의미함
				Board board = new Board();
				board.setBoard_no( rs.getInt(1) );      // rs.getInt("BOARD_NO")
				board.setTitle( rs.getString(2) );      // rs.getString("TITLE")
				board.setContent( rs.getString(3) );    // rs.getString("CONTENT")
				board.setCreate_date( rs.getDate(4) );  // rs.getDate("CREATE_DATE")
				// 가져온 게시글을 리스트에 추가함
				boards.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return boards;
	}
	
	// 3. 상세보기
	public Board selectBoardByNo(int board_no) {
		Board board = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, CREATE_DATE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);  // 1번째 물음표(?)에 board_no 전달하기
			rs = ps.executeQuery();  // SELECT문은 executeQuery() 사용
			if(rs.next()) {          // 상세보기는 if문
				board = new Board();
				board.setBoard_no( rs.getInt(1) );      // rs.getInt("BOARD_NO")
				board.setTitle( rs.getString(2) );      // rs.getString("TITLE")
				board.setContent( rs.getString(3) );    // rs.getString("CONTENT")
				board.setCreate_date( rs.getDate(4) );  // rs.getDate("CREATE_DATE")
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return board;
	}
	
	// 4. 게시글 삽입
	public int insertBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			result = ps.executeUpdate();  // INSERT문은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 5. 게시글 수정
	public int updateBoard(Board board) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();  // UPDATE문은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	// 6. 게시글 삭제
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();  // DELETE문은 executeUpdate() 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
}
