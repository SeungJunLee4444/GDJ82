package ex07_override_o;

public class CafeLatte extends Espresso {
	
	private int milk;
	
	@Override
	public void taste() {
		System.out.println("달다");
	}

}
