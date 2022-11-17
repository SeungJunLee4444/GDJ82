package com.gdu.app08.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.gdu.app08.domain.BoardDTO;


@Repository 	
public class BoardDAO {
	
	
	// # 스프링 전용 JDBC ----------------------------------------------------------------- +
	// & JdbcTemplate 
	// - 개념 : Connection, PreparedStatement, ResultSet을 내부에서 사용하는 Spring클래스
	// - 기능 : DriverManagerDataSource에 의해서 Connection Pool 방식으로 동작
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DriverManagerDataSource dataSource;

	
	
	// # 전체조회 ------------------------------------------------------------------------------------------------------
	public List<BoardDTO> selectAllBoard() {
	
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD ORDER BY BOARD_NO DESC";
		

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BoardDTO.class));
		// & BeanPropertyRowMapper : 데이터베이스의 행과 boardDTO와 연결해주겠다
		// & query() : 반환타입이 List 
		
	}
	
	// # 상세조회 ------------------------------------------------------------------------------------------------------
	public BoardDTO selectBoardByNo(final int board_no) {	// & final : 예전에 매개변수 해킹 시도가 있어서 붙여놓은것, 생략가능
		
		String sql = "SELECT BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE FROM BOARD WHERE BOARD_NO = ?";
		
		BoardDTO board = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(BoardDTO.class), board_no);
		
		// & queryForObject : 반환타입이 하나다
		
		// & BeanPropertyRowMapper
		// - DB의 컬럼명과 bean 객체의 속성명이 일치하다면 BeanPropertyRowMapper를 이용하여 자동으로 객체변환을 할 수 있습니다. 
		// - DB 컬럼명이 'snake_case'로 되어 있어도 'camelCase'로 선언된 클래스의 필드로 매핑이 됩니다.
		
		return board;
	}
	
	// # 게시글 삽입 -----------------------------------------------------------------------------------------------------
	public int insertBoard(final BoardDTO board) {
		String sql = "INSERT INTO BOARD(BOARD_NO, TITLE, CONTENT, WRITER, CREATE_DATE, MODIFY_DATE) "
				+ "VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), TO_CHAR(SYSDATE, 'YYYY-MM-DD'))";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			// & PreparedStatementSetter : preparedstatement 인터페이스를 즉석에서 해당 타입 클래스 생성
			// & ? 처리 : setValues 내부에서 ps를 이용해 작성
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setString(3, board.getWriter());
			}
		});
		return result;
	}
	
	// # 게시글 수정 : 쿼리문으로 수정일을 지정할 수 있음
	
	public int updateBoard(final BoardDTO board) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFY_DATE = TO_CHAR(SYSDATE, 'YYYY-MM-DD') WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setInt(3, board.getBoard_no());
			}
			
		});
	
		return result;
	}
	
	// # 게시글 삭제
	public int deleteBoard(final int board_no) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, board_no);
				
			}
		});
	
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
