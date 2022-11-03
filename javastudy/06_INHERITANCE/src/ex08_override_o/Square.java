package ex08_override_o;

public class Square extends Rectangle {
	// # 직사각형을 부모로 상속한다
	
//	private double width;
	// # 필드가 필요가 없음
	// # 필드가 없으면 (첫번째)자동생성을 호출하지 못함

//	public Square(String type, double width) {
//		super(type);
//		this.width = width;
//	}
	// # 필드값이 없을 때, 두번째 source 자동생성을 사용한다
	public Square(String type, double width) {
		super(type, width, width);
		// # 너비만 받아, 슈퍼클래스의 높이에 넣는다
	}
	
	
	// # 직사각형의 메서드를 그대로 쓰면됨
//	@Override
//	public double getArea() {
//		return Math.pow(width, 2);
//	}
//	
//	
//
//	@Override
//	public void info() {
//		super.info();
//		System.out.println("한 변의 길이:" + width);
//		System.out.println("넓이" + getArea());
//	}
	
	

}
