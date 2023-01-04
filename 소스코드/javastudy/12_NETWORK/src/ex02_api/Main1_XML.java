package ex02_api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main1_XML {

	public static void m1() {

		// & 정리
		// 1. 요청 => request, 클라이언트가 서버에게 요청
		// 2. 응답 => response, 서버가 클라이언트에게 응답하는것

		// [공공데이터 수집을 위한 객체지향 프로그램 구현]

		// 1. 요청하기 (api주소와 요청 파라미터 만들기)
		// => client가 server측으로 요청하는 데이터

		// ex) 전국종량제봉투가격표준데이터

		// 1) apiurl 만들기
		String apiURL = "http://api.data.go.kr/openapi/tn_pubr_public_weighted_envlp_api";

		// 2) 요청을 위한 파라미터 샘플 작성
		try {

			// 3) serviceKey서비스키
			// * 인증키는 대체로 decording 원본키를 주기 때문에, 인코딩이 필요
			String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";

			// * 파라미터 추가방식 : 주소창에 붙여서 붙이는 방식을 get, 아니면 post
			// * 파라미터 추가는 stringbuilder를 통해 효율적으로 코드를 짤 수 있다

			apiURL += "?pageNo=" + URLEncoder.encode("0", "UTF-8");
			apiURL += "&numOfRows=" + URLEncoder.encode("100", "UTF-8");
			apiURL += "&type=" + URLEncoder.encode("xml", "UTF-8");
			apiURL += "&CTPRVN_NM=" + URLEncoder.encode("서울특별시", "UTF-8");
			apiURL += "&SIGNGU_NM=" + URLEncoder.encode("금천구", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TYPE=" + URLEncoder.encode("규격봉투", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_MTHD=" + URLEncoder.encode("소각용", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_PRPOS=" + URLEncoder.encode("생활쓰레기", "UTF-8");
			apiURL += "&WEIGHTED_ENVLP_TRGET=" + URLEncoder.encode("기타", "UTF-8");

			// * 인증키 인코딩
			apiURL += "&serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");

			// * 요청시에 필요한 모든 샘플 데이터는 전부 encording해라(데이터 이름은 제외)

			// (영어, 숫자는 인코딩 필요없음, 한글, 특수기호는 필요있음)
			// * 호출할때 요구되는 요쳥변수를 작성해 요청해야한다
			// (필수인지 선택인지 해당 api사이트에서 확인)
		} catch (UnsupportedEncodingException e) {
			System.out.println("인코딩 실패");
		}

		// 2. api 주소 접속
		URL url = null;
		HttpURLConnection con = null;

		try {

			url = new URL(apiURL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			// * 파라미터 입력방식 표현 메서드
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF8");
			// 해석: xml 데이터 타입을 요청
			// * content-type : 리소스의 타입을 나타내는 정해진 키워드
			// html, xml, json인지 적을때 사용
			// ex) text/html, application/xml, application/json

		} catch (MalformedURLException e) {
			System.out.println("api 주소 오류");
		} catch (IOException e) {
			System.out.println("접속 실패"); // => openconnection에서 발생
		}

		// 3. 응답(입력스트림)
		// => server가 client한테 응답하는 것을 말한다
		// => 응답 성공시 정상 스트림 사용, 실패했을 때는 에러 스트림을 사용
		// => 응답 데이터는 stringbuilder에 저장(텍스트 기반의 string)

		// *요약:ㅣ 서버에서 데이터를 읽어와 stringbuilder

		BufferedReader reader = null;
		StringBuilder sb = new StringBuilder();

		try {

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream())); // 정상스트림
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream())); // 에러스트림
			}
			// * inputstreamreader은 byte타입의 데이터를 문자타입으로 변환시킨다
			// * stringbuilder을 쓰는 이유: string타입을 직접더하는 것보다 stringbuilder을 이용해 문자를 합하는것이
			// 효율적이기 때문에

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();

		} catch (IOException e) {
			System.out.println("API 응답실패"); // => inputstream
		}

		// * 나중에 파일 생성과 xml 분석은 안쓰게됨
		// => 자바만 사용하고있기 때문, 다른 도구로 해결가능

		// * response는 api로부터 전달받은 xml 데이터
		String response = sb.toString();

		// file 생성 및 데이터 저장된 파일 생성
		File file = new File("c:\\charlie1", "ch3.xml");

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// * xml 태그별로 하나씩 분석해보기

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			Element root = doc.getDocumentElement(); // <response> (최상위 태그)
			System.out.println(root.getNodeName());

			NodeList nodeList = root.getChildNodes(); // <response>의 자식 태그(<header>, <body>)
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i); // <header>와 <body>
				System.out.println("  " + node.getNodeName());
				NodeList nodeList2 = node.getChildNodes(); // <header>의 자식 태그(<resultCode>, <resultMsg>), <body>의 자식
															// 태그(<items>, <numOfRows>, <pageNo>, <totalCount>)
				for (int j = 0; j < nodeList2.getLength(); j++) {
					Node node2 = nodeList2.item(j);
					System.out.println("    " + node2.getNodeName());
					if (node2.getNodeName().equals("items")) { // <items> 태그 대상
						NodeList items = node2.getChildNodes(); // <items>의 자식 태그(<item>)
						for (int k = 0; k < items.getLength(); k++) {
							Node item = items.item(k);
							System.out.println("      " + item.getNodeName());
							NodeList itemChildren = item.getChildNodes(); // <item>의 자식 태그
							for (int l = 0; l < itemChildren.getLength(); l++) {
								Node itemChild = itemChildren.item(l);
								System.out.println(
										"        " + itemChild.getNodeName() + ":" + itemChild.getTextContent());
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// * document, emelemnt면 전부 w3c.dom 소속

		con.disconnect();

	}

	public static void m2() {

		// 2.
		// ex) 보건복지부 코로나 현황

		// 1) request
//			String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
		String serviceKey = "bEQBRPHjt0tZrc7EsL0T8usfsZ1+wT+5jqamBef/ErC/5ZO6N7nYdRmrwR91bh5d3I1AQeY5qdbJOF6Kv0U1CQ==";
		StringBuilder urlBuilder = new StringBuilder();

		try {

			urlBuilder.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&startCreateDt=20220808");
			urlBuilder.append("&endCreateDt=20220812");
			// * 영문과 숫자는 인코딩이 필요없음
			// * 특정날짜의 데이터를 원하면 날짜를 추가하면된다

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiUrl = urlBuilder.toString();
		// * 추가한 스트링 빌더를 다시 문자열 url로 바꿔줘야한다

		// 2) API 주소 연결

		URL url = null;
		HttpURLConnection con = null;

		try {

			url = new URL(apiUrl);
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			// * 요청방식을 요구하는 메서드
			// => 보안이 필요하면 POST, 아니면 GET(기본은 GET)
			con.setRequestProperty("Content-Type", "application/xml");

		} catch (MalformedURLException e) {
			System.out.println("api 주소 오류");
		} catch (IOException e) {
			System.out.println("접속 실패");
		}

		// 3) 입력받기
		// * 읽어내는 데이터타입은 바이트 타입으로 reader을 통해 문자열로 읽어낼 수 있음
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
			System.out.println("api 응답실패");
		}

		String response = sb.toString();

		// 4) 파일생성

		File file = new File("c:\\storage", "api2.xml");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(response);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void m3() {

		// 5) xml 파싱

		File file = new File("c:\\storage", "api2.xml");

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			Element root = doc.getDocumentElement(); // 최상위 태그 가져오기, 최상위태그를 root라 한다
//					System.out.println(root.getNodeName());		

			// * node는 줄바꿈도 노드로 인식한다

			// * <item>...</item>태그가 날짜 하나
			NodeList items = root.getElementsByTagName("item"); // 태그 이름으로 요소찾기
			// * elements는 요소가 복수, element는 요소가 단수(ex id)

			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < items.getLength(); i++) {
				Node item = items.item(i);
				NodeList itemChildren = item.getChildNodes(); // item 자식 클래스들을 호출

				Node stateDt = itemChildren.item(4); // item 자식 클래스의 5번째 요소(노드) 이름은 statedt
				// => stateDt.getNodeName() => stateDt
				// => stateDt.getTextContent() => 20220812 (가운데값)

				for (int j = 0; j < itemChildren.getLength(); j++) {
					Node itemChild = itemChildren.item(j);

					if (itemChild.getNodeName().equals("stateDt")) {
//								System.out.println(stateDt.getTextContent());
						sb.append(" 날짜 :").append(itemChild.getTextContent());
					} else if (itemChild.getNodeName().equals("decideCnt")) {
						sb.append(" 확진자수: ").append(itemChild.getTextContent());
					} else if (itemChild.getNodeName().equals("deathCnt")) {
						sb.append(" 사망자수: ").append(itemChild.getTextContent());
					}
				}
				sb.append("\n");
				// * 여기 있어도 되는 이유 => 중첩 for문 ***

				// => NODE는 줄바꿈을 하면 노드로 인식하기 때문에 item의 인덱스값에 영향을 준다
				// => equal을 이용하면 줄바꿈과 상관없이 값을 찾아낼 수 있다

			}
			System.out.print(sb.toString());

			// * nodelist에서 node하나를 지칭하는 메서드를 item, collection에서는 get

			// * <>태그 한덩이를 element, node라 부른다

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void m4() {

		StringBuilder urlBuilder = new StringBuilder();

		try {

			String serviceKey = "g1fjdlcJOfuhukMnsmo79qYkfOFBB5ocbWKYcImwfNqEb/Hja8k8hffCsEV2/sIPC/eylWm9hcE+F709h2AEAg==";
			urlBuilder.append("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst");
			urlBuilder.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			urlBuilder.append("&numOfRows=10");
			urlBuilder.append("&pageNo=1");
			urlBuilder.append("&base_date=20220818");
			urlBuilder.append("&base_time=1500");
			urlBuilder.append("&nx=55");
			urlBuilder.append("&ny=127");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiUrl = urlBuilder.toString();

		// 2. url 연결

		URL url = null;
		HttpURLConnection con = null;

		try {

			url = new URL(apiUrl);
			con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; charset=UTF8");

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

		File file = new File("c:\\storage", "api3.xml");
		BufferedWriter bf = null;

		try {

			bf = new BufferedWriter(new FileWriter(file));
			bf.write(response);
			bf.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void m6() {

		File file = new File("c:\\storage", "api3.xml");

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			Element root = doc.getDocumentElement();
			// node라 하면 items의 getElementsByTagName가 호출되지않음

			// * node는 태그이자, 줄바꿈이며, 태그보다 상위개념이다
			// => 이러한 이유로 node가 확실히 태그로 쓰이지않으면 getelementbytagname을 호출x

			NodeList items = root.getElementsByTagName("item"); // item태그들
			for (int i = 0; i < items.getLength(); i++) {		// 동일한 item태그 5개
				Element item = (Element) items.item(i);
//				NodeList categories = item.getElementsByTagName("category");
				Node category = item.getElementsByTagName("category").item(0);
				Node obsrValue = item.getElementsByTagName("obsrVaule").item(0);
				String strCategory = null;
				switch (category.getTextContent()) {
				case "PTY":
					strCategory = "강수형태";
					break;
				case "REH":
					strCategory = "습도";
					break;
				case "RN1":
					strCategory = "강수량";
					break;
				case "T1H":
					strCategory = "기온";
					break;
				case "UUU":
					strCategory = "동서바람성분";
					break;
				case "VEC":
					strCategory = "풍향";
					break;
				case "VVV":
					strCategory = "남북바람성분";
					break;
				case "WSD":
					strCategory = "풍속";
					break;

				}
				System.out.println(strCategory + ":" + obsrValue.getTextContent());
			}

			// * node 밑에 element 태그가 있다

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void m7() {

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
			con.setRequestProperty("Content-Type", "application/xml");

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

		File file = new File("c:\\charlie1", "ch4.xml");

		BufferedWriter bw = null;

		try {

			bw = new BufferedWriter(new FileWriter(file));
			bw.write(response);

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void m8() {

		// * 서버에 연결된 스트림은 무조건 바이트 스트림
		// * 만약 내려받을 자료가 이미지, 동영상이 포함되있다면, 문자열 관련 스트림을 제거해야함

		File file = new File("c:\\storage", "api4.xml");

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			Element root = doc.getDocumentElement();

			StringBuilder sb = new StringBuilder();

			// * 태그가 하나뿐이면 node, 여러개면 element 사용

			Node title = root.getElementsByTagName("title").item(0);

			Node pubData = root.getElementsByTagName("pubData").item(0);
			// * node title 데이터 상에서 0번째 item, pubdata 데이터 상에서 0번쨰
			// * node는 getelementbytagname을 쓸 수 없다
			// * getelementbytagname은 태그의 위치 상관없이 필요한 태그명을 호출할 수 있다
			// * getelmentbytagname을 호출하려면 node가 아닌 element 객체가 필요하다
			
			NodeList dataList = root.getElementsByTagName("data");
			for(int i = 0; i < dataList.getLength(); i++) {
				Element data = (Element)dataList.item(i);
				Node hour = data.getElementsByTagName("hour").item(0);
				Node temp = data.getElementsByTagName("temp").item(0);
				Node wfKor = data.getElementsByTagName("wfKor").item(0);
				sb.append(hour.getTextContent()).append("시 ");
				sb.append(temp.getTextContent()).append("도 ");
				sb.append(wfKor.getTextContent()).append("\n");
				
				// * item(0)의 의미: data 태그 안에서 해당 hour, temp 등의 태그가 한개뿐이면 0
				// 만약 여러개있다면 인덱스값 순으로 1, 2, 3 증가
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void m9() {

		// * html 요소의 특수구조
		// => 만약 contenttext의 내용이 태그에 담겨져 있는 상황이라면?
		// <div 속성이름 = "속성값"> 내용 </div>
		// attribute : 속성 / getattribute("속성이름") : 속성얻어서 해결
		// div.gettextcontent -> html, div.getattribute -> contatiner

		File file = new File("c:\\storage", "sfc_web_map.xml");

		try {

			StringBuilder sb = new StringBuilder();

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);

			Element root = doc.getDocumentElement(); // <current> 태그 - xmlns이라는 속성값을 지니고 있다
//			System.out.println(root.getNodeName());				// current
//			System.out.println(root.getAttribute("xmlns"));		// current

			// * 첫번째 자식태그로 성립하려면, 줄바꿈, 공백없이 자식태그와 붙어있어야함
			Element weather = (Element) root.getElementsByTagName("weather").item(0); // 하나뿐인 태그 weather 그중에서 0번쨰
			sb.append(weather.getAttribute("year") + "년");
			sb.append(weather.getAttribute("month") + "월");
			sb.append(weather.getAttribute("day") + "일");
			sb.append(weather.getAttribute("hour") + "시\n");

			NodeList locals = root.getElementsByTagName("local"); // getelementbytagname은 태그의 상하관계 상관없이 호출 가능하다
			for (int i = 0; i < locals.getLength(); i++) {
				Element local = (Element) locals.item(i);
				sb.append(local.getTextContent() + ":");
				sb.append(local.getAttribute("ta") + "℃");
				sb.append(local.getAttribute("desc") + "\n");

			}
			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void m10() {

	}

	public static void main(String[] args) {

		 m1();
		// m2();
		//m3();
		// m4();
		
		 //m6();
		// m7();
		// m8();
		// m9();
		// m10();

	}

}
