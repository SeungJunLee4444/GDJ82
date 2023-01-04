package ex01_one_dim;

public class Ex02_array {

	public static void main(String[] args) {
		
		// 2. 배열의 초기화
		// (1) []안의 값을 지우고 중괄호에 새로운 값을 나열
		int[]arr = new int[] {10, 20, 30, 40, 50};
		
		// (2) [] 자체를 지워버림
		int[]arr2 = {10, 20, 30, 40, 50};
		
		
		// 3. 배열 기본 알고리즘
		// 1) 평균구하기
		
		
		// 2) 최대값 최소값 구하기 **
		int max = arr[0];
		// int max2 = 0;
		int min = arr[0];
		// int min2 = 100; // * 최소값은 0을 줘선 안된다
		
		// => 최대값의 기본값은 가장 작게, 최소값은 가장 크게 적용해야한다
		
		// * 최대,최소 배열
		// => 좋은 해결법은 최대값, 최소값 전부 기본값을 배열[0]값을 넣는것이다 
		// => for문의 시작값도 1로 바꾸기
		
		for(int i = 1; i < arr.length; i++) {
			if(max < arr[i]) {
				max = arr[i]; // max값이 10 => 20 => 30 => 40 => 최종적으로 50이됨
			} 
			if(min > arr[i]) { // 0은 10보다 클수 없기 떄문에 값이 0이 나와버린다 * 
				min = arr[i];
			}
		}
		System.out.println("최대:" + max);
		System.out.println("최소:" + min);
		
		
		
		
		
		
		
	}

}
