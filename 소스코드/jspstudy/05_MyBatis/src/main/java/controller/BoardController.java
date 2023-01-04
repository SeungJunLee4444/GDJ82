package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardEditService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.BoardService;


@WebServlet("*.do")
// => 굳이 do를 사용안해도된다(임의의 명칭일뿐, 내가 원하는 이름대로 통일하면된다)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// boardservice 객체
		BoardService service = null;
		
		// actionforward 객체
		ActionForward af = null;
		
		// 요청에 따른 service 선택
		// 1) 비즈니스 로직
		switch(urlMapping) {
		case "/board/list.do":
			service = new BoardListService();
			break;
		case "/board/detail.do" :
			service = new BoardDetailService();
			break;
			
		// 2) 단순이동(포워딩)
		case "/board/write.do" : 
			af = new ActionForward();
			af.setView("/board/write.jsp");
			af.setRedirect(false);
			
		case "/board/add.do" :
			service = new BoardAddService();
			break;
		case "/board/remove.do" :	// 
			service = new BoardRemoveService();
			break;
		case "/board/edit.do" :
			service = new BoardEditService();
			break;
		case "/board/modify.do" :
			service = new BoardModifyService();
			break;
		
			// & .do와 요청에 따른 서비스처리
			// => .do는 웹서블릿 하나로 여러 요청을 받기 위한 것
			// => urlmapping은 파일에서 확장명을 땐 값
			
		
		}
		
		// 선택된 service 실행
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 어디로 어떻게 이동하는가
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
