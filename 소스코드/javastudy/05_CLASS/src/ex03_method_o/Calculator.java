package ex03_method_o;

public class Calculator {
	
	// [2. 메서드]
	// => 클래스의 함수를 메서드라 한다
	
	// # 계산 기능(메소드)
	
	// ex) add 메소드 생성
	// add는 메소드명 / int는 반환타입 / 매개변수는 int a, b
	
	int add(int a, int b) {			
		int result = a + b;			
		return result;			
	}
	// * 반환타입과 매개변수의 타입, 반환한 값을 저장하는 값은
	// 모두 같은 데이터타입이어야한다
	
//	int sub(int a, int b) {
//		int result = a - b;
//		return result;
//	} 
//	
//	int mul(int a, int b) {
//		int result = a * b;
//		return result;
//	} 
//	
//	double div(int a, int b) {		// => 소수점 표현을 위해 double 선언
//		double result = (double)a / b;
//		return result;
//	} 

}
