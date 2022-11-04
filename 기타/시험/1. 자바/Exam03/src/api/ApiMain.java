package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ApiMain {

	public static void main(String[] args) {
		
		String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
		StringBuilder urlBuilder = new StringBuilder();
		
		try {
			// * 요청메시지 예시보고 파라미터 작성
			urlBuilder.append("http://apis.data.go.kr/B552061/AccidentDeath/getRestTrafficAccidentDeath");
			urlBuilder.append("?ServiceKey=g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb%2FHja8k8hffCsEV2%2FsIPC%2FeylWm9hcE%2BF709h2AEAg%3D%3D");
			urlBuilder.append("&searchYear=2019");
			urlBuilder.append("&siDo=1100");
			urlBuilder.append("&guGun=1125");
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageNo=1");
//			urlBuilder.append("&resultCode=0000");
//			urlBuilder.append("&resultMsg=Success");
//			urlBuilder.append("&acc_year=2019");
//			urlBuilder.append("&occrrnc_dt=2019011622");
//			urlBuilder.append("&dght_cd=2");
//			urlBuilder.append("&occrrnc_day_cd=4");
//			urlBuilder.append("&dth_dnv_cnt=0");
//			urlBuilder.append("&injpsn_cnt=1");
//			urlBuilder.append("&se_dnv_cnt=1");
//			urlBuilder.append("&sl_dnv_cnt=0");
//			urlBuilder.append("&wnd_dnv_cnt=0");
//			urlBuilder.append("&occrrnc_lc_sido_cd=").append(URLEncoder.encode("1100", "UTF-8"));
//			urlBuilder.append("&occrrnc_lc_sgg_cd=").append(URLEncoder.encode("1117", "UTF-8"));
//			urlBuilder.append("&acc_ty_lclas_cd=").append(URLEncoder.encode("02", "UTF-8"));
//			urlBuilder.append("&acc_ty_mlsfc_cd=").append(URLEncoder.encode("23", "UTF-8"));
//			urlBuilder.append("&acc_ty_cd=").append(URLEncoder.encode("23", "UTF-8"));
//			urlBuilder.append("&aslt_vtr_cd=").append(URLEncoder.encode("01", "UTF-8"));
//			urlBuilder.append("&road_frm_lclas_cd=").append(URLEncoder.encode("01", "UTF-8"));
//			urlBuilder.append("&road_frm_cd=").append(URLEncoder.encode("04", "UTF-8"));
//			urlBuilder.append("&wrngdo_isrty_vhcty_lclas_cd=").append(URLEncoder.encode("01", "UTF-8"));
//			urlBuilder.append("&dmge_isrty_vhcty_lclas_cd=").append(URLEncoder.encode("01", "UTF-8"));
//			urlBuilder.append("&occrrnc_lc_x_crd=").append(URLEncoder.encode("943817", "UTF-8"));
//			urlBuilder.append("&occrrnc_lc_y_crd=").append(URLEncoder.encode("1945678", "UTF-8"));
//			urlBuilder.append("&lo_crd=").append(URLEncoder.encode("128.45275545", "UTF-8"));
//			urlBuilder.append("&la_crd=").append(URLEncoder.encode("35.94815032", "UTF-8"));
//			urlBuilder.append("&totalCount=19");
//			urlBuilder.append("&numOfRows=10");
//			urlBuilder.append("&pageNo=01");
//			
			String apiURL = urlBuilder.toString();
			
			String response = getResponse(apiURL);
			
			createFile(response);
//			System.out.println(response);
			
			// reader을 하는게 맞다
			readFile();

			
			
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("인코딩 오류");
		}
	}
	
	public static String getResponse(String apiURL) {
		
		try {
			HttpURLConnection con = getConnection(apiURL);				// api 접속 및 연결
	
		if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {	// 입력스트림
			return readData(con.getInputStream());				
		} else {													// * stream은 con으로 불러야함
			return readData(con.getErrorStream());
		}
		} catch (IOException e) {
			throw new RuntimeException("api요청 오류", e);
		}
	}
	
	public static HttpURLConnection getConnection(String apiURL) {
		
		try {
			URL url = new URL(apiURL);
			return (HttpURLConnection)url.openConnection();
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("api 접속오류", e);
		} catch (IOException e) {
			throw new RuntimeException("api 연결오류", e);
		}
	}
	
	public static String readData (InputStream in) {
		
		StringBuilder sb = new StringBuilder();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
			return sb.toString();

		} catch (IOException e) {
			throw new RuntimeException("api 응답오류", e);
		}
		
	}


	
	public static void createFile (String response) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("accident.txt"))) {
			
			bw.write(response);
//			bw.flush();
			
		} catch (IOException e) {
			throw new RuntimeException("파일생성오류");
		}
		
	}
	
	public static void readFile() {
		
			
			File file = new File("accident.txt");
			
			try(BufferedReader br = new BufferedReader(new FileReader(file))) {
				
				
				List<Accident> accidents = new ArrayList<Accident>();
				String line = null;
				while((line = br.readLine()) != null) {	// * 문자열데이터니까
					Accident accident = new Accident();
					accident.setOccrrncDayCd(line);
					accident.setOccrrncDt(line);
					accident.setDthDnvCnt(Integer.parseInt(line));
					accident.setInjpsnCnt(Integer.parseInt(line));
					System.out.println(accident);
					accidents.add(accident);
				}
				System.out.println(accidents);
				}
			catch (Exception e) {
				throw new RuntimeException("파일오류", e);
			}
		}
		
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
