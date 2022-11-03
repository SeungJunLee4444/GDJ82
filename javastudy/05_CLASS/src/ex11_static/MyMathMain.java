package ex11_static;

public class MyMathMain {

	public static void main(String[] args) {
		
		MyMath math1 = new MyMath();
		System.out.println(math1.abs(-5));
		// => -5의 절댓값 출력
		MyMath math2 = new MyMath();
		System.out.println(math2.abs(0));
		MyMath math3 = new MyMath();
		System.out.println(math3.abs(12));
		
		// => math1,2,3,은 모두 하는역할이 같다
		// * MyMath를 이용해 하나의 메소드로 문제를 해결할 수 있지 않을까?
		// => 자바의 MyMath 영역에 필드값, 메소드를 하나씩 저장하고 써오는 방법 
		
		
		
		System.out.println(MyMath.PI);
		System.out.println(MyMath.abs(-5));
		
		
		System.out.println(MyMath.pow(2,5));
		// 
	}

}
