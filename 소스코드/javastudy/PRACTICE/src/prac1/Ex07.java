package prac1;

public class Ex07 {

	public static void main(String[] args) {
		
		Player p1 = new Player("철수");
		Player p2 = new Player("영희");
		
		double res1 = p1.turn();
		double res2 = p2.turn();
		
		if(Math.abs(res1 - 10) < Math.abs(res2 - 10))
			System.out.println(p1.getName() + "님 승리. " + res1 + "초 소요.");
		else
			System.out.println(p2.getName() + "님 승리. " + res2 + "초 소요.");
		
	}
	
}
