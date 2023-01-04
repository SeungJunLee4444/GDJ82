package quiz_04;

public class Main {
	
	public static void main(String[] args) {
		
		Customer customer = new Customer();
		customer.setMoney(10000);
		customer.buy(new Snack("홈런볼", 1500));
		customer.buy(new Meat("한우", 5000));
		customer.buy(new Milk("서울우유", 2500));
		customer.buy(new Meat("불고기", 5000));		// 만원예산을 넘어가서 구매불가
		customer.receipt();
		
		
		
		
	}
	

}
