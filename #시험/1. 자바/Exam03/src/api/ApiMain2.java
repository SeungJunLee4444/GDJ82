package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ApiMain2 {

	public static void main(String[] args) {
		
		StringBuilder urlBuilder = new StringBuilder();
		
		try {
			
			String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
			urlBuilder.append("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
			urlBuilder.append("?ServiceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&searchYear=2021");
			urlBuilder.append("&siDo=1100");
			urlBuilder.append("&guGun=1125");
			urlBuilder.append("&type=json");
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageNo=1");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
		HttpURLConnection con = null;
		
		try {
			URL url = new URL(urlBuilder.toString());
			con = (HttpURLConnection)url.openConnection(); // api 접속 및 연결
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = null;
		
		try  {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));	
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));	
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		StringBuilder result = new StringBuilder();
		List<Accident> accidents = new ArrayList<Accident>();
		JSONObject obj = new JSONObject(sb.toString());			// string타입이 매개변수
		System.out.println(obj); // header, body 아래
		JSONArray itemList = obj.getJSONObject("items").getJSONArray("item");
		
		
		for(int i = 0; i < itemList.length(); i++) {
			JSONObject item = itemList.getJSONObject(i);
			String occrrncDt = item.getString("occrrnc_dt");		// 20192222
			String[] weekday = {"", "일", "월", "화", "수", "목", "금", "토"};			// 발생코드는 1부터 7까지
			String occrrncDayCd = item.getString("occrrnc_day_cd");	// 발생요일코드	// 2019년에 사건이 발생한 날의 요일코드
			int dthDnvCnt = item.getInt("dth_dnv_cnt");				// 사망자수
			int injpsnCnt = item.getInt("injpsn_cnt");				// 부상자수

			result.append("발생일자 ").append(occrrncDt).append(" ").append(weekday[Integer.parseInt(occrrncDayCd)]).append("요일,  ");
			result.append("사망자수 ").append(dthDnvCnt).append("명,");
			result.append("부상자수 ").append(injpsnCnt).append("명" + "\n");
		
			Accident accident = new Accident();
			accident.setDthDnvCnt(dthDnvCnt);
			accident.setOccrrncDt(occrrncDt);
			accident.setOccrrncDayCd(occrrncDayCd);
			accident.setInjpsnCnt(injpsnCnt);
			accidents.add(accident);
		}
		
		File file = new File("accident.txt");
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			bw.write(result.toString());
			bw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	
	
	
	}
	
	
	
	
	
	
	

}
