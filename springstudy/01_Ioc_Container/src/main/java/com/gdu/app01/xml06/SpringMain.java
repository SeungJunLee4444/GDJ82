package com.gdu.app01.xml06;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// #1 컨테이너 가져오기
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml06/appCtx.xml");
		
		// #2 빈 가져오기
		Person p = ctx.getBean("human", Person.class);	// => human이라는 클래스, person 타입을 가져오기
		p.info();
		ctx.close();

	}

}
