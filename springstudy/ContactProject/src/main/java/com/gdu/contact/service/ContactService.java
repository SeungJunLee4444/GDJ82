package com.gdu.contact.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;


public interface ContactService {
	public void findAllContacts(Model model);
	public void findContactByNo(Model model);
	public void register(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public void modify(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
