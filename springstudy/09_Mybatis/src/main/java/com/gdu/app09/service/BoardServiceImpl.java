package com.gdu.app09.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app09.domain.BoardDTO;
import com.gdu.app09.repository.BoardDAO;

// # 서비스 컴포넌트
@Service
public class BoardServiceImpl implements BoardService {

	
	@Autowired
	private BoardDAO dao;


	
	// # 모두 조회 -----------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> findAllBoard() {
		return dao.selectAllBoard();
	}

	
	// # 상세 조회 ------------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO findBoardByNo(int boardNo) {
		
		return dao.selectBoardByNo(boardNo);
	}

	
	// # 게시글 추가(* 경고창 띄우기 : 반환값인 int를 사용한다) -----------------------
	@Override
	public int saveBoard(BoardDTO board) {
		
		return dao.insertBoard(board); // * getinstance를 쓰지 않아도 @componet로 인해 알아서 싱글턴처리
	}

	
	// # 게시글 수정(* 경고창 띄우기 : 반환값인 int를 사용한다)
	@Override
	public int modifyBoard(BoardDTO board) {
		return dao.updateBoard(board);
	}

	
	// # 게시글 삭제(* 경고창 띄우기 : 반환값인 int를 사용한다)
	@Override
	public int deleteBoard(int boardNo) {
		
		return dao.deleteBoard(boardNo);
	}

	
}
