package ex02_two_dim;

public class Ex02_advanced_for {

	public static void main(String[] args) {
		
		// 2. 2차원 배열의 초기화
		
		// 1) 일반 for문
		
		String[][] timeTable = {
				{"국어", "윤리", "수학", "영어"},
				{"수학", "미술", "과학"},
				{"사회", "체육", "수학", "영어"},
				{"수학", "국어", "한자", "문학", "영어"},
				{"영어", "음악", "국어", "수학"}
		};
		
		System.out.println(timeTable[0][0]);
		System.out.println(timeTable[1][2]);
		
		for(int i = 0; i <timeTable.length; i++) {
			for(int j = 0; j <timeTable[i].length; j++) {
				System.out.print(timeTable[i][j] + "\t");
			} System.out.println();
		} 

		// 2) 향상 for문 * 그렇게 중요하진않음
		for(String[] weekName : timeTable) { 
			for(String course : weekName ) {
				System.out.print(course + "  ");
			} 
			System.out.println();
		}
		
		// (1) weekName에 각 행을 저장 
		// (2) course에 각 열의 요소들을 저장
		// (3)
		
		
		
		
	}

}
