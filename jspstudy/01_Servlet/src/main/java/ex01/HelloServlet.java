package ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/* 
 * [servlet]
 * - 동적웹페이지를 만들때 사용되는 자바 기반 웹 에플리케이션 프로그래밍 기술
 * - 웹 요청과 응답을 메서드 호출로 다룰수 있게 만듬
 * - 자바 클래스
 * - 웹 서버 안의 웹 컨테이너에 의해 실행
 * 
 * 
 * - 웹 화면을 만드는 자바 클래스
 * - jsp / servlet container에 의해서 실행(jvm이 아님)
 * - 서블릿을 실행하면 웹 브라우저가 실행됨
 * 
 * - 서블릿 컨테이너 : 
 * - 서블릿들의 생성, 실행, 파괴를 담당
 * - 서블릿을 작동시키는 역할(+관리) ex 톰캣
 * 
 * & was는 servlet을 포함하는 개념
 * - was는 웹 서버 + 웹 컨테이너로 구성되있으며, 웹서버로 정적 콘텐츠를, 웹 컨테이너를 이용해
 * 내부 로직을 거쳐 동적 페이지를 보여준다
 * - 자바는 웹 구현 기술로 서블릿(java ee의 클래스)를 사용하며, servlet를 관리하고 jsp파일을
 * 실행할 수 있게 만들어주는 것이 서블릿 컨테이너다
 * 
 * 

 * - url
 * 		- 형태 : 프로토콜://호스트:포트번호/컨텍스트패스/URLmAPPING(서블릿)
 * 		(1) 컨텍스트패스: 프로젝트 이름
 * 		(2) URLMapping	: 서블릿 이름
 * 		http://localhost:9090/01_Servlet/HelloServlet
 * 
 * 		& 컨텍스트패스(context path(root))
 * 		- was(톰캣)에서 웹 에플리케이션을 구분하기 위한 path
 * 		& URLMapping
 * 
 * 1) HttpServlet
 * - 모든 servlet 클래스는 httpservlet의 상속을 받는다
 * */


@WebServlet("/HelloServlet")	// URLMapping값

public class HelloServlet extends HttpServlet {	// Servlet 클래스는 HttpServlet를 상속받으면 된다
	/* 
	 * 1. 생성자(HelloServlet()
	 * 	- 가장 먼저 실행된다
	 * 	- 생성자 호출 뒤 init() 메서드가 호출			*1
	 * */
    public HelloServlet() {
        super();
        System.out.println("생성자");
        // TODO Auto-generated constructor stub
    }
    
    /* 
     * 2. 초기화(init)
     * 	- 각종 초기화 정보 실행
     *  - 서블릿 환경설정 처리
     *  - init() 메서드 호출 뒤 service() 메서드 호출	*2
     * 	- conf(config) 키워드는 초기화를 의미 
	*/
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init");
	}
	
	/* 
	 *	3. 서비스(service) 
	 * - 클라이언트의 요청마다 매번 호출
	 * - 처리 방식은 (1) 직접해결하거나, (2) doGET, doPOST를 사용해 처리
	 * => 대처로 (2)을 사용한다
	 * 
	 * (1) GET 방식 : doGET() 메서드 호출
	 * (2) POST 방식 : doPOST() 메서드 호출
	*/

	/**		// /** 자바독 : 주석아님?
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service");
		
		// * service 작업은 외울필요없다(없으면 알아서 처리됨)
		
		// service 메서드만 존재하는 경우 dGET(), doPOST() 가 자동으로 호출되지 않기 때문에
		// 코드를 직접 작성

		// 요청 메서드 확인 : request.getMethod()
		switch(request.getMethod()) {
		case "GET" : 
			doGet(request, response); break;	// 매개변수 request, response를 받아 doGet메서드의 매개변수에 던짐
		case "POST" :
			doPost(request, response); break;
		}
	}
	
	/* 
	 * 4. doGet
	 * 	- GET 방식의 요청을 처리
	 *  - & 주소창을 직접 이용해서 이동하는 방식을 get 방식이라 한다
	 *  	(1) ajax
	 *  	$.ajax({
	 *  		'type' : 'GET';
	 *  		'URL' : '01_Servlet/HelloServlet'
	 *  	})
	 *  	(2) 링크 
	 *  	<a href="http://localhost:9090/01_Servlet/HelloServlet">링크</a>
	 *  	(3) 폼
	 *  	<form method="GET" action="http://localhost:9090/01_Servlet/HelloServlet""> 또는 <form> (디폴트가 get)
	 *  	(4) js : location.href='http://localhost:9090/01_Servlet/HelloServlet'	?
	 * 		(5) js : open('http://localhost:9090/01_Servlet/HelloServlet')			?
	 * */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// response : 사용자에게 전달하겠다
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// => 응답하는 메서드		
	}

	/* 
	 * 5. doPost
	 *  - POST 방식의 요청을 처리
	 *  - 요청 메서드가 post인 경우
	 *  	(1) $.ajax({
	 *  		'type' : 'post'
	 *  		'URL' : '01_Servlet/HelloServlet'
	 *  })
	 *  	(2) 폼
	 *  	<form method="post" action="http://localhost:9090/01_Servlet/HelloServlet""> 또는 <form> (디폴트가 get)
	 
	 * 	& http://localhost:9090/01_Servlet/HelloServlet를 웹주소에 직접 입력하면 실행될 수 있나?
	 * - 톰캣이 켜져있으면 실행가능, 아니면 불가능
	 * 
	 * 
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// 요청과 응답정보를 doGet()로 넘김(실제 업무처리를 doGet()
		doGet(request, response);
	}

}
