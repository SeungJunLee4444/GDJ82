package ex04_method_o;

public class VendingMachineMain {

	public static void main(String[] args) {
		
		// # 클래스 선언
		VendingMachine machine = new VendingMachine();	// => 객체 생성
		
		// # 
		String coffee1 = machine.getCoffee(1000, 1);	
		String coffee2 = machine.getCoffee(2000, 2);	
		// # 위와 같은 코드로, 아메리카노 한잔, 까페라떼 두잔이 출력되게 만들기
		
		System.out.println(coffee1);
		System.out.println(coffee2);
		
	}

}
