package com.gdu.app01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:xml03/appCtx.xml");
		
		Person person = ctx.getBean("pers", Person.class);
		System.out.println(person.getAge());
		System.out.println(person.getName());
		
		Address address = person.getAddr();
		System.out.println(address.getJibun());
		System.out.println(address.getRoad());
		System.out.println(address.getZipCode());
		
		
		

	}

}
