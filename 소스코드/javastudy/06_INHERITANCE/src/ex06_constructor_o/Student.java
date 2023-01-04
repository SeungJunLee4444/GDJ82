package ex06_constructor_o;

public class Student extends Person {
	// # 상속을 작성하니 오류 발생
	// => 디폴트생성자(매개변수가 없는)는 jvm이 알아서 서브클래스에 슈퍼클래스의 생성자를 만듬
	// => 개발자가 만든 생성자는 직접 생성자를 만들어줘야 오류가 해결
	
	private String school;
	
	// # 생성자도 자동으로 만들 수 있다
	public Student(String name, String school) {
		super(name);
		this.school = school;
	}
	
//	public Student(String name, String school) {
	// # person을 호출하면서, person의 매개변수 name를 추가해야함
//		super(name);
		// # 슈퍼클래스(person)을 super이라 대신 부름
		// + 외부에서 온 이름값을 person의 매개변수 name을 괄호에 작성해 전달받기
		// * 개발자생성자는 super을 생략 불가
		
		// super();
		// => 부모 클래스가 디폴트 생성자일때(생략 가능)

//		this.school = school;
		// # 내 필드값 입력하기


	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}	
	
	
	
	
	
}
