package com.gdu.app10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app10.domain.BoardDTO;
import com.gdu.app10.service.BoardService;

@Controller
// # 필드를 이용한 생성자 매개변수로 @autowired : 생성자의 매개변수로 컨테이너의 bean이 자동주입(@autowired)되므로 필드에 @autowired 처리할 필요없음

public class BoardController{
	
	// #
	@Autowired
	private BoardService boardService;

	// # 웰컴페이지 -----------------
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # 전체조회 ====================================================
	
	@GetMapping("board/list")
	public String list(Model model) {	
		// * spring에서는 포워드할 속성을 저장할때 model을 사용한다(jsp는 request)
		model.addAttribute("boards", boardService.findAllBoard());
		return "board/list";					// * board의 detail.jsp로 이동한다
	}
	
	// 상세보기 ==============================================================================================================
	@GetMapping("board/detail")
	public String detail(@RequestParam(value="boardNo", required = false, defaultValue = "0") int boardNo , Model model) {
		
		model.addAttribute("board", boardService.findBoardByNo(boardNo));	// * board_no를 전달해서 특정 board를 가져온다
		
		return "board/detail";
	
	}
	
	
	// # 게시글 추가창 이동 ===================================================================================================
	@GetMapping("board/write")
	public String write() {
		return "board/write";	// - wirte.jsp로 포워드
	}
	
	
	// # 게시글 추가 요청 : 
	@PostMapping("board/add")
	public String add(BoardDTO board) {	 		
		boardService.saveBoard(board);	 
		return "redirect:/board/list";  
		
	}
	
	// # 편집화면 이동 요청 : 상세조회문 재활용(별도의 dao,db접근문 필요x) ======================================================
	@PostMapping("board/edit")
	public String edit(int boardNo
			         , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(boardNo));
		return "board/edit";  
	}
	
	
	// # 수정 요청 ------------------------------------------------------------------
	@PostMapping("board/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		
		return "redirect:/board/detail?boardNo=" + board.getBoardNo();
	}
	
	// # 삭제 요청 ===============================================================================================================
	@PostMapping("board/remove")
	public String remove(int boardNo) {
		boardService.deleteBoard(boardNo);
		return "redirect:/board/list";
	}
	
	
	
	
	

}
