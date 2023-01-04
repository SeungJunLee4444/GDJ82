package ex06_ajax_trycatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/Json_Servlet")
public class Json_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("utf-8");
		
		// 요청 파라미터
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 응답할 json 데이터 만들기
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		
		// 응답
		// & json데이터 처리는 응답시 타입만 건드려주면된다(텍스트와 그외에는 동일)
		response.setContentType("application/json; charset=utf-8");		// json 데이터의 mine-type
		// => 자바의 contenttype과 html의 datatype 맞추기
		
		PrintWriter out = response.getWriter();
		out.println(obj.toString());	// 응답데이터는 텍스트 처리된 json객체
		out.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
