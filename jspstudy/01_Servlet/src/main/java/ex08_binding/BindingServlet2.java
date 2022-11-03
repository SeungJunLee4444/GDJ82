package ex08_binding;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/BindingServlet2")
public class BindingServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ServletContext
		ServletContext ctx = getServletContext();
		int a = (int)ctx.getAttribute("a");
		
		// HttpSeccion
		HttpSession session = request.getSession();
		int b = (int)session.getAttribute("b");
		
		// HttpServletRequest
		int c = (int)request.getAttribute("c");
		
		// 응답
		
		// & system.out는 단방향 출력밖에 못함
		// & printwriter은 
		
		response.setContentType("text/html; charset=utf-8");	// html 문서의 형태로 출력
		PrintWriter out = response.getWriter();
		out.println("<h1>a=" + a + ",b=" + b + ",c=" + c + "</h1>");
		// => html 요소로 출력시키기 위해 위 두개의 코드가 필요
		out.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
