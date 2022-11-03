package ex05_treemap;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import javax.print.DocFlavor.STRING;

public class Main {
	
	public static void m1() {
		
		// & 16일차	:	컬렉션프레임워크(2)	- treemap
		
		// 1. 이진트리(binary tree)
		// => 모든 노드는 2개의 자식을 가질 수 있다
		// => 작은 값은 왼쪽, 큰 값은 오른쪽에 저장한다
		
		// * treemap
		// => key값을 기준으로 왼쪽에 작은값, 오른쪽에 큰 값이 저장
		// => key를 기준으로 자동으로 정렬되면서 저장된다 *
		// => 크기 비교 및 범위 연산에 적절하다
		
		// 1) 추가
		
		Map<Integer, String> map = new TreeMap<Integer, String>(); 
		
		map.put(65, "제시카");
		map.put(85, "에밀리");
		map.put(35, "제임스");
		map.put(96, "사만다");
		
		System.out.println(map);
		
		// {35=제임스, 65=제시카, 85=에밀리, 96=사만다}
		// => key값을 기준으로 순서대로 정렬
		
		// 2) 순회
		for(Map.Entry<Integer, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	public static void m2() {
		
		// 3) 순서를 바꾸기
		// => 기본정렬은 오름차순
		// => decendingMap()를 사용하면 오름차순 -> 내림차순으로, 내림차순은 오름차순으로 *
		
		TreeMap<Integer, String> map = new TreeMap<Integer, String>(); 
		
		map.put(65, "제시카");
		map.put(85, "에밀리");
		map.put(35, "제임스");
		map.put(96, "사만다");
		
	
		
		// * Map는 부모클래스, treemap는 자식클래스로 업캐스팅된 상태다
		// => descendingMap은 treemap의 메서드기 때문에,
		// (1) 접근하기 위해서는 다운캐스팅이 필요하다
		// (2) 또는 그냥 선언타입을 treemap으로 하면 된다	* treemap를 쓸때는 treemap타입으로 쓰는게좋다
		
		
		NavigableMap<Integer, String> map2 = map.descendingMap();		// * 추천
		for(Map.Entry<Integer, String> entry : map2.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		NavigableMap<Integer, String> map3 = map2.descendingMap();
		for(Integer key : map3.keySet()) {
			System.out.println(key + ":" + map3.get(key));
		}
		
		// * 위의 for문은 둘다 같은 내용의 for문이다
		
		
		
			
		}
	
	public static void m3() {
		
	}
	
	public static void m4() {
		
	}

	
	public static void main(String[] args) {
		
		m1();
		m2();
		
	}
}
