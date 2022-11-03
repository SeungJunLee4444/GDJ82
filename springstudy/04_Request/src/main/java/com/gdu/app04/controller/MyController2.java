package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app04.domain.Board;

// # 대표 mapping : board로 시작하는 모든 요청을 이 컨트롤러에서 처리(views의 jsp 폴더명)
@RequestMapping("board")

// # 컨트롤러 에너테이션
@Controller
public class MyController2 {
	
	
	/* 
	 * & 포워드와 리다이렉트 차이------------------------------------------------&
	  * 포워드는 jsp, 리다이렉트는 맵핑이다(용도가 다르다)------------*
	  (1) 포워드  
	  - return "board/detail";
	  - 설명 : board 폴더 아래 detail.jsp로 forward 하시오
	  
	  (2) 리다이렉트
	  - return "redirect:/board/detail";
	  - 해석 : urlmapping값이 /board/detail인 새로운 요청으로 리다이렉트 하시오
	  * 새로운 요청을 위한 mapping 주소를 요청(리다이렉트의 뒤에 있는건 jsp가 아니다)
	   
	  
	  * 리다이렉트의 값을 유지시켜 보내줄수 있는 객체가 스프링에는 있다
	  ---------------------------------------------------------------------------&
	 */
	
	
	// # <a href="${contextPath}/board/detail?title=공지사항&hit=10">전송</a> 요청
	// - 방법 : request로 받기
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
	
	
	// # 리다이렉트 이동 -------------------------------------------------------------------------
	// * 리다이렉트 이동이기 떄문에 파라미터값은 이동하지않는다
	
	// # @RequestParam을 생략한 방식(controller1 3번쨰 참고)
	// - 방법 : 변수로 받기
	@GetMapping("detail2") // <= 위에서 반환된 값
		public String detail2(String title, int hit, Model model) {	// <= 리다이렉트로 파라미터를 다시 전달하면 파라미터를 매개변수로 받는다(hit는 10이니까 int로 받아도 상관없다)
			model.addAttribute("title", title);
			model.addAttribute("hit", hit);
			return "board/detail";
		}
	
	
	// # 리다이렉트의 새로운 기능 ----------------------------------------------------------------
	// - 설명 : 스프링에서 리다이렉트는 파라미터 전달없이도 값을 전달할 수 있다 *
	// - 방법 : RedirectAttributes의 속성 사용
	
	// - jsp : <a href="${contextPath}/board/detail3?title=공지사항&hit=10">전송</a> 요청
	// - 방법 : 객체로 받기
	// * 객체로 받기 조건 : title과 hit를 필드값으로 지닌 클래스가 존재해야한다
	@GetMapping("detail3")
		public String detail3(Board board
							, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("board", board);
		// - 해석 : redirectAttribute 의 속성으로 board를 저장했다
		
		return "redirect:/board/detail4";	// * 새로운 요청에 파라미터를 추가하지 않았음을 주의
		// => redirect:/board/detail4" => detail4 맵핑 요청
	}
	
	@GetMapping("detail4")
		public String detail4() {
		return "board/detail";
	}
	// => detail.jsp로 포워드 이동
	
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
