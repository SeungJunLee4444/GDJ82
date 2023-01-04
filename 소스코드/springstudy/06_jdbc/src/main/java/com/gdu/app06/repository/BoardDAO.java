package com.gdu.app06.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.app06.domain.BoardDTO;

// # BoardDAO 정리 =================================================================================
// & @Repository
// - 기능 : DAO에 추가하는 @Compoent
// - 작동원리 : servelt-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색
// - 이점 : root-context.xml 또는 @configuration에 @bean으로 등록되지 않아도 컨테이너에 만들어진다
// - 특징 : 트랜젝션 기능이 추가되있음 ----* => DB관련 필요

// & 컨테이너 생성방법 3가지 -----------------------------------------------------------------------
// (1) XML방식, (2) 자바 클래스에 @Configuration 선언, (3) DAO를 @Component로 선언
// - dao가 사용하는 @component는 @repository다

// * @Component : 별도의 컨테이너 생성없이 해당 빈 클래스 자체에 빈 설정이 가능한 어노테이션 ----*

// & dao에 싱글턴이 없는 이유
// - 답 : 컨테이너에 만드는 기본적인 방법은 전부 싱글턴이기 떄문 --*


// # @Repository : DAO 전용 @Component(컨테이너) ==================================================================
@Repository
public class BoardDAO {
	
	// # DAO : DB에 접근할 클래스--------------------------
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;	// * 기능 : 결과값 표현, select문에서 사용 -- *
	private String sql;

	
	// # 오라클 드라이버 + 커넥션 얻기 메서드
	// & private인 이유 : 이 메서드들은 boardDAO에서만 쓴다
	private Connection getConnection() {
		Connection con = null;
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// # 연결닫는 메서드 생성
	private void close() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {rs.close();}
			if(con != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// * 레파지토리 계층의 이름은 db 친화적으로 작성한다 --------*
	
	
	// # 전체조회 ------------------------------------------------------------------------------------------------------
	public List<BoardDTO> selectAllBoard() {
		List<BoardDTO> boards = new ArrayList<>();
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
			ps = con.prepareStatement(sql);	// - 사전실행
			rs = ps.executeQuery();			// - 쿼리문 실행
			// * while(rs.next()) 사용법 ------------------------------------------------------------*
			// (1) while 쓰는 이유 : rs.next() 하나당 실제 쿼리문을 한 행씩만 읽음
			// (2) 각 칼럼의 타입마다 숫자는 number은 getint, varchar2는 getString으로 처리한다
			// (3) 메서드의 ()안은 쿼리문 위에서부터 1~ 순으로 추가한다
			// (4) 숫자는 해당 칼럼 자체를 의미한다, 즉 칼럼에 속한 값들이 전부 출력된다 -- *
			
			// * 데이터를 받아오는 방법은 1) 칼럼의 순서(1), 2) 칼럼의 이름("BOARD_NO')가 있다 -- *
			
			// * rs.next() -------------------------------------------------------------------------- *
			// (1) resultSet : 쿼리의 결과를 행으로 저장한다
			// (2) rs.next() : 한 행 단위로 실행한다, 다음행이 존재하면 true, 아니면 false를 반환한다
			// (3) 즉 마지막 행에 도달하면 falsf를 반환하면서 while문이 종료하게된다
			while(rs.next()) {			
				BoardDTO board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				boards.add(board);
			}						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return boards;
	}
	
	// # 상세조회 ------------------------------------------------------------------------------------------------------
	public BoardDTO selectBoardByNo(int board_no) {
		BoardDTO board = null;
		try {
			con = getConnection();
			sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?"; 
			ps = con.prepareStatement(sql);	// * prepareStatement : 매개변수를 받아와야 하는 경우에, 별도로 쿼리문을 실행시켜서 보안성을 높인다, 쿼리문에 ?를 사용할 수 있음
			ps.setInt(1, board_no);	// ?처리 : PreparedStatement의 ()안 숫자는 ?의 순서
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			} 
			
			// * PreparedStatement 와 ResultSet의 차이 ------------------------------ *
			// (1) ResultSet : set메서드 사용시 괄호안 숫자는 칼럼의 순서를 의미
			// (2) PreparedStatement : set 메서드 사용시 괄호안 숫자는 ?의 순서를 의미
					
			// * executeQurey / executeUpdate 의 용도차이 ----*
			// (1) executeQuery	 : select문에 사용
			// (2) executeUpdate : 그 외의 문에 사용
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return board;
		
	}
	
	// # 게시글 삽입 ---------------------------
	public int insertBoard(BoardDTO board) {
		int result = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?,"	// * 공백, 띄어쓰기 주의하기
					+ " TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YY-MM-DD'))";
					// * 날짜는 varchar2기 때문에 to_char을 사용
			ps = con.prepareStatement(sql);
			// * ? 처리시 ()괄호안 숫자 : ? 순서를 의미 
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getWriter());
			result = ps.executeUpdate();	// * 결과를 반환값을 int로 반환
			
			// * PreparedStatement 와 ResultSet의 차이 ------------------------------ *
			// (1) ResultSet : set메서드 사용시 괄호안 숫자는 칼럼값을 의미
			// (2) PreparedStatement : set 메서드 사용시 괄호안 숫자는 ?의 순서를 의미
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	// # 게시글 수정 : 쿼리문으로 수정일을 지정할 수 있음
	// - 방법 : T0_CHAR(SYSDATE, 'YYYY-MM-DD') 다시 사용
	public int updateBoard(BoardDTO board) {
		int result = 0;
		try {
			con = getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD')"
					+ "WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setInt(3, board.getBoard_no());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	// # 게시글 삭제
	public int deleteBoard(int board_no) {
		int result = 0;
		try {
			con = getConnection();
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, board_no);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
