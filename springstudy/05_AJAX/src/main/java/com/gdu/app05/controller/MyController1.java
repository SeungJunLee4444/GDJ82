package com.gdu.app05.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.domain.Member;
import com.gdu.app05.service.MemberService;

// # 컨트롤러 에너테이션 선언 -----------------
@Controller
public class MyController1 {
	
	// # 웰컴페이지 요청 : index.jsp로 포워딩----------------------
	@GetMapping(value="/")
	public String welcome() {
		return "index";
	}
	
	// # member 맵핑 요청 : member.jsp로 포워딩 --------------------
	@GetMapping("member")
	public String member() {
		return "member";
	}
	
	
	// # 필드 : @Autowired 어노테이션 사용 --------------------------
	@Autowired	// @Autowired : 컨테이너를 찾아서 타입이 일치하는 bean을 가져오라(이름, id는 달라도 상관없음, 일반적으론 동일하게 쓴다)
	private MemberService memberservice; // memberservice와 동일한 타입인 memberserviceimpl을 가져온다
	
	// * memberservice 인터페이스와 memberserviceimpl 클래스의 타입이 동일하다(memberserviceimpl은 memberservice 인터페이스를 상속하고있다)
	
	
	
	// # detail1 요청 : request 파라미터전달방법 사용 ------------------------------------------------------------------------------------
	// * @ResponseBody
	// - 개념 : Ajax 처리하는 에너테이션
	// - 반환값 : jsp가 아닌 데이터(text,json,xml)이다
	// - 기능 : 포워드의 반환을 jsp가 아닌, text,json,xml로 처리해주는 에너테이션 *
	// - 속성
	//	(1) value : 
	//	(2) produces : 응답 데이터의 타입(mine-type)
	@ResponseBody
	@GetMapping(value="member/detail1"
				, produces="text/plain; charset=utf-8"
			)
	public String detail1(HttpServletRequest request, HttpServletResponse response) {
		
		String str = memberservice.execute1(request, response);
		return str;	// 해석 : str.jsp
	}
	
	
	// detail2 요청 : @RequestParam() 어노테이션 사용------------------------------------------------------------------------------
	@ResponseBody	
	@GetMapping(value="member/detail2"
				, produces="application/json; charset=utf-8")
	public Member detail2(@RequestParam(value="id") String id, @RequestParam(value="pw") String pw) {
		Member member = memberservice.execute2(id, pw);
		return member;	// * jackson : member 객체를 {"id" : 아이디, "pw" : 패스워드} 형태의 json 데이터로 바꿔서 전달 -----*
						// - 설명 : jackson은 별도의 코드 없이 pom.xml에 jar파일로 별도로 추가해서 사용된다
	}
	
	
	// detail3 요청 : member 객체로 받아오기 ---------------------------------------------------------------------------------------
	// * proceduces=MediaType : 마인타입 불러오기
	
	@ResponseBody
	@GetMapping(value="member/detail3"
				, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String, Object> detail3(Member member) {
		Map<String, Object> map = memberservice.execute3(member);
		return map;	// * 나중에는 이렇게 memberservice.execute3(member); 줄여서 사용하기
	}
	
	// & @requestbody, @responsebody 에너테이션 정리 ---------------------------------------------------*
	
	// (1) ajax : 클라이언트와 서버간의 비동기 통신을 말한다
	// - ajax의 장점 : 이런 ajax를 통해 화면고침없이 화면동작들이 이루어진다(비동기통신)
	
	// (2) body 정의 : 비동기통신을 위해서는 클라이언트와 서버간에 요청, 응답 메서지를 주고받는데,
	// body는 메시지의 본문을 말한다
	
	// (3) @RequestBody  : ajax 요청메시지에 사용, - 용도: xml/json 기반 메시지를 요청하는경우에 사용된다
	//     @ResponseBody : ajax 응답메시지에 사용, - 용도: 응답메시지의 타입을 정하는 속성 MediaType를 사용할 수 있다
	
	// & @mapping("")의 경로
	// - 답 : mapping값을 의미한다
	// * (1) 단순히 mapping값만 작성하거나, (2) value속성의 값으로 맵핑값을 부여한다
	
	
	
	
	
	
	
	
	
	
	
	
}
