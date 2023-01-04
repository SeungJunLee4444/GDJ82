package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MemberService {
	
	// # 서비스 인터페이스
	
	public ActionForward login(HttpServletRequest request, HttpServletResponse response);	// 로그인
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response);	// 로그아웃
	public void register(HttpServletRequest request, HttpServletResponse response);	// 회원등록
	public void cancel(HttpServletRequest request, HttpServletResponse response);	// 회원삭제
	
	// # 아이디 중복체크(ajax + 정규식)
	
	
	

}
