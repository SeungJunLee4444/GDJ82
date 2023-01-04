package com.gdu.app05.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.app05.domain.Board;

public interface BoardService {
	
	// # 게시판 인터페이스
	
	
	// 1) request로 받아오기
	public ResponseEntity<Board> execute1(HttpServletRequest request);
	
	// * ResponseEntity : 스프링의 ajax 응답 전용 객체 ----*
	
	// 2) 변수 하나씩 받아오기
	public ResponseEntity<Board> execute2(String title, String content);
	
	
	// 3) 게사판 통째로 받아오기
	public ResponseEntity<Board> execute3(Board board);

}
