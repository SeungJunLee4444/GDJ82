package com.gdu.app05.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app05.domain.Member;

public interface MemberService {
	
	// # 서비스 인터페이스
	// - 내용 : 메서드에 따라 파라미터를 처리하는방법 3가지를 사용하기
	
	public String execute1(HttpServletRequest request, HttpServletResponse response); 
	
	public Member execute2(String id, String pw); 
	
	public Map<String, Object> execute3(Member member); 

}
