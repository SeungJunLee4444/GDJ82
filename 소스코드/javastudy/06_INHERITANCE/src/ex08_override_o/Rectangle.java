package ex08_override_o;

public class Rectangle extends Shape{
	
	// # 실습2: 직사각형, 정사각형으로 오버라이징하기
	
	public double width;
	public double height;
	
	public Rectangle(String type, double width, double height) {
		super(type);
		this.width = width;
		this.height = height;
	}
	
	@Override
	public double getArea() {
		return width * height;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("가로:" + width );
		System.out.println("세로:" + height);
		System.out.println("넓이:" + getArea());
	}
	

	
	

}
