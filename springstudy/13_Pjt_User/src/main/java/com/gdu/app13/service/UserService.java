package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
	
	// # 회원가입 점검(아이디, 이메일)
	public Map<String, Object> isReduceId(String id);
	public Map<String, Object> isReduceEmail(String email);
	
	// # 인증코드 보내기
	public Map<String, Object> sendAuthCode(String email);
	
	// # 회원가입
	// * 반환타입이 void라는건 서비스에서 직접 응답을 만들겠다는 의미
	public void join(HttpServletRequest request, HttpServletResponse response);
	
	// # 회원 삭제, 은퇴자 테이블에 추가
	public void retire(HttpServletRequest request, HttpServletResponse response);
	
	// # 로그인
	public void login(HttpServletRequest request, HttpServletResponse response);
	
	
	
}
