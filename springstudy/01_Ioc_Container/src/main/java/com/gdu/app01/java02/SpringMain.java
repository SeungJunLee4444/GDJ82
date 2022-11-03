package com.gdu.app01.java02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		// # 출력
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringBeanConfig.class);
		Student student = ctx.getBean("student", Student.class);	// & 어노테이션 컨테이너에서 메서드이름이 빈의 이름
		System.out.println(student.getAwards());
		System.out.println(student.getScores());
		System.out.println(student.getContact());

	}

}
