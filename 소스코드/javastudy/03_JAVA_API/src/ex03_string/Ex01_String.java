package ex03_string;

public class Ex01_String {

	public static void main(String[] args) {
		
		// 6. String 클래스
		
		// 1) 문자열 동등비교
		
		String str1 = "hello";
		String str2 = new String("hello");
		
		// * 클래스
		// 문자값에 따라 주소값이 배정
		// 문자값이 다르면 불러오는 주소값도 다르기 때문에 false가 나옴
		
		
		// str1
		// string의 str은 은 hello가 저장된 주소값을 값으로 저장함
		// * string 주소값은 16진수 
		
		/* 위의 값 해석
		 str1 0x123
		 str2 ox123
				
	 			hello 0x123
		
		*/ System.out.println("1:" + (str1 == str2)); //true
		System.out.println("2:" + str1.equals(str2));
		
		String str3 = new String("hi");
		String str4 = new String("i");
	
		
		// * 각자 새롭게 객체를 선언했기 때문에, 같은 문자열을 지녀도 같은 객체를 참조하는게 아니다
		/*
		str3	0x123
		str4	0x456
		
				hi	ox123
				hi	0x456
		*/	//=> 같은 내용값을 지닌 다른 객체
		System.out.println("3:" + (str3 == str4)); //false
		
		// 위의 내용 정리
		// * 여기서 == 는 객체의 저장값이 같은지를 확인하는 것
		// * 문자열을 비교할때는 다른 방법을 사용해야 한다
		
		// => 해결법
		
		boolean result1 = str1.equals(str2);
		boolean result2 = str3.equals(str4);
		
		// * string타입으로 보는 클래스와 객체, 메소드의 활용법
		// * string 클래스로 선언된 객체 str들
		// => 클래스 타입 str 객체를 이용해 .equal 메소드를 호출한다
		
		
		System.out.println("4:" + result1); //true
		System.out.println("5:" + result2); //true
		
		if(str1.equals(str2)) {
			System.out.println("6:" + "같아요");
		} else {
			System.out.println("7:" + "달라요");
		}
		
		if(str3.equals(str4)) {
			System.out.println("8:" + "str3, str4는 달라요");
		} else {
			System.out.println("9:" + "str3, str4는 같아요");
		}
		
		//=> 조건에 !를 붙여서 문자열이 같지 않다를 표현할 수 있다
		//* 개발자 스텐다드에 쓰지말라 명시하지만 간혹 쓰기도 한다 1) 가독성x 2) 코드해석 어색함
		
		// 2) 대소문자를 무시한 문자열 동등비교
		String str5 = "Hello World";
		String str6 = "hELLO wORLD";
		
		System.out.println("1:" + str5.equals(str6));//false
		System.out.println("2:" + (str5 == str6)); //false
		// => 자바입장에서는 다른 문자열이다
		
		boolean result3 = str5.equalsIgnoreCase(str6);
		//upper case, lower case의 케이스
		System.out.println("3:" + result3);
		
		// 3) 문자열의 길이 반환
		String name = "우영우  ";
		int length = name.length();
		System.out.println("4:" + "글자수 : " + length);
		// 공백도 문자열 취급을 한다 =? 스페이스바를 누르면 거리가 벌려진다 *
		
		// 4) 특정위치 문자만 반환
		// => 인덱스 : 글자마다 부여된 정수값(0부터 시작)
		System.out.println("5:" + name.charAt(0)); 
		System.out.println("6:" + name.charAt(1));
		System.out.println("7:" + name.charAt(2));
		// => charAT 메소드를 활용 *
		
		// 5) 문자열의 일부 문자열을 반환
		// (1) substring(begin) : 인덱스 begin부터 끝까지 반환(인덱스값 포함)
		
		
		// (2) substring(begin, end) : 인덱스 begin부터 인덱스 end까지 반환(begin포함, end불포함)
		
		// ex) substring(0,6); 
		// => o에서 5까지의 문자열 출력
		
		System.out.println("8:" + name.substring(0,1));
		// => 성만출력
		
		System.out.println("9:" + name.substring(0));
		// => 이름만 출력
		
		// 6) 특정 문자열 찾아서 해당 인덱스(int)를 반환
		
		// (1) indexOf 메소드
		// => 발견된 문자열이 시작되는 인덱스를 반환
		// => 발견되지 않았을 때 -1을 반환
		int idx1 = name.indexOf("우"); //0
		int idx2 = name.indexOf("영우"); //1
		
		System.out.println("1:" + idx1);
		System.out.println("2:" + idx2);
	
		
		// (2) lastIndexOf
		
		// => 발견된 마지막 문자열의 인덱스를 반환
		// => 발견되지 않았을 때 -1을 반환
		int idx3 = name.lastIndexOf("우"); //2
		int idx4 = name.lastIndexOf("승준"); //-1
		
		System.out.println("3:" + idx3);
		System.out.println("4:" + idx4);
		
		
		// 7) 문자열이 특정 '패턴'으로 시작하는가? boolean(true, faluse)
		// startWith(문자열)
		
		if(name.startsWith("이")) { 
			System.out.println("이씨입니다");
		} else {
			System.out.println("민씨가 아닙니다");
		}
		
		// 8) 문자열이 특정 '패턴'으로 끝나는지 여부를 boolean(true,false) 반환
		// endWith(문자열)
		String filename = "image.jpg"; //jpg, png로 끝나면 이미지로 가정
		if(filename.endsWith("jpg") || filename.endsWith("png")) {
			System.out.println("이미지입니다");
		} else {
			System.out.println("이미지가 아닙니다");
		}
		
		// 9) 문자열이 특정 패턴을 포함하는지 여부를 boolean(true,false) 반환
		// * 실무에서는 쓰지 못함
		String email = "gt_lee@naver.com"; // @, .		
		if(email.contains("@") && email.contains(".")) {
			System.out.println("이메일입니다");
		} else {
			System.out.println("이메일이 아닙니다");
		}
		
		
		// 10) 불필요한 공백 제거(좌우공백)
		// {1}
		String message = "   안녕   하세요   "; 
		// => 문자 좌측 우측 끝이 불필요한 공간 (중간은 아님)
		// {2}
		System.out.println("1:" + message.trim());
		// => 좌우공백만 제거된다
		System.out.println("2:" + message.trim().length()); 
		// => 공백이 제거된 후, 문자열의 길이
		
		// 11) 대소문자 변환하기 
		// {1}
		String source = "best of best";
		System.out.println(source.toUpperCase());
		// => 대문자로 변환하기
		System.out.println(source.toLowerCase());
		// => 소문자로 변환하기
		// * 변환하면 다른 값이 나온다는 것을 보여주는 것일 뿐, 소스값 자체가 바뀌는 것은 아님
		//(실행문은 단순히 보여주기 위한 용도)
		
		// {2}
		
		// 12) 찾아바꾸기
		// {1}
		// replace(old, new) : old를 찾아서 new로 바꿈
		String replaced = source.replace("best" , "worst");
		System.out.println(replaced);
		// => replace 메소드로 값을 바꿔 새로운 객체에 저장
		System.out.println(source);
		// => source의 소스값이 바뀐건 아니다 *
		
		// * 주의사항 : replaceAll() 메소드는 특정문자열을 찾아 변환하는 것이 아님
		String ip = "192.168.101.91";
		String replacedIP = ip.replaceAll(".", "_"); // 'replaceAll'에서 괄호 안 ""는 문자열이 아닌 정규식 *
		System.out.println("3:" + replacedIP); // 192_198_101_91 (안나옴
		// => ------------------ 
		// => replace와 달리 replaceAll은 찾아서 바꾸는 것이 아니라 모든 문자를 특정문자로 바꾸기라는 뜻이다
		
		// 13) 빈 문자열인지 여부를 검사한 뒤 boolean(true, faluse) 반환
		// {1} 문자열이 있는지 없는지
		String id = " ";
		if(id.isEmpty()) {
			System.out.println("빈 문자열");
		} else {
			System.out.println("빈 문자열 아님");
		}
		
		// => 공백은 비어있는것으로 취급 안한다
		// => trim(공백제거)를 같이 쓰면 공백을 지울 수 있다
		// * 빈문자열을 검사할 때 가장 정확한 방법
		
		
//		if(id.isBlank()) {
//			System.out.println("빈 문자열");
//		} else {
//			System.out.println("빈 문자열 아님");
//		}
//		// => "빈 문자열" 이라 뜬다
//		// => blank는 스페이스바를 문자취급x
//		
		//----------------------------------------------------------------------
		
		// [String]
		
		// 연습1
		// 파일이름을 파일명과 확장자로 분리
		// 단, jpg, git, png 이미지인 경우에만 작업을 진행한다
		String fullName = "apple.jpg";
		String fileName = ""; // apple
		String extName = ""; //jpg
		
		// 연습2
		// 문자열 "abc12345def67890ghijk"에서, 
		// 아라비아 숫자 12345. 67890을 제외하고 한글자씩 화면에 출력하기
		String str = "abc12345def67890ghijk";
		
		//----------------------------------------------------------------------
		
		
		
		
	}

}
