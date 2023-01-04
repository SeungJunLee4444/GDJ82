package ex04_throw_o;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		// 1. throw 던지기
		// => 예외 객체를 만들어서 직접 던질 수 있음
		// => 자바는 예외로 인식하지 않지만 실제로 예외인경우 발생
		// ex) 나이를 50000살 입력했을 때
	
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("나이 입력 >>>");
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);
			if(age < 0 || age > 100) {
				throw new RuntimeException("나이는 0 이상 100 이하만 가능합니다");
				// #1 catch e한테 던지는 것
				// #2 runtime은 문자열타입으로 메시지 입력가능
				
			}
			
			System.out.println(age > 20 ? "성인" : "미성년자");		
		} catch (Exception e) {
			// * 위의 "예외 메시지"는 객체 e에 들어가있다
			System.out.println(e.getMessage());
			// #3 runtimeexception의 string 메시지는 자식클래스 -> 부모클래스의 필드의 메시지로 저장
			// =>부모클래스 exception를 자식클래스 runtimeexception가 상속한다 
			// => exception e = new runtimeexception();
			// => 부모 클래스의 멤버만 참조한다
			
			
		} finally {
			sc.close();
			System.out.println("finally 블록 실행");
		}
	
		
	}

}
