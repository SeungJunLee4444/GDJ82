package com.gdu.app01.xml01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// [1장 : 스프링을 이용한 계산기 만들기]
		
		// & IOC 
		// - 정의 : 제어의 역전, spring에서는 빈은 new를 통해 직접 생성하지않고, 컨테이너에 담아 가져다 쓰는 방식을 말한다
		// - 프레임워크가 제어권을 가져간다
		
		// x 기존 개발자
		// Calculator calculator = new Calculator();
		
		// o 새로운 프레임워크 사용법
		// - 프레임워크가 만든 빈을 가져다 쓴다
		// #1 자바빈 컨테이너(appCtx.xml)로 이동
		// - AbstractApplicationContext : 컨테이너에 저장된 자바빈을 꺼내 사용할 때 사용
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml01/appCtx.xml");	// => 자바빈 컨테이너 경로(앞에 "classpath:xml01/appCtx.xml" classpath:는 생략가능)
		
		// xml에 저장된 bean 가져오는 클래스(둘다 AbstractApplicationContext 자식)
		// 1) GenericXmlApplicationContext
		// 2) ClassPathXmlApplicationContext
		
		
		
		// #2 해당 컨테이너에서 저장된 빈 가져오기
		Calculator calculator = ctx.getBean("calculator", Calculator.class);						// = bean의 클래스 타입 id와 동일해야한다 *
		
		// ) 메서드 실행
		calculator.add(2, 3);
		calculator.sub(3, 2);
		calculator.mul(4, 5);
		calculator.div(10, 2);
		
		//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		
		Student student = ctx.getBean("haksang", Student.class);									// bean의 타입을 student로 변경하는 코드
		System.out.println(student.getName());
		System.out.println(student.getSchool());
		student.getCalculator().add(2, 4);															// student에 참조된 calculator 클래스
		
		
		
		
		// # 컨텍스트 닫아주기(생략가능)
		ctx.close();
		
		// => main메서드는 java application으로 실행
		
		
		
		

	}

}
