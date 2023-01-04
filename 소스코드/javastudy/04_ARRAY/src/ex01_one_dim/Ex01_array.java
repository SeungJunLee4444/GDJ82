package ex01_one_dim;

public class Ex01_array {

	public static void main(String[] args) {
		
		// 1. 배열(array)
		// => 여러개의 변수를 하나의 이름으로 관리하는 자료구조
		
		// 1) 구성요소
			// (1) 인덱스 : 각 변수의 위치정보(0에서시작)
			// (2) 배열명 : 모든 변수를 관리하는 하나의 이름
		
		// => 각 변수는 배열명에 대괄호[]와 인덱스를 붙여서 구분
		
		//--------------------------------------------------------
		
		// 2) 배열선언 및 생성
			// (1) 배열선언
			// int[] arr; //자바 *
			// int arr[]; //c언어연계
			// => 대괄호까지 합쳐서 하나의 타입으로 생각하는게 좋다 *
			// => 그런 이유로 첫번째 방법으로 선언할 것 *
		
			// (2) 배열 생성
			// arr = new int[3]; 
			// => []에 적은 개수만큼 배열을 생성 (index값과 혼동 주의 *)
			
			// (3) 배열 선언 및 생성
			// => int[] arr = new int[3];
		
		//--------------------------------------------------------
			
		// 3) 배열요소
			// (1) 배열로 관리되는 각각의 변수
			// (2) 모든 배열요소의 호출
			// arr[0]
			// arr[1]
			// arr[2]
			// => 배열에서 관리되는 각각의 변수들(변수명은 같고, index로만 구분)
		
			// (3) 배열요소는 자동으로 초기화된다(어떤 값을 가진다)
			// 값이 없음을 의미하는 0, 0.0, false, null
			// (boolean에서 0에 해당하는 값은 false다/ 없다 => 거짓 * )
			// (string에서 null은 값이 없는 것 / ""는 빈 문자열이라는 값을 지닌 것 * )
		
	
		//--------------------------------------------------------

		// 4) 배열의 장점
			// (1) 변수 3개가 있을 때,
			// 일반변수						배열
			// int a, b, c;					int[] arr = new int[3];
			// System.out.println(a);		for(int i = 0; i < 3; i++) {
			// System.out.println(a);			System.out.println(arr[i]);					
			// System.out.println(a);		}
		
			// => 배열은 for문을 이용해 효율적으로 값을 반환할 수 있다 *
		
		//--------------------------------------------------------
		
		int[] arr = new int[26];
		for(int i = 0; i < 3; i++) { // 배열에서 for문변수값은 i, j, k를 써라 *
			System.out.println(arr[i]);
		}
		// => 0, 0, 0
		
		arr[0] = 100; // => 0인덱스값에 100을 주고 싶을때, 변수다루듯 쓸것
		arr[1] = 50;
		arr[2] = 80;
		
		for(int i = 0; i < arr.length; i++) { // 배열에서 for문변수값은 i, j, k를 써라 *
			System.out.println(arr[i]);
		}
		
		// 1) 평균 구하기
		
		int total = 0;
		for(int i = 0; i < arr.length; i++) { // 배열에서 for문변수값은 i, j, k를 써라 *
			total += arr[i];
		}
		double average1 = total / 3.0;			// 3을 3.0으로 *
		double average2 = (double)total / arr.length;	// double 캐스팅 *
		System.out.println("평균:" + average1 + "점");
		
		
		// 2) 배열의 길이(arr.length) *
		// 만약 int배열 인덱스값이 3이 5가 되면, 밑에 관련 값도 전부 5로 바꿔야한다(종속 *)
		// => length값으로 처리
		// => 배열의 길이
		// new int[3];
		// i < 3; => i < arr.length
		
		
		
	}

}
