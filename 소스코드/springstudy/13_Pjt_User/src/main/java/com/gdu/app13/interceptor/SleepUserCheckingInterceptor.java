package com.gdu.app13.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.gdu.app13.domain.SleepUserDTO;
import com.gdu.app13.service.UserService;

@Component
public class SleepUserCheckingInterceptor implements HandlerInterceptor {

	// # 서비스 빈 가져오기 : login 요청에 지정하기 위해
	@Autowired UserService userService;
	
	// # 서비스 실행전에 인터셉터 시행
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// # 로그인하려고 사용자가 입력한 id
		String id = request.getParameter("id");
		
		// # 휴먼테이블에 해당 id가 있는지 확인 ----> db
		SleepUserDTO sleepUser = userService.getSleepUserById(id);
		
		// # session에 휴먼계정 정보를 올려둠
		HttpSession session = request.getSession();
		session.setAttribute("sleepUser", sleepUser);
		
		
		// 휴먼회원이면 복원해야 한다는 경고창(/user/sleep/display)을 진행
		if(sleepUser != null) {
			response.sendRedirect(request.getContextPath() + "/user/sleep/display");
			return false;
			
			
		// 휴먼회원이 아니면 로그인(/user/login)을 정상적으로 진행한다
		} else {
			return true;
		}
		
	
	}
	
}
