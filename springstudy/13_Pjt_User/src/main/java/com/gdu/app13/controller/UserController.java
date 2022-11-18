package com.gdu.app13.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app13.service.UserService;

import oracle.jdbc.proxy.annotation.Post;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// # 컨트롤러의 모든 요청 이전에 개입한다는 servlet-context.xml에 작성해주기
	// # 인터셉터의 위치 : true를 반환하면 그다음 요청 실시, false를 반환하면 요청 실행안함
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/user/agree")
	public String agree() {
		return "user/agree";
	}
	
	@GetMapping("/user/join/write")
	public String joinWrite(@RequestParam(required=false) String location
			              , @RequestParam(required = false) String promotion
			              , Model model) {
		model.addAttribute("location", location);
		model.addAttribute("promotion", promotion);
		return "user/join";
	}
	
	@ResponseBody
	@GetMapping(value="/user/checkReduceId", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceId(String id){
		return userService.isReduceId(id);
	}
	
	@ResponseBody
	@GetMapping(value="/user/checkReduceEmail", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> checkReduceEmail(String email){
		return userService.isReduceEmail(email);
	}
	
	@ResponseBody
	@GetMapping(value="/user/sendAuthCode", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> sendAuthCode(String email){
		return userService.sendAuthCode(email);
	}
	
	
	// # 회원가입 : 서비스에서 응답처리를 했기 때문에 반환타입이 void
	@PostMapping("/user/join")
	public void join(HttpServletRequest request, HttpServletResponse response) {
		userService.join(request, response);
	}
	
	
	// # 로그인 페이지 이동 : 로그인 후에 돌아갈 url를 전달해줘야한다
	@GetMapping("/user/login/form")
	public String loginForm(HttpServletRequest request, Model model) {
		
		// * 요청 헤더 referer : 이전 페이지의 주소가 저장
		model.addAttribute("url", request.getHeader("referer"));	// * 로그인후 되돌아갈 주소 한 페이지 이전의 url
		return "user/login";										// * 포워드 방식이라 
	}
	
	
	// # 로그인하기
	@PostMapping("/user/login")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		userService.login(request, response);
	}

	
	// # 로그아웃 : session 초기화, 자동로그인도 해제된다
	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return "redirect:/";
	}
	// & 원래는 session을 매개변수로 선언해서 session.invalidate()로 초기화, redirect로 웰컴페이지 돌아가면된다
	// & 로그인 저장, 로그인 유지를 위해서는 request, response를 넣어둬야한다
	
	
	// # 회원탈퇴 : 탈퇴는 로그인 해야 하 ㄹ수 있음
	@PostMapping("/user/retire")
	public void Retire(HttpServletRequest request, HttpServletResponse response) {
		userService.retire(request, response);
	}
	
	// # 마이페이지 이동 : 비밀번호 확인
	@GetMapping("/user/check/form")
	public String requiredLogin_checkForm() {
		return "user/check";
	}
	
	
	// # 비밀번호 재확인
	@ResponseBody
	@PostMapping(value="/user/check/pw", produces="application/json")
	public Map<String, Object> checkPw(HttpServletRequest request) {
		return userService.confirmPassword(request);
	}
	
	// # 상세 페이지로 이동
	@GetMapping("/user/mypage")
	public String requiredLogin_mypage() {
		return "user/mypage";
	}
	
	// # 비밀번호 변경 요청
	@PostMapping("/user/modify/pw")
	public void requiredLogin_modifyPw(HttpServletRequest request, HttpServletResponse response) {
		userService.modifyPassword(request, response);
	}
	
	// # 휴먼계정 복원해야 한다는 경고창 띄우기
	@GetMapping("/user/sleep/display")
	public String sleepDisplay() {
		return "user/sleep";
	}
	
	// # 휴먼계정 일반계정 복원
	@PostMapping("/user/restore")
	public void restore(HttpServletRequest request, HttpServletResponse response) {
		userService.restoreUser(request, response);
	}
	
	
	
	
	
	
	
	
	
	
}
