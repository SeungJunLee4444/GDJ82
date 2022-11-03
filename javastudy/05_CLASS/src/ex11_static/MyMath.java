package ex11_static;

public class MyMath {
	
	// 1. MyMath 메소드
	// & 수학연산 메소드는 static (final)을 쓸 것
	// => 필드값은 public static final
	// => 메소드는 public static
	
		
	// 1) static 
	// => 정적요소
	// => 객체 생성 이전에 메모리에 미리 만들어지는 공유 요소
	// => 클래스에서 1개만 만들어짐
	// => 클래스로 호출하기 때문에 클래스 변수, 클래스 메소드 라고 부름
	// * static을 붙이면 클래스 메소드가 된다
	
	// # 필드 
	public static final double PI = 3.141592;
	// # static 
	// # final : 값을 고정
	// # public : final로 고정했기 때문에 공개해도 상관 무
	// * 위 세가지는 항상 함께 붙여씀
	

	// 2) 메소드
	public static int abs(int n) {
		return (n > 0) ? n : (n == 0) ? 0 : -n;
		// * MyMath의 기초버전
		// # 절댓값 반환하기
	}
	
	public static int pow(int a, int b) {
		int result = 1;
		for(int cnt = 0; cnt < b; cnt++) {
			result *= a;
			}
		return result;
		}
		// # 2의 5제곱(32) 반환하기
		// # result를 하나 변수선언하고 2를 대입연산자로 곱하기 *
		// & return 은 for문 밖에서 선언할 것 *
	

}
