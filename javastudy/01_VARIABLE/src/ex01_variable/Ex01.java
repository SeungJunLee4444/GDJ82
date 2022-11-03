package ex01_variable;

public class Ex01 {

	public static void main(String[] args) {
		
		// single comment -한줄짜리 주석
		/*
		 
		 *  multiple comment -여러줄 주석
		 */
		
		// 키워드 이름규칙
		// 1. 패키지: 모두 소문자, 실제로는 회사 도메인을 거꾸로 적음
		// (com.samsung.galaxy)
		// 2. 클래스: 각 단어의 첫글자만 대문자, 나머지 소문자(upper camel case)
		// 3. 메소드, 변수: 첫글자 소문자, 각 단어의 첫글자는 대문자, 나머지 소문자(lower camel case)
		// 4. 상수: 모두 대문자, _(언더바, 언더스코어)로 연결(snake case)
		
		// main 메소드
		// 1. 자바 프로젝트를 실행할 때는 반드시 필요하다
		// 2. JVM은 열려있는 메인 메소드를 실행한다
		// 3. 열려있는 메인 메소드가 없으면 최근에 실행한 메인 메소드를 실행
		
		// 변수 선언
		// 사용할 변수의 데이터 타입과 이름을 미리 결정
		// 형식: 데이터 타입 + 변수명(식별자) = 초기값; ex) 0, null
		
		// 논리타입
		boolean isGood = true;
		boolean isAlive = false; // is~는 boolean 타입의 관행
		System.out.println(isGood);
		System.out.println(isAlive);
		
		// 문자(character)타입
		char ch1 = 'A'; // 문자는 한글자를 의미하며, (')작은 따음표로 묶기
		char ch2 = '홍';
		char ch3 = '\n'; // \n : 줄바꾸기
		char ch4 = '\t';
		char ch5 = '\'';
		char ch6 = '\"';
		System.out.println(ch1);
		System.out.println(ch2);
		System.out.println(ch3);
		System.out.println(ch4);
		System.out.println(ch5);
		System.out.println(ch6);
		
		
		// 정수타입
		int score = 1;
		long view = 3000000000L;
		System.out.println(score);
		System.out.println(view);
		
		// 실수타입
		double discount = 0.5;
		double pi = 3.1415926535;
		System.out.println(discount);
		System.out.println(pi);
		
	}	

}
