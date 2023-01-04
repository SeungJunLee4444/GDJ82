package quiz_02;


public class Main {

	public static void main(String[] args) {
		
		Espresso espresso = new Espresso("케냐", 50);
		espresso.info();
		
		Americano americano = new Americano(espresso, 2, "아이스");
		americano.info();
		// # espresso 참조
		
	}
}
