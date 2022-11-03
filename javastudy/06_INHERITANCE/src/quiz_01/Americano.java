package quiz_01;

public class Americano extends Espresso {
	
	private String type;

	public Americano(String coffee, int water, String type) {
		super(coffee, water);
		this.type = type;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println(type + "아메리카노");
	}
	
	

}
