package com.gdu.app08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app08.domain.BoardDTO;
import com.gdu.app08.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller

// # @autowired 대신 생성자 에너테이션 사용 : 필드에 별도의 @autowired를 쓸 필요 없다
@AllArgsConstructor
@Data
public class BoardController{
	
	
	private BoardService boardService;

	// # 웰컴페이지 
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # 전체조회 
	
	@GetMapping("board/list")
	public String list(Model model) {	
		
		model.addAttribute("boards", boardService.findAllBoard());
		return "board/list";					
	}
	
	// 상세보기 
	@GetMapping("board/detail")
	public String detail(@RequestParam(value="board_no", required = false, defaultValue = "0") int board_no , Model model) {
		
		model.addAttribute("board", boardService.findBoardByNo(board_no));	
		
		return "board/detail";
	
	}
	
	
	// # 게시글 추가창 이동 
	@GetMapping("board/write")
	public String write() {
		return "board/write";	
	}
	
	
	// # 게시글 추가 요청 : 
	@PostMapping("board/add")
	public String add(BoardDTO board) {	 		
		boardService.saveBoard(board);	 
		return "redirect:/board/list";  
		
	}
	
	// # 편집화면 이동 요청 
	@PostMapping("board/edit")
	public String edit(int board_no
			         , Model model) {
		model.addAttribute("board", boardService.findBoardByNo(board_no));
		return "board/edit";  
	}
	
	
	// # 수정 요청
	@PostMapping("board/modify")
	public String modify(BoardDTO board) {
		boardService.modifyBoard(board);
		
		return "redirect:/board/detail?board_no=" + board.getBoard_no();
	}
	
	// # 삭제 요청
	@PostMapping("board/remove")
	public String remove(int board_no) {
		boardService.deleteBoard(board_no);
		return "redirect:/board/list";
	}
	
	
	// # 트랜잭션 aop를 확인하기위해 만든 서비스 
	// * 시험하는법 : 직접 url창에 해당 mapping을 입력해 요청
	
	// 트랜잭션 확인을 위해서 testTransaction() 메소드를 호출하는 매핑 작성
	// 동작 확인을 위해서 http://localhost:9090/app08/brd/transaction 입력
	// BoardServiceImpl의 testTransaction() 메소드가 동작하는데 이 메소드에는 오류가 있기 때문에 예외가 발생함
	// Board 테이블을 열고, 트랜잭션제목, 트랜잭션내용, 트랜잭션작성자 내용을 가진 행(Row)가 있는지 확인 -> 없으면 트랜잭션이 잘 동작한 것임
	@GetMapping("board/transacion")
	public String transaction() {
		boardService.testTransaction();
		return "redirect:/board/list";
	}
	
	
	
	

}
