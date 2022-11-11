package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app04.domain.Board;

// # 중간맵핑
@RequestMapping("board")

// # 컨트롤러 
@Controller
public class MyController2 {
	
	// [[[ 리다이렉트 + 요청영역에 속성저장 3가지
	

	  
	 // * 리다이렉트의 값을 유지시켜 보내줄수 있는 객체가 스프링에는 있다
	  
	// 1. 리다이렉트 + request  -------------------------------------------------
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		// * 리다이렉트 특징 : 파라미터를 붙여서 새로운 맵핑 요청
		 return "redirect:/board/detail2?title=" + title + "&hit=" + hit; 
	}
	
	
	
	// 2. 포워드 +  @RequestParam을 생략한 방식  ----------------------------------
	// - 설명 : detail1 요청의 반환으로 실행되는 detail2 맵핑요청
	@GetMapping("detail2") // <= 위에서 반환된 값
		public String detail2(String title, int hit, Model model) {
		// * 리다이렉트 방식으로 전달받은 title과 hit를 매개변수로 받았다
		
		
			model.addAttribute("title", title);
			model.addAttribute("hit", hit);
		// * jsp이동은 포워드방식이기 때문에 model을 이용해 속성으로 저장하였다
			return "board/detail";
		}
	
	
	// 3. RedirectAttributes 객체를 이용한 리다이렉트의 파라미터 전송  + 클래스 객체 사용 ---- *
	// - 클래스 객체 방법 : title과 hit를 필드값으로 지닌 클래스의 객체를 매개변수로 받는법
	// - RedirectAttributes : 반환시 해당 객체에 속성저장을 하면 리다이렉트라도 파라미터 전송이 가능하다
	

	@GetMapping("detail3")
		public String detail3(Board board
							, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("board", board);
		// - 해석 : redirectAttribute 의 속성으로 board를 저장했다
		
		return "redirect:/board/detail4";	// * 새로운 요청에 파라미터를 추가하지 않았음을 주의
		// => redirect:/board/detail4" => detail4 맵핑 요청
	}
	
	// # 단순이동 : detail.jsp로
	@GetMapping("detail4")
		public String detail4() {
		return "board/detail";
	}
	
	// # 속성전달방식
	// 포워드	  : request, model.addAttribute()
	// 리다이렉트 : 직접 파라미터작성, RedirectAttributes.addFlashAttribute
	
	
	/* 
	 * 속성전달방식
	  (1) 포워드	
	  	- 인터페이스	: Model(jsp로 포워드할때 사용함)
	  	- 메서드		: addAttribute()
	  	
	  (2) 리다이렉트
	  	- 인터페이스	: RedirectAttributes
	  	- 메서드		: addFlashAttribute()
	  					* addAttribute()를 사용하면 포워드처럼 동작하기 때문에 리다이렉트 방식에서는 사용x
	 */
	// -------------------------------------------------------------------------------------------
	
	
	
	
	
	

}
