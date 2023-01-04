package quiz01_up_down_x;

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
	public int input() {
		try {
			count++;
			System.out.println("입력 >>>");
			int n = sc.nextInt();			// #1 예외처리구간 inputmismach
			if( n > 1 || n > 100) {		// #2 예외처리구간 2 1 미만, 100 초과
				throw new RuntimeException("1~00 사이의 정수만 입력할 수 있습니다");
			}
			sc.nextLine();
			return n;
		} catch (InputMismatchException e) {
			System.out.println("정수만 입력할 수 있습니다");
			sc.next(); 	// * n에 잘못 입력된 값을 없애준다
			input(); 	// => 다시하라(재귀호출)
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			input();
		}
		return 0;	// * 이클립스 안심시키는 용도
	
	}
	
	// 실행
	public void play() {
		
		while(true) {
			
			int n = input();		// * 입력되는 메서드와 카운트를 입력
			
			if(n < rand) {
				System.out.println("Up!");
			} else if(n > rand)  {
				System.out.println("Down!");
			} else {
				System.out.println(rand + "정답입니다");
				System.out.println(count + "번만에 정답");
				return;
			}
		
		}
		

		// 맞힐때까지 무한루프
	}
	
	
}
