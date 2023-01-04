package com.gdu.app05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.service.MemberService;

// [[[ ajax 활용
// ajax 정의 : 클라이언트와 서버간의 비동기 통신을 말한다
//      장점 : 이런 ajax를 통해 화면고침없이 화면동작들이 이루어진다(비동기통신)

// # 컨트롤러 에너테이션 선언
@Controller
public class MyController1 {
	
	// # 웰컴페이지 요청 
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	// # member 맵핑 요청
	@GetMapping("member")
	public String member() {
		return "member";
	}
	
	
	// # 필드 : @Autowired 어노테이션 사용 
	// - @Autowired 기능 : bean에 저장된 같은 타입(차선으로 같은 id)의 빈 클래스를 호출하는 어노테이션
	@Autowired	
	private MemberService memberservice;
	

	// # detail1 요청
	// * @ResponseBody : AJAX의 응답을 처리하는 에너테이션
	// - 기능 : JSP가 아닌 데이터(text,json,xml)으로 응답하기 위해 사용된다
	// - 속성종류 : (1) value , (2) produces : 응답 데이터의 타입(mine- type)
	@ResponseBody
	@GetMapping(value="member/detail1"
				, produces="text/plain; charset=utf-8"
			)
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		String str = memberservice.execute1(request, response);
		return str;	
	}
	
	
	// # detail2 요청 : @RequestParam() 어노테이션 사용
	@ResponseBody	
	@GetMapping(value="member/detail2"
				, produces="application/json; charset=utf-8")
	public Member detail2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {
		Member member = memberservice.execute2(id, pw);
		return member;	
		// * jackson : member 객체를 {"id" : 아이디, "pw" : 패스워드} 형태의 json 데이터로 바꿔서 전달 -----*
		
	}
	
	
	// # detail3 요청 : member 객체로 받아오기 
	// * proceduces=MediaType : 마인타입 불러오기
	
	@ResponseBody
	@GetMapping(value="member/detail3"
				, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> detail3(Member member) {
		Map<String, Object> map = memberservice.execute3(member);
		return map;	
	}
	
	// * ajax 에너테이션
	// (1) @RequestBody	 : ajax 요청메시지에 사용, xml/json 기반 메시지를 요청할 때 사용
	// (2) @ResponseBody : ajax 응답메시지에 사용, 응답메시지 타입을 결정하는 mediaType 사용가능
	

	
	

	
	
	
	
	
	
	
	
	
	
	
}
