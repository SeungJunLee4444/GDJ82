package ex02_casting;

public class Ex01 {

	public static void main(String[] args) {
		
		// 1. 자동변환 (promotion)
		// 상위범위인 다른 데이터 타입으로 변환
		
		// ex) 1
		long money = 10000; // int인 4바이트값을 8바이트 long값으로 변환
		System.out.println(money);
		
		// ex) 2
		int score =100;
		double realScore = score; // 4바이트 int가 8바이트 double로 프로모션
		System.out.println(realScore);
		
		
		
	}

}
