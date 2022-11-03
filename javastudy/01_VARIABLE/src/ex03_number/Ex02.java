package ex03_number;

public class Ex02 {

	public static void main(String[] args) {
		
		// 2. 증감연산자
		
		// => 1씩 증가하거나 감소
		// => 증가는 ++, 감소는 --
		
		// 1) 전위연산 (pre operator) 
		// ++a : 변수 a의 값을 1 증가시키고 연산
		int a = 1;
		int b = ++a; // a = 2, b = 2
		System.out.println(a);
		System.out.println(b);
		
		// 2) 후위연산
		// a++ : 연산 후에 변수 a의 값을 증가시킴
		int x = 1;
		int y = x++;
		System.out.println(x);
		System.out.println(y);
		
		// 연습 * 학습할떄나 보는코드, 실무에서는x
		int i = 1;
		int j = 1;
		int result = i++ + --j; // i=2, j=0, result=1
		// i와 j에서는 고민할필요없음, result에서 고민
		System.out.println(i);
		System.out.println(j);
		System.out.println(result);
		
		
		
		
		
		
		

	}

}
