package com.gdu.app01.java04;


public class Gun {
	
	// [java04 : xml 컨테이너에 저장된 자바빈을 @어노테이션 컨테이너에 저장하고,
	// 이를 어노테이션 컨테이너 호출방식으로 조회할 수 있다]
	
	// * 결과 : 어노테이션 컨테이너에 저장된 XML 컨테이너의 자바빈 => 어노테이션 방식으로 출력가능
	
	// # 필드
	private String model;
	private int bullet;
	
	// # 생성자
	public Gun(String model, int bullet) {
		super();
		this.model = model;
		this.bullet = bullet;
	}
	
	// # info()
	public void info() {
		System.out.println("모델명:" + model);
		System.out.println("총알수:" + bullet);
	}
	
	

}
