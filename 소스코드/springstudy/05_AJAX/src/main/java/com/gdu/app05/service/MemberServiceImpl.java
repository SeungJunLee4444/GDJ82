package com.gdu.app05.service;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.app05.domain.Member;

public class MemberServiceImpl implements MemberService {

	// # 파라미터 처리방법1 : request 사용 + response로 출력
	@Override
	public String execute1(HttpServletRequest request, HttpServletResponse response) {
		String id = null;
		String pw =null;
		try {
			id = request.getParameter("id");
			pw = request.getParameter("pw");
			if(id.isEmpty() || pw.isEmpty()) {
				throw new RuntimeException("아이디와 패스워드 모두 입력하세요");
			}
			return "당신의 아이디는" + id + "이고, 패스워드는" + pw + "입니다";
		} catch (Exception e) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println(e.getMessage());
				out.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			
			}
		} return null;
	}

	// # 파라미터 처리방법2 :  사용
	@Override
	public Member execute2(String id, String pw) {
		return new Member(id, pw);
	}
	
	// # 파라미터 처리방법3 : member을 hashmap으로 바꿔서 반환 사용
	@Override
	public Map<String, Object> execute3(Member member) {			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", member.getId());
		map.put("pw", member.getPw());
		return map;
	}
	


}
