package com.gdu.app11.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app11.service.EmpService;

// # Controller : 컨테이너, @controller은 @component의 일종
@Controller
public class EmpController {
	
	// # 서비스 컴포넌트 가져오기
	@Autowired
	private EmpService empService;
	
	// # 웰컴페이지 : welcome.jsp -------------------
	@GetMapping("/")
	public String welcome() {
		return "welcome";
	}
	
	// # 직원목록 요청 : 비즈니스 로직 --------------
	@GetMapping("/emp/list")
	public String list(HttpServletRequest request, Model model) {
		empService.findAllEmployees(request, model);
		
		return "employee/list";					// * 폴더앞 슬래시 : /는 써도되고 생략해도된다
	}
	
	
	// # 검색 요청
	@GetMapping("/emp/search")
	public String search(HttpServletRequest request, Model model) {
		empService.findEmployees(request, model);
		return "employee/list";
	}
	
	// # 이메일 자동완성 : ajax 처리 + json 처리
	@ResponseBody	// ajax 응답처리
	@GetMapping(value="/emp/autoComplete", produces="application/json")	// jsp가 아닌 json 응답처리
	public Map<String, Object> autoComplete(HttpServletRequest request) {
		return empService.findAutoCompleteList(request);
	}
	
	// * 로그 : 맵핑값이 잘못되면 제대로 파라미터가 전달되지 않는다
	
	
	
	

}
