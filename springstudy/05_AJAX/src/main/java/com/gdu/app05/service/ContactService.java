package com.gdu.app05.service;

import java.util.Map;

import com.gdu.app05.domain.Contact;

public interface ContactService {

	// 요청 JSON
	public Contact execute1(Contact member);
	public Map<String, Object> execute2(Map<String, Object> map);
	
}