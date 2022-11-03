package ex01_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	// & 15일차	:	컬렉션 프레임워크
	
	public static void m1() {
		
		// 1. 생성
		// 1) 제네릭(generic) 기반
		// => 생성할 때, 데이터 타입을 결정하는것(구체화)
		
		List<String> list = new ArrayList<String>();
		// => list(인터페이스), arraylist(자식클래스) 배열 생성
		// # 추천시 보라색기호는 인터페이스를 말한다
		
		// 2. 요소 추가
		// 1) 인덱스 지정이 없으면 순서대로 저장
		list.add("월");
		list.add("화");
		// 2) 인덱스 지정도 가능
		list.add(0, "일");					// 일, 월, 화

		// 3. 요소 제거
		// 1) 문자열 제거
		boolean result = list.remove("일");	// 월, 화
		System.out.println(result);	// true
		// 2) Object remove(int index)		: index 위치의 요소 제거, 제거한 요소 반환
		
		
		// 4. 요소 제거 + 제거한 내용 확인하는법
		String removeItem = list.remove(0);	// 화
		System.out.println(removeItem);	// 월
	
		
		// 5. 요소 수정
		list.set(0, "일");	// 화 -> 일 로 수정
		
		
		// # 리스트 확인
		System.out.println(list);	
		
		
	}

	public static void m2() {
		
		// 5. 리스트 초기화 작업
		// => 배열을 리스트로 변환하는 과정을 말한다
		List<String> list = Arrays.asList("일", "월", "화", "수");
		// *  초기화된 배열은 배열의 사이즈가 고정된다
						
		// * (String a) => String이 a뿐이다
		// * (String ...) => String이 여러개 들어갈 수 있음(갯수제한x)	
		System.out.println(list);
		
				
		// 6. 리스트 길이
		int size = list.size();
		System.out.println(size);	// 배열의 길이는 4
		
		
		// 7. 개별요소 확인
		String element1 = list.get(0);
		String element2 = list.get(1);
		String element3 = list.get(2);
		String element4 = list.get(3);
		System.out.println(element1);
		System.out.println(element2);
		System.out.println(element3);
		System.out.println(element4);
		
		// 8. for문 순회
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		// => size 메서드를 4번이나 반복하기 때문에 성능이 떨어지게된다
		// * 개선	: list.size를 한번만 호출할수 있도록 만들기
		// => for문 size메서드를 초기화로 한번만 진행---------------------?
		for(int i = 0, length = list.size(); i < length; i++) {
			System.out.println(list.get(i));
		}
		
		// 9. 향상 for문
		for(String element : list) {
			System.out.println(element);
		}
		
		
	}
	
	public static void m3() {
		
		// 10. Wrapper
		// => generic은 참조타입만 사용 가능
		// => 기본타입이 필요한 경우, 기본타입의 Wrapper 클래스를 사용한다
		
		// * wrapper은 자바의 기본타입을 클래스화한 8개의 클래스를 말한다
		// => Integer, Long, Byte, Character, Boolean, Float, Double
		// => 우리 변환할때 많이봄
		
	//	List<int> list = new ArralList<int>():	// x
		
		List<Integer> list = new ArrayList<Integer>();	// o
		
		list.add(1);	// 저장
		System.out.println(list);
		
		// 11. 비어있는 리스트인지 확인
		boolean result = list.isEmpty();
		System.out.println(result);
		
		// 12. 특정요소를 포함하고 있는지
		if(list.contains(4)) {
			System.out.println("4를 포함한다");
		} else {
			System.out.println("4를 포함하지 않는다");
		}
		
	}
	
	public static void main(String[] args) {
		
		m1();
		//m2();
		//m3();
		
		

	}

}
