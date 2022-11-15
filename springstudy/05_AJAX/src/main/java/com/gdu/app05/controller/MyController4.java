package com.gdu.app05.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Contact;
import com.gdu.app05.service.ContactService;

@Controller
public class MyController4 {
	
	// # jsp 이동(포워드)
	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	// # 빈 가져오기
	@Autowired
	ContactService contactService;
	
	// # 서비스 : post 요청
	@ResponseBody
	@PostMapping(value="contact/detail1"
	           , produces=MediaType.APPLICATION_JSON_VALUE)
	public Contact detail1(@RequestBody Contact contact) {  // post 방식으로 넘어온 JSON을 bean에 저장할 수 있다.
		return contactService.execute1(contact);
	}
	
	
	@ResponseBody
	@PostMapping(value="contact/detail2"
	           , produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> detail2(@RequestBody Map<String, Object> map) {  // post 방식으로 넘어온 JSON을 Map에 저장할 수 있다.
		return contactService.execute2(map);
	}
		

}
