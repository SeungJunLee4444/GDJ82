package com.gdu.app002.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
 	! @Controller
 	- 정의 : 컨트롤러 에너테이션
 	- 동작 : @Component에 의해서 자동으로 Bean으로 만들어지고 스프링에 의해서 사용된다(내부동작)
 */

// # 컨트롤러 에너테이션
//@Controller
public class MvcController {
	
	// ! 메서드 1개 : 요청 1개와 응답 1개를 처리
	/*
	 	! 요청,응답을 처리하는 에너테이션
	 		1) @RequestMapping : 요청을 인식하는 에너테이션
	 		- url 매핑과 요청 메서드(get/post)를 인식
	 		- 속성
	 			(1) value : urlMapping
	 			(2) method : RequestMethod
	 			
	 			* mapping : 해당값이 다른 값으로 
	 	
	 	! welcome파일 작업하기
	 	- urlMapping으로 "/"를 요청하면 "/WEB-INF/views/index.jsp"를 열어준다
	 */
	
	//-------------------------------------------------------------------------
	
	// # /(컨텍스트패스)에 웰컴페이즈 index.jsp 등록하기
	
	@RequestMapping(value="/p", method=RequestMethod.GET)
	
	// ! 메서드 작성방법
	// 	- 반환타입 : String(응답할 뷰(jsp)의 이름을 반환하기때문)
	//  - 메서드 이름 : 아무일도 안함, 맘대로 작성
	// - 매개변수 : 선택(요청이 있으면 request, 응답을 만들면 response 등)
	
	public String welcome() {
		return "index";			// => 웰컴파일명과 동일하게 짓기(확장자제외)
	}
	
	// & index만 쓰는 이유
	// DispatcherServlet의 viewResolver에 의해서 해석
	// prefix="/WEB-INF/views/"
	// suffix=".jsp"
	// index의 사전경로와 확장자가 viewmapper에서 응답하기 때문에 index만 입력하면된다
	
	// & index.jsp로 forward했는지, redirect했는지
	// -정답 : forward 했다
	// redirect 할때는 return "redirect경로"; 처럼 반환한다
	// * 이걸로 우리가 알고있던 controller의 forward, redirect 경로 코드들이 사라짐
	
	//-------------------------------------------------------------------------
	
	// # animal 요청 
	
	// <a href="${contextPath}/animal">동물보러가기</a>
	@RequestMapping(value="/animal", method=RequestMethod.GET) 
	public String 동물보러가기() {
		// /WEB-INF/views + gallery/animal + .jsp (viewpointer가 prefix, suffix값을 붙여서 실제 경로를 만들어준다)
		return "gallery/animal";
	}
	
	
	
	
	
	
	

}
