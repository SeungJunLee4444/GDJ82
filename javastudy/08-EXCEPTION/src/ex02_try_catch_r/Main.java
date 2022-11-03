package ex02_try_catch_r;

import java.io.File;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	public static void m1() {
		try {
			
			String[] hobbies = new String[3];
			hobbies[1] = "swimming";
			hobbies[2] = "running";
			for(String hobby : hobbies) {
				System.out.println(hobby.substring(0, 2));
			}
	} catch(NullPointerException e) {
				// # 무조건 예외클래스 객체명은 e
		System.out.println("NullPointerException 발생");
		// NullPointerException 자리에 부모 클래스인 RuntimeException, Exception 전부 들어올 수 있다
		// exception은 모든 예외클래스의 슈퍼클래스기 때문에 모든 경우에 통용된다
	}
		
		// #1 오류가 발생하지 않음
		// => null값이 있다고 무조건 nullpointer가 발생한는 것은 아니다
		// => null값이 메서드를 부를때 발생
	}
	
	
	
	public static void m2() {
		
		// 1. 문자열을 배열로 나눠 저장하고 인트배열로 변환하기			?
		try {
			String input = "20, 21, 22, 23, 24, 25";


			String[] inputs = input.split(",");		// & split(",") 안에 들어간 기호를 기준으로 배열로 나눔
		
			int[] ages = new int[inputs.length];	
			for(int i = 0; i < inputs.length; i++) {
				ages[i] = Integer.parseInt(inputs[i]);
				System.out.println("변환값:" + ages[i]);
			}
			
			// #1 오류발생 : split을 사용할 때, 공백이 들어가면 배열처리가 안된다
			// => 값이 출력되지 않기 때문에 변환하려 하면 numberformat 오류 발생 
			
			// & 데이터 전처리
			
			// => input ,가 두개일 때: split는 사이 공간도 배열로 인식한다
			// => ,,도 ,로 인식하게 만들려면 : replace
		} catch (NumberFormatException e) { // #2 세가지 클래스 전부 가능
			System.out.println("NumberFormatException 발생"); 
		
		} catch (Exception e) {	
									
			// # 3 다중예외처리
			// => 위에서부터 순서대로 처리된다
			// # 4 주의 부모클래스 catch는 자식 - 부모 순으로 작성할 것
			// (부모가 위에 있으면 전부 처리해버리기 때문에)
			System.out.println("Exception 발생");				
		}
	}
	
	public static void m3() {

		
		// 2. 연습문제 
		// 예외 발생 확인 후 , try-catch 문 넣기(exception , runtimeException 사용x)
		try {
			Scanner sc= new Scanner(System.in);
			System.out.println("정수1 >>>");
			int a = sc.nextInt();
			System.out.println("정수2 >>>");
			int b = sc.nextInt();
			System.out.println(a + "/" + b + "=" + (a / b));
			System.out.println(a + "%" + b + "=" + (a % b));
			System.out.println(a + "+" + b + "=" + (a + b));
			System.out.println(a + "-" + b + "=" + (a - b));
			System.out.println(a + "*" + b + "=" + (a * b));
			System.out.println(a + "/" + b + "=" + (a / b));
			System.out.println(a + "%" + b + "=" + (a % b));
			sc.close();
		} catch (ArithmeticException e) {		// ex) 0 / 0 은 안됨
			System.out.println("ArithmeticException 발생");
		} catch (InputMismatchException e) {	// ex) int 타입이 아닌 것(실수, 문자, 20억 이상)
			System.out.println("InputMismatchException 발생");
		}
	} 

	public static void m4() {
		
		// 3. try-catch문이 없으면 실행불가능한 checked Exception 문 
		
		try {
		File file = new File("C:\\sample.txt");
		FileReader fr = new FileReader(file);
		// # 특정파일을 읽어보려는 코드
		
		// #1 오류발생 : 파일이 없거나, 읽히지 않거나(io)
		
		} catch (Exception e) {
			
		}
		
		
		
	}

	
	public static void main(String[] args) {
		
		//m1();
		m2();
		//m3();
		//m4();
		
	}
	
}

