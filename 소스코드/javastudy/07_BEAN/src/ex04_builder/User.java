package ex04_builder;

public class User {
	
	private int userNo;
	private String id;
	private String email;
	
	// #1 User 생성자 만들기(Builder을 매개변수로 하는)
	public User(Builder builder) {
		this.userNo = builder.userNo;
		this.id = builder.id;
		this.email = builder.email;
	}

	// #1 User 클래스 내부에 Builder클래스 생성
	// (클래스 내부에 클래스 만들기
	// => User 클래스로 호출해야하기 때문에 static 처리 ?
	public static Builder builder() {
		return new Builder();
	}
	
	
	public static class Builder {
	
		
		// #2 내부필드를 또 만듬(여기에 값을 전달받아서 User의 필드로 보내는 원리)
		private int userNo;
		private String id;
		private String email;
	
		// userNo() 메서드 
		public Builder userNo(int userNo) {
			this.userNo = userNo;
			return this;
		}
		
		// id() 메서드
		public Builder id(String id) {
			this.id = id;
			return this;
		}
		
		// email 메서드
		public Builder email(String email) {
			this.email = email;
			return this;
		}
		
		
		// build() 메서드
		public User build() {
			return new User(this); 	// this 는 Builder 객체(userNo, id, email을 가지고 있는 객체를 의미함
		}
		
		}	
		
		@Override
		public String toString() {
			return super.toString();
		}




}
