
package com.gdu.app14.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.service.UploadService;
@Controller
public class UploadController {

	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// # 목록창 이동 : list.jsp로 uploadList 전달 => ${uploadList}
	@GetMapping("/upload/list")
	public String list(Model model) {
		model.addAttribute("uploadList", uploadService.getUploadList());	
		
		return "/upload/list";
	}
	
	// # 게시글 추가 창 이동
	@GetMapping("/upload/write")
	public String write() {
		return "/upload/write";
	}
	
	// # 게시글 추가 로직 : 
	// * 첨부파일을 삽입할 때는 MultipartHttpServletRequest 사용
	@PostMapping("/upload/add")
	public void add(MultipartHttpServletRequest multipartHttpRequest, HttpServletResponse response) {
		uploadService.save(multipartHttpRequest, response);
	}
	
	
}
