package com.gdu.app05.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

public class BoardServiceImpl implements BoardService {

	// # board 서비스
	
	// * ResponseEntity : 스프링의 ajax 응답 전용 객체 --------------------------*
	// - 형태 : new ResponseEntity<T>(HttpHeaders header, HttpStatus status)
	// - 구성 
	// (1) T body : 실제 응답할 데이터
	// (2) HttpHeaders header : 응답헤더
	// - 기능 : 요청과 응답에 부가적인 정보 저장 가능
	// (3) HttpStatus status : 응답코드(200,300,400..)
	
	// 1) request로 받아오기 ----------------------------------------------------
	
	@Override
	public ResponseEntity<Board> execute1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board board = null;
		
		ResponseEntity<Board> entity = null;
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(board, HttpStatus.INTERNAL_SERVER_ERROR);	// INTERNAL_SERVER_ERROR : 500번, 자바코드 오류		=> ajax의 error에서 처리
		} else {
			board = new Board(title, content);	// * 오류 : 전달할 board가 없으니 당연히 응답을 못하지--*
			entity = new ResponseEntity<Board>(board, HttpStatus.OK);	// 성공 : board가 entity에 저장되서 응답할 데이터로 전송된다			/	HttpStatus.OK => ajax의 success에서 처리
		}
		return entity;
	}

	// 2) 변수 하나씩 받아오기 ----------------------------------------------------
	@Override
	public ResponseEntity<Board> execute2(String title, String content) {
		
		ResponseEntity<Board> entity = null;
		
		// * httpheader 사용 : 요청응답에 부가적 정보 저장
		// - 용도 : 응답의 컨텐츠 타입을 담는데 사용하였다
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", MediaType.APPLICATION_JSON_VALUE);
		
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			entity = new ResponseEntity<Board>(new Board(title, content), header, HttpStatus.OK);  
		}
		
		return entity;	// * 오류 : 반환을했어야지--*
	
	}

	// 3) 게사판 통째로 받아오기 ----------------------------------------------------
	@Override
	public ResponseEntity<Board> execute3(Board board) {
		ResponseEntity<Board> entity = null;
		
		HttpHeaders header = new HttpHeaders();
		header.add("content-type", MediaType.APPLICATION_JSON_VALUE);
		
		if(board.getTitle().isEmpty()) {
			entity = new ResponseEntity<Board>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			entity = new ResponseEntity<Board>(board, header, HttpStatus.OK);
		}
		return entity;	// * 응답에서 title과 content는 resData.title, content 식으로 꺼내쓰고 있기때문에,
						// 전달은 board로 해야한다
	}
	
	
	
	
	

}
