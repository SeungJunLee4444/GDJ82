package com.gdu.contact.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.contact.service.ContactService;

@Controller
public class ContactController {

	
	// # ContactController ================================================================
	// - 대부분 서비스 호출의 기능 수행, 비즈니스 처리를 서비스가 담당
	// - 조회처리는 매개변수 model 사용, 삽입, 수정, 삭제는 매개변수로 request,response 사용
	
	// * select : 조회후 포워드 이동하기 위해 매개변수를 model로 쓰기
	// * 삽입, 수정, 삭제 : 리다이렉트 이동
	
	
	@Autowired
	private ContactService contactService;
	
	// & url에 있는 맨 마지막은 맵핑값이다 - 실제 경로와 구분하기 -- *
	
	// # 목록 : 웰컴페이지 + 리스트 전체 조회 서비스호출
	// * 두개 이상의 맵핑 처리
	@GetMapping({"/", "card/list"})  // 2가지 이상의 매핑 처리 방법
	public String list(Model model) {
		contactService.findAllContacts(model);  // model에 목록을 저장할 수 있도록 서비스에게 model을 전달합니다.
		return "contact/list";
	}
	
	@GetMapping("card/write")
	public String write() {
		return "contact/write";
	}
	
	@PostMapping("card/register")
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 서비스에게 request와 response를 모두 전달하면 요청 파라미터와 응답 결과를 모두 처리할 수 있습니다. 
		contactService.register(request, response); 
	}
	
	@GetMapping("card/detail")
	public String detail(HttpServletRequest request, Model model) {
		model.addAttribute("request", request); // model에 request를 저장해 두었다가 다시 꺼낼 수 있습니다.
		contactService.findContactByNo(model);  // model에 목록을 저장할 수 있도록 서비스에게 model을 전달합니다. 이 model에는 request도 저장되어 있습니다.
		return "contact/detail";
	}
	
	
	@PostMapping("card/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 서비스에게 request와 response를 모두 전달하면 요청 파라미터와 응답 결과를 모두 처리할 수 있습니다.
		contactService.modify(request, response);
	}
	
	
	@PostMapping("card/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 서비스에게 request와 response를 모두 전달하면 요청 파라미터와 응답 결과를 모두 처리할 수 있습니다.
		contactService.remove(request, response);
	}
	
}
