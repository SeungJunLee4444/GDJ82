package ex03_number;

public class Ex01 {

	public static void main(String[] args) {
		
		int a = 7;
		int b = 2;
		
		int result1 = a + b;
		int result2 = a - b;
		int result3 = a * b;
		int result4 = a / b;
		int result5 = a % b; // 나머지
		
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
		System.out.println(result5);
		
		// 연습1   ***
		// 25를 사칙연산을 이용해, 2와 5라는 값으로 도출하기 
		
		int n =25;
		int ten = 2;
		int res1 = n % ten + 1; //2
		System.out.println(res1);
		
		int one = 5; //5
		int res2 = n / one;
		System.out.println(res2);
		
		// 답: 25에 10을 나누면 된다
		int n1 =25;
		int ten1 = 25 / 10; // 2
		int one1 = 25 % 10; // 5
		
		// 연습2  ***
		// 90초를 1분 30초로 나눠보기
		int second = 90;
		int m = second / 60; // 1
		int s = second % 60; //30
		System.out.println(m + "분");
		System.out.println(s + "초");
		
		// 답: 60으로 나누기
		
		// 연습 3 (캐스팅)     ?????????????????????????????????????????
		// a = 7이고, b는 2이므로,
		// a 나누기 b는 3.5라는 값을 도출
		a = 7;
		b = 2;
		double result = (double)a / (double)b; //3.5
		System.out.println(result);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
