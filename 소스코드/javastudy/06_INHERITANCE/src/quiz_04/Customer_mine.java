package quiz_04;

public class Customer_mine {
	
	private Product[] cart = new Product[10];	// #1 제품을 10개 담을 수 있는 카트
	private int idx;							// #1-2 카트에 담은 물건이 몇번째인지 indx 값
	private int money;
	private int bonusPoint;
	private int total;
	
	// * 필드는 기본적으로 값을 초기화
	
	// # 2 디폴트 생성자기 때문에 생략가능
	public Customer_mine() {
		
	}
	
	// # 3 setMoney 메서드
	
	public int getMoney() {
		return money;
	}
	
	// # set 메서드 
	public void setMoney(int money) {
		this.money = money;
	}

	public int getBonusPoint() {
		return bonusPoint;
	}

	public void setBonusPoint(int bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	
	// # 4 buy 메서드
	public void buy(Product product) {
		int price = product.getPrice();	// # 리펙토링
		
		// # 4-1 가진 돈(money)보다 가격이 비싼 제품은 못산다(1만원 예산을 넘어서면 x)(예외사항)
		if(money < price) {		// total(쓴 금액) + price(사려고 하는 제품의 가격)이 예산(money)를 넘어서면		
			System.out.println("돈이" + (total + price) + "원 부족합니다");
			return;
			// # product.getPrict() : 한번 살때 물건의 가격
		}
	
		// # 4-2 카트가 가득차면 물건을 못산다(index)(예외사항)
		if(idx == cart.length) {
			System.out.println("카트가 가득 찼습니다");
			return;
		} 
		
		// # 4-3 구매(본사항)
		cart[idx++] = product;	// # 카트 idx 0부터 넣고, idx 1씩 늘리기
		money -= price; // # 물건을 산만큼 돈도 줄어든다
		total += price;
		bonusPoint += price * 0.1;	// # 제품의 10분의 1가격만큼 포인트가 늘어남
		
	}
	
	// # 5 영수증 출력(receipt)
	
	public void receipt() {
		// # 5-1 물건을 안샀다(예외사항)
		if(idx == 0) {
			System.out.println("구매한 물건이 없습니다");
			return;
		}
		
		// # 5-3 구매총액(idx만큼 구매된 상태)(본상황)
		for(int i = 0; int < idx; i++) { // # 배열의 길이가 아닌 idx, 구매한 만큼인 idx만 나오게 하는 null 회피법 *
			Product product = cart[i];
			System.out.println(product.getName() + "\t" + product.getPrice() + "원");
//			total += product.getPrice();
		}			
		System.out.println("----------------------------");	
		System.out.println("구매총액:" + total + "원");
		System.out.println("보너스:" + bonusPoint + "원");
		System.out.println("남은 돈" + money + "원");
	}

	
	

	

		
	
	
	
	
	
	
	

}
