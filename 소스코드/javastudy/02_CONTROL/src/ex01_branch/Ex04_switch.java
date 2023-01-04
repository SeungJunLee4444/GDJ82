package ex01_branch;

public class Ex04_switch {

	public static void main(String[] args) {
		
		// 4. switch 문
		// if와 달리 조건을 쓰지 않고, 표현식을 사용
		// 제한: 표현식의 결과값을 double, boolean 데이터 타입일 수 없다
		// 형태: switch(표현식) {
		// case 값 : 실행문, break; 
		// case 값 : 실행문, break;
		// case 값 : 실행문, break;
		// default: 실행문; // 나머지 / break없어도됨
		//}
		
		
		int step = 1;  // double과 boolean은 안됨, int는 됨
		
		switch(step) {
		case 1: System.out.println("1단계");        // break가 없으면 다음 케이스까지 실행해버림
		case 2: System.out.println("2단계"); break;
		case 3: System.out.println("3단계"); break;
		default: System.out.println("잘못된 단계");
		}
		// 연습1 ** 코드를 더 정리하는 방법에 익숙해질 것(변수의 사용)
		// 각 층별 관리자를 출력하라
		// 1~2층의 관리자는 전지현, 3~4층은 한지민, 5~6층은 박은빈, 나머지층은 한효주
		
		int floor = 4;
		String manager;
		
		switch(floor) {
		case 1: 
		case 2: manager = "전지현"; break;
		case 3: 
		case 4: manager = "한지민"; break;
		case 5:
		case 6: manager = "박은빈"; break;
		default: manager = "한효주"; 
		} System.out.println(floor + "층의 관리자는" + manager + "입니다");
		
		
		// 연습2
		// 짝수, 홀수
		int n = 1;
		switch(n % 2) {
		case 0 : System.out.println("짝수"); break; // case의 값은 변수n의 값과 동일
		default : System.out.println("홀수");
		}
		
		// 연습3  ** 
		// 분기 출력하기
		int month = 1;
		int mod = (month -1) / 3; // **  개발자식 사고방식 *나누기 모듈과는 다른 방식
		switch(mod) {
		case 0 : System.out.println("1분기"); break;
		case 1 : System.out.println("2분기"); break;
		case 2 : System.out.println("3분기"); break;
		case 3 : System.out.println("4분기"); break;
		
		}
		
		
		// 연습 ** 
		// 점수에 따른 학점. 
		// A는 90이상 B는 80이상 C는 70이상 D는 60이상 나머진 F 
		int score = 100; 
		String grade;
		switch(score / 10) {
		case 10 : 
		case  9: grade = "A"; break;
		case  8: grade = "B"; break;
		case  7: grade = "C"; break;
		case  5: grade = "D"; break;
		default: grade = "F"; 
		}
		System.out.println(score + "점은" + grade + "학점입니다");
		
		// => 해결 점수에 10을 나눠 같은 케이스 값을 가지도록 해결
		// => 같은 케이스 값을 가지기 위해 어떻게 해야하는지 고민할 것
	
		// 연습 ** 
		// 등급(1,2,3) 에 따른 권한 출력
		// 1등급은 쓰기실행읽기, 2등급 실행 읽기, 3등급 읽기, 나머지 없음
		
		int level = 4;
		String right = "";
		switch(level) {
		case 1 : right += "쓰기"; // level이 1이면 쓰기부터 출력   * += 참고
		case 2 : right += "실행"; // level이 2이면 실행부터 출력
		case 3 : right += "읽기"; break;// level이 3이면 읽기부터 출력
		default : right += "없음";
		}
		
		System.out.println(right);
		
		// => break 문을 생략하는 것을 이용
		
	
	}
}

