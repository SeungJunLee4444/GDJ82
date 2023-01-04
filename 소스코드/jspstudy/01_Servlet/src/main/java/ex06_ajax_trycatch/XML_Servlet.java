package ex06_ajax_trycatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.json.XML;


@WebServlet("/XML_Servlet")
public class XML_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// [xml]
		// 요청
		request.setCharacterEncoding("utf-8");
		
		// 요청 파라미터
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		
		// 응답할 json 데이터 만들기
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("age", age);
		
		
		JSONObject person = new JSONObject();
		person.put("person", obj);
		
		// + 응답할 json 객체를 xml로 변환하기
		String responseXML = XML.toString(person);	// xml을 문자열로 변환
		// => 결과
		// <person>
		// 		<name>가나다</name>
		// 		<age>44</age>
		// </person>
		
		// 응답
		// & json데이터 처리는 응답시 타입만 건드려주면된다(텍스트와 그외에는 동일)
		response.setContentType("application/xml; charset=utf-8");		// json 데이터의 mine-type
			
		
		PrintWriter out = response.getWriter();
		out.println(responseXML);	// 응답데이터는 텍스트 처리된 json객체
		out.close();
				
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
