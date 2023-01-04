package ex02_loop;

public class Ex02_while {

	public static void main(String[] args) {
		
		// 2. while 
	    /* 특정 실행문을 반복할 때, 정보를 확인할 떄 사용
	     * 특정 반복횟수가 정해지지 않은 경우(for은 횟수가 정해져있음) *
	     * 형태:while(조건문) {
	     * 실행문
	     * } => if문과 똑같이 생김 / if와 달리 조건이 충족되면 다시 반복
		*/
		
		int balance = 79350; // 통장 잔액
		int money = 450;  // 450원 단위로 출금됨
		while(balance >= money) { // => 450원보다 작으면 출금되지 않는점을 고려
			
			System.out.println("잔액" + balance + "인출액" + money);
			balance -= money;
		} System.out.println("최종잔액" + balance); 
		
	
		
		/* 
		 * 
		 */
		
		
		
		
	}

}
