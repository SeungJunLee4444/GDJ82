package prac3;

public class Bakery {

	// 필드
	private int price;  // 빵가격
	private int cnt;    // 빵개수
	private int money;  // 자본금
	
	// 생성자
	public Bakery(int price, int cnt, int money) {
		super();
		this.price = price;
		this.cnt = cnt;
		this.money = money;
	}
	
	// 판매
	public BreadAndChange sell(int custMoney) throws RuntimeException {
		
		// 판매 불가
		if(custMoney < price) {
			throw new RuntimeException("판매 불가");
		}
		
		// 판매할 수 있는 빵은 몇 개인가?
		int sellCnt = custMoney / price;
		
		// 잔돈은 얼마인가?
		int change = custMoney % price;
		
		// 매장 내부의 변화 처리
		cnt -= sellCnt;
		money += (custMoney - change);
		
		// 고객에게 되돌려 줄 빵과잔돈
		return new BreadAndChange(sellCnt, change);
		
	}

	// 정보 확인
	public void info() {
		System.out.println("빵 " + cnt + "개, 자본금 " + money + "원");
	}
	
}