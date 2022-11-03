package ex10_this_o;

public class Rectangle { // & 4각형
	
	// 문제: 직사각형과 정사각형을 다르게 만들어지도록 만들기
	
	// # 필드
	private int width;
	private int height;


	// 2. 생성자
	public Rectangle (int width, int height) { 
		this.width = width;
		this.height = height;
	}
	// * 생성자는 반환타입이 없음
	// * 메서드는필드 타입과 동일하게 작성
	// * 만약 매개변수로 받을 타입이 다양하다는 것은
	// 필드의 타입도 다양하다는 것이다 
	
	public Rectangle (int n) {
		this(n, n);
		// & this의 두번째 용법
		// (2) 나말고 다른 생성자 호출
		// # int가 2개인 생성자를 호출하기
	}

	// 3. 메소드
	
	public int getArea () {
		return width * height;
	}
	// # 필드값 타입이 int이니, 
	// 값을 받을매개변수 타입도 int고,
	// 메소드의 타입도 int야 한다 *
	
	public int get () {
		return (width + height) * 2;
	}
 	

}
