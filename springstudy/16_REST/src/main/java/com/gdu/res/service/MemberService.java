package com.gdu.res.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.gdu.res.domain.MemberDTO;

public interface MemberService {
	
	// 추가
	public Map<String, Object> register(MemberDTO member, HttpServletResponse response);

	// 목록
	public Map<String, Object> getMemberList(int page);
	
	// 상세
	public Map<String, Object> getMember(int memberNo);
	
	// 수정
	public Map<String, Object> modifyMember(Map<String, Object> map, HttpServletResponse response);
	
	// 삭제
	public Map<String, Object> deleteMember(String memberNoList);	
	
}
