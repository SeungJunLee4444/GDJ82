package prac_v;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Prac03B")
public class Prac03B extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("utf-8");
		
		String fileName = request.getParameter("fileName");
		
		// 응답
		response.setContentType("text/html; charset=utf-8");
		
		// 경고창
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + fileName + "파일이 생성되었습니다')");		
		out.println("history.back()");
		out.println("</script>");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
