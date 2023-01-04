package com.gdu.app01.java01;

public class Singer {
	
	// [java01 : 어노테이션 컨테이너 만들기]
	// - 자바 파일에 만드는 스프링 컨테이너
	// - @어노테이션을 사용함으로서 만들 수 있다
	// - 생성자, setter 방식 두가지가 있지만, setter 방식을 훨씬 더 많이 사용한다 
	
	//###########################################################################################################
	// & 스프링 인젝션과 스프링 컨테이너 개념정리
	// 1) 인젝션 
	// (1) 생성자 인젝션 : 생성자를 이용한 인젝션			=> 자바빈에 저장할 값에 변화를 주고 싶을때 사용한다
	// (2) setter 인젝션 : setter 메서드를 이용한 인젝션	=> setter방식을 대체로 많이 씀
	
	// & 컬렉션 프레임워크의 사용은 인젝션 여부와 상관없다
	
	// 2) 컨테이너의 종류
	// (1) xml방식			: Spring Bean configuration file을 생성해 컨테이너로 사용한다
	// (2) @어노테이션 방식 : java클래스에 @configuration, @bean 어노테이션을 붙여 스프링 컨테이너로 사용한다
	//###########################################################################################################
	
	
	// 필드
	private String name;
	private Song song;
	
	// 생성자
	public Singer() {
		
	}

	// 필드 생성자
	public Singer(String name, Song song) {
		super();
		this.name = name;
		this.song = song;
	}

	// getter, setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Song getSong() {
		return song;
	}

	public void setSong(Song song) {
		this.song = song;
	}
	
	
	

}
