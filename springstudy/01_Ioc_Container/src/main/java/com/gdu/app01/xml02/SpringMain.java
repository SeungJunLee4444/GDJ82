package com.gdu.app01.xml02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// #1 자바빈 컨테이너 연결
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:xml02/appCtx.xml");
		
		// #2 해당 컨테이너에 저장된 빈 가져오기
		Car mycar = ctx.getBean("dreamCar", Car.class);	// & getBean(자바빈id, 타입)메서드 : 빈의 id명이 틀리면 오류가 발생한다
		System.out.println(mycar.getModel());
		System.out.println(mycar.getMaker());
		Engine engine = mycar.getEngine();
		
		System.out.println(engine.getFuel());
		System.out.println(engine.getEfficency());
		System.out.println(engine.getCc());
		
		
		// #3 컨텍스트 닫아주기
		ctx.close();
	}

}
