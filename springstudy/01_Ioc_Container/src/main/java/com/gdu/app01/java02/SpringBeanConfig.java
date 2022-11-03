package com.gdu.app01.java02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// # 1 @Configuration 어노테이션 생성 : 자바로 만든 컨테이너 생성
@Configuration
public class SpringBeanConfig {
	
	// # 2 @Bean 어노테이션 생성 : 빈 생성
	@Bean
	public Student student() {	// bean의 id : student(=메서드명)
		
		// list
		List<Integer> scores = new ArrayList<Integer>();
		for(int cnt = 0; cnt < 5; cnt++) {
			scores.add((int)(Math.random() * 101));	// 0부터 100개의 난수 발생
		}
		
		// set(* 배열이니 여러 상을 저장할 수 있다)
		Set<String> awards = new HashSet<String>();
		awards.add("개근상");
		awards.add("장려상");
		awards.add("우수상");
		
		
		// map
		Map<String, String> contact = new HashMap<String, String>();
		contact.put("address", "서울");
		contact.put("tel", "02-123-4567");
		
		
		// #3 빈 생성 및 반환
		Student student = new Student();
		student.setScores(scores);
		student.setAwards(awards);
		student.setContact(contact);
		return student;	
	}
		
		
}
	
	


