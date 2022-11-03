package ex05_synchronized;

public class RoomRobot extends Thread {
	
	private Cleaner cleaner;
	
	public RoomRobot(Cleaner cleaner) {
		super();
		this.cleaner = cleaner;
	}
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
		cleaner.roomCleaning();
		}
	}

}
