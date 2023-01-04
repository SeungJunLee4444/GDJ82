package ex07_naver_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
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


@WebServlet("/PapagoServlet")
// 서블릿 맵핑명 지정
// 1) @webservlet 어노테이션 이용 : 맵핑명만 지정


public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 1. API 주소 생성
		// 클라이언트 아이디, 시크릿
		String clientId = "JAc4HWJzPvJx6H4u4Lct";
		String clientSecret = "KsgzMThwYW";
		
		// 요청 파라미터(원본언어, 목적언어, 번역할 테스트)
		String source = request.getParameter("source");
		String target = request.getParameter("target");
		String text = request.getParameter("text");
		
		// 번역할텍스트 utf-8 인코딩
		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch(UnsupportedEncodingException e) {
			response.setContentType("text/plain; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("번역할 텍스트 인코딩 실패");
			out.close();
		}
			// & mine 타입
			// => 데이터를 응답받을 때, 응답할 데이터의 타입을 지정
			// text-plain : 텍스트 => ex) 실패시 데이터 대신 출력할 데이터타입(텍스트)
			
			// application/xml, json => 성공시 데이터의 타입
			
		
		// 2. API 접속
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
		} catch (MalformedURLException e) {
			response.setContentType("text/plain; charset=utf-8");		// => 왜 text/plain인지 : 응답을 텍스트 형식으로 하기위함
			PrintWriter out = response.getWriter();
			out.println("API URL 주소 형식이 잘못되었습니다");
			out.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");		// => 왜 text/plain인지?
			PrintWriter out = response.getWriter();
			out.println("API 접속이 실패했습니다");
			out.close();
		}
		
		// 3. API 요청
		try {
			
			// 1) 요청 헤더
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
			// 12 POST방식 
			con.setRequestMethod("post");
			con.setDoOutput(true);
			// => 포스트방식일때는 setDoOutput를 같이 써줘야한다
			
			// & get방식과 post방식의 차이
			// 파라미터에 ?로 날리는건 get방식, 주소에 파라미터를 붙이지 않고, 본문에 붙이는 방식이 post
			
			// 3) 파라미터를 본문에 출력
			String params = "source=" + source + "&target=" + target + "&text=" + text;
			OutputStream outputStream = con.getOutputStream();
			outputStream.write(params.getBytes());
			// = string을 byte타입으로
			outputStream.close();
			
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");		// => 왜 text/plain인지?
			PrintWriter out = response.getWriter();
			out.println("API 요청이 실패했습니다");
			out.close();
		}
		
		// API 응답 스트림 생성(정상 스트림, 에러 스트림)
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode(); // 200
			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));	
				}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");		// => 왜 text/plain인지?
			PrintWriter out = response.getWriter();
			out.println("API 응답 스트림이 실패");
			out.close();
		}
		
		// API 응답(StringBuilder에 저장)
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
		while((line = reader.readLine()) != null) {
			sb.append(line);
		} 
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");		// => 왜 text/plain인지?
			PrintWriter out = response.getWriter();
			out.println("API 응답이 실패했습니다");
			out.close();
		}
		
		// client.html로 응답결과 보내기
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.close();
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
