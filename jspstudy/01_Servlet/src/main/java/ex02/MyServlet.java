package ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/My","/me"})		

// & URLMapping값 변경 가능 (1)생성시 변경, (2) <= 바로 변경가능
// & URLMapping({"/my", "/me"}) 처럼 2개 이상의 URL Mapping을 지정할 수 있음

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//* */ : 문서주석
	// 	   - 클래스의 선언부, 멤버, 생성자 등 클래스 인터페이스 시작부분에 사용해야한다
	
	//-------------------------------------
	// * 이유없는 오류 발생시
	// (1) 여러개 등록된 서버 context를 정리
	// (2) restart
	// (3) clear
	//-------------------------------------
	
	// & 서블릿
	// => 지금당장은 아래처럼 코드를 작성하지만, 실제로 사용하지는 않으며,
	// 이후에는 다른곳에 연결하는 용도로 사용된다
	

    public MyServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 요청(request)
		//	- 클라이언트가 서버측으로 보내는 요청 또는 데이터
		//	- HttpServletRequest request 객체가 처리(톰켓이 있어야 처리가능)
		
		// 1) 요청에 포함된 '한글처리'(문자셋 : utf-8)
		request.setCharacterEncoding("UTF-8");
		// & 전달하는 값이 한글이 포함안되면 사용할필요x
		
		//--------------------------------------------------------------------------------------------------------------------
		// & 인코딩과 디코딩
		// 1) 인코딩 : 사람의 문자를 문자코드(아스키코드)를 기준으로 컴퓨터가 이해할 수 있는 숫자(0,1)로 이루어진 코드로 변환하는것
		// - 용도 : 인터넷으로 url을 보낼 때 
		// => url에 한글이 들어가 있으면 에러가 발생
		// => 이는 url에는 ascii문자만 해석할 수 있기 때문에다(아스키문자에 영어는 해당, 한글은 해당하지않음)
		// => 한글은 % + 16진수숫자(해당하는)로 변환되어 url에 인코딩된다
		
		// 2) 디코딩 : 반대로 코드를 문자로 변환하는것
		
		
		
		//--------------------------------------------------------------------------------------------------------------------
		
		
		// 2) 요청 파라미터(Parameter) 생성
		//	- URL?파라미터=값&파라미터=값
		//	- 모든 파라미터는 'string'타입(숫자를 보내도 문자로 인식)
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");	// 모든 파라미터는 string타입이라 string타입에 저장
		
//		System.out.println(name + "," + age);
		// 결과 : 오류발생
		// => 파라미터값이 아직 완성되지 않았기때문
		
		// & NumberFormatException: null값 발생시 오류

		// => 해결 : null처리
		int age = 0;
		if(strAge != null) {	// null을 제외한다
			age = Integer.parseInt(strAge);
		}
		
		System.out.println(name + "," + age);
		
		// 2. 응답(response)
		//	- 서버에서 클라이언트로 보내는 응답
		//  - HttpServletResponse response 객체가 처리(당연히 톰켓이 있어야 처리가능)
			
		//	1) 사용자에게 전달할 데이터형식을 html문서로 결정한다(가정)
		//		- MINE-TYPE을 활용
		//		- MINE-TYPE의 종류
		//		(1) HTML	: text/html
		//		(2) CSS		: text/css
		//		(3) JS		: text/javascript
		//		(4) xml 	: application/xml
		//		(5) json 	: application/json
		//  (MINE-TYPE : 문서의 형식을 의미)
		
//		response.setContentType("text/html");		(X)
		// => 해석 : 사용자한테 html 문서 보낼꺼야
		
		//	2) 응답에 포함되는 한글 처리
//		response.setCharacterEncoding("UTF-8");		(X)
		
		//	1) + 2) MINE-TYPE + 문자셋(앞으로 이거쓰기)
		response.setContentType("text/html; charset=UTF-8");		// (O)
		
		// 3) 응답 스트림 생성
		//	- 문자 출력 스트림(*writer)을 생성
		//	- response 객체로부터 printwriter 객체를 얻을 수 있음
		//	- IOException 예외 처리를 throws로 던져버린다(try-catch문 필요없음)
		PrintWriter out = response.getWriter();
		
		// & write() 메서드는 줄바꿈처리가 안되서, write('\n') 줄바꿈 처리를 보낸다
		
		// 4) 응답 만들기(html 문서 만들기)
		// - 태그를 한땀한땀 만들면된다
		out.print("<html lang=\"ko\">");
		// & 자바에서는 \" 이스케이프 문자로 큰따음표"를 표현해야한다
		out.print("<head>");
		out.print("<meat charset=\"UTF-8\">");
		out.print("<title>");
		out.print("<나의 첫번째 응답은>");
		out.print("</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>안녕하세요" + name + "님 반갑습니다 ★ </h1>");
		if(age >= 20) {
			out.print("<h1>귀하는" + age + "살이므로 입장이 가능합니다</h1>");			
		} else {
			out.print("<h1>" + age + "살 애들은 다음에</h1>");			
		}
		out.print("</body>");
		out.print("</html>");
		
		out.flush();	// 출력 스트림에 남아있는 모든 데이터 내보내기(만약을 위해)
		out.close();
		
		// & 요즘은 이렇게 안만듬
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
