package prac_v;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Prac02B")
public class Prac02B extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// & 요청(request는 1회용이기 떄문에 다시 파라미터를 요청해야한다)
		
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");	
		String age = request.getParameter("age");
		
		// 응답
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("<h1> 파라미터 name =" + name + "</h1>");
		out.print("<h1> 파라미터 age =" + age + "</h1>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
