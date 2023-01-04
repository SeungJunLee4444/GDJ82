package ex01_random;

public class Ex01 { // => 클래스

	public static void main(String[] args) { // => 메소드
		
		 //1. Math.random으로 보는 메소드 활용
		
		// * 난수 : 정해진 범위 안에서 무작위로 추출된 수, 예측 못하는 수
		 
		// 1) 메소드의 형태
			// (1) Math.random에서 Math는 클래스 random은 메소드, (main도 메소드)
			// (2) System.out.println(Math.random()); 
			// => 추천창의 초록 공들도 전부 메소드
		
	
			// 2) Math.random의 기능 
			// => Math.random 0에서 1사이의 실수를 무작위로 출력
		
		
		// 3) Math.random의 용도 
			// (1) 확률처리 : if문을 이용한 확률성 값 도출
				// => 0.0 <= Math.random() < 1.0
				// => 0% <= Math.random() < 100% => 확률처럼 사용할 수 있다
			
				// ex) 10퍼센트의 확률로 "대박", 90%의 확률로 "쪽박"
	
		if(Math.random() <0.1) {
			System.out.println("대박");
		} else {
			System.out.println("쪽박");
		}
		
			// (2) 난수값 생성 : 값의 범위를 정수값으로 조절해 주사위 만들기
				// Math.random()             	0.0 <= n < 1.0
				// => Math.random() *5       	0.0 <= n < 5.0
				// => (int)(Math.random() *5) 	  0 <= n < 5
		
		//----------------------------------------------------------------------------
		
				// 연습 1
		        // 주사위 출력 
		
		System.out.println((int)((Math.random()*6) +1));
		
		
				// * 주사위 두번 돌리기
		for(int n = 0; n < 2; n++) {  // => 두번 돌린다, 0에서 1 
			int dice = (int)(Math.random() * 6) + 1; 
			System.out.println("주사위" + dice);
		}
		
		//----------------------------------------------------------------------------
		
				// 연습 2
				// 6자리 숫자로 된 인증번호 만들기 
				// String code "512345"
		
		String code = "";
		for(int n = 0; n < 6; n++) {
			code += (int)(Math.random() * 10); // => 인증번호는 0부터 9까지 있음
			
		} System.out.println(code);
		
		// * 주의: int로 값을 도출하면 값이 전부 연산되며, 앞자리에 0을 표현할 수 없다
		
		//----------------------------------------------------------------------------
		
				// 연습3 ------------*
				// 65에서 90까지 랜덤으로 유니코드값을 나오게 만들기
				// * 아스키코드 
				// A는 65 a는 a는 97
		
		System.out.println((char)((int)(Math.random() * 26) + 'A')); // 26은 왜 곱하지?-----------8
		
				// => 영문대문자는 총 26개로 코드값은 65~90이다
				// => 0 <= Math < 1 ==> 0 <= Math < 26 ==> 65 <= Math < 91
				// * 시작값이 65야 하기 때문
				// * 26은 65와 90 사이값으로, 이걸 빠르게 알아채는 것이 관건
				// => 여기에 char을 캐스팅하면 문자값으로 출력가능

				int a = 1 + 'A'; // => 컴퓨터 입장에서는 A는 65랑 똑같다
		
		//----------------------------------------------------------------------------
				
				// 연습4 ------------*
				// 6자리 영문(대문자, 소문자) 인증번호 만들기

		code = "";
		for(int n =0; n <6; n++) {
			if(Math.random() < 0.5) {
				code += (char)((int)(Math.random() * 26) +'A');  
			} else {
				code += (char)((int)(Math.random() * 26) +'a'); 
			}
		} System.out.println(code);
		
				// Math.random() * 26 + A / a 는 각 대문자, 소문자의 범위를 뜻한다
		
		
		
	}

	}
