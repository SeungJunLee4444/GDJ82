package com.gdu.app01.xml04;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
	
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml04/appCtx.xml");
		Dao dao = ctx.getBean("dao", Dao.class);
		Dao dao2 = ctx.getBean("dao", Dao.class);
		Dao dao3 = ctx.getBean("dao", Dao.class);
		
		System.out.println(dao == dao2);
		System.out.println(dao2 == dao3);
		System.out.println(dao3 == dao);
		
		
		
	}

}
