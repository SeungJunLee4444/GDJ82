package ex05_constructor_o;

public class Student extends Person{
	// # Person을 부모클래스로 상속한다
	
	// # 서브클래스(student)는 슈퍼클래스(person)의 생성자를 "반드시" 호출해야한다
	// # 자식이 태어나려면 부모가 있어야 하는 것과 같은 논리
	
	
//	public Student() {
//		System.out.println("Student 생성");
//	}
	// => 개발자가 슈퍼클래스의 생성자를 호출하지 않으면 자동으로 jvm이 호출
	// * 디폴트형식의 슈퍼클래스만 자동호출 가능(ex06에서 오류 시험)
	
	

}
