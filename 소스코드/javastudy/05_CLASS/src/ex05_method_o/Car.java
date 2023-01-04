package ex05_method_o;

public class Car {
	
	// [메서드]
	// # 연습 2 자동차로 필드, 메소드 동시에 활용하기
	// 자동차의 기능, 기름과 속도를 메소드로 표현하기
	
	// # 필드
	int oil;
	int speed;
	
	// # 메소드
	
	// #1 기름넣기
	// void 타입 / addoil 이름 / int o 매개변수
	void addoil(int o) {
		if(oil == 60) {
			return;
			// # 기름이 애초에 60이면 채울필요가 없음
		}
		
		oil += o;
		if(o >60) {
			oil = 60;
		}
		// # 난수만큼 기름을 넣고, 만약 60이상이면 60으로 표시
		// # 60이 저장량 최대임을 표현
	}
	
	// * void는 반환값이 없는 타입으로, return; 또는 생략해도된다
	// return은 반복문, 메서드의 완전 탈출에 사용된다
	// * 반환값이 없다는 것은 클래스 내 필드에 값을 저장할 때
	// * 반환값이 있다는 것은 값을 받아서 새로운 값을 리턴한다는 뜻
		
	
	// #2 액셀
	// void 타입 , pushaccel 이름 / 매개변수 x
	// 액셀 한번밟을때마다 속도가 25상승,기름은 1씩 감소 최대속도 120이 한계

	void pushaccel() {
		if(oil == 0) {
			return;
		}
		// # 애초에 기름이 0이면 못간다
		
		if(speed == 120) {
			oil--;
		}
		// # 애초에 속도가 120최대면 기름만 소비한다
		
		speed += 25;
		oil--;
		if(speed > 120) {
			speed = 120;
		// # 정상작동, 120이 최대속도
		}
	}
	
	// #3 브레이크
	// void 타입 / pushbrake 이름 / 매개변수 x
	// 한번 밟을때 25감소
	void pushbrake() {
		// # 애초에 스피드가 0이면 메서드를 빠져나가라
		if(speed == 0) {
			return;
		}
		speed -= 25;
		oil--;
		if(speed < 0) {
		speed = 0;
		}
		
	}
	
	// #4 계기판 생성
	// void 타입 / panel 메소드명 / 매개변수x
	void panel() {
		System.out.println("기름" + oil);
		System.out.println("속도" + speed);
	}
}
		
		
			
	
	

		
		
		
	

	


