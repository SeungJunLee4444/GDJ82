package ex04_hashmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	
	// 1. Map<k, v>
	
	public static void m1() {
		
		Map<String, String> dictionary = new HashMap<String, String>();
		// * key, value 모두 string타입이다
		
		// 1) 추가, 수정
		// => 새로운 key값을 사용하면 추가
		// => 기존의 key값을 사용하면 수정
		dictionary.put("apple", "사과");
		dictionary.put("banana", "바나나");
		dictionary.put("tomato", "토마토");
		dictionary.put("mango", "망고");
		dictionary.put("melon", "멜론");
		System.out.println(dictionary);
		dictionary.put("melon", "메론");
		
		// 2) 삭제
		// => 삭제할 요소의 key값을 전달하면 삭제됨
		// => 삭제된 value가 반환됨
		String removeItem = dictionary.remove("tomato");
		System.out.println(removeItem);	// "토마토"
		// => tomato 키값을 지우기
		
		// 3) value 확인
		System.out.println(dictionary.get("apple"));
	
	}
	
	public static void m2() {
		
	
		// 4) 기본타입 저장하는 법
		// (1) value를 string으로 관리
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("title", "어린왕자");
		map1.put("author", "생택쥐베리");
		map1.put("price", 10000 + "");	// * 텍스트가 아닌건 빈문자열을 더해서 작성
		System.out.println(map1);
		
		// (2) value를 object로 관리
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "홍길동전");
		map2.put("author", "허균");
		map2.put("price", 20000);
		// * key는 대체적으로 string 타입이다
		System.out.println(map2);
		
		// 5) value값 확인
		System.out.println(map2.get("apple"));
	}
	
	public static void m3() {
		
		// 6) for문 작성
		// * key, value를 합쳐서 entry라 한다
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("title", "소나기");
		map.put("author", "황순원");
		map.put("price", 20000);
		// * 세 개의 엔트리로 구성되있다
		
		// (1) entry를 이용한 for
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		// (2) key를 이용한 for
		for(String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}
		
		
		
		
		
	}
	
	public static void m4() {
		
		// 7) 연습
		// => title, author, price 정보를 가진 임의의 map 3개를 만들고,
		// => 생성된 map3개를 arraylist에 저장된 뒤
		// => arraylist에 저장된 map3개를 for문으로 순회하시오
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("title", "소나기");
		map1.put("author", "황순원");
		map1.put("price", 5000);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("title", "어린왕자");
		map2.put("author", "생택쥐베리");
		map2.put("price", 10000);
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("title", "홍길동전");
		map3.put("author", "허균");
		map3.put("price", 30000);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		// * map을 배열로 만듬
		
		for(Map<String, Object> map : list) {
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			System.out.println();
			// * list안에 map이 저장된 타입(실무에서 자주 볼 수 있음)
		}
		
		
		
	}
	
	public static void main(String[] args) {
		
		//m1();
		//m2();
		//m3();
		m4();
		
	}

}
