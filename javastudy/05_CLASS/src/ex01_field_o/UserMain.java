package ex01_field_o;


public class UserMain {
	
	int a; // => 0으로 초기화

	public static void main(String[] args) {
		
		
		// * 메인 메소드에서 해당 클래스를 선언할 수는 없다
		//System.out.println(a);
	
		// * 클래스 구성 개념
		// => user : 데이터타입
		// => 객체를 만들기 위한 수단
		// => 
		// 3) 객체 user : 변수명
		
		// 1. 생성과정
		// 1). 객체 선언과 생성
		User user = new User();
		
		// 2) 필드값 get
		// 마침표(.)를 이용해서 필드값을 호출한다
		System.out.println(user.id);
		System.out.println(user.pw);
		System.out.println(user.email);
		System.out.println(user.point);
		System.out.println(user.isVip);
		
		// 3) 필드값 set
		user.id = "admin";					
		user.pw = "123456";
		user.email = "admin@web.com";
		user.point = 1000;					
		user.isVip = (user.point >= 10000); 
		// => 값의 입력은 각 필드의 데이터 타입에 맞게 입력 *
		
		// 4) 출력
		System.out.println(user.id);
		System.out.println(user.pw);
		System.out.println(user.email);
		System.out.println(user.point);
		System.out.println(user.isVip);
		

	}

}
