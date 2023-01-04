package com.gdu.app04.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gdu.app04.domain.Member;

// # @RequestMapping : 중간맵핑 별도로 작성
@RequestMapping("member")		 

@Controller
public class MyController1 {
	
	
	// [[[ 요청에 저장하는 법 3가지
	
	// (1) HttpServletRequest request : 기존방식
	// - 파라미터값 : request 객체에 저장
	
	// (2) @RequestParam(value="파라미터명", required=true/false, defaultValue="디폴트값") String 파라미터객체
	// - required : 파라미터의 필수여부, required=false는 null값이어도 처리하겠다는 뜻 (기본값은 true)
	// - defaultValue : 디폴트값, 전달받은 값이 없을경우 부여하는 파라미터값
	// - 파라미터값 : 매개변수의 개별 객체들에 저장
	

	// (3) DTO 객체 사용
	// - 전달하려는 파라미터를 필드로 지닌 클래스를 매개변수로 사용한 방법
	// * @ModelAttribute(value="속성명") 클래스 객체명(=속성값)
	// - 기능 : 매개변수란에서 바로 속성으로 저장할 수 있다
	
	
	// * (1) request가 추천되는 이유 : 다른 요청방법은 제약이 있음 ex) session 사용시
	// ]]] 스프링은 파라미터로 null값이 전달된다 해도 null 예외로 인한 오류가 발생하지않는다
	
	

	
	
	
	// 1. HttpServletRequest 사용  ----------------------
	// # member/detail1 맵핑 요청
	@GetMapping("detail1")		
	public String detail1(HttpServletRequest request) {	
		
		// # 요청 파라미터								
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// # 전달받은 파라미터를 DTO에 저장
		Member member = new Member(id, pw);	
		
		// # DTO를 request 영역에 속성으로 저장
		request.setAttribute("member", member);			
		
		// # 경로반환 : 포워드
		return "member/detail";	 
	}
	
	
	
	// 2. @RequestParam : 일반버전 -------------------------------------------------------------------
	// # member/detail2 맵핑 요청
	@GetMapping("detail2")
	public String detail2(@RequestParam(value="id", required=false, defaultValue="master") String id	
						, @RequestParam(value="pw", required=false, defaultValue="1111") String pw					
						, Model model) {
		Member member = new Member(id,pw);	
		model.addAttribute(member);
		return "member/detail";
		// [[[ 포워드와 Model
		// - 스프링에서는 포워드시 request 대신 model에 속성을 저장한다 
		
	}
	
	
	// 3. @RequestParam(2) : 생략버전  ------------------------------------------------------------------- @RequestParam은 생략가능하다
	// # member/detail3 맵핑 요청
	
	@GetMapping("detail3")
	public String detail3(String id, String pw, Model model) {
		Member member = new Member(id, pw);
		model.addAttribute("member", member);
		return "member/detail";
	}
	
	
	// 4. DTO 객체 사용  ---------------------------------------------------------------------------
	// * get, post 방식은 동일한 맵핑으로 요청해도 별도의 요청으로 진행된다
	// 1) member/detail4 맵핑 get 요청
	@GetMapping("detail4")
	public String getDetail4(Member member	// * 파라미터 id,pw가 member에 자동으로 담겨져있음
							, Model model) {
		model.addAttribute("member", member);
		return "member/detail";
	}
	
	
	
	// 2) member/detail4 맵핑 post 요청
	@PostMapping("detail4")				
	public String postDetail4(@ModelAttribute(value="member") Member member	
							, Model model) {
	// @ModelAttribute : value로 속성명을 정하고, 객체를 속성값으로 저장해주는 어노테이션
	// // 해석 : 파라미터 id,pw로 member을 만들고, model에 member이라는 이름의 속성으로 저장하시오 
		return "member/detail";
	}
}
			
		
		
		
	
	
	

