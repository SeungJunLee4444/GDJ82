package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main_JSON {
	
	public static void m1() {
		
		// * json과 xml의 차이
		// => xml에서 자식태그를 파악할때, 줄바꿈도 자식태그로 인식해버린다
		// ex) response
		//			header
		// => response의 자식노드는 header 와 줄바꿈이 포함된다
		// => json은 줄바꿈으로 인한 불편함이 없다
		
		// --------------------------------------------------------------------------------
		
		
		// 1. 데이터 요청을 위한 api 주소 생성
		
		// * UnsupportedEncodingException : 인코딩 오류
		
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";

		try {
			String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
			apiURL += "?pageNo=" + URLEncoder.encode("0", "UTF-8");
			apiURL += "&numOfRows=" + URLEncoder.encode("100", "UTF-8");
			apiURL += "&type=" + URLEncoder.encode("json", "UTF-8");
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("인천광역시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("계양구", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}

		// 2. api 주소 접속
		
		// * MalformedURLException : url 오류
		// * IOException	: 
		
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF8");
		} catch (MalformedURLException e) {
			System.out.println("api 주소 오류");
		} catch (IOException e) {
			System.out.println("접속 실패"); 
		}

		// 3. 데이터 읽어내기
		
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		try {

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream())); 
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream())); 
			}
	
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();

		} catch (IOException e) {
			System.out.println("API 응답실패"); 
		}

	String response = sb.toString();

		// 4. 파일에 저장
	
		File file = new File("c:\\charlie1", "ch1.json");

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void m2() {
		
		// * json 문서 분석
		
		// jsonobject 클래스 	: {}, 객체를 의미
		// jsonarray 클래스 	: [], 배열을 의미
		
		File file = new File("c:\\charlie1", "ch1.json");
		
		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject obj = new JSONObject(file);
		// * xml과 달리 json은 파일을 직접 내려받지 못함
		// => string은 받을 수 있기 때문에, string타입으로 파일을 변환해야한다
		
		JSONObject obj2 = obj.getJSONObject("response");	// header 이하 프로퍼티
		JSONObject obj3 = obj2.getJSONObject("header");	// header 이해 프로퍼티
		String resultCode = obj3.getString("resultCode");	// 
		
		String resultMsg = obj3.getString("resultMsg");	// 
		
		System.out.println(resultCode + "" + resultMsg);
		
		// {"프로퍼티" : 값}
		// obj의 프로퍼티 하나, value 하나
		
	}
	
	public static String m3() {
		
		String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
		StringBuilder urlBuilder = new StringBuilder();

		try {

			urlBuilder.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageNo=1");
			urlBuilder.append("&dataType=JSON");
			urlBuilder.append("&base_date=20220821");	// 최근 1일간의 데이터만 가능
			urlBuilder.append("&base_time=1500");
			urlBuilder.append("&nx=55");
			urlBuilder.append("&ny=127");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiUrl = urlBuilder.toString();
		URL url = null;
		HttpURLConnection con = null;

		try {
			url = new URL(apiUrl);
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 3. 입력

		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();

		try {
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

			reader.close();
			con.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}

		String response = sb.toString();

		// 4. 파일에 저장

//		File file = new File("c:\\charlie1", "ch2.json");
//		BufferedWriter bf = null;
//
//		try {
//
//			bf = new BufferedWriter(new FileWriter(file));
//			bf.write(response);
//			bf.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return response;
//		// * string 타입의 반환값을 가진다는 의미
//
//		
	}
	
	public static void m4() {
//		String response = ;		// m3의 string response 반환값이 string response에 저장
		
		JSONObject obj = new JSONObject(m3());	
		System.out.println(obj);
		JSONObject response = obj.getJSONObject("response");		//header, body	
		JSONObject body = response.getJSONObject("body");
		System.out.println(body);
		JSONObject items = body.getJSONObject("items"); //body의 items 오브젝트 호출
		JSONArray item = items.getJSONArray("item");  // item은 복수개있으니 배열for문
		System.out.println(item);
		// * []는 배열
		// => item 배열은 items의 "item"인 제이슨 어레이를 갖는다
		for(int i = 0; i < item.length(); i++) {
			JSONObject element = item.getJSONObject(i);	// item 안의 모든 값을 element라 명칭해 저장
			System.out.println(element.getString("category") + ":" + element.getString("obsrValue"));
		}
	}
	
	
	
	public static String m5 () {
		
		String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
		StringBuilder urlBuilder = new StringBuilder();

		try {

			urlBuilder.append("http://apis.data.go.kr/B553077/api/open/sdsc2/storeZoneOne");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&key=9734");	// * 지역번호
			urlBuilder.append("&numOfRows=29");
			urlBuilder.append("&type=json");
		
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiUrl = urlBuilder.toString();
		URL url = null;
		HttpURLConnection con = null;

		try {
			url = new URL(apiUrl);
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 3. 입력

		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();

		try {
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}

			reader.close();
			con.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}
		String response = sb.toString();

		// 4. 파일에 저장

		File file = new File("c:\\charlie1", "ch3.json");
		BufferedWriter bf = null;

		try {

			bf = new BufferedWriter(new FileWriter(file));
			bf.write(response);
			bf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// * string 타입의 반환값을 가진다는 의미
		
	
		
		return response;
		
	}
		
		public static void m6() {
			
//			String response = m5();
//			System.out.println(response);
			
			JSONObject obj = new JSONObject(m5());
			JSONObject header = obj.getJSONObject("header");		// * {} 면 객체타입
			JSONArray columns = header.getJSONArray("columns");		// * [] 면 배열타입
			for(int i = 0; i < columns.length(); i++) {
//				System.out.println(columns.getString(i));
			}
			
			JSONObject body = obj.getJSONObject("body");
			JSONArray items = body.getJSONArray("items");
			JSONObject item = items.getJSONObject(0);	// items의 첫번째 요소 item
//			System.out.println(item.toString());
			
			// * key값은 string으로, value값은 object(문자열, 숫자 등 모든값이 저장될 수 있도록)
			
			Map<String, Object> map = new HashMap<>();
			map.put("상권번호", item.getInt("trarNo"));
			// => trarno의 value값 가져오기(int타입)
			
			map.put("상권명", item.getString("mainTrarNm"));
			
			String[] p = {"trarNo", "mainTrarNm", "ctprvnCd", "ctprvnNm", "signguCd", "signguNm", "trarArea", "coordNum", "coords", "stdrDt"};
			
			for(int i = 0; i < columns.length(); i++) {
			map.put(columns.getString(i), item.get(p[i]));	// * 순서대로 나오지는 않는다
		
				
			}
			
			System.out.println(map);
			
			
			
			
			
		}
		
		public static String m7() {

			// ex) 기상청 rss 자료 내려받기

			// 1. apiurl 저장

			String apiUrl = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013061000";

			// 2. url 연결

			URL url = null;
			HttpURLConnection con = null;

			try {

				url = new URL(apiUrl);
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.setRequestProperty("Content-Type", "application/json");

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 3. 내려받기

			BufferedReader reader = null;
			StringBuilder sb = new StringBuilder();

			try {

				if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
					reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else {
					reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}

				String Line = null;
				while ((Line = reader.readLine()) != null) {
					sb.append(Line + "\n");
				}

				reader.close();
				con.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}

			String response = sb.toString();

			// 4. 파일에 출력

			File file = new File("c:\\charlie1", "ch5.json");

			BufferedWriter bw = null;

			try {

				bw = new BufferedWriter(new FileWriter(file));
				bw.write(response);

				bw.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return response;
		}
		
		public static void m8() {
		}
		
			
////			String response = m7();
////			System.out.println(response);
//			JSONObject obj = XML.toJSONObject(m7());
//			// * xml.문서를 json문서로 바꾸는 메서드
//			System.out.println(obj.toString());
//			
//			// * 기상청 문서는 처음부터 xml문서만 받을수있었다
//			
//			JSONArray dataList = obj.getJSONObject("rss")
//					.getJSONObject("channel")
//					.getJSONObject("item")
//					.getJSONObject("description")
//					.getJSONObject("body")
//					.getJSONArray("data");
//			// * data 배열들 전체를 의미
//			
//			for(int i = 0; i < dataList.length(); i++) {
//				JSONObject weather = dataList.getJSONObject(i);// * 출력문을 깔끔하게 작성하기 위해
//				System.out.println(weather.getInt("hour") + "시," + weather.getInt("temp") + "도," + weather.getString("wfKor"));
//			}
//			
//			
//		}


		
	
	
	
	public static void main(String[] args) {
		m1();
	//m2();
		//m3();
		//m4();
		//m5();
		//m6();
		//m7();
		//m8();
	}

}
