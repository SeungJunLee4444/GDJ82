package com.gdu.app05.service;

import java.util.Map;

import com.gdu.app05.domain.Contact;

public class ContactServiceImpl implements ContactService {

	@Override
	public Contact execute1(Contact member) {
		return member;
	}
	
	@Override
	public Map<String, Object> execute2(Map<String, Object> map) {
		return map;
	}

}
