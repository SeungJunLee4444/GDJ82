package com.gdu.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.gdu.staff.domain.StaffDTO;

public interface StaffService {
	public List<StaffDTO> getStaffList();
	public ResponseEntity<StaffDTO> detailStaff(HttpServletRequest request);
	public ResponseEntity<String> addStaff(StaffDTO staff);
	
	

}
