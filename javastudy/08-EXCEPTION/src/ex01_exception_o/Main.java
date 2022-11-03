package ex01_exception_o;

import java.util.Scanner;

public class Main {
	
	public static void m1() {	
	
	// 1. null 오류
	
	// * static을 붙이는 이유 : m1을 부르는 애가 static이니까
	//  static은 static이 아닌걸 호출할 수 없음
	// => static이 들어가면 먼저 만들어졌다는 시점을 가지게 됨
	
	// --------------------------------------------------------------------
	
	// #1 NullPointerException : null값이 어떤 메서드를 호출할 때 발생
	String[] hobbies = new String[5];
	
	hobbies[1] = "수영";
	hobbies[2] = "골프";
	hobbies[3] = "영화";
	hobbies[4] = "집콕";
	
	for(int i = 0; i < hobbies.length; i++) {
		if(hobbies[i].equals("수영")) {
			System.out.println("취미가 나와 같군요");
		}
	}
	// => nullpointer 발생 : 인덱스 0, hobbies[0]이 equals를 호출하려하기 때문
	
	// #2 오류사항
	// at exception.Main.m1(Main.java:21)	=> 21번째 줄에 오류
	// at exception.Main.main(Main.java:33)	=> 33번째 줄
	
	// * stack trace : 오류창에서 오류를 추적하는 것을 말한다
	// => 빨간 경고창이 쌓이는 것을 stack, 이걸 추적하는것을 trace

		
	}

	public static void m2() {	
	
	// 1-1. null 회피
	// => null값이 아니다를 if문에 추가

	String[] hobbies = new String[5];
	
	hobbies[1] = "수영";
	hobbies[2] = "골프";
	hobbies[3] = "영화";
	hobbies[4] = "집콕";
	
	for(int i = 0; i < hobbies.length; i++) {
		if(hobbies[i] != null && hobbies[i].equals("수영")) {
			System.out.println("취미가 나와 같군요");
		}
		
		// # 주의사항
		//if(hobbies[i] != null && hobbies[i].equals("수영"))
		//if(hobbies[i].equals("수영") && hobbies[i] != null)	
		// => 두 코드는 다르다
		// * 코드는 왼쪽에서 오른쪽으로 실행되기 때문, 아래 코드는 null이 되버린다
	}
	
	}
	
	public static void m3() {
		
		// 3. NumberFormatException
		// => String을 number타입으로 변경할 때 발생
		// => 못바꾸는게 발생할 때 오류창에 발생
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력(필수) >>>");
		String name = sc.nextLine();
		System.out.println("나이 입력(선택) >>>");
		String strAge = sc.nextLine();		
		// & nextLine은 엔터도 입력받는다(아무것도 입력 안해도 입력된 것으로 인식)
		int age = Integer.parseInt(strAge);
		
		System.out.println("이름" + name + ",나이" + age + "살");
		
		// #1 오류 : 값을 전달하지 않고, 무언가 변환하려 시도할 때 발생
		// => strAge에 아무런 값을 입력하지 않고 엔터를 치면, 빈 문자열값을 변환하면서 오류
		
		//at exception.Main.m3(Main.java:80)
		//at exception.Main.main(Main.java:95)

	}
	
	public static void m4() {
		
		// 3-1. NumberFormatException 회피
		
		Scanner sc = new Scanner(System.in);
		System.out.println("이름 입력(필수) >>>");
		String name = sc.nextLine();
		System.out.println("나이 입력(선택) >>>");
		String strAge = sc.nextLine();
		
		int age;
		if(strAge.isEmpty()) {
			age = 0;
		} else {
			age = Integer.parseInt(strAge);
		}
		// => 
		
		System.out.println("이름" + name + ",나이" + age + "살");
		
		
		
	}





	public static void main(String[] args) {
		
//		m1();	// null
//		m2();	// 취미가 나와 같군요
//	m3();
		m4();
	}

}
