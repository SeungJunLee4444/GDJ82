package ex01_network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Main_o {
	
	public static void m1() {
		
		// & 정리
		// => url주소의 데이터를 입력받는 stringbuilder로 받는 코드
		try {
		String apiURL = "https://www.naver.com";
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("api 접속 실패");
			return;
		} 
		InputStreamReader reader = new InputStreamReader(con.getInputStream());
		// => 받아온 데이터타입은 byte기 때문에, 이를 stringbuilder에 저장
		StringBuilder sb = new StringBuilder();
		int readCnt = 0; 	// 유니코드값 저장
		char[] cbuf = new char[100];
		
		while((readCnt = reader.read(cbuf)) != -1) {
			sb.append(cbuf, 0, readCnt);
		}
		reader.close();
		con.disconnect();
		
		} catch (MalformedURLException e) {
			System.out.println("api 주소 오류");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("api 접속 및 연결 오류");
		} 
		// 최대 100개의 글자 단위로 읽어내기
		
		
		
		// ----------------------------------------------------------------------------------------------------
		
		// 1.
		// => url은 인터넷 주소( ex) https://search.naver.com/search.naver?=날씨)
		// => 프로토콜(http)// 호스트(서버주소): 포트번호/ 서버경로(url mapping)? 파라미터(서버로 전송하는 데이터)
		//* 포트번호는 생략가능하다
		
		try {
		String apiURL = "https://search.naver.com/search.naver?=날씨";
		
		// 1) 선언
		URL url = new URL(apiURL);	// # 예외처리: malformedurlexception => url주소에 오류가 있을때 발생
		
		// 2) 확인
		
		// (1) URL 확인
		System.out.println("프로토콜:" + url.getProtocol());
		// (2) 호스트 확인
		System.out.println("호스트: " + url.getHost());
		// (3) 경로확인
		System.out.println(url.getPath());
		// (4) 파라미터
		System.out.println("파라미터: " + url.getQuery());
		// (5) 웹접속용 메서드
		//url.openConnection();
		// => 웹 접속시 httpurlconnection 클래스를 사용할때 사용하는 메서드
		// * ioexception 예외가 발생
		
		} catch (MalformedURLException e) { 	// * uri와 url 혼동 주의
			System.out.println("API 주소 오류");
		} 
		
		
	}
	
	public static void m2() {
		
		// 2. httpurlconnection 클래스
		// => 웹 접속을 담당하는 클래스 *
		// => url 클래스와 함께 사용
		// => urlconnection 클래스의 자식 클래스
		
		// * url 클래스의 openconnection() 메서드를 호출하면 urlconnection이 되며, 이를 httpurlconnection 클래스 타입에 저장  
		
		// & 정리
		// httpurlconnection 
		// (1) 응답코드		: getresponsecode() -정상(200), 사용자오류(40x), 서버오류(50x)
		// (2) 컨텐트 타입	: getcontenttype() - 영어인지, 한글인지
		// (3) 요청방식		: getresuestedmethod
		// (4) 커넥션 종료  : disconnect();
		// (5) 바이트타입 스트링 : InputStream in = con.getInputStream(); *
		// => 웹 주소의 내용을 바이트 타입 스트링 연결을 할 수 있다
	
		
		
		try {
			String apiURL = "https://www.naver.com/";
			URL url = new URL(apiURL);
			
			// 1) 선언
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection(); // * 타입이 맞지 않아 오류 => 강제 캐스팅
			// url - urlconnection - httpurlconnection
			
			// 2) http 응답 코드 메서드 (httpurlconnection - getresponsecode)
			// (1) 200 : 정상
			// (2) 40x : 요청이 잘못되었음(사용자잘못)
			// (3) 50x : 서버오류
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
			// *1 응답코드가 정상인지 확인하는 코드
			// (이렇게 쓴다)
			
			System.out.println("응답 코드:" + con.getResponseCode());	
			System.out.println("정상: " + HttpURLConnection.HTTP_OK);	// 200						// => 종종쓴다
			System.out.println("not found " + HttpURLConnection.HTTP_NOT_FOUND);	// 404			// 아래는 잘 안쓸듯
			System.out.println("internal error" + HttpURLConnection.HTTP_INTERNAL_ERROR);	// 500
			
			// 3) 컨텐트 타입
			System.out.println("컨텐트 타입: " + con.getContentType());
			// text/html; charset=ISO-8859-1(구글) - 영어
			// text/html; charset=UTF-8(네이버) - 한글
			
			// 4) 요청방식
			System.out.println("요청 방식: " + con.getRequestMethod());
			
			
			// 2) 커넥션끊기 메서드
			con.disconnect(); // close 개념
			
			
		} catch (MalformedURLException e) {		// * URL 클래스 생성시 발생
			System.out.println("API 주소 오류");
		} catch (IOException e) {				// * httpURLconnection 때문, openconnection 메서드도 같은 오류발생(주로 연결관련)
			System.out.println("API 접속 오류");
		}
	}
	
	public static void m3() {
		
		// 3. httpurlconnection과 스트림, 웹 정보를 텍스트로 저장
		// => connection으로 스트림 생성
		try {
			String apiURL = "https://www.naver.com";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			// 1) 바이트 입력 후 reader 문자타입으로 변환
			InputStreamReader reader = new InputStreamReader(con.getInputStream());
			// => 서버가 제공하는 데이터를 client가 (input) 입력받을때
			// => 인터넷에서 stream 받는건 byte타입으로, 한글이 제대로 표기되도록 reader사용
			
			// 모두 읽어서 stringbuilder에 저장
			StringBuilder sb = new StringBuilder();
			char[] cbuf = new char[100];
			int readCnt = 0; // 실제로 읽은 글자수
			
			while((readCnt = reader.read(cbuf)) != -1) {
				sb.append(cbuf, 0, readCnt);
			}
			
			// 4. stringbuilder의 모든 내용을 c:\\storage\\naver.html로 내보내기
			File file = new File("c:\\storage", "naver.html");
			FileWriter fw = new FileWriter(file);
			
			fw.write(sb.toString());
			
			fw.close();
			reader.close();
			con.disconnect();
			
		} catch (MalformedURLException e) {
			System.out.println("api 주소 오류");
		} catch (IOException e) {
			System.out.println("api 접속 및 연결 오류");
		}
	}
	
	public static void m4() {
		
		// 인코딩 : utf-8 방식으로 암호화
		// 디코딩 : utf-8 방식으로 복호화
		// 원본데이터 -> 인코딩 -> 전송 -> 디코딩 -> 원본데이터
		
		// 요청 : 어떤 데이터를 처리해달라고 요청하는것
		// => client가 서버 측으로 보내는 것을 전부 요청이라 한다
		// 이를 위해 주소, 요청 파라미터(서버로 보내줄 데이터)
		// (필수/ 선택)으로 되어있기 때문에 api 설명서를 잘 봐야함)
		
		// 1. api 문서를 보고 아래를 만들 수 있는지?
		// apiURL?파라미터=값&파라미터=값....
		
		// & 
		// url 주소를 통해 서버에 내가원하는 데이터를 요청

		
		
		try {
			
			// 1. 원본데이터
			String str1 = "한글 english 12345 !@*^+)";
			
			// * 인코딩을 해야하는 이유?
			// => +
			
			// 2. 인코딩(데이터를 
			String encord = URLEncoder.encode(str1, "UTF-8");
			System.out.println(encord);

			// => %ED%95%9C%EA%B8%80+english+12345+%21%40*%5E%2B%29
			// * 공백을 인코딩하면 +로 바꿈
			// url 주소를 텍스트에 저장할 때 한글이 이런식으로 변화하는 경우에서 확인가능
			
			// 3. 디코딩(암호화된 데이터를 다시 원본데이터로)
			String decode = URLDecoder.decode(str1, StandardCharsets.UTF_8);
			System.out.println(decode);
			
			// => 한글 english 12345 !@*^ )

			
			
		} catch (UnsupportedEncodingException e) {	// * utf-8 잘못쓰면 발생
			e.printStackTrace();
			// * encording은 있는데 decording은 오류가 없음
		} 
		
		
		
		
	}
	
	
	public static void main(String[] args) {
		
		//m1();
		//m2();
		//m3();
		m4();
		
	}

}
