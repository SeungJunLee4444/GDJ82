package com.gdu.app07.service;

import java.util.List;

import com.gdu.app07.domain.BoardDTO;
import com.gdu.app07.repository.BoardDAO;

import lombok.AllArgsConstructor;

// # @Service : 서비스가 사용하는 @component

//# BoardDAO : @repository로 컨테이너에 자바빈으로 등록
//& @Service
//- 기능 : DAO에 추가하는 @Service
//- 작동원리 : servelt-context.xml에 등록된 <context:component-scan> 태그에 의해서 bean으로 검색
//- 이점 : root-context.xml 또는 @configuration에 @bean으로 등록되지 않아도 컨테이너에 만들어진다



public class BoardServiceImpl implements BoardService {

	
	// # 서비스는 boardDao를 항상 사용한다 ---------------------------------------------
	// 컨테이너
	// @Autowired // * @Autowired : 컨테이너에 생성된 빈 중에서 boardDAO 타입을 가져온다
	

	// ## 생성자의 매개변수 boardDAO dao로 new boardDAO()가 주입되고 있음
	private BoardDAO dao;
	
	public BoardServiceImpl(BoardDAO dao) {
		super();
		this.dao = dao;
	}

	

	
	// && 컨테이너에서 빈을 꺼내쓸 수 있는 방법 ------------------------------------------------------------------------&
	// (1) @autowired
	// (2) getbean
	// 		- xml일 경우 : ClassPathXmlApplicationContext, GenericXmlApplicationContext
	//		- java일 경우 : AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BoardAppContext.class);
	
	
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


	
	
}
