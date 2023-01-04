package quiz06_game;

public class Marine extends GameUnit {

	public Marine(String name) {
		super(name, 50, 5 );	// # 마린 에너지 50, 공격력 5
		
	}
	
	@Override
	public void attack(GameUnit unit) {
		
		// # 내 에너지 확인: getEnergy(); (this를 붙여도됨)
		// # 내 공격력     : getPower(); 
		// # 내 이름 	   : getName();
		
		// # 상대 에너지   : unit.getEnergy()
		// # 상대 공격력   : unit.getPower()
		// # 상대 이름 	   : unit.getName()
		
		// # 40퍼센트 확률로 ko 시킬 수 있다
		if(Math.random() < 0.4) {
			unit.setEnergy(0); 	// # set은 수정
			unit.setAlive(false); // # 체력이 0이되어 죽음
			System.out.println(unit.getName() + "를 한방에 죽였다");
		} else {
			int unitEnergy = unit.getEnergy() - getPower() < 0 ? 0 : unit.getEnergy() - getPower();
			// # 상대에너지 때렸는데 0이하면 0으로 표시, 아니면 뺀값
			unit.setEnergy(unitEnergy);	// # 남은 체력을 저장
			// # 상대의 에너지 = 상대의 에너지 - 내 공격력
			unit.setAlive(unitEnergy > 0);	// # 체력이 0보다 크면 true
			System.out.println(unit.getName() + "의 남은 에너지" + unit.getEnergy());
			
		}
		
		
	}
	
	

	
	
	

}
