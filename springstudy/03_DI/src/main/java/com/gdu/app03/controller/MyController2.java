package com.gdu.app03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// # 대표 mapping : board로 시작하는 모든 요청을 이 컨트롤러에서 처리(views의 jsp 폴더명)
@RequestMapping("board")

// # 컨트롤러 에너테이션
@Controller
public class MyController2 {
	
	
	// # 포워드, 리다이렉트의 반환값 차이
	// (1) 포워드 
	// - 용도 : jsp 반환, 해당 jsp로 이동
	// - 형태 : return "패키지/jsp파일명"
	// (2) 리다이렉트 : 맵핑 반환, 새로운 요청 url 반환
	// - 용도 : 맵핑 반환
	// - 형태 : return "redirect:/맵핑"
	
	
	

	
	
	// # <a href="${contextPath}/board/detail?title=공지사항&hit=10">전송</a> 요청
	@GetMapping("detail1")
	public String detail1(HttpServletRequest request) {
		
		String title = request.getParameter("title");
		String hit = request.getParameter("hit");
		
		request.setAttribute("title", title);
		request.setAttribute("hit", hit);
		
		// return "redirect:/board/detail2";	// 해석 : redirect방식 이동, board/detail2 새로운 맵핑 요청
		
		// * 파라미터를 전송하려면 다시 붙여야한다
		 return "redirect:/board/detail2?title=" + title + "&hit=" + hit; 
	}
	
	// * 리다이렉트 이동이기 떄문에 파라미터값은 이동하지않는다
	
	// # @RequestParam을 생략한 방식(controller1 3번쨰 참고)
	@GetMapping("detail2") // <= 위에서 반환된 값
		public String detail2(String title, int hit, Model model) {	// <= 리다이렉트로 파라미터를 다시 전달하면 파라미터를 매개변수로 받는다(hit는 10이니까 int로 받아도 상관없다)
			model.addAttribute("title", title);
			model.addAttribute("hit", hit);
			return "board/detail";
		}
	
	
	
	
	
	

}
