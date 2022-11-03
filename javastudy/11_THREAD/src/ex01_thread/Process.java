package ex01_thread;

// 1. 스레드
// => 세부적인 실행 단위
// => 자바 실행의 기본단위(main 스레드)
// => main 스레드 이외의 스레드 추가 가능

// 1) 스레드 생성
// * thread 클래스 상속받기 || runnable 인터페이스를 구현하기(상속받기)
// => 둘중 하나를 통해서 스레드를 사용할 수 있음
// => 방법이 두개인 이유: 자바는 다중상속이 불가능하기 때문
// * 다중상속이 되면 상속받은 메서드가 어떤 부모클래스의 것인지 알수가없음
// => 다른 클래스를 상속받고, 인터페이스로 runnalbe을 받는 식으로 부모클래스상속과 thread를 
// 동시에 사용가능해진다

// (1) thread 클래스 상속
// => extends thread
// => thread 클래스의 public void run() 메서드를 오버라이딩해서 수행할 작업 작성

// 2) 스레드 실행
// => start() 메서드를 호출
// run() 메서드에 오버라이드한 내용이 실행

// * thread는 run 메서드를 만들어서 start로 호출하는것이 규칙이다


// (2) runnable 인터페이스로 구현하기 => ex03

public class Process extends Thread {
	
	private String name;

	public Process(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public void run() {		// => run 메서드에서 오버라이딩
		try {
		// 1) 스레드
		Thread.sleep(3000);
		// * sleep : 천분의 1초 동안 잠깐 멈춤, sleep(1000)이면 1초
		
		System.out.println(name + "작업 실행");
	} catch (InterruptedException e) {
		e.printStackTrace();
		
	}
	}
}
