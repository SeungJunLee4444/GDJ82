package ex01_one_dim;

import java.util.Arrays;

public class Ex03_array {

	public static void main(String[] args) {
		
		// 3. 배열의 데이터타입
		// => int[] : 참조타입(reference type)
		
		// 4, 배열의 길이 늘리기
		// * 배열은 고유한 주소값을 가진 참조타입이며, 인덱스값으로 각 변수값을 참조한다
		// * 배열의 길이는 변경할 수 없음(선언한 순간 확정)
		
		// * 늘리는 법
		// (1) 원하는 길이의 새로운 배열 생성
		// (2) 기존 배열의 변수값을 모두 새로운 배열에 옮기고,
		// (3) 기존 배열의 참조값을 새로운 배열의 참조값으로 수정한다
		
		
		// 예제1
		// (1) 길이가 5인 배열을 사용하다 길이가 1000인 배열로 바꾸기
		int[] arr = {1, 2, 3, 4, 5};
		int[] temp = new int[1000];
		
		System.out.println(arr);
		// (2) 기존의 배열값을 모두 새로운 배열로 옮기고,
		System.arraycopy(arr, 0, temp, 0, arr.length);
		// arraycopy 메소드 활용
		// => arr[0]에 있는걸 temp[0]에 옮기는게 시작이며, arr.길이만큼 옮겨라 *
		System.out.println(arr);
		// (3) 기존 배열의 참조값을 새로운 배열의 참조값으로 수정한다
		arr = temp; 
		// => temp의 참조값을 arr에 저장한다
		
		// arr은 사라지지않고 temp의 요소값을 참조하게된다
		System.out.println(Arrays.toString(arr));
		System.out.println(arr.length);
		
		// * 배열값을 확인하는법
		System.out.println(Arrays.toString(arr));
		// => 콘솔에 배열의 변수값들을 문자열로 보여준다 *
		
		// ==> arr은 이제 메모리 누수가 된다(용량은 차지하는데, 쓸수없는 메모리) *
		System.gc(); //?
	}

}
