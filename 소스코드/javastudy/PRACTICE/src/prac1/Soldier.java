package prac1;

public class Soldier {

	private Gun gun;
	private String name;
	
	public Soldier(Gun gun, String name) {
		super();
		this.gun = gun;
		this.name = name;
	}
	
	public void reload(int bullet) {
		gun.reload(bullet);
	}
	public void shoot() {
		gun.shoot();
	}
	public void info() {
		System.out.println("군인이름: " + name);
		gun.info();
	}
	
}
