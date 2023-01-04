package com.gdu.app07.service;

import java.util.List;

import com.gdu.app07.domain.BoardDTO;

public interface BoardService {
	
	// * 서비스계층의 이름은 사용자 친화적으로 -------------*

	// # 모두 조회
	public List<BoardDTO> findAllBoard();
	
	// # 상세 조회
	public BoardDTO	findBoardByNo(int board_no);
	
	// # 게시글 추가(* 경고창 띄우기 : 반환값인 int를 사용한다)
	public int saveBoard(BoardDTO board);
	
	// # 게시글 추가(* 경고창 띄우기 : 반환값인 int를 사용한다)
	public int modifyBoard(BoardDTO board);
	
	// # 게시글 추가(* 경고창 띄우기 : 반환값인 int를 사용한다)
	public int deleteBoard(int board_no);
}
