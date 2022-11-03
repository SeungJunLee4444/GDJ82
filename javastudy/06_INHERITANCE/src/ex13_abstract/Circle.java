package ex13_abstract;

public class Circle extends Shape {
	
	private double radius;

	public Circle(String type, double radius) {
		super(type);
		this.radius = radius;
	}
	
	// # shape는 추상 클래스이므로, 반드시 getarea 메서드를 오버라이징 해야함
	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2);	// # 원의 넓이 구하기
	}
	
	

}
