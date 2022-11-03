package ex04_condition;

public class Ex02 {

	public static void main(String[] args) {
		
		// 2. 논리연산자
		// and : &&, 모두 true이어야, 값이 true
		// or : ||, 둘중 하나라도 true면, 값도 true
		// not : !, 값을 반대로 바꿈, true는 false로 바꿔주고, false는 true로 바꿔줌 *
		
		int a = 10;
		int b = 10;
		boolean result1 = (a == 10) && (b == 10); // true
		boolean result2 = (a == 10) || (b == 10); // true
		boolean result3 = (a == 10) && (b == 20); // false
		boolean result4 = (a == 10) || (b == 20); // true
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		
		int c = 10;
		boolean result5 = !(c == 10); // false *
		boolean result6 = !(c == 20); // true *
		
		System.out.println(result5);
		System.out.println(result6);
		
		// 연습1
		// 변수 n을 10 증가시킨 뒤 100보다 크다면 true 아니면 false
		int n = 95;
		n += 10;
		boolean result7 = (n > 100);
		boolean result9 = (n += 10) > 100; // *
		System.out.println(n);
		System.out.println(result7); // true
		
		// 연습2
		// 변수 x를 1 증가시킨 뒤 x가 10과 같으면 true, 아니면 false
		int x = 9;
		x +=1;
		boolean result8 = (x == 10); // true
		boolean result10 = (++x) == 10; // *
		System.out.println(x);
		System.out.println(result8);
		
		// 논리연산자 특징: short circuit evaluation **
		// && : false가 발생하면 더이상 진행하지 않는다, 최종결과가 false기 때문
		// || : true가 발생하면 더이상 진행하지 않는다, 최종결과가 true기 때문
		int i = 10;
		int j = 10;
		boolean result11 = (i == 20) && (++j == 11);
		System.out.println(result11);
		System.out.println(j); // 10
		// j가 10인 이유는 앞에 조건이 false기 떄문에 j가 실행되지 않았음 *
		
		boolean result12 = (i == 10) || (++j == 11); 
		System.out.println(result12);
		System.out.println(j); // 10
		// or은 하나라도 true면 값이 true기 때문에 j가 실행되지 않는다
		

	}

}
