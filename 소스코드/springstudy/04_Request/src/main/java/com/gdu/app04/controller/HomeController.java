package com.gdu.app04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// # @Controller : 자바파일을 컨트롤러로 만들어준다
@Controller
public class HomeController {
	
	// # 웰컴페이지 생성 -----------------------------------------------------/
	// * GET요청이면 @GetMapping, POST 요청이면 @PostMapping 사용

	@GetMapping(value="/")
	public String welcome() {
		return "index";
	}
	// => 
	// 반환한 index를 뷰 컨트롤러에서 처리(절대경로, 확장자가 붙여짐)
	
	//------------------------------------------------------------------------/
	
}
	
