package ex07_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {
	
	// 1. // 아바타 -> 쇼생크탈출 -> ~ -> 여인의 향기 출력하기
	// * 맨 마지막은 ->가 없다
	
	// 일반 for문
	
	public static void printMovies(List<String> list) {
		for(int i = 0, length = list.size(); i < length; i++) {
			System.out.print(list.get(i));
			if(i <  length - 1)			// * size - 1은 마지막 인덱스
			System.out.print("->");	// => 마지막 요소 이전에는 화살표 출력
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		List<String> movies = new ArrayList<String>();
	
		movies.add("아바타");
		movies.add("쇼생크탈출");
		movies.add("명량");
		movies.add("에일리언");
		movies.add("여인의향기");
		
		printMovies(movies); 
		
		// 1) movies 리스트를 오름차순 정렬
		Collections.sort(movies);
		printMovies(movies);
		
		// 2) movies 리스트를 내림차순으로 정렬
		Collections.reverse(movies);
		printMovies(movies);
		// * descendingMap은 현재 정렬의 반대로 바꾸는 메서드(hashmap 소속 메서드)
		
		// 3) 특정 요소의 '인덱스' 반환
		// => 이진검색(binary search) 을 이용하므로 검색속도가 매우 빠름
		// => 단, 크기순으로 정렬이 되있어야한다
		
		int idx = Collections.binarySearch(movies, "아바타");
		System.out.println(idx);
		// => 가장 최근의 아바다 위치는 2
		
		
	
	}

}
