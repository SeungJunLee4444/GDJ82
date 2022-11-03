package ex02_datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Ex04_Calendar {

	public static void main(String[] args) {
		
		// [클래스 종류]
		// 4. Calendar 클래스
		// => java.util.Calendar 
		// => 현재날짜나 특정날짜 표현
		// ex) 년,월,일,시,분,초
		
		// 1) 사용법
		// (1) 클래스 선언
		Calendar cal = Calendar.getInstance(); 
		// => 캘린더 인스턴스를 얻는다
		// => getInstance(); 메서드를 선언하면 현재 시각으로 초기화됨
		System.out.println(cal);
		// => java.util.GregorianCalendar[time=1658749634212,
		
		// (2) 반환
		// => 원래는 클래스파일의 상수값을 Calendar.뒤에 집어넣는 방식이나 아래 같은 방식으로 개선
		int year = cal.get(Calendar.YEAR); 				// 년
		int month = cal.get(Calendar.MONTH);			// 월 		+1 더해줘야함. 반환값이 0~11
		int date = cal.get(Calendar.DAY_OF_MONTH);  	// 월중일	
		int weekNo = cal.get(Calendar.DAY_OF_WEEK); 	// 주중일	1(일), 2(월) ~
		int ampm =cal.get(Calendar.AM_PM);				// 오전(0), 오후(1)
		int hour12 = cal.get(Calendar.HOUR); 			// 시(1~12)
		int hour24 = cal.get(Calendar.HOUR_OF_DAY); 	// 시(0~23)
		int minute = cal.get(Calendar.MINUTE); 			// 분(0~59)
		int second = cal.get(Calendar.SECOND);			// 초(0~59)
		
		// * 요일과 오전오후는 별도의 문자값으로 표현해야 하기 떄문에 switch문을 활용한다
		
		switch(weekNo) {
		case 1: System.out.println("일요일");
		case 2: System.out.println("월요일");
		case 3: System.out.println("화요일");
		case 4: System.out.println("수요일");
		case 5: System.out.println("목요일");
		case 6: System.out.println("금요일");
		default: System.out.println("토요일");
		}
		
		switch(ampm) {
		case 0 : System.out.println("오전"); break;
		case 1 : System.out.println("오후"); break;
		} 
		
		System.out.println(year);
		System.out.println(month + 1); // * 월은 하나씩 적게 출력된다 / 반환값이 0~11
		System.out.println(date);
		System.out.println(weekNo);
		System.out.println(hour12);
		System.out.println(hour24);
		System.out.println(minute);
		System.out.println(second);
		
		SimpleDateFormat sdf = new SimpleDateFormat("a h:mm:ss yy-MM-dd");
		String result = sdf.format(cal);
		
		
		// 3) Calendar을 이용한 타임스탬프

		long timestamp = cal.getTimeInMillis();
		// => getTimeInMillis는 ms(천분의1)단위로 현재시간을 호출하는 메서드
		
		System.out.println(timestamp); 
		
		// * timestamp은 currentTimeMillis(ms), nanoTime(ns) 두개의 메서드를 호출하여
		// 단위별 현재 시간을 출력한다
		// * currentTimeMillis는 통상적인 시간을 구할때, 
		// * nanoTime ns는 특정 작업의 소요시간을 구할 때 사용한다
		

	
		
	
		
	}

}
