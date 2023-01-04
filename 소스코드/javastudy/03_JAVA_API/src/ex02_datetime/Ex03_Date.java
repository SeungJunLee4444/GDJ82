package ex02_datetime;

import java.sql.Date;

public class Ex03_Date {

	public static void main(String[] args) {
		
		// 5. 메소드 첫번쨰 방법으로 호출 
		
		// 1) java.sql.Date 클래스 *
			// (1) java.sql이라는 패키지의 Date클래스를 호출
			// (2) import로 자바에서 자동으로 호출해줌
		
			// * java.util/sql.Date의 차이점
				// => util은 epochtime(1970)을 참고(권장x)
				// => sql은 데이터베이스를 다룰 때 사용(시간정보를 안다루고 0으로 초기화?)
					// oracle 데이터베이스의 날짜타입("/", "-"과 매칭해서 사용할 것?
			
			// (3) 클래스 선언 후, 객체 생성
			 Date time = new Date(System.currentTimeMillis()); 
		// => long 타입의 date값은 timeStemp뿐이다?
			 // currentTimeMillis는 long타입 선언을 해야한다
		System.out.println(time);
		
		// 2) 
		

		
		
		
		
		
		
		
		
	}

}
