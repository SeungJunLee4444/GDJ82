package com.gdu.app15.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app15.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # 목록 : model에 request 담아서 전달
	// - 장점 : 모든 매개변수를 model로 통일할 수 있음
	// * model에 request뿐 아니라 response도 담아서 쓸 수 있음
	@GetMapping("/blog/list")
	public String list(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);	// model에 request 저장
		blogService.getBlogList(model);			// request가 담긴 model을 서비스로 전달
		return "blog/list";
	}
	
	
	// # 작성
	// 1. 화면이동
	@GetMapping("/blog/write")
	public String write() {
		return "blog/write";
	}
	
	// 2. 작성 서비스
	
	
}
