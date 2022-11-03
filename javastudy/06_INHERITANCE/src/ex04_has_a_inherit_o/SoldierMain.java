package ex04_has_a_inherit_o;

public class SoldierMain {

	public static void main(String[] args) {
		
//		Gun gun = new Gun();
//		gun.setModel("k2");
//		gun.setBullet(9);
		
		Soldier soldier = new Soldier();
		
		
		// # 상속을 통해, 
		// soldier은 메소드가 없는데도 gun의 메소드를 사용가능해짐
		soldier.reload(10);
		soldier.shoot();
		System.out.println(soldier.getBullet());
		// => 
	}

}
