package com.gdu.app13.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PreventLoginInterceptor implements HandlerInterceptor {
	
	// # PreventLoginInterceptor : 이미 로그인이 되어있는 상태일 경우 실행
	// 로그인 완료된 사용자가 로그인 페이지 이동, 약관 페이지 이동, 가입페이지 이동 등의 요청을 하면
	// 이를 막는 인터셉터
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// # 로그인된 상태에서는 응답실패
		if(request.getSession().getAttribute("loginUser") != null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// # 실패응답
			out.println("<script>");
			out.println("alert('해당 기능은 사용할 수 없습니다');");
			out.println("location.href='" + request.getContextPath() + "';");
			out.println("</script>");
			out.close();
			return false; // 컨트롤러의 요청이 처리되지 않음
		} else {
			return true;  // 컨트롤러의 요청이 처리
		}
		
	}

}
