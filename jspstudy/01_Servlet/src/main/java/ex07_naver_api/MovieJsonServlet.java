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


@WebServlet("/MovieJsonServlet")

// 한 프로젝트에 동일한 맵핑이 두개 있을 경우 => 톰켓이 작동안됨
public class MovieJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// & 
		// 왜 실패할때 텍스트는 text/plain이고,
		// 성공하면 application~ 등을 쓰는지?
		
		
		// 1. 클라이언트 아이디, 시크릿
		String clientId = "8DMw73fQdkYHHygxtkVX";
		String clientSecret = "JMXw6j0N4f";
		
		
		// 2. 요청 파라미터
		request.setCharacterEncoding("utf-8");	// 모든 데이터타입은 string타입으로 들어온다
		String query = request.getParameter("query");
		String display = request.getParameter("display");
		
		// 3. 검색어 utf-8 인코딩
		try {
			query = URLEncoder.encode(query, "utf-8");
			
		} catch (UnsupportedEncodingException e) {
			// 1) 데이터형식과 한글 처리 
			response.setContentType("text/plain; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("검색어 인코딩 실패");
			out.close();
		}
		
		// 4. api 접속
		String apiURL = "https://openapi.naver.com/v1/search/movie.json";
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			
		} catch (MalformedURLException e) {
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API URL이 잘못되었습니다");	
			out.close();
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패했습니다");	
			out.close();	
		}
		
		// 5. api 요청
		try {
			con.setRequestMethod("get");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 연결이 실패했습니다");	
			out.close();
		}
		
		// 6. api 응답 스트림 생성
		BufferedReader reader = null;
		try {
			int responseCode = con.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 응답스트림 생성이 실패했습니다");	
			out.close();
		}
		
		// 7. api 응답 데이터 저장하기
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);
				
			}
		} catch (IOException e) {
			response.setContentType("text/plain; charset=utf-8");	
			PrintWriter out = response.getWriter();
			out.println("API 응답이 실패했습니다");	
			out.close();
		}
		
		// 8. client.html api 응답결과 보내기
		response.setContentType("application/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("API 응답이 실패했습니다");	
		out.close();
		

		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
