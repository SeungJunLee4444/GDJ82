package com.gdu.app01.xml06;

import java.util.List;
import java.util.Map;
import java.util.Set;

// [6장 : 컬렉션 프레임워크에 주입]

// 1. 자바빈 생성
// - 자바빈 : 필드값 + getter,setter

public class Person {

	// field
	private List<String> hobbies;
	private Set<String> contacts;	// 연락처
	private Map<String , String> friends;
	
	// # getter, setter
	public List<String> getHobbies() {
		return hobbies;
	}
	
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	
	public Set<String> getContacts() {
		return contacts;
	}
	
	public void setContacts(Set<String> contacts) {
		this.contacts = contacts;
	}
	
	public Map<String, String> getFriends() {
		return friends;
	}
	
	public void setFriends(Map<String, String> friends) {
		this.friends = friends;
	}
	
	
	
	public void info() {
		// list
		for(int i = 0; i < hobbies.size(); i++) {
			System.out.println((i + 1) + "번 째 취미 : " + hobbies.get(i)) ;
		}
		
		// set(인덱스가없어서 향상 for문 사용)
		for(String contact : contacts) {
			System.out.println(contact);
		}
		
		// map (key + value)
		// * key와 value를 합치면 entry
		for(Map.Entry<String, String> entry : friends.entrySet())
			System.out.println(entry.getKey() + ":" + entry.getValue());
		
	}


}