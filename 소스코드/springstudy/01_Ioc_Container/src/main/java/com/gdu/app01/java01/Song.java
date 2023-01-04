package com.gdu.app01.java01;

public class Song {
	
	// [java01 : 자바를 이용한 컨테이너]
	
	// 필드
	private String title;
	private String genre;	// 장르
	 
	// 디폴트생성자
	public Song() {
	
	}
	
	// 생성자
	public Song(String title, String genre) {
		super();
		this.title = title;
		this.genre = genre;
	}

	// getter,setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	
	

}
