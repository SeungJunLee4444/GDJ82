package com.gdu.app13.aop;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
// # aop 에너테이션 2가지 
@EnableAspectJAutoProxy
@Aspect
public class RequiredLoginAspect {
	
	// # 포인트 컷 : requiredLogin_으로 시작하는 모든 메서드
	@Pointcut("execution(* com.gdu.app13.controller.*Controller.requiredLogin_*(..))")
	public void requiredLogin() { }
	
	// # @Before: 포인트컷으로 지정된 메서드들 수행하기 전에 실행
	@Before("requiredLogin()")  
	public void requiredLoginHandler(JoinPoint joinPoint) throws Throwable {
		
		// # 로그인이 되어있는지 확인하기 위해 session이 필요하므로
		// request가 필요하다
		// 응답을 만들기 위해 response도 필요
		
		// # request, response 가져오기
		ServletRequestAttributes servletWebRequest = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletWebRequest.getRequest();
		HttpServletResponse response = servletWebRequest.getResponse();
		
		// # 세션
		HttpSession session = request.getSession();
		
		// # 로그인 여부 확인
		if(session.getAttribute("loginUser") == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			// # 실패응답
			out.println("<script>");
			out.println("if(confirm('로그인이 필요한 기능입니다. 로그인 하시겠습니까?')){");
			out.println("location.href='" + request.getContextPath() + "/user/login/form';");
			out.println("} else {");
			out.println("history.back();");
			out.println("}");			
			out.println("</script>");
			out.close();
		}
		
	}
}
