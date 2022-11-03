package ex15_interface;

public abstract class Phone {		// # abstract class 지우고 interface
									// => 인터페이스화 된다
	// [상속 - 다중상속]
	// 
	
	public abstract void call(); 
	public abstract void sms();
	
	// # 추상화 작성법
	// 클래스, 메서드에 abstract 추가하고 ();
	
	
	// # 다중 인터페이스
	// => 가능하다
	// 클래스란에 implement a, b 
}		
