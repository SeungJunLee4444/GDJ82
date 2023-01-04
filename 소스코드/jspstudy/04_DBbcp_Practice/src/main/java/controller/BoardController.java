package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardListService;
import service.BoardService;

// [컨트롤러 : 컨트롤러는 서블릿파일로 생성한다]

// # 웹 서블릿 어노테이션 : @webservlet을 통해 web.xml 파일 작성없이 mapping이 가능해진다
// - 기능 : 서블릿 접근 경로 지정

// * 복수의 요청을 하나의 서블릿 컨트롤러로 처리하는법 : *.임의의이름
@WebServlet("*.do")	
// - 설명 : .do로 끝나는 모든 요청을 이 서블릿 페이지에서 처리한다는 뜻

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// # 요청,응답 인코딩
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		// => 응답을 html 파일로 할것이다
		
		//----------------------------------------------------------------------------------------------------------
		
		
		// # urlMapping 얻기 
		// (1) 컨텍스트 + urimapping
		String requestURI = request.getRequestURI();				
		// (2) 컨텍스트만
		String contextPath = request.getContextPath();
		// (3) urlmapping만 얻기
		String urlMapping = requestURI.substring(contextPath.length());
		
		// - 결과 : urlmapping값만 얻어낼 수 있다
		
		// ######################################################################
		// & url 주소 메서드들
		// (1) getRequestURI()  : 컨텍스트 패스 + urlmapping값  ex) board/list.do
		// (2) getContextPath() : 컨텍스트 패스 값만 가져온다   ex) 컨텍스트패스
		// (3) getRequestURL()  : 전체 경로를 가져온다          ex) http://localhost:9090/컨텍스트패스/board/list.do
		
		// * uri : 컨텍스트패스 + urlmapping값을 의미
		// * url : 전체 주소를 의미
		// * urlmapping : url 주소의 매핑값(요청)을 의미
		// ######################################################################
		
		//----------------------------------------------------------------------------------------------------------
		
		// # 서비스 인터페이스(BoardService)
		BoardService service = null;
		
		// # 경로 인터페이스(ActionForward)
		ActionForward af = null;
		
		//----------------------------------------------------------------------------------------------------------
		
		// # 서비스 선택
		switch(urlMapping) {
		
		// 1) 게시판 이동 list.do 요청 
		// - boardlistservice 서비스 선택
		case "list.do" : 
			service = new BoardListService();
			break;
		
		// # wrtie.jsp 게시판 작성페이지로 단순이동하는 요청
		// - 서비스 생성x, 직접 경로 작성
		
		}
		
		// # 서비스 실행
		
		
		// # 이동
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
