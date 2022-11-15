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
	
	// # 포워드 이동 : board.jsp
	@GetMapping("board") 
	public String welcome() {
		return "board";				
	}
	
	
	// # 빈 가져오기 : 자바 컨테이너에서 호출
	@Autowired
	BoardService boardService;
	
	// * ResponseEntity : ajax 응답 전용 객체
	// - 기능 responseEntity<>는 응답에서 map<>을 대신한다
	
	// # ajax 응답 : json타입 데이터로 응답받기
	@ResponseBody
	@GetMapping(value="board/detail1"
				, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Board> detail1(HttpServletRequest request) {
		return boardService.execute1(request);
	}
	
	// # @RequestParam 사용 파라미터 처리 
	// * ResponseEntity : ajax 응답에서 map<>을 대신한다
	@ResponseBody
	@GetMapping("board/detail2")// * produce가 없음, 반환값인 responseEntity에 관련코드를 적었음(httpheader이용)?
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
