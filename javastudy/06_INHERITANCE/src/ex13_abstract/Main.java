package ex13_abstract;


public class Main {

	public static void main(String[] args) {
		
		Shape s = new Shape();

		
		// # shape 클래스타입의 객체는 존재할 수 없는 객체이다
		// # 실제 도형이 아니기 때문
		// => 생성을 막아주는 것이 좋다(abstract 처리로 객체의 생성 막기)
		//Shape s1 = new Shape("도형");	// => 추상 클래스 처리했기 떄문에 막힘
		System.out.println(s1.getType());
		System.out.println(s1.getArea());
	
		
		Circle s2 = new Circle("원", 1);
		System.out.println(s2.getType());
		System.out.println(s2.getArea());
		
		Shape s3 = new Circle("원", 3);
		// => shape 타입이기 때문에 shape 클래스의 getArea를 우선 호출
		// => 이후 circle 메서드를 호출
	}

}
