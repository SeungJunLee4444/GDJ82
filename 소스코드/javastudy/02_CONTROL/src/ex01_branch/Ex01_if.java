package ex01_branch;

public class Ex01_if {

	public static void main(String[] args) {

		// 1. if문 
		// => '조건을 만족하는 경우'에만 실행
		// => 비교연산자를 활용해 조건을 작성
		// if(조건) {
					//실행문
	//}
		int score = 100; // 변수이름 지을때 남들이 봐도 무엇이 저장될 수 있는지 가늠할 수 있도록 짓기
		if (score >= 60) {
			System.out.println("합격");
			System.out.println("축하합니다"); //실행문이 두개 이상일 경우에는 괄호가 필수
		}
		
		if (score <= 60) 
			System.out.println("불합격"); // 실행문이 하나일 때, 괄호 생략가능
		
		
		
		
		
		
		
	}

}
