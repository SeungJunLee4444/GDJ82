package prac1;

public class Ex08 {

	public static void main(String[] args) {
		
		Watch w = new Watch(16, 15, 30);

		w.addHour(25);      // 1시간 후
		w.addMinute(61);    // 1시간 1분 후
		w.addSecond(3615);  // 1시간 0분 15초 후
		
		w.see();
		
	}

}
