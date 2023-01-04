package ex02_datetime;

import java.util.Date; // => import *

public class Ex02_Date {

	public static void main(String[] args) {
		
		
		/*  2. 클래스 명명 규칙 
		 *  1) 오리지널 규칙
		 *  원래 클래스를 부를 때는 패키지를 앞에 호출해야함
		 *  => package명.class명.메소드
		 *  
		 *  ex) java.lang.System.out.print
		 *  (예외적으로 java.lang 패키지의 클래스들은 원래 패키지를 생략할 수 있다) *
		 *  
		 *  // => 즉 lang을 제외하면 모든경우 패키지를 적어줘야하는데,
		 *  
		 *  ex) java.utilDate => Date 사용시 import.java.util.Date; 상단에 호출
		 *      
		 *  => 이를 간편화하기 위해, 자바에서는 메소드를 상단에 import로 호출해준다
		 *  ==> import를 호출하면 클래스명만 호출해도 사용 가능하다
		 *  
		 */
		
		/*  3. 클래스와 객체
		 *  1) 정의
		 *  클래스: 객체를 만들기 위한 설계도
		 *  객체: 클래스를 이용해서 만든 실제 객체
		 *  
		 *  2) 객체생성법
		 *  클래스 객체 = new 클래스();
		 * 
		 *  3) 메소드 호출 2가지 방법
		 *  (1) 클래스를 이용한 호출(기존)
		 *  System.out.println();
		 *  System.out.currentTimeMillis();
		 *  System.nanoTime();
		 *  Math.random();
		 *  
		 *  (2) 객체를 이용한 호출 (이런식으로 객체를 호출해야하는 경우가 개발에는있다 *)
		 *  String str = new String("Hello"); String클래스 str 객체
		 *  => str.equal("Hello");
		 *  
		 *  ex1) Date now = new Date();
		 *  => now.getMonth();
		 *
		 *  ex2) StringBuilder sb = new StringBuilder
		 *  => sb.append(); 
		 */ 
		
		// 4. Date 클래스의 now 객체
		// => 메소드 호출 두번째 방법
		
		Date now = new Date();
		System.out.println(now);

		// => 여기서 Date는 클래스 now는 Date클래스를 참조하여 만든 실제 객체다
		// => now는 Date 클래스를 참조하여 실제 시간을 계산한다
		

		

		

		
	}

}
