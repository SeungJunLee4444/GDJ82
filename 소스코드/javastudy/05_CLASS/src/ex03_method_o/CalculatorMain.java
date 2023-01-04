package ex03_method_o;

public class CalculatorMain {

	public static void main(String[] args) {
		
		
		// # 클래스 선언
		Calculator calculator = new Calculator();
	
		// # 메서드 호출
		int answer1 = calculator.add(2, 3);
		// * add 메서드의 매개변수로 보내는 값은 '인수'다
		
		
//		int answer2 = calculator.sub(2, 3);
//		int answer3 = calculator.mul(2, 3);
//		double answer4 = calculator.div(2, 3);
		System.out.println(answer1);
//		System.out.println(answer2);
//		System.out.println(answer3);
//		System.out.println(answer4);	
	}

}
