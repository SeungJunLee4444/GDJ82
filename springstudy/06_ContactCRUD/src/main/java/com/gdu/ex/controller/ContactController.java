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
	
	// # 서비스 빈 가져오기
	@Autowired 
	ContactService contactService;
	
	// # 웰컴페이지
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("contacts", contactService.fineAllContact());
		return "contact/list";
		
	}
	
	// # 추가화면 이동 
	@GetMapping("insertContactPage.do")
	public String insertContactPage() {
		return "insert";
	}
	
	// # 연락처 추가
	// * 추가는 리다이렉트 이동
	@PostMapping("insertContact.do")
	public String addContact(ContactDTO contact) {
		int result = contactService.saveContact(contact);
		return "redirect:/insertResult?res=" + result ;
	}
	
	
	
	
	
	
	

}
