package ex02_constructor;

public class User {
	
	private int userNo;
	private String id;
	private String email;
	
	
	public User(int userNo, String id, String email) {
		super();
		this.userNo = userNo;
		this.id = id;
		this.email = email;
	}


	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", id=" + id + ", email=" + email + "]";
	}
	
	// * 롬복은 호출하려는 객체의 클래스에 있어야 기능한다 
	
	
	
	

}
