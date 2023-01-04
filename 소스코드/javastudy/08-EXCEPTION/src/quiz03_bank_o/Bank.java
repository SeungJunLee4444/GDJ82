package quiz03_bank_o;

public class Bank {
	
	private String accNo;
	private long balance;
	
	// # quiz3 
	// 마이너스 입금불가, 코드값 1000
	// 마이너스 출금 불가, 코드값 2000
	// 잔액보다 큰 출금 불가, 코드값 2000

	
	
	public Bank(String accNo, long balance) {
		this.accNo = accNo;
		this.balance = balance;
	}

	public void deposit(long money) throws BankException {
		if(money < 0) {
			throw new BankException("마이너스 입금 불가", 1);
		}
		balance += money;	
	}
	
	public long withdrawl(long money) throws BankException {
		if(money < 0) {
			throw new BankException("마이너스 출금 불가", 2);
		} else if(balance < money) {
			throw new BankException("잔액부족", 3);
		} 
		balance -= money;
		return money;	// 출금액
			
			
		}
	
	public void transfer(Bank acc, long money) throws BankException {
		acc.deposit(withdrawl(money));	// * 오류 : deposit, withdrawl 은 호출하는 쪽(현재위치)으로 예외를 던지기 때문	
										// => trycatch를 하거나 / 다시 던지거나
	}

	public void inquiry() { 		// * 단순한 정보 출력이기 때문에 예외x
		
		System.out.println(this);	// * 입력된 값들의 현재값이니 this
		
	}


	@Override
	public String toString() {
		return "Bank [accNo=" + accNo + ", balance=" + balance + "]";
	}
	
	public static void main(String[] args) throws BankException {		// * 아무도 trycatch 안할 수도 있음
		Bank me = new Bank("1111", 10_000);	// * 세자리마다 _를 써도 문제없음
		Bank mom = new Bank("2222", 100_000);
		
		try {
			me.deposit(-1);
			me.transfer(me, 50000 );
		} catch (BankException e) {
			System.out.println(e.getMessage() + "," + e.getErrorCode());
		} 
			
		
		
		mom.inquiry();
	
	}
	

}
