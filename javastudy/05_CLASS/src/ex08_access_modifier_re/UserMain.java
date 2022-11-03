package ex08_access_modifier_re;

public class UserMain {

	public static void main(String[] args) {
		
		// # 클래스 생성
		User user = new User();
		
		// # user 필드가 private기 때문에, 우회하여 확인하기 위한 get 작업 
		System.out.println(user.getId());
		System.out.println(user.getpassword());
		System.out.println(user.getEmail());
		System.out.println(user.point());
		System.out.println(user.isVip());
		
		// # 필드값 수정을 위한 set작업
		user.setId("admin");
		user.setPassword("1234");
		user.setEmail("sdksl@fklsd");
		user.setPoint(5000);
		// user.setIsVip(true);
		
		// # 조건 : 10000점 이상부터 vip
		// => 만약 포인트를 1000점, vip여부를 true라고 한다면 오류가 발생할 것 
		// => 처음부터 point 메서드에 setisvip 메서드를 집어넣어 조건으로 
		// 값이 나오도록 할것?
		// # 선언 메서드를 이용한 반환
		user.status();
	}

}
