package ex02_loop;

public class Ex04_continue {

	public static void main(String[] args) {
		
		// 4. continue문 (공부 안해도됨)
		// 반복문의 시작지점으로 이동시킨다
		// 사용이유: 실행에서 제외할 코드가 있는 경우에 사용
		
//		while() {
//			a;
//			b;
//			c;
//			continue; // => a,b,c만 실행하고 다시 while문으로 이동
//			d;  // => d 이후는 실행안시킴
//			e;
//			f;
//		}
//		
		// => break는 멈추게 하는 역할, continue는 도중에 시작점으로 돌아가게함
		
		// 연습1
		// 1에서 100 중에서 3의 배수를 제외하고 모두 더하기
		int sum = 0;
		int n = 0;
		
		while(n < 100) { //=> if와 조건의 형태 다름 +
			n++; // 위치에 따라 n의 시작값이 달라짐 0아님 1
			if(n % 3 == 0) {
				continue; // => 아래 합셈에 이행되지 않고, 다시 올라감
			}
			sum += n;
		}
		System.out.println("합계" + sum);
		
		// continue는 필수코드가 아니다
				int total = 0;
				int num = 0;
				while(num < 100) { 
					num++;
					if( num % 3 != 0) {
						total += num;
					} System.out.println(total);
		
		}
		
	  
			
			
			
			
		}
	

	}


