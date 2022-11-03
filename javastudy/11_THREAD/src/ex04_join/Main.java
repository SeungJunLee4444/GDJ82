package ex04_join;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		
		// ex) 계산을 빨리하고싶어서, 계산기를 2개 동시사용
		// 작업을 반으로 나눠서 진행
		
		// calculator가 동시에 연산을 수행하려면 calculator를 스레드로 처리해야함
		
		Calculator calc1 = new Calculator(1, 50000);
		Thread thread1 = new Thread(calc1);
		thread1.start();							// 1번째 계산기 동작
		
		Calculator calc2 = new Calculator(50001, 1000000);
		Thread thread2 = new Thread(calc2);
		thread2.start();							// 2번째 계산기 동작
		
		// 모든 계산기 동작이 끝날때까지 기다린다
		thread1.join();
		thread2.join();
		// => join 메서드 : 스레드가 종료될때까지 기다림
		
		System.out.println(thread1.isAlive());	// true면 아직 계산이 안끝난것, false면 계산이 끝난것
		System.out.println(thread2.isAlive());
		System.out.println(calc1.getTotal() + calc2.getTotal());
		
		// 0이 나오는 이유 : calculator이 작동하기 전에 main 동작이 끝났기 때문
		// => 계산이 미처 끝나지 않았는데 thread가 죽어버릴 수 있다
		
		// thread 관련 메서드
		// sleep, join, isalive, notify, wait
		
		
	
	}

}
