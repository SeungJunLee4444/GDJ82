package ex02_datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex06_SimpleDateFormat {

	public static void main(String[] args) {
		
		// * java.text.SimpleDateFormat 클래스
		// => 옛날 패턴적용 방식
		
		// 1) 날짜 두개 만들기 : 예시로 두가지 방식으로 시행
		Date date1 = new Date();
		java.sql.Date date2 = new java.sql.Date(System.currentTimeMillis());
		// => 같은 클래스 이름을 import하는건 불가능 (인식에 혼동이 오기 떄문)
		
		// 2) 패턴 넣기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm--dd HH:mm:ss");
		
		// 3) String 타입에 값을 저장
		String result1 = sdf.format(date1);
		String result2 = sdf.format(date2);
		// sdf 패턴에 date1,2에 저장된 현재시간을 형식으로 넣은것을 string타입 result1,2에 저장 *
		
		// 4) 출력
		System.out.println(result1);
		System.out.println(result2);
		
		// => 둘이 클래스는 다르지만 현재 날짜를 출력하는 메소드는 같기 때문에 값도 같다
		
	}

}
