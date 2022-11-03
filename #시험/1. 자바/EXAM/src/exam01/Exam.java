package exam01;

import java.util.Scanner;



public class Exam {

	// 문제1. String serial의 첫 번째 글자가 "1"이면 "남자", "2"이면 "여자"를 출력하시오.
	// 예시
	// 1234567은 남자입니다.
	public static void q1() {
		String serial = "1234567";
		int number = Integer.parseInt(serial);
		String result = (number / 1000000 == 1) ? "남자" : (number / 1000000 == 2) ? "여자" : "";
		System.out.println(result);
	}
	
	// 문제2. int a와 int b에 저장된 결과를 이용해서 사칙연산 결과를 출력하시오.
	// 예시
	// 7 + 2 = 9
	// 7 - 2 = 5
	// 7 * 2 = 14
	// 7 / 2 = 3.5
	public static void q2() {
		int a = 7;
		int b = 2;
		System.out.println(a + b);
		System.out.println(a - b);
		System.out.println(a * b);
		System.out.println((double)a / b);
		
	}
	
	// 문제3. Scanner 클래스를 이용해서 입력 받은 점수를 int score에 저장한 뒤 아래와 같이 처리하시오.
	//  0 <= score < 60   : "불합격" 출력하기
	// 60 <= score < 70   : "합격C" 출력하기
	// 70 <= score < 90   : "합격B" 출력하기
	// 90 <= score <= 100 : "합격A" 출력하기
	// score < 0, score > 100 : "잘못된 점수" 출력하기
	// 예시
	// 점수 입력 >>> 75
	// 75점은 합격B입니다.
	public static void q3() {
		Scanner sc = new Scanner(System.in);
		int score = sc.nextInt();
		System.out.println(score);
		
		String grade = "";
		if(score == 100 || score >= 90) {
				grade = "합격A";
			} else if(score >= 70) {
				grade = "합격B";
			} else if(score >= 60) {
				grade = "합격C";
			} else {
				grade = "합격C";	
			} System.out.println(score + "점은" + grade + "입니다");
	}
	
	// 문제4. 0부터 Scanner 클래스를 이용해서 입력 받은 정수까지 모두 더한 뒤 평균을 출력하시오.
	// 만약, 0보다 작은 정수가 입력되면 평균은 그냥 0을 출력하시오.
	// 예시1
	// 마지막 정수 입력 >>> 5
	// 0부터 5사이 모든 정수의 평균 : 3.0
	// 예시2
	// 마지막 정수 입력 >>> -5
	// 평균 : 0
	public static void q4() {
		Scanner sc2 = new Scanner(System.in);
		int begin = 0;  // 0
		int end = sc2.nextInt();// 입력 받은 정수
		if(begin > end) {
			int temp = 0;
			temp = begin;
			begin = end;
			end = temp;
		}
		int total = 0;
		for(int i = 0; i <= end; i++ ) {
			total += i;
		} 
		double average = (double)total / (end - begin);
		System.out.println(average);
	}
	
	// 문제5. 1부터 100 사이의 모든 정수를 대상으로 짝수들의 합계와 홀수들의 합계를 각각 출력하시오.
	// 예시
	// 짝수 합 : 2550
	// 홀수 합 : 2500
	public static void q5() {
		int even = 0;  // 짝수 합
		int odd = 0;   // 홀수 합
		for(int n = 1; n <= 100; n++) {
			if((n % 2) == 0) {
				even += n;
			} else {
				odd += n;
			}
		} 
		System.out.println(even);
		System.out.println(odd);
		
	}
	
	// 문제6. 통장(balance)에 5000원이 있다. 1원부터 1000원 사이의 난수를 발생시켜서 '해당 난수'만큼 10번 출금 처리하시오.
	// 마이너스 통장이므로 잔액은 음수일 수 있다.
	// 예시
	// 출금 전 5000원, 1회 출금 1원, 출금 후 4999원
	// 출금 전 4999원, 2회 출금 10원, 출금 후 4989원
	// ...
	// 출금 전 100원, 10회 출금 500원, 출금 후 -400원
	public static void q6() {
		int balance = 5000;  // 통장
		int money = (int)((Math.random() * 1000) + 1);
		int serial = 0;
		
		while(balance >= money) {
			serial++;
			System.out.println("출금 전" + balance + "원," + serial + "회" + "출금" + money + "원," + "출금 후" + (balance - money) + "원");
			balance -= money;
		}
	}
	
	// 문제7. Scanner 클래스를 이용해서 입력 받은 문자열에 문자 'h'가 몇 개 포함되어 있는지 갯수를 구해서 출력하시오.
	// next() 메소드를 이용해서 문자열을 입력 받으시오.
	// 예시
	// 문자열 입력 >>> happy
	// happy에 포함된 h는 1개입니다.
	public static void q7() {
		Scanner sc3 = new Scanner(System.in);
		String str3 = sc3.nextLine();
		char chr = 'h';
		int count = 0;
	
		for(int k = 0; k < str3.length(); k++) {
			if(str3.charAt(k) == chr) 
				count++;
			} System.out.println(str3 + "에 포함된 h는" + count + "개입니다");
		} 
		
	// 문제8. 다음 기준에 따라서 파일명을 변환하시오.
	// Scanner 클래스의 next() 메소드를 이용해서 파일명을 입력 받은 뒤 파일명 마지막에 밑줄(_)과 타임스탬프 값을 붙이시오.
	// 예시
	// 변환 전 파일명 >>> happy.jpg
	// 변환 후 파일명 = happy_1658792128410.jpg
	public static void q8() {
		Scanner sc4 = new Scanner(System.in);
		System.out.println("파일명을 입력하시오    >>>");
		String beforeName = sc4.nextLine();  // 변환 전 파일명
		//String afterName = "";   // 변환 후 파일명
		
		int indexDot = beforeName.indexOf(".");
		String frontName = beforeName.substring(0, indexDot);
		String endName = beforeName.substring(indexDot);
		
		long timeStamp = System.currentTimeMillis();
		
		StringBuilder sb = new StringBuilder();
		sb.append(frontName);
		sb.append("_");
		sb.append(timeStamp);
		sb.append(endName);
		
		String afterName = sb.toString();
		System.out.println("변환 후 파일명 = " + afterName);
	}
	
	// 문제9. Scanner 클래스의 next() 메소드를 이용해서 사람 이름을 입력 받은 뒤
	// 아는 사람의 이름이면 "반갑다 친구야"를 출력하고, 
	// 모르는 사람의 이름이면 "안녕하세요 처음뵙겠습니다"를 출력하시오.
	// 아는 사람은 "전지현", "정우성", "한지민" 뿐이다.
	// 예시1
	// 이름 입력 >>> 정우성
	// 반갑다 친구야
	// 예시2
	// 이름 입력 >>> 유재석
	// 안녕하세요 처음뵙겠습니다
	public static void q9() {
		Scanner sc5 = new Scanner(System.in);
		String name = sc5.nextLine();  // 이름
		
		String[] nameList = {"전지현", "정우성", "한지민"};
//		String name1 = "전지현";
//		String name2 = "정우성";
//		String name3 = "한지민";
		
		if(name.equals(nameList[0])) {
			System.out.println("반갑다 친구야");	
		} else if(name.equals(nameList[1])) {
			System.out.println("반갑다 친구야");		
		} else if(name.equals(nameList[2])) {
			System.out.println("반갑다 친구야");
		} else {
			System.out.println("처음 뵙겠습니다");
		}
	}
	
	// 문제10. 다음 조건에 따라 비밀번호 체크 프로그램을 구현하시오.
	// 1. 비밀번호는 최대 5번까지 입력이 가능하다.
	// 2. 비밀번호는 "1234abcd"로 가정한다.
	// 3. 비밀번호 입력이 성공하면 "성공"을 출력하고 프로그램을 종료한다.
	// 4. 비밀번호 입력이 실패하면 "실패"를 출력하고 다시 비밀번호를 입력 받는다.
	// 5. 5번째 비밀번호 입력 결과가 실패하면 "횟수 초과"를 출력하고 프로그램을 종료한다.
	
	// 예시1
	// 비밀번호 입력(1회) >>> 1234
	// 실패
	// 비밀번호 입력(2회) >>> abcd
	// 실패
	// 비밀번호 입력(3회) >>> 1234abcd
	// 성공
	// 예시2
	// 비밀번호 입력(1회) >>> 1234
	// 실패
	// 비밀번호 입력(2회) >>> abcd
	// 실패
	// 비밀번호 입력(3회) >>> 5678
	// 실패
	// 비밀번호 입력(4회) >>> 8888
	// 실패
	// 비밀번호 입력(5회) >>> 5555
	// 횟수 초과
	
	public static void q10() {
		Scanner sc5 = new Scanner(System.in);
		System.out.println("비밀번호 입력 (1회) >>>> ");
		String pw = sc5.nextLine();
		
		String realPw = "1234abcd";
		boolean run = true;
		int n = 1;
		
		while(run) {
			System.out.println("실패");
			System.out.println("비밀번호 입력(" + (n+1) + "회) >>>> ");
			pw = sc5.nextLine();
			if(pw.equals(realPw)) {
				System.out.println("성공");
			} else {
				n++;	
			} if(n == 5) {
				System.out.println("실패");
				System.out.println("횟수초과");
				break;
			}		
		}	
	}
			
	// main 메소드는 그대로 사용합니다.
	public static void main(String[] args) {
		System.out.println("=====문제1=====");
		//q1();
		System.out.println("=====문제2=====");
		//q2();
		System.out.println("=====문제3=====");
		//q3();
		System.out.println("=====문제4=====");
		//q4();
		System.out.println("=====문제5=====");
		//q5();
		System.out.println("=====문제6=====");
		q6();
		System.out.println("=====문제7=====");
		//q7();
		System.out.println("=====문제8=====");
		//q8();
		System.out.println("=====문제9=====");
		//q9();
		System.out.println("=====문제10=====");
		//q10();
	}

}
