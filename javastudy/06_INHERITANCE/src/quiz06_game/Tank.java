package quiz06_game;

public class Tank extends GameUnit {

	public Tank(String name) {
		super(name, 100, 10);	// # 이름만 받아오는 방법, 에너지 100, 공격력 10
		
	}
	
	@Override
	public void attack(GameUnit unit) {
		// # 내 파워만큼 상대의 에너지가 줄어든다
		
		if(Math.random() < 0.1) {
			unit.setEnergy(0); 	// # set은 수정
			unit.setAlive(false); // # 체력이 0이되어 죽음
			System.out.println(unit.getName() + "을 한방에 죽였다");
		} else {
			int unitEnergy = unit.getEnergy() - getPower() < 0 ? 0 : unit.getEnergy() - getPower();
			// # 상대에너지 때렸는데 0이하면 0으로 표시, 아니면 뺀값
			unit.setEnergy(unitEnergy);
			// # 상대의 에너지 = 상대의 에너지 - 내 공격력
			unit.setAlive(unitEnergy > 0);
			System.out.println(unit.getName() + "의 남은 에너지" + unit.getEnergy());
			
	}
	
	}	
	
	
	
	

}
