package prac3;

public class Main {

	public static void main(String[] args) {
		
		Bakery paris = new Bakery(500, 100, 10000);  // 빵1개 500원, 빵100개, 자본금10000원
		Bakery tour = new Bakery(1000, 50, 10000);   // 빵1개 1000원, 빵50개, 자본금10000원
		
		Customer customer = new Customer(20000);  // 20000원가진고객
		
		try {
			// customer.buy(paris, 30000);  // 구매 불가
			// customer.buy(tour, 500);     // 판매 불가
			customer.buy(paris, 10000);  // 구매한빵20개, 남은돈10000원
			customer.buy(tour, 5000);    // 구매한빵25개, 남은돈5000원
		} catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		paris.info();  // 빵80개, 자본금20000원
		tour.info();   // 빵45개, 자본금15000원

	}

}
