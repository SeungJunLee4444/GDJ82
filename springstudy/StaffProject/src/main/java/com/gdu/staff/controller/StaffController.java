package com.gdu.staff.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.service.StaffService;

@Controller
public class StaffController {
	
	
	// # 서비스 빈
	@Autowired
	StaffService staffService;
	
	// # 웰컴페이지
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	// # 목록조회 요청
	@ResponseBody	// json 데이터 반환에 필요
	@GetMapping(value="/list.json", produces="application/json; charset=utf-8")
	public List<StaffDTO> listBogi() {
		return staffService.getStaffList();
	}
	
	// # 사원 조회
	@ResponseBody
	@GetMapping(value="/detail.json", produces="application/json; charset=utf-8")
	public ResponseEntity<StaffDTO> detail(HttpServletRequest request) {
			System.out.println(request.getParameter("no"));
		return staffService.detailStaff(request);
		// * jackson이 staffDTO를 JSON 데이터로 변환
	}
	
	// # 사원 등록 : 파라미터 1번 request
	@ResponseBody
	@PostMapping(value="/add", produces="text/plain; charset=utf-8")
	public ResponseEntity<String> add(HttpServletRequest request) {
		String sno = request.getParameter("sno");
		String name = request.getParameter("name");
		String dept = request.getParameter("dept");
		StaffDTO staff = new StaffDTO(sno, name, dept, 0); 
		return staffService.addStaff(staff);
	}
	
	
	
	// # 사원 등록 : 파라미터 3번 객체사용
	/*
	 * @ResponseBody
	 * 
	 * @PostMapping(value="/add", produces="text/plain; charset=utf-8") public
	 * ResponseEntity<String> add(StaffDTO staff) { return
	 * staffService.addStaff(staff); }
	 */

}
