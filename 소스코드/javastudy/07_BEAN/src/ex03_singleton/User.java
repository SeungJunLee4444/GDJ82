package ex03_singleton;

public class User {
	
	// singleton Pattern
	// db접근하는 객체는 오직 하나 뿐이어야함
	// => 만들어진 객체를 가져다 쓰는 것만 가능(클래스 필드에)
	// 외부에서는 새로운 객체를 생성할 수 없음
	
	// static 필드
	// => 객체 생성 이전에 메모리에 미리 만든 필드값
	// (미리 유저를 하나 만들어두고)
	// (필요하면 아래 User getInstance로 가져다 써라)
	// (외부에서는 new User 불가능)
	private static User user = new User();	
	
	private User() {	// User 객체 생성은 User 내부에서만 가능하다
					
	}
	
	public static User getInstance() {	// static 메서드는 클래스 메서드
		return user;					// => 클래스가 생성될 때 함께 만들어지는 메서드
	}									// => 객체 생성 이전에 만들어지기 때문에, 객체로 접근하지않음
										// 호출하는법 User user = User.getInstance()	/ 값 user
	
	

}
