package ex04_has_a_inherit_o;

// 예시) 군인이 총을 들고있는 경우

public class Gun {
	
	// # 필드
	private String model;
	private int bullet;
	private final int MAX_BULLET = 15;
	// & final을 설정하면 get set을 가져올 때, set을 만들 수 없다
	// => 값이 고정되기 때문 *
	
	// * 여기에 생성자가 없는게 아니라,
	// 디폴트 생성자가 선언되서 굳이 만들지 않은것
	
	
	// # 메소드
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getMAX_BULLET() {
		return MAX_BULLET;
	}
	public int getBullet() {
		return bullet;
	}
	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
	// # 장전
	public void reload(int bullet) {
		if(this.bullet == MAX_BULLET) {
			return;
		} 
		// # 현재 총알이 15개면 리턴;
		this.bullet += bullet;		
		this.bullet = (this.bullet > MAX_BULLET) ? MAX_BULLET : this.bullet;
		// # 만약 15개 이상 넘어가면 15개로 표시하고, 아니면 현 총알개수를 출력
	}
		
	// # 총쏘기
	public void shoot() {
		if(bullet == 0) {
			return;
		// # 매개변수가 없으니 this를 굳이 안써도됨	
		}
		bullet--;
	}
	

}
