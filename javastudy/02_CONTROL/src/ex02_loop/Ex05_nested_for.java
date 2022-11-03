package ex02_loop;

public class Ex05_nested_for {

	public static void main(String[] args) {
		
		// 5. 중첩 for
		// ~월~일, ~일차~교시, 
		
		// => 이런 여러 반복되는 값들을 한꺼번에 출력하기 위해 중첩을 사용
		
		for(int day = 1; day <= 3; day++) {
			for(int hour = 1; hour <=8; hour++) {
				System.out.println(day + "일차\t" + hour + "교시");
			}
		}
		
		//-----------------------------------------------------------------------------------
		
		// 연습1
		// 구구단 만들기
		// 구구단을 만들려면 변수가 3개 ? 2개여도된다
		int a;
		int b;
		for(a = 1; a <= 9; a++) {
			for(b = 1; b <=9; b++) {
				System.out.println(a + "x" + b + "=" + a * b );	
			}
		}
		
		// 연습2 2단부터 5x5까지 출력하기
		
		int c;
		int d;
		label : for(c = 2; c <=9; c++) { // => c가 6이되도 조건식이 5보다 작거나 같음이면 멈춤
			for(d = 1; d <=9; d++) {
				System.out.println(c + "x" + d + "=" + (c*d));
				if(c == 5 && d == 5) { // => 종료조건
					break label; // => 안쪽 for문이 끝남
					
				} 
				// 그 외 해결책 2가지
				// (1) if(c == 5 && d == 5) => 바깥에 한번더 붙이기
				// (2) c의 범위를 5로 바꿔도 된다
				
				// * label을 이용한 풀이
				// break와 바깥 for문에 label(임의의 명칭)을 부여하여 동작 자체를 멈추게 할 수있다 *
				
			}
		}
		
		
		// 연습3 
		// 옆으로 출력하는 구구단
		
		int e;
		int f;
		for(e = 1; e <= 9; e++) {
			for(f = 2; f <=9; f++) {
				System.out.print(f + "x" + e + "=" + (e * f) + "\t");	
			} System.out.println();
		}
		
		// => 위나 아래나 똑같은 값이 나온다
		
		
		int dan;
		int da;
		for (da = 1; da <= 9; da++) { // => 바깥함수가 고정되는 형태 
			for(dan = 2; dan <=9; dan++) {
				
				System.out.print(dan + "x" + da + "=" + (dan * da) + "\t" );
				if(dan == 9) {  
					System.out.println(); // => 앞에  단이 9가 되면 줄바꿈
				} 
				
				
				// => 출력문 마다 \t으로 거리를 벌려지는건 '반복문'이기 때문이다 *
				// => 변수명을 이해하기 쉽게 지으면 뒤바뀐 상황에서도 빨리 캐치 *
			
			}
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
