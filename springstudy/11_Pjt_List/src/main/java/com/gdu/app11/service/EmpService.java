package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface EmpService {
	
	public void findAllEmployees(HttpServletRequest request, Model model); 

}
