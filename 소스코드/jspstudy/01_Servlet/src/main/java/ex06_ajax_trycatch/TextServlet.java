package ex06_ajax_trycatch;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TextServlet")
public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			
		
		// 요청 : 파라미터값을 가져와서 객체에 저장
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		
		// 1. 첫번째 예외 생성 : null값 오류방지
		int age = Integer.parseInt(request.getParameter("age")); 		
		// => # null 오류 발생의 여지가있는곳
		// => 파라미터에 age가 없다던가, 잘못된 타입의 값을 전달했다던가
		// => 예외처리는 try-catch문으로 만든다
		
		// 2. 두번째 예외 생성 : 나이제한 방지
		if(age < 0 || age >100) {
			throw new RuntimeException(age + "살은 불가능한 나이입니다");
		}
		
		
															
		// 응답 : 응답받은 데이터와 저장된 파라미터객체를 이용해 요소의 텍스트로 출력
		response.setContentType("text/plain; charset=utf-8"); 	
		// 단순텍스트 mine/type
		// text/plain text/html application/xml application/json
		
		PrintWriter out = response.getWriter();
		out.println("이름은" + name + "이고, 나이는" + age + "살입니다");
		// => ex05 html문서의 success 함수의 resData 변수로 넘어간다
		out.close();
					
		
		// 1. null 예외
		} catch (NumberFormatException e) {
			e.printStackTrace();
			
			// # 예외처리 응답
			response.setContentType("text/plain; charset=utf-8");
			
			// # 개발자가 임의로 작성한 응답코드
			response.setStatus(1000);	
			// => 예외사항 발생시 status(응답코드)를 설정
			
			PrintWriter out = response.getWriter();
			out.println("예외발생! 파라미터 age는 정수입니다");
			out.close();
			
			
		// 2. 나이제한 예외
		} catch (Exception e) {
			
			response.setContentType("text/plain; charset=utf-8");
			
			response.setStatus(2000);
			
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());  // RuntimeException 예외 객체에 저장된 예외 메시지를 responseText로 처리
			out.close();
			
		}
	}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
