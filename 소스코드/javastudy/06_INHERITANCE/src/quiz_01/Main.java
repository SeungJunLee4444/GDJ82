package quiz_01;

public class Main {

	public static void main(String[] args) {
		
		Coffee coffee = new Coffee("가나");
		coffee.info();
		
		Coffee coffee2 = new Espresso("다라", 30);
		coffee2.info();
		
		//부모클래스 + 부모 생성자 : 부모 info
		//부모클래스 + 자식 생성자 : 부모 메서드와 이름이 동일하면 자식인포, 다르면 부모 info
		
		Espresso espresso = new Espresso("케냐", 50);
		espresso.info();
		
		Americano americano = new Americano("케냐", 300, "아이스");
		americano.info();

	}

}
