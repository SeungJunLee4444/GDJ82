package ex06_constructor_o;

public class Person {
	
	private String name;
	
	public Person(String name) {
		this.name = name;
		// # 디폴트가 아닌 개발자가 만든 생성자
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// * getter과 setter : 필드값이 private일때 두 메서드를 통해 값을 확인
}
