package ex16_interface_o;

// extends Pet : 애완동물이다
// implements Walkable : 산책이 된다

// dog cat 에게는 extends, implement 둘다 부여
// snake 에게는 extends만 부여

public class Person implements Walkable {
	
	// # person은 디폴트 생성자다
	
	public void foodFeed(Pet pet, String food) {
		System.out.println(pet.getPetName() + "에게" + food + "주기");
		
		// * 매개변수에서 클래스 선언
		// pet 클래스는 dog, cat, snake를 전부 저장할 수 있는 클래스 타입이다
		// ex) String에 모든 문자열을 저장할 수 있고, int에 20억 위아래의 모든 정수를 저장할 수 있듯이
		

		// * 접근제한자
		// => public 어디서든 접근 가능
		// => private 현재 객체에서만 접근가능
		// => default 같은 패키지 내에서만 접근가능
		
	}
		
		public void walk(Walkable pet) {
			System.out.println(((Pet) pet).getPetName());
			// # Walkable에는 아무내용도 없기 때문에, pet을 강제 캐스팅하여 getPetName을 강제 호출
			// => 캐스팅을 자유자재로 쓸 수 있게될 것
			
		}
		
		
		
		
	
	
	

}
	
	
	
	

	
	

	
	
	

