package ex04_condition;

public class Ex03 {

	public static void main(String[] args) {
		
		// 3. 조건연산자 *
		// 조건을 만족하는 경우와 그렇지 않은 경우 모두를 처리하는 연산
		// 형식
			// 조건 ? 만족하는 경우 : 만족하지 않은 경우
		
		int score = 50;
		String result = (score >= 60) ? "합격" : "불합격";
		char result2 = (score >= 60) ? 'A' : 'B';
		System.out.println(result);
		System.out.println(result2);
		
		// 연습
		// 순위가 1이면 "금메달", 순위가 2이면 "은메달", 순위가 3이면 "동메달"
		// 나머지 순위는 없음
		
//		int level = 4;
//		String strLevel = (level == 1) ? "금메달" : (level == 2) ? "은메달" : (level == 3) ? "동메달" : "없음";
		

		int rank = 4;
		String medal = (rank == 1) ? "금메달" : (rank == 2) ? "은메달" : (rank == 3) ? "동메달" : "없음";
		System.out.println(medal);
		
		// 연습2 *
		// 홀수는 "홀수", 짝수는 "짝수"로 출력하기 
		
		// => 홀수는 2로 나눈 나머지 값이 1인것, 짝수는 반대
//		int number = 1;
//		String strNumber = (number % 2 == 1) ? "홀수" : "짝수";
		
		// * 0은 2의 배수이다
		
		int n = 0;
		String type = (n % 2 == 1) ? "홀수" : (n  % 2 == 0) ? "짝수" : "";
		String type1 = (n % 2 == 1) ? "홀수" : "짝수"; // *
		System.out.println(type);
		System.out.println(type1);
		
		// 홀수는 2로 나눈 나머지가 1, 짝수는 2로 나눈 나머지가 0 *
		
		// 연습3 **
		// 홀수는 "홀수", 짝수는 "짝수"로, 3의 배수는 "3의 배수" (0은 고려하지않는다)
//		int number2 = 6;
//		String strNumber2 = (number2 % 3 == 0) ? "3의 배수" : (number2 % 2 == 1) ? "홀수" : "짝수"; 
//		
//		
		int a = 6;
		String type2 = (a % 3 == 0 ) ? "3의배수" : (a % 2 == 1 ) ? "홀수" : "짝수";
		System.out.println(type2);
		
		// 3의 배수는 3으로 나눈 나머지가 0인 배수 *
		// => 3의 배수는 홀수, 짝수를 포함한 명제이기 때문에, 홀수 짝수 조건에 묻히지 않기 위해 조건을
		// 앞에 위치시켜야 한다 *
		
		// 연습4 ***
		// 주민등록번호 뒷 7자리 중 첫번째 숫자가 1,3,5면 "남자", 2,4,6이면 "여자"
		int serial = 7234567;
		
		int serial1 = 1123456;
		String strSerial1 = ((serial1 / 10000) % 2 == 1) ? "남자" : "여자";
		
		// 해결책: 1234567을 1백만을 나누면 된다

		String gender = ((serial / 1000000) % 2 == 1) ? "남자" : "여자"; 
		// 1, 3, 5는 홀수기에 조건에 추가
		System.out.println(gender);
		
		
	}

}
