package ex02_datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ex05_LocalDateTime {

	public static void main(String[] args) {
		
		// 5. LocalDateTime 클래스
		// => java.time.LocalDateTime 
		// => Calendar과 역할은 같음
		// => 날짜 표현과 날짜의 패턴 지정
		// => 날짜의 패턴 지정이 가능
		// * calendar보다 효율적이나 아직 과도기
		
		// 1) 사용법
		// (1) 클래스 선언
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		// => 2022-07-25T21:33:15.768233600
		
		// (2) 반환 (객체.get~)
		int year = now.getYear();
		int month = now.getMonthValue(); 	// 반환값 1~12(Calendar처럼 +1 할필요x) *
		int day = now.getDayOfMonth();
//		int day2 = now.getDayOfWeek(); 		// int타입으로 반환 불가능
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(minute);
		System.out.println(second);
	
		// (3) 출력할 날짜의 패턴 지정
		// => DateTimeFormatter 클래스로 출력패턴 설정 가능 *
		
		// {1} 먼저 DateTimeFormatter로 출력패턴을 만든다
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("a h:mm yyyy-MM-dd");
		
		// {2} 만든 패턴이 저장된 객체에.format 메서드를 이용해 String타입에 반환값을 저장한다
		String date = dtf.format(now); //format은 형식, 형식을 string타입에 저장
		System.out.println(date);
		
		
		// 날짜 클래스 정리
		// 1. Calendar 클래스(기존)
		// => SimpleDateFormat로 패턴화
		// 2. LocalDateTime 클래스(최신)
		// => DateTimeFormatter로 패턴화
		
		
		
		
		
		
		
		
		

	}

}
