package ex03_has_a_o;

public class Soldier {
	
	// # 필드
	private Gun gun;
	// # 클래스 Gun을 포함한다 
	// # gun이 부모, soldier이 자식
	
	// # 메소드

	public Gun getGun() {
		return gun;
		// # getgun 메서드 : gun을 값으로 반환
	}

	public void setGun(Gun gun) {
		this.gun = gun;
		// # setgun 메서드 사용 : gun을 값으로 설정
	}
	
	public void reload(int bullet) {
		gun.reload(bullet);
		// # gun의 reload를 호출하여 bullet을 넣는다 * 캡슐화
	}
		
	public void shoot() {
		gun.shoot();
		// # gun의 shoot 클래스를 호출 * 캡슐화
	}
}
