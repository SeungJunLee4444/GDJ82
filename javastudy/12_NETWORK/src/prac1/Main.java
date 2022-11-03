package prac1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
	
	public static void main(String[] args) {
		
		// & 정리
		// * 서버 => client (input, reader) client => 파일 (output, 출력)
		
		// p1: url을 이용해 데이터를 input하기 
		
		try {
		// 1) 접속
		String apiURL = "https://www.kma.go.kr/XML/weather/sfc_web_map.xml";
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		
		// 2) 접속상태 확인
		if(con.getResponseCode() != HttpURLConnection.HTTP_OK) {
			System.out.println("api 접속 실패");
			return;
		}
		
		// 3) 바이트 입력 스트립 -> 문자 스트림 -> 버퍼추가

		InputStreamReader reader = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		// *1 getinputstream은 입력을 받기위한 빨때꼳기 메서드, httpurlconnection 소속
		// *2 inputstreamreader을 통해 char타입 문자스트림으로 변환해 저장되었다 -> writer
		
		// * 현재 데이터는 모두 br에 저장되있다
		
		// 4) 출력스트림
		File file = new File("c:\\storage", "sfc_web_map.xml");
		FileWriter fw = null;
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		// * bufferedreader 은 readline(한줄씩읽기) 지원
		
		// 4) readline() 으로 입출력
		String line = null;	// * string 타입은 더이상 읽을 데이터가 없을 때 null 반환
		while((line = br.readLine()) != null) {
			bw.write(line + "\n");
		
		bw.close();
		con.disconnect();
		}
			
	} catch (MalformedURLException e) {
		System.out.println("api 주소 오류");
	} catch (IOException e) {
		System.out.println("api 서버오류");
	}
	
}
	}
