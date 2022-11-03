package com.gdu.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gdu.ex.domain.ContactDTO;
import com.gdu.ex.service.ContactService;

// # 컨트롤러
@Controller
public class ContactController {
	
	// # 서비스 : 서비스에서 @service 사용, @authorwired로 같은 타입의 클래스를 가져온다
	@Autowired 
	ContactService contactService;
	
	
	// # 목록 전체조회 : 웰컴페이지 + 비즈니스 로직 =================================================
	// * select : 포워드 이동, model 객체를 이용한 속성 저장
	// & local:9090/contact01 : 여기서 contact01은 패키지 이름 맨 뒷자리 이름
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("contacts", contactService.fineAllContact());
		return "contact/list";
		
	}
	
	// # 추가화면 이동 요청 : 단순이동 =================================================
	@GetMapping("insertContactPage.do")
	public String insertContactPage() {
		return "insert";
	}
	
	// # 연락처 추가 요청 : 비즈니스 로직 -----------------------------------------------
	// * 추가는 리다이렉트 이동
	@PostMapping("insertContact.do")
	public String addContact(ContactDTO contact) {
		int result = contactService.saveContact(contact);
		return "redirect:/insertResult?res=" + result ;
	}
	
	// # 
	
	
	
	
	

}
