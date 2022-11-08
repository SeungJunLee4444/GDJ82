package com.gdu.app09.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gdu.app09.domain.BoardDTO;

// # BoardDAO : 컨테이너에 저장, 싱글턴 처리
@Repository 	// * @component는 servlet-context.xml에 빈이 저장된다
public class BoardDAO {
	
	
	// # mybatis dao 
	
	// # SqlSessionTemplate : mybatis에서 처리하는 매퍼 처리 클래스
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	String mapper = "mybatis.mapper.board";
	
	// # 전체조회 ------------------------------------------------------------------------------------------------------
	public List<BoardDTO> selectAllBoard() {
	
		return sqlSessionTemplate.selectList(mapper + ".selectAllBoards");
		
	}
	
	// # 상세조회 ------------------------------------------------------------------------------------------------------
	public BoardDTO selectBoardByNo(int boardNo) {	

		
		return sqlSessionTemplate.selectOne(mapper + ".selectBoardByNo");
	}
	
	// # 게시글 삽입 -----------------------------------------------------------------------------------------------------
	public int insertBoard(BoardDTO board) {
	
		return sqlSessionTemplate.insert(mapper + "insertBoard" + board);
	}
	
	// # 게시글 수정 : 쿼리문으로 수정일을 지정할 수 있음
	
	public int updateBoard(BoardDTO board) {
	
	
		return sqlSessionTemplate.update(mapper + "insertBoard" + board);
	}
	
	// # 게시글 삭제
	public int deleteBoard(int boardNo) {
	
		return sqlSessionTemplate.delete(mapper + "insertBoard" + boardNo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
