package ex02_loop;

public class Ex03_break {

	public static void main(String[] args) {
		
		// 3. break
		// switch 뿐 아니라 반복문(for, while)에서도 사용
		
		// 예제 : 
		// 모금목표: 100000원
		// 한번에 30원씩 모금
		
		// 1회 모금 30원 현재30원, 2회 모금 30원 현재 60원....
		
		int total = 0;
		int money = 30;
		int goal = 100000;
		
		int serial = 0; // => 모금횟수
		
		while(true) { // => 조건문을 따로 넣지 않고, 무한루프 *
			
			if(total >= goal) {
				break;				// => if문을 추가하여 조건을 만족하면 break로 멈추게 함 * 
			} 
			total += money;
			serial++;
			System.out.println(serial + "회 모금액" + money + "\t현재" + total + "원");
		} 
//		System.out.println(); // => 오류발생

	}

}
