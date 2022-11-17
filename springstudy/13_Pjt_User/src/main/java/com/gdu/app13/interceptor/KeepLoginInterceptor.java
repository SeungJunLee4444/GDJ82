package com.gdu.app13.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import com.gdu.app13.domain.UserDTO;
import com.gdu.app13.service.UserService;

public class KeepLoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	UserService userService;
	
	// [[[ 인터셉터
	// - preHandle은 모든 요청 이전에, postHandel은 모든 요청 이후에
	
	// # preHandle : 컨트롤러의 모든 mapping요청 이전에 처리, KeepLoginIntercepter가 개입
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {	// * 컨트롤러로 넘어가는 request, response를 가로채 사용
	
		
		// # 로그인 되있지 않은 경우 + 쿠키에 keepLogin이 있는 경우 -> 로그인 유지 동작(자동로그인)
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			Cookie cookie = WebUtils.getCookie(request, "keepLogin");
			if(cookie != null) {
				
				Map<String, Object> map = new HashMap<>();
				map.put("sessionId", cookie.getValue());
				
				UserDTO loginUser = userService.getUserBySessionId(map);
				if(loginUser != null) {
					session.setAttribute("loginUser", loginUser);					
				}
			}
		}
		
		return true;	// 컨트롤러의 요청을 처리하는 메서드가 수행된다
		
		
	}
}
