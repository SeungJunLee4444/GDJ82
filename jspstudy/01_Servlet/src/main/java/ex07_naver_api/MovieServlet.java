package ex07_naver_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/MovieServlet")
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 클라이언트 아이디, 시크릿
		// => application - 내 에플리케이션에서 확인가능
		// => 별도의 인코딩 필요x
		String clientId = "8DMw73fQdkYHHygxtkVX";
		String clientSecret = "JMXw6j0N4f";
		
		
		// 2. 요청 파라미터(검색어, 검색결과수)
		request.setCharacterEncoding("UTF-8");
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		
		// 3. 검색어 utf-8 인코딩
		try {
			query = URLEncoder.encode(query, "UTF-8");
			// => 검색어를 컴퓨터가 이해할 수 있도록 인코딩(네이버 api사이트에 파라미터 조건확인)
			
		} catch (UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");	// & text/plain : 텍스트(문자열)만 반환한다
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패");	// => error의 responseText로 넘어간다
			out.close();
		}
		
		// api 접속(display는 숫자로 넘어오니 인코딩필요x)
		String apiURL = "https://openapi.naver.com/v1/search/movie.xml?query=" + query + "&display=" + display;
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			
		} catch (MalformedURLException e) {
			// 1. api주소가 잘못된 형태
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다");	
			out.close();
		} catch (IOException e) {
			// 2. 입출력 오류 : URL, CON의 문제
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패했습니다");	
			out.close();	
		}
		
		// API 요청
		try {
			// 요청메서드
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			// 요청 헤더
			
		} catch (IOException e) {
			// 2. 입출력 오류 : URL, CON의 문제
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패했습니다");	
			out.close();	
		}
		
		
	
		
		// API 응답 스트림 생성(정상스트림, 에러스트림)
		
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode(); // 응답코드(status)를 의미";
		if(responseCode == HttpURLConnection.HTTP_OK) {
			// = status값
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		
		}
		
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 응답스트림 생성이 실패했습니다");	
			out.close();
		}
		
		// API 응답 데이터 저장하기
		StringBuilder sb = new StringBuilder();
		String line = null; // 한줄씩 읽어들이기
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IOException e) {
			// 입출력오류
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 응답이 실패했습니다");	
			out.close();
		}
		
//		System.out.println(sb.toString());	// 검색시, 해당정보가 콘솔에 표기
		
		// client.html로 api 응답결과 보내기
		response.setContentType("application/xml; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
