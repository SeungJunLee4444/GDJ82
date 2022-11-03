package com.gdu.app04.domain;

public class Member {
	
	// # dto : 빈을 만들기 위해 생성
	
	private String id;
	private String pw;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
	

}
