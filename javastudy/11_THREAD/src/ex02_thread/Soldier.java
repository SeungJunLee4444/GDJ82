package ex02_thread;

public class Soldier extends Thread {
	
	private String name;
	private Gun gun;

	
	public Soldier(String name, Gun gun) {
		super();
		this.name = name;
		this.gun = gun;
	}
	
	public void shoot() {
		System.out.print(name + " : " );
		gun.shoot();
	}
	
	@Override
	public void run() {
		try {
		// ex) 1초에 한발씩 쏘기
		while(gun.getBullet() != 0) {
			shoot();
			Thread.sleep(1000);
		}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
