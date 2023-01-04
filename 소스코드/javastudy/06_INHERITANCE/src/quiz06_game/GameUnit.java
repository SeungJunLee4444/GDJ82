package quiz06_game;

public abstract class GameUnit {
	
	private String name;
	private int energy;
	private int power;
	private boolean Alive;	// # boolean alive;로 해도 동일한 메서드가 생성
	
	public GameUnit(String name, int energy, int power) {
		this.name = name;
		this.energy = energy > 0 ? energy : 0;	//# 0보다 크면 energy표기, 아니면 0
		this.power = power;
		this.Alive = energy > 0; // # 0보다 에너지가 크면 살아있음
	}
	
	public void info() {
		System.out.println(name + "에너지" + energy + "공격력" + power);
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public boolean isAlive() {
		return Alive;
	}

	public void setAlive(boolean alive) {	// # boolean 타입은 get메서드가 get이 아니라 is다
		Alive = alive;
	}
	
	public abstract void attack(GameUnit unit);
	// # 추상타입 => marine, tank 클래스에서 오버라이징
	

	
	
	
	

}
