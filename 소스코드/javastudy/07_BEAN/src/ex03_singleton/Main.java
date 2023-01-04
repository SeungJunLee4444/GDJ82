package ex03_singleton;

public class Main {
	
	public Main() {

//		User user = new use
		// => 새로운 생성자를 만들 수 없음
		
		User user1 = User.getInstance();
		System.out.println(user1);
		
		// singleton 객체는 하나만 생성된다
		// => 여러 객체가 만들어지면 안되는 경우에 사용
		
		User user2 = User.getInstance();
		System.out.println(user2);
		
		
		
	}
	
	
	

}
