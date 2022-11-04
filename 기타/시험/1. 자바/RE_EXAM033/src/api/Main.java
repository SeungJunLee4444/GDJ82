package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

public class Main {

	public static void main(String[] args) {
		
		// * json은 바로 위 부모의 객체가 있어야 자식 태그를 제대로 호출할 수 있다(여러칸을 띄어서 호출은 불가능)
		
		// * postman에는 servicekey값을 인코딩값으로 넣어야한다
		// => 컴파일 도중에는 컴퓨터가 인식해야하니 디코딩 상태로 이용
		// * rss 주소는 별도의 인코딩이 필요하지않다
		
		// * 인코딩
		// 사람의 언어 -> 컴퓨터의 언어 0,1
		// => 컴퓨터가 인식하는 과정에서 한글이나 문자 등 꺠질수 있는 문자가 있을 수 있기 떄문에
		// 프로그램 상에서 인코딩(컴퓨터식)해야한다?
		
		// #1 xml과 달리 json은 중간에 파일생성과정이 필요하지 않다
		// xml 파싱
		// api응답(xml) -> xml 파일로 만듬 -> document로 읽기
		
		// json 파싱
		// api응답(json) -> 곧바로 jsonobject에 저장
		
//		String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
		String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000";
		// * 인코딩할 필요없음
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			// * 주소창에 파라미터를 붙이는 방식이 'GET'
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			// * 위 두가지는 안적어도 지장없음
			
			
			BufferedReader reader = null;
			StringBuilder sb = new StringBuilder();
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
//			System.out.println(sb.toString());
			// * 단계별로 하위 태그로 갈때는 롬복의 빌터 패턴을 이용할 것
			
			// # 파싱결과를 전달할 파일
			File file = new File("test.txt");	
			// * 경로없이 파일을 만들면 프로젝트에 생성된다
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			// # stringbuilder에 저장된 xml 데이터를 json으로 변경
			JSONObject obj = XML.toJSONObject(sb.toString());
			JSONObject rss = obj.getJSONObject("rss");
			System.out.println(rss);
			JSONObject channel = rss.getJSONObject("channel");
			String link = channel.getString("link");	// item이 아닌 상위 태그여도 가능하다
			String description = channel.getString("description");
			String generator = channel.getString("generator");
			String language = channel.getString("language");
			String title = channel.getString("title");
			String pubDate = channel.getString("pubDate");
			bw.write(pubDate + "\n"); // pubdate의 값
			JSONObject item = channel.getJSONObject("item");
			String author = item.getString("author");
			String link2 = item.getString("link");
			String category = item.getString("category");
			bw.write(category+ "\n"); // category의 값
			String title2 = item.getString("title");
			JSONObject description2 = item.getJSONObject("description");
			JSONObject body = description2.getJSONObject("body");
			JSONArray dataList = body.getJSONArray("data");					// json 부모의 바로 자식 태그만 호출 가능하다
			for(int i = 0; i < dataList.length(); i++) {
				JSONObject data = dataList.getJSONObject(i);
				String wfKor = data.getString("wfKor");
				int sky = data.getInt("sky");
				int temp = data.getInt("temp");
				int wd = data.getInt("wd");
				int hour = data.getInt("hour");	// 같은 배열의 형태와 같은 키값?
				bw.write(hour + "시," + temp + "도," + wfKor + "\n");
			}
			
			bw.close();
			reader.close();
			con.disconnect();
			
			// item에서 category와 pubdate를 찾고,
			// 

		
			
			// * 자식태그가 있으면 객체, 반복되는 객체를 지니면 배열, 마지막 태그면 string, int
			System.out.println(obj);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
