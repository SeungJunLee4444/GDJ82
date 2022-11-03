package ex10_this_o;

public class RectangleMain {

	public static void main(String[] args) {
		
		// 문제: 직사각형과 정사각형을 다르게 생성
		
		// 1. 직사각형
		Rectangle rectangle1 = new Rectangle(3,4);
		
		// 2. 정사각형
		Rectangle rectangle2 = new Rectangle(5);
		
		System.out.println("넓이 :" + rectangle1.getArea());
		System.out.println("둘레 :" + rectangle1.get());
		System.out.println("넓이 :" + rectangle2.getArea());
		System.out.println("둘레 :" + rectangle2.get());
	}

}
