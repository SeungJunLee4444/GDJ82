package com.gdu.app10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app10.domain.BoardDTO;
import com.gdu.app10.mapper.BoardMapper;

// # 서비스 컴포넌트
@Service
public class BoardServiceImpl implements BoardService {

	
	// + dao에서 boardmapper을 사용
	@Autowired
	private BoardMapper mapper;


	
	// # 모두 조회 -----------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> findAllBoard() {
		return mapper.selectAllBoards();
	}

	
	// # 상세 조회 ------------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO findBoardByNo(int boardNo) {
		
		return mapper.selectBoardByNo(boardNo);
	}

	
	// # 게시글 추가(* 경고창 띄우기 : 반환값인 int를 사용한다) -----------------------
	@Override
	public int saveBoard(BoardDTO board) {
		
		return mapper.insertBoard(board); // * getinstance를 쓰지 않아도 @componet로 인해 알아서 싱글턴처리
	}

	
	// # 게시글 수정(* 경고창 띄우기 : 반환값인 int를 사용한다)
	@Override
	public int modifyBoard(BoardDTO board) {
		return mapper.updateBoard(board);
	}

	
	// # 게시글 삭제(* 경고창 띄우기 : 반환값인 int를 사용한다)
	@Override
	public int deleteBoard(int boardNo) {
		
		return mapper.deleteBoard(boardNo);
	}

	
}
