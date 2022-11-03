package ex15_interface;

// # 상속extneds 먼저, 구현 implements 나중

public class Smartphone extends Phone implements Computer {

	
	
	public void call() {
		System.out.println("전화기능");
		
	}
	
	
	public void sms() {
		System.out.println("sms기능");
		
	}
	
	
	public void game() {
		System.out.println("게임기능");
		
	}
	
	
	public void internat() {
		System.out.println("인터넷기능");
		
	}
	
	
}
