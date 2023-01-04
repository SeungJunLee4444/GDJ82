package com.gdu.app02.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 
	! @Controller
	- 정의 : 컨트롤러 에너테이션
	- 동작 : @Component에 의해서 자동으로 Bean으로 만들어지고 스프링에 의해서 사용된다(내부동작)
*/

	//-----------------------------------------------------------------------------------------------------
	//# 컨트롤러 에너테이션
	@Controller
	public class MvcController {

	// - 메서드 1개당, 요청 1개와 응답 1개를 처리
		/*
		 ! 요청,응답을 처리하는 에너테이션
		 - 형태 : @RequestMapping 
		 - 기능 : 요청을 인식하는 에너테이션
		 - url 매핑과 요청 메서드(get/post)를 인식
		 		(1) value  속성		   : urlMapping
		 		(2) method 요청 메서드 : RequestMethod
		 			
		 	! welcome파일 작업하기
		 	- urlMapping으로 "/"를 요청하면 "/WEB-INF/views/index.jsp"를 열어준다
		 */
		
	//-----------------------------------------------------------------------------------------------------
	// # /(컨텍스트패스)에 웰컴페이즈 index.jsp 등록하기
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	
	// ! 메서드 작성방법
	// 	- 반환타입 : String(응답할 뷰(jsp)의 이름을 반환하기때문)
	//  - 메서드 이름 : 아무일도 안함, 맘대로 작성
	// - 매개변수 : 선택(요청이 있으면 request, 응답을 만들면 response 등)
	
	public String welcome() {
		return "index";  	
		             
	}
	
	// & return "index"만 쓰는 이유
	// - 답 : DispatcherServlet의 viewResolver에 의해서 해석
	// 		(1) prefix="/WEB-INF/views/"
	// 		(2) suffix=".jsp"
	// index의 사전경로와 확장자가 viewmapper에서 응답하기 때문에 index만 입력하면된다
		
	
	// & index.jsp의 이동방법
	// - 정답 : forward
	// - 스프링 이동방법 : redirect 할때는 return "redirect:/경로"; 처럼 반환한다
	// => 이걸로 우리가 알고있던 controller의 forward, redirect 경로 코드들이 사라지고,
	// 전부 return으로 처리된다
		
	//-----------------------------------------------------------------------------------------------------
	// # @RequestMapping 사용
	
	// # 단순이동 
	// - 동물보러가기 요청(/animal)
	
	// <a href="${contextPath}/animal">동물보러가기</a>
	@RequestMapping(value="/animal", method=RequestMethod.GET)
	
	public String 동물보러가기() {
		// /WEB-INF/views + gallery/animal + .jsp (viewpointer가 prefix, suffix값을 붙여서 실제 경로를 만들어준다)
		return "gallery/animal";
	}

	// * @RequestMapping 작성법
	//@RequestMapping(value="/animal", method=RequestMethod.GET)
	//@RequestMapping(value="animal", method=RequestMethod.GET)				// /(슬래시)없어도됨
	//@RequestMapping(value="/animal")										// - get은 생략가능
	//@RequestMapping("/animal")											// - value로 인식
	//@RequestMapping("animal")												// * 최종버전 
	
	//-----------------------------------------------------------------------------------------------------
	// # 단순이동
	// -꽃보러가기 요청(/flower)
	@RequestMapping("flower")
	public String 꽃보러가기() {
		return "gallery/flower";
		// - return "/gallery/flower"; : 슬래시(/)가 있어도 되며 없어도 무방
	}
	
	//----------------------------------------------------------------------------------------------
	// # insert + 리다이렉트
	// - 동물보러 갔다가 꽃보러가기
	@RequestMapping("animal/flower")
	public String 동물보고꽃보고() {
		// * redirect: 다음에는 항상 다음 URLMapping값을 적어준다
		return "redirect:/flower";		// => /flower으로 인해, 2) 꽃보러가기 요청을 실행시킨다
		
	}
	
	//----------------------------------------------------------------------------------------------
	// # 요청 : 포워드 + 파라미터 전달후 꺼내쓰기
	
	// jsp :  <a href="${contextPath}/want/filename?animal=animal5.jsp"
	@RequestMapping("want/animal") 	// => 요청과 동일하게 작성
	public String 동물5보기(HttpServletRequest request) {
		System.out.println(request.getParameter("filename"));
		
		return "gallery/animal5";		// <= * 포워드 
	}
	
	// & 파라미터 전달 설명 
	// (1) 해당 방식은 포워드 방식이며, 포워드는 파라미터값을 다음페이지로 전달한다
	// (2) 따라서 index.jsp에서 전달된 파라미터값은 animal5로 전달된 것이다!
	
		
	//----------------------------------------------------------------------------------------------
	// # 응답 : 응답은 return을 하지않는다, 즉 반환타입이 void 
	@RequestMapping("response") 
	public void 응답만들기(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('동물보러가자');");
			out.println("location.href='" + request.getContextPath() +"';");
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
