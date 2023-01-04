package ex06_method_overload_o;

public class CalculatorMain {

	public static void main(String[] args) {
		
		Calculator calculator = new Calculator();
		
		System.out.println(calculator.add(1, 1));
		System.out.println(calculator.add(1, 1, 1));
		System.out.println(calculator.add(1, 1, 1, 1));
		
		int[] arr = {1, 2, 3, 4, 5};
		
		int ttt = calculator.add(arr);
		
		System.out.println(calculator.add(arr));
		
		
		// calculator의 arr과 위의 arr은 다른 변수(이름만 같음) *
		
		
	}

}
