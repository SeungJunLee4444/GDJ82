package com.gdu.res.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member/handle")
	public String handle() {
		return "member/handle";
	}

}
