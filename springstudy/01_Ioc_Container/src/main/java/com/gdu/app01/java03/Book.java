package com.gdu.app01.java03;

public class Book {
	
	// [java03 : xml 컨테이너 내에 어노테이션 컨테이너를 넣을 수 있으며,
	// 어노테이션 컨테이너에 저장된 자바빈을 xml 컨테이너에서 꺼내는 방식으로 main에서 가져올수있다]
	
	// * 결과 : xml 컨테이너에 저장된 자바컨테이너의 자바빈 => xml방식으로 출력가능
	
	// # 필드
	private String title;
	private String author;
	private Publisher publisher;
	
	// # setter => 인젝션(* 인젝션은 '주입한다'는 뜻이다
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	

}
