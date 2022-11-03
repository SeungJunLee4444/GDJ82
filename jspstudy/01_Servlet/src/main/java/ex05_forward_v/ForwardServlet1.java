package ex05_forward_v;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// [forward]
		// - 1요청 1응답
		// 서버에서 서버로 request와 response를 전달한다
		
		// 코드

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ForwardServlet2");
		requestDispatcher.forward(request, response);
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
