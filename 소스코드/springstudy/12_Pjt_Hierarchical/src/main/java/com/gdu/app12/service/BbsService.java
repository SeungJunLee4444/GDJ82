package com.gdu.app12.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface BbsService {
	
	// * request를 사용한 이유 : 필드의 ip를 얻기 위해서 사용했다
	
	// 게시글 조회 서비스
	public void findAllBbsList(HttpServletRequest request, Model model); 
	
	// 원글 삽입 서비스
	public int addBbs(HttpServletRequest request);
	
	// 답글 삽입 서비스
	public int addReply(HttpServletRequest request);
	
	// 삭제 서비스
	public int removeBbs(int bbsNo);
	

}
