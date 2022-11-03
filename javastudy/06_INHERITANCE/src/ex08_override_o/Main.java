package ex08_override_o;

public class Main {

	public static void main(String[] args) {
		
		Shape circle = new Circle("도넛", 7.5);
		circle.info();
		// # 업캐스팅
		// => 
		
		Rectangle rectangle = new Rectangle("직사각형", 3, 5.6);
		rectangle.info();
		
		Square square = new Square("정사각형", 3);
		square.info();
		
		
		// * ex07, 08 정리 : 메서드 오버라이징
		// => 부모 클래스에서 만든 메서드를 재작성해야 하는 경우
		// ex) 모양(부모) - 삼각형(자식) / 사각형(자식)
		// if) 모양 클래스에 넓이를 구하는 메서드가 반환값이 0인 상태
		// => 삼각형과 사각형은 각자의 넓이를 구하기 위해, 
		// getArea 넓이 메서드를 오버라이징 해야 한다
		
		// # 과정
		// 1) 상속 선언 후, 생성자 만들기(source로 자동생성)
		// 2) 다시 작성해야할 메서드를 호출
		// (source / ctrl + 스페이스바)
		// => 이후 상황에 맞게 작성
		
		// * 필드값이 없는 경우(예시참고)
		// ex) 직사각형 클래스를 부모로 상속하는 정사각형 클래스
		// 1) source 두번째 생성자 사용
		// 2) 필요한 너비값만 매개변수로 받고,
		// super 부모() 괄호의 높이란에 너비변수를 넣기
		
		// + 예시
		
		// & 추가
		// 1) 	math.pi : 파이값
		// 		math.pow(숫자,제곱지수) : 제곱구하기
		
		// 2) 같은 클래스 내에서 메서드의 호출 가능 

	}

}
