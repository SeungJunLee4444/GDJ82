package ex04_input;

import java.util.Scanner;

public class Ex02_Scanner {

	public static void main(String[] args) {

		// 2. Scanner * 자바 기초공부할때나 잠깐 볼 것
		// * 시험에 나옴
		// => JOptionPane보다 이걸 쓰는게 나음
		
		// java.util.Scanner 클래스
		// => 데이터 타입별로 입력받을 수 있는 메소드를 제공
		
		// (int, long, double, string 메소드가 따로따로 있음)
		// => int		: nextInt();
		// => long		: nextLong();
		// => double 	: nextDouble();
		// => String	: nextLine(); -공백포함가능, next() -공백포함 불가능 *
		

		Scanner sc = new Scanner(System.in);
		// => 객체 sc는 System.in으로부터 입력을 받는다
		// => System.in은 키보드
		
		System.out.print("이름을 입력하세요 >>>  ");
		String name1 = sc.next();
		
		
		System.out.println(name1);
	
		// => String을 입력받을 때 next를 쓴다
		
		System.out.print("나이를 입력하세요 >>>");
		int age = sc.nextInt();
		// => int를 입력받을 때 nextInt를 쓴다
		
	
		
		
		
		
		sc.close(); //생략 가능
		
		// => 선언한 타입에 맞게 입력해야함
		
		
		//----------------------------------------------------------------------
		
		// [Scanner]
		
		// 연습1
		Scanner sc1 = new Scanner(System.in);
		System.out.println("성별(남/여)을 입력하세요 >>> ");
		char gender = sc1.next().charAt(0);
		System.out.println(gender);
		
		sc.close(); // 생략 가능하다
		
				
	
	}

}
