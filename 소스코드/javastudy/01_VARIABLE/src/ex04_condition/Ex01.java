package ex04_condition;

public class Ex01 {

	public static void main(String[] args) {
		
		// 1. 비교연산자
		// 초과 : >
		// 미만 : <
		// 이상 : >=
		// 이하 : <=
		// 같다 : == ( = : 대입연산, 저장을 의미)
		// 같지 않다 : != 
		// 연산의 결과는 boolean이다 (true, false으로 나온다)
		
		int a = 7;
		int b = 2;
		boolean result1 = a > b;
		boolean result2 = a < b;
		boolean result3 = a >= b;
		boolean result4 = a <= b;
		boolean result5 = a == b;
		boolean result6 = a != b;
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		System.out.println(result6);
	}

}
