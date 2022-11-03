package ex04_outputstream;

import java.io.Serializable;

public class User implements Serializable {
	
	
	private static final long serialVersionUID = -1830845902387248224L;
	// => 원래 같은 값을 가진 애들을 다시 모으기 위해 사용한다
	
	// 1. 직렬화
	// => 스트림을 이용해 객체를 전송하려면, 직렬화가 필요하다 *
	// (한번에 못보내고 하나씩 보내는것이 스트림이기 때문에)
	// => 직렬화가 필요한 객체는 serializable interface를 구현해야한다
	// => serializable을 구현한 클래스는 serialversionuid 필드가 필요하다	( 예외처리에서 나온 이야기)
	
	// (1) 인터페이스 설정
	// (2) uid 생성
	
	
	
	
	private int userNo;
	private String name;
	private int age;
	

	public User(int userNo, String name, int age) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", name=" + name + ", age=" + age + "]";
	}
	
	// & object 클래스 메서드 
	// 1) tostring 재정의
	// => object
	
	
	// 2) equals 재정의
	

}
