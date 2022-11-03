package ex05_synchronized;

public class Main {

	public static void main(String[] args) {
		
		// 클리너 1개
		Cleaner cleaner = new Cleaner();

		// 로봇 2개
		ToiletRobot robot1 = new ToiletRobot(cleaner);
		RoomRobot robot2 = new RoomRobot(cleaner);
		
		// 청소 시작
		robot1.start();
		robot2.start();
		
		// &1 동일한 cleanr을 사용하면 쟁탈전이 벌여진다
		// &2  

	}

}
