package com.gdu.app05.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MovieServiceImpl implements MovieService {

	@Override
	public String getBoxOffice(String targetDt) {
		
		// # api 입력 : java io 참고
		
		// # uri 생성
		String key = "2498e2d354a71469a81bae46a105d7c9";
		String apiURL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=" + key + "&targetDt=" + targetDt;

		URL url = null;
		HttpURLConnection con = null;
		
		String response = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");																				// * GET을 대문자로 지정해줘야한다
	
		
		} catch(MalformedURLException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		
		
		// # API 응답																									// * try-catch resoucre : 리소스 자동반납, close를 안해도된다
		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			response = sb.toString();
			
			// reader.close(); 			= >  생략가능
		} catch (Exception e) {
			e.printStackTrace();
		}	
					
		con.disconnect();

		return response;
	}

}
