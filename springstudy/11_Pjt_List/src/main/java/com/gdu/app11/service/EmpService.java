package com.gdu.app11.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpService {
	
	public void findAllEmployees(HttpServletRequest request, Model model); 
	public void findEmployees(HttpServletRequest request, Model model); 
	public Map<String, Object> findAutoCompleteList(HttpServletRequest request);	// String param 저장
	

}
