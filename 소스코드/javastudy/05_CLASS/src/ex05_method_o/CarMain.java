package ex05_method_o;

public class CarMain {

	public static void main(String[] args) {
		
		// # 연습 2 
		// 자동차의 기능, 기름과 속도를 메소드로 표현하기
		
		// # 객체생성
		Car car = new Car();
		
		// #1 난수값만큼 기름넣기, 60이 저장량 최대
		car.addoil(50);
		System.out.println(car.oil);
		// # car 클래스의 필드값 접근이 private가 아니기 때문에 열람 가능 *
		
		// #2 달리기
		car.pushaccel();
	;
		// # 최대속도가 120이기 때문에 120값만 나오게된다
		
		// #4 계기판 생성
		System.out.println("기름" + car.oil);
		System.out.println("속도" + car.speed); 
		// => 일일히 출력문을 내야하는 방식을, 메서드 생성으로 해결할 수 있다

		car.panel();
		
		
		
		
		
		
		
	}

}
