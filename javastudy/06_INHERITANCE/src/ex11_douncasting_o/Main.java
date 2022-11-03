package ex11_douncasting_o;

public class Main {
	
	public static void main(String[] args) {
		
		// # 다운사이징 연습문제 1
		// : 임의의 자동차 10대 배열에 저장하기(33확률로 랜덤생성)
		
		Car[] cars = new Car[10];
		
		for(int i = 0; i < cars.length; i++) {
			if(Math.random() < 0.33) {			// # 확률응용, 0.33까지
				cars[i] = new Car();
			} else if (Math.random() < 0.66) {	// # 확률응용, 0.33~0.66까지
				cars[i] = new Ev();
			} else {
				cars[i] = new Hybrid();
			}	
			// # 클래스가 선언되는 순간, 생성자에 담긴 출력문이
			// cars 배열에 임의의 확률로 클래스를 선언
			// 객체배열 관련 o

			
		}
		// => 
		// Car이면 drive() 호출 
		// Ev charge() 호출
		// Hybrid면 addOil() 호출
		
		for(int i = 0; i < cars.length; i++) {
			if(cars[i] instanceof Hybrid) {
				((Hybrid)cars[i]).addOil();
			} else if (cars[i] instanceof Ev) {
				((Ev)cars[i]).charge();
			} else if (cars[i] instanceof Car) {
				cars[i].drive();
			}
		}	
		// #  cars 배열에 담긴 클래스들에 따라 
		// # 만약 car 클래스가 제일 위에있었으면, 전부 값이 drive로 나왔을 것
		// => hybrid, ev 전부 car 클래스를 참조하기 때문
		// => 따라서 제일 특수한 범위인 hybrid부터 맨 위에 조건을 작성
		// ex) 배열의 값이 hybrid면 hybrid 조건에서 결과가 나오고 조건문종료 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
