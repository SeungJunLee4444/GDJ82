package quiz_02;

public class Americano {
	
	// * 상속없이 특정 클래스의 필드를 참조하고싶을떄
	
	private Espresso espresso;
	// # 상속되지 않을때 특정 클래스를 필드로 가져온다
	private int shot;
	// # 샷 추가
	private String type;
	
	public Americano(Espresso espresso, int shot, String type) {
		super();
		this.espresso = espresso;
		this.shot = shot;
		this.type = type;
	}



	// # 부모클래스가 없으니 새로 만들어야함

	public void info() {
		espresso.info();
		// # espresso 클래스의 info 클래스를 호출
		System.out.println(shot + "샷");
		System.out.println(type + "아메리카노");
	}
	
	
	
	
	

}
