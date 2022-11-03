package ex08_override_o;


public class Circle extends Shape {
	
	private double radius;
	// # radius : 반지름

	public Circle(String type, double radius) {
		super(type);
		this.radius = radius;
	}
	// # 생성자 자동생성 : 부모클래스 매개변수, super까지

	// # 원의 넓이 구하기
	// * 넓이는 모형마다 구하는 법이 다르니, getArea메서드 재생성
	// # shape 클래스의 넓이는 0이니 다시 작성해야한다
	// # 자동생성 가능(source / ctrl + 스페이스바)
	@Override
	public double getArea() {
		return Math.PI * Math.pow(radius, 2); 
		// * math.pi : 파이
		// * math.pow : (숫자, 제곱지수)
	}
	
	// # info도 다시 만들기
	// => 종류와 반지름의 길이도 같이 보여주게하기
	@Override
	public void info() {
		super.info();	// 도형의 종류
		System.out.println("반지름:" + radius);
		System.out.println("넓이:" + getArea() );
	}	// 넓이값을 getarea 호출
	
	

	
	
	
	
	

}
