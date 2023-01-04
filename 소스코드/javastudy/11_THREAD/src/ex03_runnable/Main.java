package ex03_runnable;

public class Main {

	public static void main(String[] args) {

		Runnable robot1 = new WashRobot("로봇1");
		Thread thread1 = new Thread(robot1);
		
		
		WashRobot robot2 = new WashRobot("로봇2");
		Thread thread2 = new Thread(robot2);

		// => 부모클래스 타입을 runnable을 쓰거나, washrobot을 사용하면된다
		
		// 1) runnable 인터페이스 구현은 start()가 바로 보이지않음
		// * runnable은 인터페이스기 때문에, start()가 구현되지 않을 뿐 아니라,
		// 상속받아 부모클래스로서 사용할 수 없다

		// 2) thread 객체를 생성해 robot의 객체값을 넣는다
	
		Thread thread3 = new Thread(new WashRobot("로봇3"));
		
		thread1.start();
		thread2.start();
		

	}

}
