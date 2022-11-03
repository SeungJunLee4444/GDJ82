package ex03_parameter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// & [form]
		// (1) null 예외 : input의 radio와 checkbox에서만 발생한다	**
		
		// 1. 요청
		request.setCharacterEncoding("UTF-8");
		// & 모든 데이터는 string 타입으로 들어옴
		
		
		
		// 변수
		// & getParameter : client 웹에서 파라미터를 가져오는 메서드 
		String id = request.getParameter("id");
		
		// 1) 아이디
		// (1) 공백처리 정답(short circut evaluation)
		if(id.isEmpty() ) {
			id="빈아이디";
		}
//		if(id == null || id.isEmpty()) {
//			id = "빈 아이디";
//		}
//		// 2) 공백처리 오류
//		if(id.isEmpty() || id == null ) {
//			id = "빈 아이디";
//		}
		// nullpointer 오류가뜸(null이 메서드를 부르고있음)
		
		
		// 2) 비번
		String pw = request.getParameter("pw");
		if(pw.isEmpty()) {
			pw = "빈 문자열";
		}
		// => 공백도 ***로 표시
		

				
		String gender = request.getParameter("gender");
		if(gender == null) {
			gender = "빈 성별";
		}
		String city = request.getParameter("city");
		if(city.isEmpty()) {
			city = "빈 도시";
		}  
		
		
		// & isempty() 	: 빈 문자열인지 확인, 주소값은 있음("")
		// & null		: null 아무런 값도 할당되지않은 상태
		
		// & 동일한 파라미터가 2개 이상이면 배열로 요청
		// 예외 : radio => 하나만 선택하기 때문
		
		// 배열(파라미터 : 동일한 파라미터가 2개이상이면 배열로 요청)
		String[] phone = request.getParameterValues("phone");
		if(phone[0].isEmpty() ) {
			phone[0] = "빈 전화1";
		}
		if(phone[1].isEmpty() ) {
			phone[1] = "빈 전화2";
		}
		if(phone[2].isEmpty() ) {
			phone[2] = "빈 전화3";
		}
		// => 파라미터 phone이 3개이기 때문에 배열에 저장
		String strPhone = phone[0] + "-" + phone[1] + "-" + phone[2];
		
		String[] agree = request.getParameterValues("agree");
		if(agree == null) {
			agree = new String[1];
			agree[0] = "빈 동의";
		}
		
		// datalist도 하나만 고르기 때문에 배열이 필요없다
		String email_id = request.getParameter("email_id");
		String domain = request.getParameter("domain");
		
		
		
		// 응답
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<h3>아이디 : " + id + "</h3>");
		out.println("<h3>비밀번호: " + pw + "</h3>");
		out.println("<h3>성별: " + gender + "</h3>" );	
		out.println("<h3>도시: " + city + "</h3>");		
		out.println("<h3>폰: " + strPhone + "</h3>");
		out.println("<h3>동의 :" + Arrays.toString(agree) + "</h3>");	// => 배열의 value값 전달
		List<String> list = Arrays.asList(agree);
		// => 문자열배열을 리스트로 변환
		if(list.contains("marketing")) {
			// contain : 검사속성
			out.println("<h3>마케팅 동의한 회원</h3>");
		}
		
		out.println("<h3>" + email_id + '@' + domain + "</h3>");
		
		out.close();
		
		
		// => value값 전달
		out.flush();
		out.close();
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
