package ex04_redirect_v;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RedirectServlet1")
public class RedirectServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. redirect
		// - 코드 : response.sendRedirect(이동할 경로)
		response.sendRedirect("/01_Servlet/RedirectServlet2");
		// => 01_Servlet/RedirectServlet1에서 01_Servlet/RedirectServlet2로 응답
		
		// & 과정설명
		// 1) 현 위치에서 1요청, 1응답이 이루어지기 때문에 RedirectServlet1은 종료된다
		// 2) RedirectServlet2 요청은 별개로 이루어짐
		
		// & request와 forward의 차이
		// (1) 기존의 요청을 전달해주는것을 forward
		// (2) 기존의 요청을 전달해주지 않고 서버로 주는것을 redirect
				
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
