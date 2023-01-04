package ex02_set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Name {
	
	public static void m1() {
		
		// 1. 세트 생성
		// => generic 사용
		Set<String> set = new HashSet<String>();
		
		// 2. 요소추가
		set.add("일");
		set.add("월");
		set.add("화");
		set.add("수");
		set.add("수");	// *1 중복 불가능
		System.out.println(set);
		// *2 순서없이 저장된다(= 인덱스가없다) 
		
		// 3. 요소 제거
		// * index가 없기 때문에 문자열 삭제만 가능
		set.remove("일");	
	}

	public static void m2() {
		
		// 4. 세트의 초기화
		// => 리스트를 세트로 변환하는 방식으로 초기화
		Set<String> set = new HashSet<String>(Arrays.asList("일", "월", "화", "수"));
		
	
		// 5. 세트의 길이
		int size = set.size();
		
		// 6. 향상 for문
		// * 인덱스가 없으니 일반 for문 불가능
		for(String element : set) {
			System.out.println(element);
		}
}
	
	public static void m3() {
		
		Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(3, 4, 5, 6, 7));
		Set<Integer> set3 = new HashSet<Integer>(Arrays.asList(3, 4, 5, 6, 7));
		// * aslist는 배열을 list에 담아 저장
		
		// 7. 교집합
		set1.retainAll(set2);
		System.out.println(set1);	// [3, 4, 5]
		// * 교집합 결과는 retainAll 메서드를 호출한 set에 저장
		
		// 8. 합집합
		set1.addAll(set2);
		System.out.println(set1);	// [3, 4, 5, 6, 7]
		// * 마찬가지로 호출한 객체에 값을 저장
		
		// 9. 차집합
		// => 겹치는 부분을 제외한 값을 저장
		set1.removeAll(set2);		// []
		System.out.println(set1);
		// * 마찬가지로 호출한 객체에 값을 저장
		
		// 10. 부분집합 여부 판단(boolean)
		boolean result1 = set1.containsAll(set3);
		// => set3가 set1의 부분집합이면 true
		boolean result2 = set2.containsAll(set3);
		System.out.println(result1);
		System.out.println(result2);
		
		
	}
	
	public static void m4() {
		
		// 11. 중복여부 필터링에 사용
		// * 중복요소가 있는 리스트를 세트로 변환, 다시 리스트로 변환
		List<String> list = new ArrayList<String>();
		list.add("일");
		list.add("월");
		list.add("화");
		list.add("화");	// #1 화요일 중복
		System.out.println(list);

		Set<String> set = new HashSet<String>(list);
		System.out.println(set);
//		
		list.clear(); 	// * list 요소 모두 제거
//		
		list = new ArrayList<String>(set);
//		System.out.println(list);
		System.out.println(list);
//		
		// => 화 삭제 / 순서 삭제
		
	}
	
	public static void main(String[] args) {
		//m1();
		//m2();
		//m3();
		m4();
	}
}
