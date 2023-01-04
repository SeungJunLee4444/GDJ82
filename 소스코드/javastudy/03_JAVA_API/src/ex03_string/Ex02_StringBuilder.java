package ex03_string;

public class Ex02_StringBuilder {

	public static void main(String[] args) {
		
		// 7. StringBuilder 클래스
		// => 문자열 +를 처리하는 역할
		// (메소드도 딱 두가지)
		// => 마지막에 문자열로 꼭 변환시켜줘야함
		// * StringBuilder은 String보다 연산시 속도가 빠르다 *
		// (사용하는 이유)
		
		
		// 1) java.lang.StringBuilder.append()
		// => lang소속이라 import가 없어도 됨 *
		
		StringBuilder sb = new StringBuilder();
		sb.append(1);
		sb.append(true);
		sb.append('T');
		sb.append(3.14);
		sb.append("hello");
		
		System.out.println(sb);
		//=> StringBuilder.append()는 문자열을 합쳐서 나열하는 역할만 한다 *		
		

		// hello가 포함되있는가?
		
		// 동등비교
		System.out.println(sb.equals("1trueT3.14hello")); //false
		
		// => sb는 StringBuilder, String이 아니기 때문에 false

		// * StringBuilder로 만든 문자열은 반드시 마지막에 String으로 변환해줘야함
		
		// String타입으로 변환하기
		String result = sb.toString();
		System.out.println(result);
		
		System.out.println(result.equals("1trueT3.14hello")); // true
		// => String으로 변환하니 제대로 작동함
		
		
		
		// 예제1-1
		// abcdefghijklmnopqrstuvwxyz a부터 z까지 문자열로 합쳐 출력하기
		// 연산이 진행된 시간을 ns단위로 계산하기
		
		long begin1 = System.nanoTime();
		String alphabet1 = "";
		for(char ch = 'a'; ch <= 'z'; ch++) {
			alphabet1 += ch;
		} 
		long end1 = System.nanoTime();
		
		System.out.println((end1 - begin1) + alphabet1);
		

		
		// 예제1-2
		// StringBuilder.append로 연습1번 해결하여 시간비교하기
		
		StringBuilder sb2 = new StringBuilder();
		long begin2 = System.nanoTime();
		for(char ch = 'a'; ch <= 'z'; ch++) {
		sb2.append(ch);
		} 
		long end2 = System.nanoTime();
		String alphabet2 = sb2.toString();
		System.out.println((end2 - begin2) + alphabet2);
		
		// => StringBuilder가 더 빠르다
		
		
		//----------------------------------------------------------------------
		
		// [StringBuilder]
		// 연습1 대문자 6자리로 구성된 인증코드 만들기
		StringBuilder sbcode = new StringBuilder();
		String code = sbcode.toString();
		System.out.println("인증코드:" + code);
		
		
		
		// 연습2 1 2 3 4 5 6 7 8 9 10 만들기
		StringBuilder sbPaging = new StringBuilder();
		
		String paging = sbPaging.toString();
		
		
		//----------------------------------------------------------------------

		
	}

}
