package com.gdu.app01.java04;

public class Soldier {
	
	// # 필드
	private String name;
	private Gun gun;
	
	// # 생성자 
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	// # info
	public void info() {
		System.out.println("이름:" + name);
		gun.info();								// => 건의 정보 메서드
	}
	

}
