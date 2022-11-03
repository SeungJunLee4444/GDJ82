package quiz_02;

public class Espresso extends Coffee {
	
	private int water;

	public Espresso(String coffee, int water) {
		super(coffee);
		this.water = water;
	}
	
	@Override
	public void info() {
		super.info();
		System.out.println("물" + water + "ml");
	}
	
	
	
	
	

}
