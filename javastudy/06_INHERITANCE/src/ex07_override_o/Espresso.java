package ex07_override_o;

public class Espresso extends Coffee {
	
	// # 둘다 부모가 에스프레소, 아메리카노, 까페라떼로 오버라이딩 실습
	
	@Override
	public void taste() {
		System.out.println("쓰다");
		// # 오버라이딩 됨
		// => 필수 규칙: 상속하는 부모 클래스의 메서드와 형태가 같아야함
		// (같은 매개변수, 같은 반환타입, 같은 이름)
		
		// # @override
		// => 이름이 다르면 자바는 다른 생성자라 생각하고 차이를 인식못한다
		// => @Override 작성으로 자바에서 확인하도록 한다
	}

}
