package ex04_method_o;

public class VendingMachine {

	// [메서드]
	
	// # 연습1 
	
	// 커피뽑기, 한잔당 천원, 커피메뉴 버튼1: 아메리카노, 버튼2: 까페라떼
	// # 아메리카노 한잔, 까페라떼 두잔이 출력되는 것이 목적 
		
	// * 배열을 이용한 풀이
	String getCoffee(int money, int button) {
		String[] menu = {"아메리카노", "까페라떼"};
		
		return menu[button-1] + " " + (money / 1000) + "잔";
		// => button의 값과 인덱스값을 연계한 풀이
		// * return은 
		// (1) 반복문의 완전한 탈출, (2) 메서드의 값을 반환, 그 형태를 정할 수있다
	}
		

	
		

}