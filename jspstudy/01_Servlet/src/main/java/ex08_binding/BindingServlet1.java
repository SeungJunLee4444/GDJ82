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


@WebServlet("/BindingServlet1")	
// [바인딩서블릿1]
	// & 서블릿 자원을 서블릿 관련 객체에 저장하는 것을 바인딩이라 한다
	// 저장을 위해서는 서블릿 영역과 속성 접근메서드가 필요하다

// - & 웹 프로그램 실행시 서블릿 자원을 서블릿 관련 객체에 저장하는 방법	& 요청에 데이터를 저장하는것을 바인딩이라한다
// - 정의 : 속성의 정보를 저장하고, 사용하는것(데이터저장)
// - 속성을 저장할 수 있는 3개의 영역(시간?)
//		(1) servletcontext  	: 애플리케이션(프로젝트) 종료 전까지 가능, 	
// 		=> ex) 방문자수
//		(2) httpsession			: 브라우저 종료 전까지 접근해서 사용가능		&로그인 구현
//		=> ex) 로그인정보 : 브라우저를 닫지만 않으면 유지됨
//		(3) httpservletrequest 	: 하나의 요청~응답 전까지만 사용가능(요청-응답은 한번 사용하면 끝나기떄문) 
//		=> request는 데이터 저장용도로도 사용가능

// - 메서드(속성(객체)에 값(데이터)를 바인딩
//		(1) getAttribute('속성') 		: 해당 속성값 가져오기
//		(2) setAttribute('속성', 값) 	: 속성에 값 저장하기
//			=> 값을 저장할 때는 object 타입 : 값으로는 모든 타입이 저장될 수 있기 떄문에 object 타입이다 
//			=> 가져올때는 캐스팅이 필요
//		(3) removeAttribute('속성) 		: 속성 지우기
//		
//

// * stateless
// - 바인딩이 중요한 이유
// - 의미 : 상태없음
// - 웹 페이지간 이동은 stateless로 이동한다
// - 이전 페이지가 어떻게 돌아갔는지 현재 페이지에서는 정보가 전혀없음
// => 이전 페이지의 정보를 저장하는 바인딩이 필요한 이유

public class BindingServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 각 메서드로 저장하는법
		// ServletContext(방문자수 저장)
		ServletContext ctx = getServletContext();	// & 또는 request.getServletContext();
		// => generic 소속 메서드라 servletrequest
		ctx.setAttribute("a", 1);
		
		// HttpSession(로그인 저장)
		HttpSession session = request.getSession();
		session.setAttribute("b", 2);
		
		// HttpServletRequest(요청응답저장)
		request.setAttribute("c", 3);
		// => 매개변수에 이미 선언되있어서 별도의 선언이 필요없음
		// => 요청으로도 데이터를 저장할 수 있다
		
		// & 파라미터 추가하는거 아님, 요청에 값을 전달할때 바인딩 사용
		

		
		// 응답
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.println("<a href=\"01_Servlet/BindingServlet2\">이동</a>");
//		out.close();
		// => servlet1에서 2로 넘어갈떄
		// => 요청-응답 안에서만 사용되는 httpservletrequest는 2로 넘어갈경우 예외발생(null처리)
		// =
		
		// => 해결
		// 1. 포워드(서버내부이동)
		// - request 전달
		// - 서버 내부 이동으로, 경로 작성 시 컨텍스트 패스는 작성하지 않음
		// => request의 값을 bingingservlet2에서 한번더 쓸 수 있도록 전달해줌
//		request.getRequestDispatcher("/BindingServlet2").forward(request, response);

		
		// & 한 페이지에서 다른페이지로 데이터를 전달할때 가장 흔히쓰는 패턴
		request.setAttribute("c", 3);
		request.getRequestDispatcher("/BindingServlet2").forward(request, response);
		
		// 2. 리다이렉트
		// - request를 전달하지않음(요청을 서버로 애초에 보내지않는다)
		// - 클라이언트 -> 서버로 이동하므로 컨텍스트 패스를 전달해야함
		
		//-----------------------------------------------
		// & 리다이렉트와 포워드의 차이 2
		// (1) 리다이렉트	: 다른 서블릿으로 이동시 파라미터 상에는 최종적인 urlmapping이 입력된다
		// (2) 포워드		: 다른 서블릿으로 이동해도 파라미터 상에 최초의 urlmapping이 입력된다
		
		// & 페이지 전환 주체에 따른 시각******************************
		// 1. 페이지 전환주체가 클라이언트면 리다이렉트며, 이경우 페이지 전환을 위해서는 다른 url에 접속해야한다
		// 즉, 리다이렉트를 사용하면 클라이언트가 요청한 최종적인 서블릿으로 이동하게 된다
		// 2. 페이지 전환 주체가 서버면 포워드로, 이경우 페이지 전환을 위해서 다른 url을 쓸 필요가 없기 때문에
		// 기존의 urlmapping을 유지하게된다
		
		// 리다이렉트 => 다른 url로 재접속하라고 명령, 회원가입같은 요청이 중복되면 안되는 경우에 사용(db에 저장되야하기 때문)
		// 포워드 => 모든 처리를 서버 내에서 하기 때문에 클라이언트의 url이 변동되지 않는다(특정 url이 노출되면 안될때 사용)
		
		// & 요청정보에 따른 시각******************************
		// 1. 리다이렉트는 요청한 페이지로 이동하면, 요청에 담긴 정보가 사라지기 때문에,
		// => 데이터를 수정해야하는 회원가입, 글쓰기 등에 사용된다
		// 2. 포워드는 요청한 페이지로 이동시, 기존의 요청정보가 그대로 전달되기 때문에,
		// 새로고침을 해도 글쓰기가 계속 중복등록되는 경우가 발생한다
		// => 글목록보기, 검색 등 데이터를 건드리지 않는 작업에 사용된다
//		response.sendRedirect("/01_Servlet/BindingServlet2");
		//-----------------------------------------------
		
	}
	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
