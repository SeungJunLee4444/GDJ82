package quiz03_random;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
	
	public static void main(String[] args) {
		
	
		Scanner sc = new Scanner(System.in);
		System.out.println("몇 개의 랜덤을 생성할께요 >>>");
		int cnt = sc.nextInt();
		
		if(cnt < 1 || cnt >100) {
			System.out.println("다음에는 1~100 사이로 입력하세요");
			return;
		}
		
		int[] arr = new int[cnt];	
		
		Set<Integer> set = new HashSet<Integer>();			// * set이기 때문에 중복된 수는 출력x
		while(set.size() < cnt) {	// * cnt 만큼 출력되고 멈춘다
			set.add((int)(Math.random() * 100 + 1));
		}
		int idx = 0;
		for(Integer n : set) {
			arr[idx++] = n;
		}
		
		for(int i = 0; i < cnt; i++) {
			System.out.println(arr[i] + " ");
			if((i + 1) % 10 == 0) {	// * 10개부터 줄바꿈
				System.out.println();
			}
		}
	}

}
