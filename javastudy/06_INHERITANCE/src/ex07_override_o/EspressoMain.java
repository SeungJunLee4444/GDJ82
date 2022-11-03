package ex07_override_o;

public class EspressoMain {

	public static void main(String[] args) {
		
		Coffee coffee = new Americano();
		coffee.taste();
		
		Espresso espresso = new Espresso();
		espresso.taste();	// 쓰다
		
		Americano americano = new Americano();
		americano.taste();	// 고소하다
		
		CafeLatte cafeLatte = new CafeLatte();
		cafeLatte.taste(); 	// 달다
		
	}

}
