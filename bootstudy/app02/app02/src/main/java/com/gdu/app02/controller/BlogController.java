package com.gdu.app02.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app02.service.BlogService;

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
	@PostMapping("/blog/add")
	public void add(HttpServletRequest request, HttpServletResponse response) {
		blogService.saveBlog(request, response);
	}
	
	// 3. service : 이미지 첨부
	// * produces : ajax 응답
	// * MultipartHttpServletRequest : 이미지 첨부가 가능한 request
	@ResponseBody
	@PostMapping(value="/blog/uploadImage", produces="application/json")
	public Map<String, Object> uploadImage(MultipartHttpServletRequest multipartRequest) {
			return blogService.saveSummernoteImage(multipartRequest);
	}
	
	// 4. service : 상세
	// (1) 조회수 증가시키기 요청을 받아 성공하면 (2) 상세보기 요청을 리다이렉트한다
	
	// (1) 조회수 증가시키기
	@GetMapping("/blog/increase/Hit")
	public String increaseHit(@RequestParam(value="blogNo", required=false, defaultValue ="0") int blogNo) {
		int result = blogService.increaseBlogHit(blogNo);
		// * 조회수 증가에 성공하면 상세보기에 이동
		if(result > 0) {
			return "redirect:/blog/detail?blogNo=" + blogNo; // * 상세이동 요청을 위한 blogNo를 전달
		// * 실패하면 목록으로 돌아감
		} else {
			return "redirect:/blog/list";	
		}
	}
	
	// (2) 상세보기 : 수정화면 이동시에도 사용
	// * model을 서비스가 아닌 컨트롤러에서 처리한 이유 ----? 수정창과 상세보기?
	// - 이유 : 상세보기와 수정창 이동은 과정이 같다
	// - 
	
	@GetMapping("/blog/detail")
	public String detail(@RequestParam(value="blogNo", required=false, defaultValue="0") int blogNo, Model model) {
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));
		// 원래는 모델에 저장하지않고 서비스만 사용했음
		// 서비스의 매개변수로 blogNo와 model을 전달헤서 서비스에서 model처리
		return "blog/detail";
	}
	
	
	// # 블로그 수정
	// 1. move : 수정화면 이동
	@PostMapping("/blog/edit")
	public String edit(int blogNo, Model model) {
		model.addAttribute("blog", blogService.getBlogByNo(blogNo));
		return "blog/edit";
	}
	
	// 2. service : 수정
	// * 수정후 상세보기로 이동
	@PostMapping("/blog/modify")
	public void modify(HttpServletRequest request, HttpServletResponse response) {
		blogService.modifyBlog(request, response);
	}
	
	// # 삭제
	// * 삭제후 목록보기로 이동
	@PostMapping("/blog/remove")
	public void remove(HttpServletRequest request, HttpServletResponse response) {
		blogService.removeBlog(request, response);
	}
	
}
