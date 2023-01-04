package ex03_runnable;

// (2) runnable 인터페이스로 thread 생성

	public class WashRobot extends Robot implements Runnable {
		
		private String name;
		
		public WashRobot(String name) {
			super();
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println(name + "빨래중");
		}
		
	
	
	
	
	
}
