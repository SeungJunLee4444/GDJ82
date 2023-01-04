package ex05_synchronized;

public class ToiletRobot extends Thread {
	
	private Cleaner cleaner;
	
	public ToiletRobot(Cleaner cleaner) {
		super();
		this.cleaner = cleaner;
	}

	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
		cleaner.toiletCleaning();
		}
	}
	
}
