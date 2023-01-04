package com.gdu.app06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.service.BoardService;

@Controller
public class BoardController{
	
	
	
	// # 서비스 빈
	@Autowired	
	private BoardService boardService;
	
	// # 웰컴페이지 
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # 전체조회 
	
	@GetMapping("board/list")
	public String list(Model model) {	
		// * spring에서는 포워드할 속성을 저장할때 model을 사용한다(jsp는 request)
		model.addAttribute("boards", boardService.findAllBoard());
		return "board/list";					// * board의 detail.jsp로 이동한다
	}
	
	// 상세보기 
	@GetMapping("board/detail")
	public String detail(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no , Model model) {
		
		model.addAttribute("board", boardService.findBoardByNo(board_no));	// * board_no를 전달해서 특정 board를 가져온다
		
		return "board/detail";
	
	}
	
	
	// # 게시글 추가창 이동 ===================================================================================================
	@GetMapping("board/write")
	public String write() {
		return "board/write";	// - wirte.jsp로 포워드
	}
	
	
	// # 게시글 추가 요청 : * insert, update, delete는 이동시 리다이렉트 ----------
	@PostMapping("board/add")
	public String add(BoardDTO board) {	 // * 파라미터 처리방법 3번째 : write.jsp에서 전달된 파라미터가 board 객체에 전달된다
		
		boardService.saveBoard(board);	 // - saveBoard() 부터 0또는 1이 반환
		return "redirect:/board/list";  
		// * redirect 
		// - 뒤에는 jsp같은 경로가 아닌 요청(맵핑)을 받아야한다
		// - redirect: 뒤에 /(슬래쉬) 꼭 붙이기
	}
	
	// # 편집화면 이동 요청 : 상세조회문 재활용(별도의 dao,db접근문 필요x) ======================================================
	@PostMapping("board/edit")
	public String edit(int board_no
			         , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/edit";  // board 폴더의 edit.jsp로 forward 
	}
	// * 단순 jsp 이동은 포워드(model을 사용), 삽입,수정,삭제는 리다이렉트
	
	// # 수정 요청 ------------------------------------------------------------------
	@PostMapping("board/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		// * 상세보기는 board_no를 파라미터로 보내줘야한다 ---*
		return "redirect:/board/detail?board_no=" + board.getBoard_no();
	}
	
	// # 삭제 요청 ===============================================================================================================
	@PostMapping("board/remove")
	public String remove(int board_no) {
		boardService.deleteBoard(board_no);
		return "redirect:/board/list";
	}
	
	
	

}
