package ex14_interface;

public interface Shape {	// # class 자리에 interface
	
	// [상속 - 인터페이스]
	
	// static?
	
	// 1) final 상수
	public final static double PI = 3.141592;
	
	// # static 
	// 1) static 메서드를 사용하면 객체 생성없이 필드값, 메서드 호출이 가능해진다
	
	
	// 2) 추상메서드
	public double getArea();	// # abstract 생략가능 , public도 가능하긴 한데 잘 안지움
	
	// 3) default 메서드(본문, 내용이 있는 메서드)
	public static void message() {
		System.out.println("나는 도형");
	}
	

}
