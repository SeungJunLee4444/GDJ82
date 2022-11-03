package prac1;

public class Ex10 {

	public static void main(String[] args) {
		
		Soldier soldier = new Soldier(new Gun("K2"), "람보");
		
		soldier.reload(2);
		soldier.shoot();
		
		soldier.reload(10);
		soldier.info();

	}

}
