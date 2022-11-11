package com.gdu.app12.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.app12.service.BbsService;

// # Controller : 컨테이너, @controller은 @component의 일종
@Controller
public class BbsController {
	
	// # 등록된 bbsService와 동일한 타입인 BbsServiceImpl를 가져온다 ----------
	@Autowired
	private BbsService bbsService;
	
	
	// [[[ 웰컴페이지 
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	// [[[ 목록조회 서비스 
	// * 조회시 속성에 저장해 가져올때 request대신 model을 사용한다
	@GetMapping("/bbs/list")
	public String list(HttpServletRequest request, Model model) {
		bbsService.findAllBbsList(request, model);
		return "bbs/list";
	}
	
	
	// [[[ 추가페이지 이동
	@GetMapping("/bbs/write")
	public String write() {
		return "bbs/write";
	}
	
	// [[[ 원글 삽입 서비스 : 삽입후 list 재요청(redirect 사용)
	@PostMapping("/bbs/add")
	public String add(HttpServletRequest request) {
		bbsService.addBbs(request);
		return "redirect:/bbs/list";
	}
	
	// [[[ 답글 삽입 서비스
	@PostMapping("/bbs/reply/add")
	public String replyAdd(HttpServletRequest request) {
		bbsService.addReply(request);
		return "redirect:/bbs/list";
	}
	
	
	
	// [[[ 삭제 서비스
	@PostMapping("/bbs/remove")
	public String remove(int bbsNo) {
		bbsService.removeBbs(bbsNo);
		// System.out.println(bbsNo);
		return "redirect:/bbs/list";
	}
	
	
	
	

}
