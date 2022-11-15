package com.gdu.app05.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

public class BoardServiceImpl implements BoardService {

	// # board 서비스
	
	// * ResponseEntity : ajax응답 전용객체
	// - 매개변수 : HttpHeaders header, HttpStatus status
	// - 형태 : new ResponseEntity<T>(HttpHeaders header(본문내용), HttpStatus status(응답코드))
	//	(1) body : 실제 응답할 데이터
	//	(2) header : 응답 헤더
	//	(3) status : 응답코드
	
	
	// 1) request로 받아오기 ----------------------------------------------------
	
	@Override
	public ResponseEntity<Board> execute1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board board = null;
		
		// # 실패, 성공응답
		ResponseEntity<Board> entity = null;
		if(title.isEmpty()) {
			entity = new ResponseEntity<Board>(board, HttpStatus.INTERNAL_SERVER_ERROR);	// 실패 : INTERNAL_SERVER_ERROR : 500번, 자바코드 오류		=> ajax의 error에서 처리
		} else {
			board = new Board(title, content);	
			entity = new ResponseEntity<Board>(board, HttpStatus.OK);						// 성공 : board가 entity에 저장되서 응답할 데이터로 전송된다			/	HttpStatus.OK => ajax의 success에서 처리
		}
		return entity;
	}

	
	// # 서비스2 : @RequestParam로 파라미터 전달 + 헤더 영역에 ajax 응답데이터 타입 저장 + ResponseEntity 반환
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

	
	// # 서비스3 : 클래스객체로 파라미터 전달 + 헤더 영역에 ajax 응답데이터 타입 저장 + ResponseEntity 반환
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
