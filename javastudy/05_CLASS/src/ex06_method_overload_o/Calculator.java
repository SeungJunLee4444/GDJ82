package ex06_method_overload_o;

public class Calculator {

	// # 메서드 오버로딩
	// => 이름이 같은 메서드들이 각자 매개변수 타입, 개수가 서로 다를때
	// 반환타입은 영향을 주지 않는다
	// * 반환타입, 반환값의 차이는 오버로딩에 영향을 주지 않는다 *
	
	
	int add(int a, int b) {
		return a + b;
	}
	// # 반환 타입이 다르든, 반환값이 다르든 영향이 없다
	double add(double a, int b) {
		return a + b;
	}
	double add(int a, double b, int c) {
		return a - b;
	}
	int add(int a, int b, int c, int d) {
		return a + b + c + d;
	}
	
	int add(int[] arr) {
		int total = 0;
		for(int n : arr) {
		total += n;
		}
		return total;
	}
	
	
}
