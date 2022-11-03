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

// # 중간 매핑(member)을 빼내기--------------------------------------#
@RequestMapping("member")		 // * 해석 : urlMapping이 member로 시작하는 모든 요청을 처리하는 컨트롤러다

@Controller
public class MyController1 {
	
	// & 정리 #########################################################################################
	// & 요청에 저장하는 법 정리
	// (1) request 방법
	// (2) @RequestParam 에너테이션 사용하는 방법 + 개별 변수로 받는다
	// 		=> @RequestParam은 생략 가능하다
	// (3) 파라미터를 저장할수 있는 객체(dto?) 사용
	
	// * (1)번을 추천한다 : 다른것들은 스프링에서 사용하면 제약이 생긴다
	// ex) session
	
	// * forward할 데이터는 model에 담아둔다
	//####################################################################################################################
	
	
	
	
	// #) 1 <a href=${contextPath}/member/detail"> 요청을 받는곳 --------------------------------------------------------/
	// = 기존의 파라미터 요청받는법

	@GetMapping("detail1")		 // * 해석 : member/detail 요청을 처리해라
	
	public String detail1(HttpServletRequest request) {	// # 요청보낸 파라미터는 request 매개변수에 저장된다
		
		// # 요청 파라미터								// # 그래서 request에서 파라미터를 호출한다
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// # request 속성값으로 저장하여 detail.jsp로 포워드 이동
		Member member = new Member(id, pw);				// dto
		request.setAttribute("member", member);			// member을 속성에 저장해서 전달
		
		return "member/detail";	 // * 해석 : member 폴더 아래 detail.jsp로 forward 하시오 
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------
	// [location.href 전송]
	
	// #) 2 location.href='${contextPath}/member/detail2?id=admin&pw=1234'; 요청받는곳 -----------------------------------/
	// = 새로운 파라미터 요청받는법
	
		// # 맵핑 + 요청 파라미터 처리
		
	
		// # @RequestParam 속성-------------------------------------------------------------------------------------#
		// 기존 value = 파라미터의 이름
		// (1) required=false : null값이여도 문제없이 진행하겠다는 뜻, 파라미터의 필수여부를 처리할 수 있다(jsp의 optional null처리)
		// (2) defaultValue : 파라미터가 없을때 사용될 디폴트값
		//----------------------------------------------------------------------------------------------------#
	
	@GetMapping("detail2")
	public String detail2(@RequestParam(value="id", required=false, defaultValue="master") String id	// 해석 : 파라미터 id를 String id에 저장하시오
						, @RequestParam(value="pw", required=false, defaultValue="1111") String pw					// 해석 : 파라미터 pw를 String pw에 저장하시오
						, Model model) {
		Member member = new Member(id,pw);	// * 스프링에서는 forward할 때 request가 아닌 model에 데이터를 저장한다(보안이 향상)
		
		// # model에 저장 : request에 실제로 저장되나, request를 호출하는것보다 model을 호출하는것이 더 보안에 유리하다(실제로는 request에 저장된다)-----------*
		model.addAttribute(member);
		
		return "member/detail";
	}
	
	// #) 3 location.href='${contextPath}/member/detail3?id=admin&pw=1234';------------------------------------------------/
	// * 2)의 @RequestParam은 생략가능하다--------------------------------------------------------------------*
	@GetMapping("detail3")
	public String detail3(String id, String pw, Model model) {
		Member member = new Member(id, pw);
		model.addAttribute("member", member);
		return "member/detail";
	}
	// => 결과 : 파라미터가 없는 경우에 오류가 떨어지지 않고, null값이 떨어진다
	// (일반적인 요청은 null값 발생시 오류발생)
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// [form 요청]
	
	
	// #) 4-1 <form action="${contextPath}/member/detail4" method="get"> 요청 -----------------------------------------------/
	@GetMapping("detail4")
	public String getDetail4(Member member	// 파라미터 id, pw를 setId(), setPw() 메서드를 이용해서 member 객체에 저장
							, Model model) {
		model.addAttribute("member", member);
		
		return "member/detail";
	}
	
	// * form태그의 get,post방식은 동일한 mapping명이라도 요청에 따라 다르게 작동한다 --------------------*
	
	// #) 4-2 <form action="${contextPath}/member/detail4" method="post"> 요청 -----------------------------------------------/
	@PostMapping("detail4")						// 요청구성 : urlmapping + 요청메서드 
	
	public String postDetail4(@ModelAttribute(value="member") Member member	// 해석 : 파라미터 id,pw로 member을 만들고, model에 member이라는 이름의 속성으로 저장하시오 
							, Model model) {
		
	
		return "member/detail";
	}
}
			
		
		
		
	
	
	

