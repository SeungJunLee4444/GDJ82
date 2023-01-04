package com.gdu.app01.java01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpirngMain {

	public static void main(String[] args) {
		
		// # 자바 컨테이너 호출
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		// - AnnotationConfigApplicationContext : 자바로 만든 스프링 컨테이너
		// - 자바 컨테이너 클래스.class
		
		// # 빈 얻어내기(싱글턴)
		Singer s1 = ctx.getBean("singer1", Singer.class);
		System.out.println(s1.getName());
		System.out.println(s1.getSong().getTitle());
		System.out.println(s1.getSong().getGenre());
		
		Singer s2 = ctx.getBean("singer2", Singer.class);
		System.out.println(s2.getName());
		System.out.println(s2.getSong().getTitle());
		System.out.println(s2.getSong().getGenre());
		
		Singer s3 = ctx.getBean("singer3", Singer.class);
		System.out.println(s3.getName());
		System.out.println(s3.getSong().getTitle());
		System.out.println(s3.getSong().getGenre());

	}

}
