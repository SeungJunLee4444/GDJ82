package ex08_access_modifier_re;

public class User {
	
	// # 필드는 private
	private String id;
	private String password;
	private String email;
	private int point;
	private boolean isVip;
	
	// #1 get 작업
	// => 우회하여 필드값을 확인하는 메서드 생성
	public String getId() {
		return id;
	}
	public String getpassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public int point() {
		return point;
	}
	public boolean isVip() {
		return isVip;
	}
	// * 필드의 데이터 타입과 맞게 작성할것 *
	// => 필드값 확인을 위해 값을 반환해야 하므로,
	// 타입은 void가 아닌 int string boolean, return으로 반환값을 작성하였다
	
	// * this의 기능
	// (1) 해당 클래스 내 필드값 호출(매개변수와 혼선이 없기위해)
	// (2) 다른 생성자의 내용 가져오기
	// this의 기능 (1) 필드변수명 호출 (2) 다른 생성자의 내용 가져오기 *
	
	
	// #2 필드값을 변경하는 set 작업
	public void setId (String id) {
		this.id = id;
	}
	public void setPassword (String Password) {
		this.password = Password;
	}	
	public void setEmail (String Email) {
		this.email = Email;
	}	
	public void setPoint (int Point) {
		this.point = Point;
		setIsVip(point >= 10000);
	// * point와 vip여부를 동시에 처리 *
	// # point가 만점 이상이면 setisvip가 참이 된다 *
	// => 생성된 메서드들을 위와같은 식으로도 활용할 수 있음을 숙지 *
	}
	
	private void setIsVip (boolean IsVip) {
		this.isVip = IsVip;
	}
	// # 포인트로 vip 여부를 확인
	// # vip 여부는 private로 바꾸기
	// => point가 만점 이상이면 setisvip가 참이 된다 *

	// # 선언 메소드 생성
	void status() {
		System.out.println(id);
		System.out.println(password);
		System.out.println(email);
		System.out.println(point);
		System.out.println(isVip);
	}
	// * 여러 실행문을 편하게 사용가능
	
	
}
