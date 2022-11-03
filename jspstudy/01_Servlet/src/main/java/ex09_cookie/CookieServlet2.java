package ex09_cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CookieServlet2")
public class CookieServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 응답
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		// printwriter : 요청/응답시 웹 화면에 필요한 내용을 출력시켜주는 클래스
		// ex html타입이면 html문서처럼 작성시 html요소가 웹 화면에 반영
		
		
		// 쿠키 확인하는법
		// - 쿠키는 골라서 못가져오고 다 가져와야함
		// - 클라이언트에게 쿠키가 있기 때문에 요청을 해야함
		
		Cookie[] cookies = request.getCookies();
		// => 배열로 저장
		
		// 전체 쿠키 확인
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				out.println("<h1>쿠키이름 : " + cookies[i].getName() + "</h1>");
				out.println("<h1>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h1>");
		}
		}
		// => 쿠키 생성시 인코딩이 되어있었으니 반대로 디코딩
		
		// 원하는 쿠키 확인
			if(cookies != null) {
				for(int i = 0; i < cookies.length; i++) {
					if(cookies[i].getName().equals("name")) {
						out.println("<h1>쿠키이름 : " + cookies[i].getName() + "</h1>");
						out.println("<h1>쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8") + "</h1>");				
					}
				}
			}
		
		out.println("<a href=\"/01_Servlet/CookieServlet3\">이동</a>");
		out.close();
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
