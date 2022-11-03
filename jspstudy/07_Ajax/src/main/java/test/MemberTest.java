package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.junit.Test;

public class MemberTest {

	@Test
	public void 회원목록테스트() {
		// 회원목록을 반환하는 MemberListService() 동작 전반을 살펴보는 테스트
		
		URL url = null;
		HttpURLConnection con = null;
		try {
			
			String apiURI = "http://localhost:9090/07_Ajax/member/list.do";
			url = new URL(apiURI);
			con = (HttpURLConnection)url.openConnection();	// 해당 api에 접속
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			JSONObject obj = new JSONObject(sb.toString());
			assertEquals(2, obj.getInt("count"));
			// 통과 또는 실패
			
			reader.close();
			con.disconnect();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
