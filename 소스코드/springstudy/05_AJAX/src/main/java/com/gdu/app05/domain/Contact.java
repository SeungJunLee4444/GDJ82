package com.gdu.app05.domain;

public class Contact {
	
	private String name;
	private String tel;
	
	public Contact() {
		
	}
	public Contact(String name, String tel) {
		super();
		this.name = name;
		this.tel = tel;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
