package ex03_number;

public class Ex03 {

	public static void main(String[] args) {
		
		// 3. 대입연산
		int score = 100; // 등호(=)를 대입연산이라 말함, 같다(x)
		System.out.println(score);
		//ex) score <- 100;
		
		
		// 연습1
		// x에 10있고, y에 20있다
		// x와 y에 저장된 값을 서로 교환하시오
		int x = 10;
		int y = 20;
		int z = x; // z 10
		x = y; // x 20
		y = z; // y 10
		System.out.println(x); //20
		System.out.println(y); //10
		System.out.println(z); //10
		
		// 해결책: 임의의 변수 하나를 만들어서 해결할 수 있다
		
		// 4. 복합대입연산자
		// +=. -=, *=, /=, %=, 등
		int wallet = 0;
		wallet = wallet + 5000; //지갑에 5천원 더하기
		wallet += 5000; // 위와 비슷한 개념 , wallet = wallet + 5000;
		wallet -= 3000; // wallet = wallet - 3000;
		System.out.println(wallet);
		
		// 연습2 ****
		// 통장잔액(balance)에서 이자 5%를 돌려받았음을 나타내자
		long balance = 10000; // 프로모션 적용
		balance *= 1.05; //이자까지 포함해 총액을 계산해야 하기 떄문
		// 정수하고 실수 연산은 무조건 실수쪽으로 맞춰진다
		// 그래서 1.05가 1으로 계산되지 않은 것 ******
		System.out.println(balance);
		
		
		// balance = balance * 1.05; 실패
		// 이유: balance * 1.05의 값은 double이기 때문에 long balance에 저장할수x
		// balance = (long)balance * 1.05; 성공
		// 이유: long으로 캐스팅하였기 떄문(만약 소수점값이 있었다면 생략된 채로 도출)
		
		// balance = balance + balance * 0.05; 실패
		// 이유: 값은 double인데 long에 저장불가능
		
		// balance = balance + (long)(balance * 0.05); 성공
		// 이유: 값을 long으로 캐스팅해서 타입을 맞춤
		
		// * 계산에 소수점이 섞일때 주의하기
		
		
		
		
		
		

	}

}
