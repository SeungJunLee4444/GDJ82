package quiz_01;

public class Espresso extends Coffee {
	
	private int water;

	public Espresso(String coffee, int water) {
		super(coffee);
		this.water = water;
	}
	

	

	public void infom() {
	
		System.out.println("물" + water + "ml");
	}
	
	
	

}
