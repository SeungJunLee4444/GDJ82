package ex05_constructor_o;

public class StudentMain {
	
	// ex05, 06 생성자에 따른 상속

	public static void main(String[] args) {
		
		Student student = new Student(); 
		// # 서브 클래스 생성자 호출
		// * 매개변수가 없는 디폴트 생성자만 자동으로 상속된 출력값이 나온다
		// => 디폴트 생성자가 아니면 자동으로 진행되지 않음
		
		
		// * ex05, 06 정리 : 상속의 생성자 
		// # person(부모) - student(자식) 
		// if) 부모가 디폴트 생성자고 자식클래스에 생성자가 없으면
		// => jvm이 자동으로 자식클래스에 생성자를 인식하고 부모클래스의 생성자를 호출
		
		// if) 부모의 생성자가 개발자 생성자면
		// => 부모의 매개변수와 필드값도 같이 작성해줘야 한다 
		// ex) super(부모의 매개변수)(); , 자식생성자의 매개변수에 부모 매개변수 우선작성
		
		// & 디폴트 생성자는 super 생략가능, 개발자 생성자는 불가능
		

	}

}
