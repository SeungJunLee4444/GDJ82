package prac1;

import java.util.Scanner;

public class Ex04 {

	public static void main(String[] args) {
		
		// 입력 받은 정수 -> 배열의 길이로 사용하기
		// 1~100만 가능
		Scanner sc = new Scanner(System.in);
		
		System.out.println("몇 개 생성할까요? >>> ");
		int count = sc.nextInt();
		
		if(count < 1 || count > 100) {
			System.out.println("1~100 사이만 가능합니다.");
			return;
		}
		
		int[] arr = new int[count];
		
		// 배열 arr에 난수(1~100) 저장
		// 중복 생성되면 재생성
		for(int i = 0; i < arr.length; i++) {
			int random = (int)(Math.random() * 100) + 1;
			// random 검사 메소드
			// 중복이 있으면 true 반환, 중복이 없으면 false 반환
			// exists(배열arr, 인덱스, 난수)
			if(exists(arr, i, random)) {
				i--;
				continue;
			}
			arr[i] = random;
		}
		
		// 배열 arr의 출력
		// 한 줄에 10개씩
		
		for(int i = 0; i < arr.length; i++) {
			if(i != 0 && i % 10 == 0)
				System.out.println();
			System.out.print(arr[i] + "\t");
		}
		
	}  // main
	
	public static boolean exists(int[] arr, int idx, int random) {
		
		// 배열arr의 인덱스 0부터 idx까지 random이 존재하는가?
		for(int i = 0; i <= idx; i++)
			if(arr[i] == random)
				return true;
		
		return false;
		
	}

}
