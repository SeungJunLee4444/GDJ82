package com.gdu.staff.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gdu.staff.domain.StaffDTO;
import com.gdu.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffMapper mapper;
	
	// # 사원 목록
	@Override
	public List<StaffDTO> getStaffList() {
		return mapper.selectStaffList();
	}
	
	// # 상세조회
	@Override
	public ResponseEntity<StaffDTO> detailStaff(HttpServletRequest request) {
		try {
			
			// 파라미터
			Optional<String> strNo = Optional.ofNullable(request.getParameter("no"));
		
			int no = Integer.parseInt(strNo.orElse("00000"));
			
			// db 전달
			StaffDTO staff = mapper.selectStaffDetail(no);
			System.out.println(staff);
			if(staff != null) {				
				return new ResponseEntity<StaffDTO>(staff, HttpStatus.OK);				
			} 
			return new ResponseEntity<StaffDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<StaffDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	// # 사원 등록 
	@Override
	public ResponseEntity<String> addStaff(StaffDTO staff) {
		
		try {

			// # 부서에 따른 연봉 다르게 부여
			// ex) 기획부는 1000, 개발부는 2000, 영업부는 3000, 나머지 4000
			String dept = staff.getDept();
			switch(dept) {
			case "기획부" : staff.setSalary(1000); break;
			case "개발부" : staff.setSalary(2000); break;
			case "영업부" : staff.setSalary(3000); break;
			default : staff.setSalary(4000); break;
			}
			
			mapper.insertStaff(staff);			
			return new ResponseEntity<String>("사원등록이 성공했습니다", HttpStatus.OK);
			// * tbody : 응답할 데이터
			// * tbody의 t : 응달할 데이터에 따라 변환되는 데이터 타입
		} catch (Exception e) {
			return new ResponseEntity<String>("사원등록이 실패했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}

}
