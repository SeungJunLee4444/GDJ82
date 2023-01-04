package ex03_finally_o;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		// 1. finally 블록
		// => try-catch문 마지막에 추가하는 블록
		// => 예외가 있든 없든 무조건 마지막에 실행되는 블록
		// * finally는 자원을 반납할 때 사용된다(대부분 close가 들어간다)
		Scanner sc = new Scanner(System.in);
		try {
//			Scanner sc = new Scanner(System.in);
			System.out.println("나이 입력 >>>");
			String strAge = sc.nextLine();
			int age = Integer.parseInt(strAge);
			System.out.println(age > 20 ? "성인" : "미성년자");
//			sc.close();
		} catch (Exception e) {
			System.out.println("예외발생");
		} finally {
		
			System.out.println("finally 블록 실행");
		}
		// #1 sc.close 위치문제
		// => 13에서 오류가 생기면 14,15~ 이후가 작동하지 않기 때문
		// #2 해결 : finally 블록에 sc.close 넣기
		
		// & 모든 변수와 객체는 {} 안을 벗어날 수 없다
		// & 범위에 따라 코드의 위치, 범위를 조정하는 것을 스코프 조정이라 한다
		
		
	}

}
