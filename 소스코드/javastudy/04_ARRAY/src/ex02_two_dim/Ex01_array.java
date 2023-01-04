package ex02_two_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// 1. 2차원 배열
		// => 1차원 배열을 여러개 관리하는 자료 구조
		// => 1차원 배열들의 길이는 다를 수 있음
		
		// 1) 2차원 배열의 선언과 생성
			// (1) 2차원 배열의 선언
			// int[][] arr;
			
			// (2) 2차원 배열의 생성
			// {1} arr = new int[3][2]; => 3행 2열구조 (김밥이 3줄, 줄마다 2조각)
			// {2} arr = new int[3][n]; => 3행 n열구조 (배열이 생성된게 아님 *)
			//	   arr[0] = new int[5];	=> 1번째 1차원 배열(1번째 김밥은 5조각)
			//	   arr[1] = new int[3];	=> 2번째 1차원 배열(2번째 김밥은 3조각)	
			//	   arr[2] = new int[8];	=> 3번째 1차원 배열(3번째 김밥은 8조각)	
			
	
		// 2) 2차원 배열의 각 요소
		// => 요소는 데이터 하나하나를 말한다 *
		
			// (1) 인덱스를 2개를 사용
			// => 몇번째 1차원 배열인가, 해당 배열의 몇번째 요소인가
		
			// (2) 2차원 배열이 관리하는 1차원 배열
			// arr[0]
			// arr[1]
			// arr[2]
		
			// => 2차원 배열은 1차원 배열을 참조하기 때문에, 
			// 혼자서 반환은 불가능하다 *
															
			
		
			// (3) 각각의 1차원배열이 관리하는 배열 요소
			// arr[0][0], arr[0][1]
			// arr[1][0], arr[1][1]
			// arr[2][0], arr[2][1]
		
		
		// 3) 예시
		// => 3행 2열 2차원 배열
		int [][] arr1 = new int[3][2];
		for(int i = 0; i < arr1.length; i++)  {
			for(int j = 0; j < arr1[i].length; j++ ) {
				System.out.print(arr1[i][j] + "\t");
			} System.out.println();
		}
		
		
		
		// => 3행 n열 2차원 배열
		int [][] arr2 = new int[3][];
		arr2[0] = new int[5]; // 1번째 1차원배열의 길이는 5
		arr2[1] = new int[4]; // 2번째 1차원배열의 길이는 4
		arr2[2] = new int[8]; // 3번째 1차원배열의 길이는 8
		
		for(int i = 0; i < arr2.length; i++)  {
			for(int j = 0; j < arr2[i].length; j++ ) {
				System.out.print(arr2[i][j] + "  ");
			} System.out.println();
		} 
		
		
		
		
		
			
		}
	}


