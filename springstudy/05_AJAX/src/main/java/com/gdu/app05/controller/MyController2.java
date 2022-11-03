package com.gdu.app05.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Board;
import com.gdu.app05.service.BoardService;

@Controller
public class MyController2 {
	
	// # 웰컴페이지 생성 : 이미 index가 있으니, index에서 board 요청을 받으면 board로 이동할 수 있게 포워드 이동 생성
	@GetMapping("board") 
	public String welcome() {
		return "board";				// => board.jsp
	}
	
	// # BoardServletImpl의 execute() 메서드 호출 -----------------------------------------------------------
	// (1) 컨테이너에 등록(5장실습은 자바컨테이너 사용)
	// (2) 컨트롤러에 @Autowired 사용
	@Autowired
	BoardService boardService;
	// - 기능 : boardservice와 비슷한 타입의 자바빈을 가져온다 
	// - 결과 : boardserviceimpl 가져온다
	
	// * ResponseEntity : ajax 응답 전용 객체
	// - 기능 responseEntity<>는 응답에서 map<>을 대신한다
	
	// # request 파라미터 처리 ---------------------------------------------------------------------------------
	@ResponseBody
	@GetMapping(value="board/detail1"
				, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Board> detail1(HttpServletRequest request) {
		return boardService.execute1(request);
	}
	
	// # @RequestParam 사용 파라미터 처리 --------------------------------------------------------------------
	@ResponseBody
	@GetMapping("board/detail2")
							// * produce가 없음, 반환값인 responseEntity에 관련코드를 적었음(httpheader이용)?
	public ResponseEntity<Board> detail2(@RequestParam(value="title") String title,
										@RequestParam(value="content") String content) {
		return boardService.execute2(title, content);		// * 성공시 title, content이 저장된 entity 실패시 오류숫자가 저장된 entity를 반환
		
	}
	
	// # 객체 사용 파라미터
	
	@ResponseBody
	@GetMapping("board/detail3")
	public ResponseEntity<Board> detail3(Board board) {			// * responseEntity<>는 응답에서 map<>을 대신한다
	
		
		return  boardService.execute3(board);	// entity타입이 반환되서 
	}
	
	

}
