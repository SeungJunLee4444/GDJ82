package ex02_thread;

public class Main {

	public static void main(String[] args) {
		
		// & 스레드는 실행되는 각 우선순위가 있으며, 임의로 설정할 수 있으나
		// 절대적이지는 않다
		
		// * 스레드의 우선순위
		System.out.println("가장 높은 우선순위: " + Thread.MAX_PRIORITY);	//10
		System.out.println("가장 낮은 우선순위:" + Thread.MIN_PRIORITY);	//1
		System.out.println("보통 우선순위:" + Thread.NORM_PRIORITY);		//5
		
		// # 스레드 2개(s1,s2)
		Soldier s1 = new Soldier("김상사", new Gun(6));
		Soldier s2 = new Soldier("장병장", new Gun(10));
		System.out.println("s1의 우선순위:" + s1.getPriority());	//5
		System.out.println("s2의 우선순위:" + s2.getPriority());	//5
		
		// # 우선순위가 높은 스레드를 '최대한' 먼저 실행한다(무조건은 아님)
		// => 우선순위는 자바에서 알아서 결정
		
		// => 우선순위 조정
		s1.setPriority(Thread.MIN_PRIORITY);	//s1을 낮은순위
		s2.setPriority(Thread.MAX_PRIORITY);	//s2는 우선순위
		
		// # 스레드 실행
		s1.start();
		s2.start();
		// * 무조건 s1이 먼저 작동하지는 않음
		

	}

}
