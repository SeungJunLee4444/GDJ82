package quiz08_schedule;

import java.util.Scanner;

public class WeekScheduler {
	
	private int nthWeek;	
	private Day[] week;					// # 요일은 7개 있고, 이 요일을 스케줄에 할당해야함
	private String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};		
	private Scanner sc;
	
	public WeekScheduler(int nthWeek) {
		this.nthWeek = nthWeek;
		week = new Day[7];				// # 배열 : 생성자에서 배열 생성
		sc = new Scanner(System.in);	// * 배열, 스캐너 생성은 생성자에서
	}
	
	private void makeSchedule() {
		System.out.println("===== 등록 =====");
		System.out.print("요일 입력 >>>");
		String dayName = sc.next().substring(0, 1);	// # 월요일(입력) => 월만 인식하도록 만들어줌
		sc.nextLine();					// # 엔터 가져감
		
		
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i]))	{				// #1 입력한 요일과 배열의 이름과 같으면
															// ex) 수요일이면 i가 3일때 조건에 걸림
															// #2 week 배열에 dayNames 배열의 값을 같은 인덱스 위치에 저장  
			
				if(week[i] == null) {						// # 등록된 스케줄이 없으면
					System.out.println("스케줄 입력>>> ");
					String schedule = sc.nextLine();	// # 요일 입력하기
					Day day = new Day();				// # Day 배열
					day.setSchedule(schedule);			// # day배열에 입력한 
					week[i] = day;						// # ?
					System.out.println("요일에 새 스케줄이 등록되었습니다");
				} else {
					System.out.println(dayName + "요일은 이미 스케줄이 있습니다");
				}
				return;										// # 등록해도 끝, 등록 못해도 끝
				
				// * 입력한 요일이 배열의 문자열과 같으면 
				
			}
		}
		System.out.println(dayName + "요일은 없는 요일입니다");
		
		
		
	}
	
	private void changeSchedule() {
		System.out.println("===== 수정 =====");
		System.out.println("변경할 요일 입력 >>>");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케줄이 없습니다");
					System.out.println("새 스케줄을 등록할까요? >>>");
					String yesNo1 = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo1.equalsIgnoreCase("y")) {
						System.out.println("새 스케줄 입력 >>>");
						String schedule1 = sc.next().substring(0, 1);
						Day day = new Day();
						day.setSchedule(schedule1);
						week[i] = day;
						System.out.println("새 스케줄이 등록되었습니다");
					} else {
						System.out.println("스케줄 변경가 취소되었습니다");
					}
				} else {
					System.out.println(dayName + "의 스케줄은" + week[i].getSchedule() + "입니다");
					System.out.println("변경할까요? >>>");
					String yesNo2 = sc.next().substring(0, 1);
					sc.nextLine();
					if(yesNo2.equalsIgnoreCase("y")) {
						System.out.println("변경할 스케줄 입력 >>>");
						String schedule2 = sc.next().substring(0, 1);
//						Day day2 = new Day();
//						day2.setSchedule(schedule2);
//						week[i] = day2;
						week[i].setSchedule(schedule2);
						System.out.println("수요일의 스케줄이 변경되었습니다");
					} else {
						System.out.println("스케줄 변경가 취소되었습니다");
					}
			}
			
			} return;
			
			
		} System.out.println(dayName + "요일은 없는 요일입니다");
	}
	
	private void deleteSchedule() {
		System.out.println("===== 삭제 =====");
		System.out.println("삭제할 요일 입력 >>>");
		String dayName = sc.next().substring(0, 1);
		sc.nextLine();
		for(int i = 0; i < week.length; i++) {
			if(dayName.equals(dayNames[i])) {
				if(week[i] == null) {
					System.out.println(dayName + "요일은 스케줄이 없습니다");
				} else {
					System.out.println(dayName + "요일의 스케줄은" + week[i].getSchedule());
					System.out.println("삭제할까요(y/n)? >>> ");
					String yesNo = sc.next().substring(0, 1);
					if(yesNo.equalsIgnoreCase("y")) {
						week[i] = null;
						System.out.println(dayName + "요일의 스케줄이 취소되었습니다");
					} else {
						System.out.println("스케줄 삭제가 취소되었습니다");
						
					}
				}return; // # return의 위치?
			} 
			// # 영역 : 입력한 내용이 요일이 아닌경우
		}
		System.out.println(dayName + "요일은 없는 요일입니다");
		
		
		
		
	}
	
	private void printWeekSchedule() {
		System.out.println("===== 전체조회 =====");
		System.out.println(nthWeek + "주차 스케줄 안내");
		for(int i = 0; i < week.length; i++) {
			System.out.print(dayNames[i] + "요일 -");
			System.out.println(week[i] == null ? "x" : week[i].getSchedule());
			// # day 클래스 week 객체의 배열값을 불러오기
		}
		
		
		
	}
	
	public void manage() {
		
		while(true) {
		
			System.out.println("1.등록 2.수정 3.삭제 4.전체조회 0.종료 >>>");
			int choice = sc.nextInt();	// * 숫자를 받아감
			sc.nextLine();				// * 엔터를 받아감
			switch(choice)		{		// 숫자 - 조건문
			case 1: makeSchedule(); break;
			case 2: changeSchedule(); break;
			case 3: deleteSchedule(); break;
			case 4: printWeekSchedule(); break;
			case 0: System.out.println("스케줄러를 종료합니다"); return;
			default : System.out.println("인식할 수 없는 명령입니다");
			}
		}
		
	}

}
