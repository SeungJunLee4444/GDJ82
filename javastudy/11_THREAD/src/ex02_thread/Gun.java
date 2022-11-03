package ex02_thread;

public class Gun {
	
	private int bullet;

	public Gun(int bullet) {
		super();
		this.bullet = bullet;
	}
	
	public void shoot() {
		if(bullet == 0) {
			System.out.println("총알없음");
			return;
		}
		bullet--;
		System.out.println("빵야!" + (bullet) + "발 남음" ); 
	}

	public int getBullet() {
		return bullet;
	}

	public void setBullet(int bullet) {
		this.bullet = bullet;
	}
	
	
	
	
	
	

}
