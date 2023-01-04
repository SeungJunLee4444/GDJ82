package com.gdu.app01.java02;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
	
	// [java02 : 어노테이션 컨테이너 + 컬렉션 프레임워크]
	// & 꼭 생성자를 사용해야 하는건 아니다, setter을 보통 더 많이 사용
	// & 자바 컨테이너에서는 자바빈 메서드의 이름이 자바빈의 id를 의미한다
	
	// # 필드
	private List<Integer> scores;
	private Set<String> awards;
	private Map<String, String> contact;
	
	
//	// # 디폴트 생성자
//	public Student() {
//		// TODO Auto-generated constructor stub
//	}
//
//	// # 매개변수 생성자
//	public Student(List<Integer> scores, Set<String> awards, Map<String, String> contact) {
//		super();
//		scores = scores;
//		this.awards = awards;
//		this.contact = contact;
//	}
	
	// # getter, setter-------------------------사용
	public List<Integer> getScores() {
		return scores;
	}

	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public Set<String> getAwards() {
		return awards;
	}

	public void setAwards(Set<String> awards) {
		this.awards = awards;
	}

	public Map<String, String> getContact() {
		return contact;
	}

	public void setContact(Map<String, String> contact) {
		this.contact = contact;
	}
	
	
	
	

}
