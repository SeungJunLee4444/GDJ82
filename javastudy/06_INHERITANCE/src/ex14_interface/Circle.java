package ex14_interface;

public class Circle implements Shape {
	
	private double radius;
	
	
	
	
	public Circle(double radius) {
		super();
		this.radius = radius;
	}



	@Override
	public double getArea() {
		return PI;
	}
	


	public double getRadius() {
		return PI * Math.pow(radius, 2);
		// # 여기서 pi는 인터페이스에 static 메서드가 선언된 값으로,
		// 상속관계이기 때문에 별도의 객체선언없이 사용 가능하다
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

}
