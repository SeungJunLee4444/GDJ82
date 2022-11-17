package com.gdu.app13.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app13.domain.UserDTO;

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
	
	// # 로그인 유지
	public void keepLogin(HttpServletRequest request, HttpServletResponse response);
	
	// # 로그아웃
	public void logout(HttpServletRequest request, HttpServletResponse response);
	
	// # 인터셉터 : 초기화면 로그인, keepLoginInterCepter에서 호출
	public UserDTO getUserBySessionId(Map<String, Object> map);
	
	// # 비밀번호 재확인
	public Map<String, Object> confirmPassword(HttpServletRequest request);
	
	// # 비밀번호 변경
	public void modifyPassword(HttpServletRequest request, HttpServletResponse response);
}
