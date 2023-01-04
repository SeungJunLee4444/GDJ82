package prac1;

public class Ex06 {

	public static void main(String[] args) {
		
		// 도개걸윷모 -> 배열에 저장, 이동횟수를 인덱스와 일치시켜서 사용
		String[] yut = {
				"",  // 더미 요소(사용 안 함)
				"도",
				"개",
				"걸",
				"윷",
				"모"
		};
		
		int total = 0;
		
		while(true) {
			
			// 이동횟수 == 인덱스 -> 난수 처리
			int move = (int)(Math.random() * 5) + 1;

			total += move;
			
			if(move <= 3) {
				System.out.println(yut[move] + ", " + total + "칸 움직인다.");
				break;
			} else {
				System.out.print(yut[move] + ", ");
			}
			
		}
	
	}

}
