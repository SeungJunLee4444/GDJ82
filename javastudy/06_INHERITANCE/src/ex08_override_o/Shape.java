package ex08_override_o;

public class Shape {
	
	// * ex07, 08 : 메서드 오버라이징 실습
	// ex08 : 
	
	private String type;
	// # 도형의 종류

	public Shape(String type) {
//		super();
		this.type = type;
	}
	// # 생성자 자동 형성
	
	// # 넓이 반환 메서드
	public double getArea() {
		return 0;
	}
	// # 업캐스팅을 하기위한 자식클래스의 메서드 호출용 메서드
	
	// # 도형의 정보 메서드
	public void info() {
		System.out.println("도형의 종류:" + type);
		
	}
	
	
	

}
