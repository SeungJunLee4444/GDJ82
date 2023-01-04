package ex03_has_a_o;

public class SoldierMain {

	public static void main(String[] args) {
		
		Gun gun = new Gun();
		gun.setModel("k2");
		gun.setBullet(9);
		
		
		Soldier soldier = new Soldier();
		soldier.setGun(gun);
		soldier.getGun().shoot();
		
	
		// * 자식 => 부모의 메소드까지 호출하는 법
		// # soldier가 총을 쏜다
		// soldier.getGun().shoot();
		soldier.shoot();
		
		// * 캡술화 
		// => 특정 기능을 어느 클래스에 넣을건지?
		// => 상위 메소드에 하위메소드를 호출하는 식
		// => 캡술화는 상속이 아니라 클래스의 특성
		// => 상속으로 해결할수 있다
		// * 캡술화도 좋은 코드는 아니다
		
		// # soldier가 장전한다
		soldier.reload(1);
		
		
		// # soilder가 가지고 있는 gun의 모델명
		System.out.println(soldier.getGun().getModel());
		System.out.println(soldier.getGun().getBullet());
		
	}

}
