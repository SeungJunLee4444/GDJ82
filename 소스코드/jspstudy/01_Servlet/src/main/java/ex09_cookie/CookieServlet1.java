package ex09_cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieServlet1")

public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// [Cookie]
		// - 서버가 만들어서 클라이언트 pc에 저장해두는 정보
		// - 웹 페이지들에서 참조해야하는 공유 정보를 저장해두고 사용하기위함
		// - 응답할 때 쿠키를 저장함
		// - 클라이언트 요청이 있을 때 모든 쿠키정보를 읽어들일 수 있음?
		// - 특징
				// (1) 보안에 취약
				// (2) 사이트당 1개
				// (3) 4kb 용량제한
		
		// 1. 쿠키 만들기
		//	1) 쿠키이름 : 알파벳, 숫자로 만듬
		//	2) 쿠키값 	: 공백, 세미콜론 등 컴퓨터가 해독 불가능한 문자가 있는경우 인코딩해서포함
		//	1)
		Cookie cookie1 = new Cookie("name", "가나다");
		Cookie cookie2 = new Cookie("address", URLEncoder.encode("서울시 금천구 가산동", "utf-8"));	// 안됨
		
		// 2. 쿠키 유효시간 설정
		cookie1.setMaxAge(15*24*60*60);	// 15일후 x
		cookie2.setMaxAge(10);	// 10초후 x
		
		// 3. client pc에 쿠기 저장(개발자도구-application에서 확인가능)
		// (1) 쿠키는 서버에서 생성
		// (2) 클라이언트에게 전달, 클라이언트가 저장
		// (3) 서버가 만든 쿠키를 클라이언트에게 전달하는데에 response 사용
		response.addCookie(cookie1);
		response.addCookie(cookie2);	// 안됨, 이유 : 아스키코드32번(공백)
		
		// 4. 리다이렉트
		response.sendRedirect("/01_Servlet/CookieServlet2");
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
