package ex05_lombok;

public class Main {
	
	public static void main(String[] args) {
		
		User user1 = new User();
		user1.setId("admin");
		user1.setEmail("admin@naver.com");
		user1.setUserNo(1);
		System.out.println(user1);
		
		User user2 = new User(1, "admin", "admin@naver.com");
		System.out.println(user2);
		
		// 빌터패턴
		
		
		User user3 = User.builder()
				.userNo(1)
				.id("admin")
				.email("admin@naver.com")
				.build();
				System.out.println(user3);
		
	}

}
