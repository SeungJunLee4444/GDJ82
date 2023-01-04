package prac1;

import java.util.Scanner;

public class Ex03 {

	public static void main(String[] args) {
		
		// 75832원
		
		// 75832 / 50000 -> 1
		// 75832 % 50000 -> 25832
		
		// 25832 / 10000 -> 2
		// 25832 % 10000 -> 5832
		
		// 이해
		// 암기
		// 문제해결
		
		int[] unit = {50000, 10000, 5000, 1000, 500, 100, 50, 10, 5, 1};
		int[] count = new int[unit.length];
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("금액 입력 >>> ");
		int money = sc.nextInt();
		
		for(int i = 0; i < unit.length; i++) {
			count[i] = money / unit[i];
			money = money % unit[i];
			System.out.println(unit[i] + "원 " + count[i] + "개");
		}
		
		sc.close();

	}

}
