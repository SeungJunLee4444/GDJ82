package ex02_casting;

public class Ex02 {

	public static void main(String[] args) {
		
		// 2. 강제변환 (캐스팅)
		
		// 작은 범위의 다른 데이터 타입으로 값을 변환하여 저장
		// 실수를 정수로 변환할때
		
		// ex1)
		int score = 1000; // 4바이트 int를 1바이트 byte로 줄이기
		byte realScore = (byte)score;
		System.out.println(realScore); //-24 : byte는 128까지 인식하기 때문에 오류
		
		// ex2)
		int score2 = (byte)50;

		
		// ex3)
		double grade = 4.5;
		int realGrade = (int)grade;
		System.out.println(realGrade); //4 : 소수점 값은 잘려나감
		
	}

}
