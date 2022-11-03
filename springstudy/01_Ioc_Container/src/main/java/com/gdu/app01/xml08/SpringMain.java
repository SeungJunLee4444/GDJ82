package com.gdu.app01.xml08;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
	
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml08/appCtx.xml");
		Member member = ctx.getBean("member", Member.class);
		member.info();
		ctx.close();
		
	}

}
