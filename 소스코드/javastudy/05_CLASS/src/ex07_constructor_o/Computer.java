package ex07_constructor_o;

public class Computer {

	// [8일차]
	// [클래스구성]
	// => 필드 + 생성자 + 메소드
	
	// 1. 생성자(Constructor)
	// => 객체를 생성할 때 사용되는 메서드
	
	// 1) 특징
		// 메소드의 이름이 클래스 이름과 같다
		// 반환타입이 존재하지 않는다(void와 다른 개념)
	
	// 2) 생성자는 필드 초기화 용도로 사용 
	// * 필드값의 선언시 값이 자동으로 0으로초기화되는 것과 다름

	
	// * 필드
	String model;
	int price;
	
	// * 생성자
	Computer(String pModel, int pPrice) {
		model = pModel; // * 필드값 초기화
		price = pPrice; // * 필드값 초기화2	
	}
	Computer() {
		
	}
	// => 이름이 같고 매개변수가 다르면, 메소드 오버로딩이 됨 * 
	
	// * 메소드
	void printComputerStatus() {
		System.out.println("모델: " + model);
		System.out.println("가격: " + price);
	}
	// => 실행문을 만들 때는 void, 매개변수 없음
	
	
	

	// 2. 디폴트 생성자(Default Constructor)
	// => 개발자가 생성자를 안만들면 자바가 자동으로 생성하는 생성자
	// => 아무일도 안하는 형태를 가짐
	
	// 1) 호출형태
	// ex) new Computer();
	// => new는 생성자 호출
	// => 디폴트 생성자의 형태
	
	// 생성자 메소드가 없을 때 자동으로 형성되며,
	// 만약 개발자가 생성자를 만들면 디폴트 생성자는 사용되지 않는다 *
	
}
