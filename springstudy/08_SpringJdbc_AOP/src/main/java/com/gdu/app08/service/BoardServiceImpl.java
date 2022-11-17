package com.gdu.app08.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gdu.app08.domain.BoardDTO;
import com.gdu.app08.repository.BoardDAO;

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
	public BoardDTO findBoardByNo(int board_no) {
		
		return dao.selectBoardByNo(board_no);
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
	public int deleteBoard(int board_no) {
		
		return dao.deleteBoard(board_no);
	}

	
	// # 트랜잭션 : 한 메서드 안에 두개의 비즈니스 로직이 이루어질때 트랜잭션이 작동한다
	// - 특징 : 둘중 하나만 실패해도 실패로 처리
	
	@Transactional
	@Override
	public void testTransaction() {
		// & 성공
		dao.insertBoard(new BoardDTO(0, "트랙잭션제목2", "트랜잭션내용2", "트랜잭션작성자2", null, null));
			
		// & 실패
		dao.insertBoard(new BoardDTO());	// => nullpointerexception이 떨어질 것으로 예상

		// & 트랜잭션이 정상적으로 동작한다면 둘다 실패해야 한다
	
	}
	
}
