package prac_v;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Prac02A")
public class Prac02A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 요청( & name,age 파라미터 요청해서 전달해주기)
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");	
		String age = request.getParameter("age");
		
		// & 요청으로 받은 정보를 다시 prac02b에 보냄
		response.sendRedirect("/01_Servlet/Prac02B?name=" + URLEncoder.encode(name, "utf-8")+ "&age=" + age);
		// & 한글은 인코딩이 필요하다
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
