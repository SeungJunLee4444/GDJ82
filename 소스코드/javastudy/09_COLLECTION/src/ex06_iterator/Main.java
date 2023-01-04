package ex06_iterator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Main {
	
	public static void m1() {
		
		// 2. Iterator 인터페이스(반복자)
		// => 컬렉션을 반복시키기 위한 표준 인터페이스
		// ex) Iterator의 메서드(3가지)
		// public interface Iterator {
		// boolean hasNext();			// => 남은 요소가 있는지 참, 거짓여부 확인
		// Object next();				// => 있는 요소를 가져오기
		// void remove();				// => 있는 요소 제거하기
		// }
		
		// => set, list, map을 저장하여 사용한다
	
		// 1) 메서드
		// (1) hasNext()	:	남아있는 요소가 있으면 true 반환
		// (2) next()		: 	요소를 하나 반환
		// => 주로 set에서 사용, list는 for이 있으니까
		
		Set<String> set = new HashSet<String>();
		
		set.add("제육");
		set.add("닭갈비");
		set.add("돈까스");
		set.add("김치찌개");
		
		// 2) set을 조회할 반복자 itr
		// => set을 iterator에 저장
		
		Iterator<String> itr = set.iterator();
		// => iteractor은 주머니에 들어가는 손
		// => 주머니 안에서 뭐가 잡혔다는걸 hasnext라 한다

		
		// 3) 반복
		// => while문 사용
		
		while(itr.hasNext()) {
			String element = itr.next();
			System.out.println(element);
		}
	}
	
	public static void m2() {
		
		// 3. HashMap과 iterator
		// => keyset() 메서드로 key만 set에 저장
		// => key를 저장한 set에 iterator을 등록해 사용
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", 1);
		map.put("column", "제목");
		map.put("query", "날씨");
		
		// * while, iterator을 이용해 조회
		
		Set<String> keys = map.keySet();	// => key만 쭉 빠져서 나열
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()) {
			String key = itr.next();
			Object value = map.get(key);
			System.out.println(key + ":" + value);
		}
		
		// 1) set을 사용(
		// 2) set의 객체를 iterator에 삽입
		// 3) while, hasnext, next를 사용
	}
	
	public static void main(String[] args) {
		
		m1();
		m2();
		
	}

}
