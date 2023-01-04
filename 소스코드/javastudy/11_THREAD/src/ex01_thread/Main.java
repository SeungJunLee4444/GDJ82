package ex01_thread;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("main 시작");

		Process process1 = new Process("연산");
		process1.start();
		// * start메서드를 쓰면, 오버라이딩 된 run 메서드에 담긴 내용이 실행된다
		// (약속)
		
		// * main은 process 클래스만 실행시키고 종료하는 역할만 한다
		// =>  start 이후에는 process만 독립적으로 실행됨
		Process process2 = new Process("제어");
		process2.start();
		// => main은 실행후 바로 종료되며, process 두개가 실행된다
		

		
		System.out.println("main 종료");

		
	}

}
