package ex02_casting;

public class Ex03 {

	public static void main(String[] args) {
		
		// 3. 문자열 변환
		
		// 1) string 타입을 기본타입으로 변환하기
		String strScore = "100"; //int 값
		String strMoney = "10000000000"; //long 값
		String strGrade = "4.5"; // double 값
		
		int score = Integer.parseInt(strScore); // int값으로 변환
		long money = Long.parseLong(strMoney);
		double grade = Double.parseDouble(strGrade);
		
		// * 우측의 값 뿐 아니라 좌측의 데이터 타입도 변환하고자 하는 타입으로 
		// 일치시켜줘야함
		
		
		System.out.println(score);
		System.out.println(money);
		System.out.println(grade);
		
		
		// 2) 기본타입을 string타입으로 변환하기
		
		int age = 100;
		String strAge = String.valueOf(age); // 연두: 메소드, lowcamel 타입
		System.out.println(strAge);
		
		double grade2 = 4.5;
		String strGrade2 = String.valueOf(grade2);
		System.out.println(strGrade2);
		
		// => 간편한 방법
		
		
		
		
		
		
	}

}
