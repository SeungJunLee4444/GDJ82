package quiz02_up_down_o;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UpDownGame {
	
	// 필드
	private int rand; 	// 1~100
	private int count;	// 시도횟수
	private Scanner sc;
	
	
	// 생성자
	public UpDownGame() {
		rand = (int)(Math.random() * 100 +1);
		sc = new Scanner(System.in);
	}
	
	// 입력
	public int input()  { 	// #1 public int input() throws RunTimeException, inputMisMatchException 
					// => 런타임과 인풋 생략을 던진다(uncheckexception에 속하기 때문에 생략해도됨)
			count++;
			System.out.println("입력 >>>");
			int n = sc.nextInt();			// #1 예외처리구간 inputmismach
			if( n < 1 || n > 100) {		// #2 예외처리구간 2 1 미만, 100 초과
				throw new RuntimeException("1~100 사이의 정수만 입력할 수 있습니다");
			}
				return n;
			
				
			
			
	}
	
	// 실행
	public void play() {
		
		while(true) {
			
			try {
				int n = input();		
				
				if(n < rand) {
					System.out.println("Up!");
				} else if(n > rand)  {
					System.out.println("Down!");
				} else {
					System.out.println(rand + "정답입니다");
					System.out.println(count + "번만에 정답");
					break;
				}
		
				} catch (InputMismatchException e) {
					sc.next();
					System.out.println("정수만 입력 가능합니다");
				} catch (RuntimeException e) {
					e.getMessage();
				}
					
		}
		

		// 맞힐때까지 무한루프
	}
	
	
	
}
