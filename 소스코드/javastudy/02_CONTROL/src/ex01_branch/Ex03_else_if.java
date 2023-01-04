package ex01_branch;

public class Ex03_else_if {

	public static void main(String[] args) {
		
		// 3. else if문
		// 조건이 여러개 사용되는 경우에 각 조건을 처리한다
		// if(조건) {
		// 실행문1
	    //} else if { => 여러개 사용 가능
		// 실행문2
		//} else { => 나머지 모든것들
		// 실행문3
		//}
		
		// ex) 나이에 따른 결과 출력
		// 0~7: 미취학 아동 / 8~13: 초등학생 / 14~16: 중학생 / 17~19: 고등학생 / 20~: 성인
		int age = 105;
		if(age < 0 || age > 100) {   // boolean에서 참이나 거짓이냐 이야기 할때의 ||와 혼동 x *
			System.out.println("잘못된 나이");
		} else if(age <= 7) {
			System.out.println("미취학아동");
		} else if(age <= 13) {
			System.out.println("초등학생");
		} else if(age <= 19) {
			System.out.println("고등학생");
		} else {
			System.out.println("성인");
		}
		
		// 연습
		// 월에 따른 계절 출력
		// 봄(3~5) / 여름(6~8) / 가을(9~11) / 겨울(12~2)
		
		
		
		int month = 1;  
		if(month < 1 || month >12) { // 해석: 제외해야할 조건이기 떄문에 or을 사용한 것 **
			System.out.println("잘못된 월"); // 위 조건을 통해, 1~12의 정수만 아래로 도출됨
		} else if(month == 12 || month <=2) {
			System.out.println("겨울");
		} else if(month <=5) {
			System.out.println("봄"); 
		} else if(month <=8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
		
		// modular 연산  *****
		// * 모듈로 연산하는 이유
		// 1) 특정값을 나누어 계산할 때 나머지만 필요로 하는 경우가 있다
		// 2) 특정 주기를 가진 시간, 달 등을 반복적으로 값을 도출하기 위해 나눈 후 나머지 값을 이용
		
		
		month = 1;
		int mod = month % 12;
		if(month < 1 || month > 12) {
			System.out.println("잘못된 월");
		} else if(mod <=2) {
			System.out.println("겨울");
		} else if(mod <=5) {
			System.out.println("봄");
		} else if(mod <=8) {
			System.out.println("여름");
		} else {
			System.out.println("가을");
		}
		
		// 모듈러 연습2
		// 일수만 고려, 1일이 수요일일때, n일후 무슨 요일인지 출력하기
		
		int day =1;
		int n =1;
		String weekName; // 목
		
		day += n; // 이유: 범위가 1~25일이 있고, 각 요일이 1회성이 아닌 여러 번 반복되기 때문
		if(day % 7 == 0) {
			weekName = "화";
		} else if(day % 7 == 1) {
			weekName = "수";
		} else if(day % 7 == 2) {
			weekName = "목";
		} else if(day % 7 == 3) {
			weekName = "금";
		} else if(day % 7 == 4) {
			weekName = "토";
		} else if(day % 7 == 5) {
			weekName = "일";
		} else {
			weekName = "월";
		} System.out.println(weekName + "입니다");
	
	
		// * 모듈을 이용한 계산  ****
		// 모듈은 반복되는 주기를 지닌 숫자들을(일, 주, 시간, 등) 나눈 값이 반복되는 것을 이용한 계산법
		// 코드를 훨신 간편화시킬 수 있다
			
		
		
		
		
		
		
		
		
		// 점수에 따른 학점 ***
		// score / grade
		// score이 100~90이면 a, 80~89는 b, 70~79는 c, 60~69는 d, 59~0 은 f
		
		int score = 60;
		char grade; //* 학점 abcdf라 char에 저장
		if (score < 0 || score > 100) { // * 제외해야할 범위 0 미만, 100 초과를 제거
			grade = 'X';                // 0에서 100까지의 정수로 범위를 제한
		} else if(score >=90) { 
			grade = 'A';
		} else if(score >=80) {
			grade = 'B';
		} else if(score >=70) {
			grade = 'C';
		} else if(score >=60) {
			grade = 'D';
		} else {
			grade = 'F';
		} 
		
		System.out.println("점수는" + score + "점입니다");
		System.out.println("학점는" + grade + "점입니다");
		
		
		
		
		// * if는 논리 연산자를 활용할 수 있다
		// 
		
		 
		
	

	}

}
