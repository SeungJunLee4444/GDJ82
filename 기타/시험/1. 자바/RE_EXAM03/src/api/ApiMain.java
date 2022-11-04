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

public class ApiMain {

	public static void main(String[] args) {

		String apiURL = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1154551000";
		
		HttpURLConnection con = null;
		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();
		
		try {
			
			URL url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF-8");
			
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
	
			File file = new File("weather.txt");
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			JSONObject obj = XML.toJSONObject(sb.toString());
//			JSONObject rss = obj.getJSONObject("rss");
//			JSONObject channel = obj.getJSONObject("channel");
			
			
			JSONObject channel = obj.getJSONObject("rss").getJSONObject("channel"); //* 왜 안되지?
			
			String pubDate = channel.getString("pubDate");
			bw.write(pubDate + "\n");
			JSONObject item = channel.getJSONObject("item");
			String category = item.getString("category");
			bw.write(category + "\n");
			JSONArray dataList = item.getJSONObject("description").getJSONObject("body").getJSONArray("data");
			for(int i = 0; i < dataList.length(); i++) {
				JSONObject data = dataList.getJSONObject(i);
				int hour = data.getInt("hour");
				int temp = data.getInt("temp");
				String wfKor = data.getString("wfKor");
				bw.write(hour + "시,  " + temp + "도,  " + wfKor + "\n");
			}
		
			bw.close();
			reader.close();
			con.disconnect();
			
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		

	

}
