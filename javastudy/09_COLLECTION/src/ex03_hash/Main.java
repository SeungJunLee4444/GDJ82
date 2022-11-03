package ex03_hash;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		
		// 1. hashcode
		// => 많은 데이터에서 빠른 조회를 위해 사용되는 코드
		// => 해시코드 비교, 같은 해시코드를 지닌 코드끼리 동등비교
//
//		Book book = new Book();
//		System.out.println(book.hashCode());
//		// => 653305407 참조값
//		
//		Book book2 = new Book();
//		System.out.println(book2.hashCode()); 
//		// => 1227229563 참조값
//		// * 16진수가 아닌 int 참조값인것이 특징
//		
//		System.out.println(book.equals(book2));	// false
		// => 참조값, 해시값 비교
		
		// * 자바의 hashcode
		// => 객체의 참조값 기반의 해시코드
		// => 반대로는 불가능 
		
		
		// 2. 
		Book book1 = new Book(1, "어린왕자");
		Book book2 = new Book(2, "홍길동전");
		Book book3 = new Book(3, "소나기");
		Book book4 = new Book(3, "소나기");	
		
		// *1 자바는 기본적으로 객체별로 주소값, 해시코드를 다르게 본다
		// *2 set는 중복x이어야 하는데,
		// 그러나 주소값이 다르기 때문에, 같은 문자열임에도 둘다 출력
		// => 해결을 위해선 hashcode,equals메서드를 오버라이징해야함
		
		System.out.println(book3.hashCode());
		System.out.println(book4.hashCode());
		// => hashcode, equal 오버라이징을 통해 객체의 주소값이 같아지게된다
		
		Set<Book> books = new HashSet<Book>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);			// * 중복x
		
		for(Book book : books) {
			System.out.println(book);
		}
	}

}
