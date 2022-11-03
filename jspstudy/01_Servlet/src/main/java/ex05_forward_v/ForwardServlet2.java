package ex05_forward_v;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet2")
public class ForwardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 요청 파라미터 a
		String a = request.getParameter("a");
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println("<h1>파라미터 a=" + a + "</h1>");
		
//		& 리다이렉트든 포워드든 처음1회에 파라미터를 전송할때는 상관없이 파라미터 전송이 가능하다
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
