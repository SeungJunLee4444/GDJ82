package ex_09_this;

public class Student {
	
	// [this]
	// 1) 현재 객체의 참조값
	// 2) * 현재 객체의 멤버(필드, 메소드)를 호출할 때 사용
	// ex) this.stuNo, this.name
	// (student와 this와 같다
	// 3)  생성자 내부에서 다른 생성자를 호출할 때 this() 형태로 사용 가능

	// 1. 필드
	private String stuNo; 	// = this.stuNo *
	private String name;	// = this.name  *
	
	// 2. 생성자
	// 1) 디폴트 생성자
	public Student () {
	}
	
	public Student (String stuNo, String name) {
		this.stuNo = stuNo;
		this.name = name;
	}
	// => 새로만든 생성자는 setter가 필요없다
	// => * setter의 역할을 수행하기 떄문
	
	// # this의 역할 1 : 필드변수와 매개변수와의 중복 방지
	
	
	// 3. 메소드
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// => 개발자는 직접 입력 안한다
	// => source -generate getter and setter에서 호출한다 *
	// * 메소드명은 절대 바꾸지말것, DB 같은 데에서 참조하는데 오류
	// 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// pStuNo라 하는 이유: 매게변수로 가져온 변수기 때문에,
	// 색이 다르다
	// => 이름이 같으면 루트가 달라짐
	// => this를 활용하면 필드값을 필드에 고정시킨다 *
	

	

	
	
}



