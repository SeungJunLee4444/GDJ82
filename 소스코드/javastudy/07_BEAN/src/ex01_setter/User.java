package ex01_setter;

public class User {
	
	// 필드

	private int userNo;
	private String id;
	private String email;


	
	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", id=" + id + ", email=" + email + "]";
	}
	// => 객체의 주소값을 문자열로 변환

	
	// 메서드
	

	
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
