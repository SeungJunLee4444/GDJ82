package ex05_synchronized;

// cleaner 1개				
// cleaner를 사용할 robot 2개
// => cleaner라는 공유자원을 사용한다, robot는 스레드

// robot이 cleaner를 차지하기 위한 쟁탈전 벌어짐

// synchronized 처리
// => 공유자원의 점유를 방지하기 위해서, 한번에 한 스레드만 접근할 수 있도록 허용 
// => 공유자원의 일관성을 보장
// => 한번에 한 스레드만 접근할 수 있는 영역을 임계영역(critical section)이라한다

// object 클래스 wait() 
// => 스레드가 대기상태가 됨
// => 예외처리필요(interruptedexception)

// object 클래스 notify()
// => 다른 스레드를 깨움
// => notifyAll() 메서드로 모든 스레드를 깨움

// 스레드의 구조
// new(생성) -> ready(실행준비) -> run(대기) -> stop
//				<- sleep, join은 run에서 ready로 자동으로 감
//				<- wait는 멈추기만하고 ready로 가지 못하기 때문에 notify사용

// 스레드의 대기
// => sleep, join, notify + wait

public class Cleaner {
	
	public synchronized void toiletCleaning() {
		System.out.println("화장실 청소");
		try {
			notify(); 	// '나 화장실 청소 끝났다' 고 알림 => 화장실 로봇한테
			wait();		// 잠깐 쉼
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
	}
	
	public synchronized void roomCleaning() {
		System.out.println("방 청소");
		try {
		notify();
		wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
