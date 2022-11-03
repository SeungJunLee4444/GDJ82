package com.gdu.app01.java03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// * 나중에는 @어노테이션으로 자바든 xml이든 상관없이 가져오기 때문에,
		// 구분할 필요가없다(연습시에만 구분)
		
		// ! xml을 이용해서 내부에 저장된 어노테이션 컨테이너의 자바빈을 출력해보기
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("java03/appCtx.xml");	// - 컨테이너
		
		Book book = ctx.getBean("book1", Book.class);
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getPublisher().getName());
		System.out.println(book.getPublisher().getTel());
		
		

	}

}
