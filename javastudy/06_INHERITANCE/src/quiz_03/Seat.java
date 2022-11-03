package quiz_03;

public class Seat {
	
	// # 몇명이 앉는지 저장
	
	private Person person;
	// # 업캐스팅 : 공통된 부모 클래스의 값만 호출함
	// => person, student, alba를 모두 저장할 수 있는 타입은 person(부모)

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		// => 
	}

}
