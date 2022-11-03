package ex15_interface;

public class Main {
	
	public static void main(String[] args) {
		
		// # 메서드 호출연습
		
		Phone p1 = new Smartphone();
		
		p1.call();
		p1.sms();
		
		
		if(p1 instanceof Smartphone) {
			((Smartphone) p1).game();
			((Smartphone) p1).internat();
			// # 다운캐스팅
		}
		
		((Computer)p1).game();
		((Computer)p1).internat();
		// # 그냥 강제 캐스팅을 한 것(변수떄와 같음) 
		// => computer, phone은 각자 부모, smartphone이 이를 상속
		// 
		
		Computer p2 = new Smartphone();
		
		p2.game();
		p2.internat();
		
		if(p2 instanceof Smartphone) {
			((Smartphone) p2).call();
			((Smartphone) p2).sms();
		}
		((Phone)p2).call();
		((Phone)p2).sms();
		
		Smartphone p3 = new Smartphone();
		
		p3.call();
		p3.sms();
		p3.game();
		p3.internat();
		
		
	}

}
