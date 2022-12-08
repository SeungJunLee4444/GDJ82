package com.gdu.res.controller;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gdu.res.domain.MemberDTO;
import com.gdu.res.service.MemberService;

// * @restcontroller : 모든 메서드에 @ResponseBody 에너테이션을 추가한다

@RestController	
public class MemberRestController {
	
	/*
	 	== REST : representational state transfer
	 	
	 	- 기능 			: 자원을 정의하고 자원의 주소를 지정하는 방식에 대한 하나의 형식 
	  	- restful의 뜻 	: REST방식을 따르는 시스템을 RESTFUL하다고 표현한다
	 	- 사용의미 		:  이걸 꼭 써야한다가 아니라 이런 방식으로도 작동할 수 있음을 참고 
	 	- 특징 
	 		(1) 동작의 결정을 정하는 url로만 이루어지지않고, method 도 포함하여 동작을 결정한다(즉, 동일한 mapping을 사용할 수 있다)  
	 		(2) 파라미터가 url에 경로처럼 포함(?를 사용하지않는다)
		- crud 처리 방법
							url				method					
			(1) 삽입	/members			POST
			(2) 조회	/members			GET
			(3) 상세	/members/1			GET
			(4) 수정	/members			PUT
			(5) 삭제	/members/1			DELETE
	  	
	  	
	 */
	
	
	@Autowired	
	private MemberService memberService;
	
	// == 추가
	// 	
	//		1. ajax로 보낸 json 데이터를 controller에서 받는법 : 매개변수로 (1) memberDTO, (2) Map
	// 		* ajax에서 보낸 id, name, gender, address는 dto의 이름과 동일하게 부여
	
	// 		2. @RequestBody : 본문에 있는 데이터를 가져오는 에너테이션(요청 본문을 뒤져라)
	// 		- handle의 요청 본문을 뒤져서 memberDTO와 비슷한 데이터를 가져와라(AJAX에서 가져온다)
	
	// = 
	
	
	// # service : 추가
	// @RequestBody : 본문에 포함된 데이터 받기
	@PostMapping(value="/members", produces="application/json")
	public Map<String, Object> addMember(@RequestBody MemberDTO member, HttpServletResponse response) {
		return memberService.register(member, response);
	}
	
	// # service : 목록
	// * {page} : 파라미터대신 //로 처리한 파라미터값을 {}로 받는다
	// * @PathVariable : 전달받은 page를 서비스로 던져주기 위해 매개변수처리

	@GetMapping(value="/members/page/{page}", produces="application/json")
	public Map<String, Object> getMemberList(@PathVariable(value="page", required=false) Optional<String> opt) {
		int page = Integer.parseInt(opt.orElse("1"));
		return memberService.getMemberList(page);
	}
			
	// # 상세
	@GetMapping(value="/members/{memberNo}", produces="application/json")
	public Map<String, Object> getMember(@PathVariable(value="memberNo", required=false) Optional<String> opt) {
		int memberNo = Integer.parseInt(opt.orElse("0"));
		return memberService.getMember(memberNo);
	}
	
	// # 수정
	@PutMapping(value="/members", produces="application/json")
	public Map<String, Object> modifyMember(@RequestBody Map<String, Object> map , HttpServletResponse response) {
		return memberService.modifyMember(map, response);
	}
	
	// # 삭제
	// (ex 3, 1)이 string으로 넘어옴
	@DeleteMapping(value="/members/{memberNoList}", produces="application/json")
	public Map<String, Object> deleteMember(@PathVariable String memberNoList) {
		return memberService.deleteMember(memberNoList);
	}
	
	
	
	


}
