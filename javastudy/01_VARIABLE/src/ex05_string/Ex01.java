package ex05_string;

public class Ex01 {

	public static void main(String[] args) {

		// 4. 문자열 연결 연산자
		// 문자열이 포함된 + 연산은 연결하는 것
		
		String str1 = "구디" + "아카데미";
		String str2 = 100 + "번지";
		String str3 = 1 + 1 + "행사"; // 2행사
		String str4 = 1 + "1" + "행사";
		
		// 연산은 항상 왼쪽에서 오른쪽으로, 하나씩 진행 *
		
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
				
		
		// + 연산을 이용한 문자열 만들기 *
		// 빈 문자열 만들기("") + 해주면 됨 
		// StringvalueOf() 보다 간편한 방법
		
		String str5 = 100 + ""; //String.valueOf(100)과 동일
		System.out.println(str5);
	}

}
