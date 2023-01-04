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
import service.BoardListService;
import service.BoardService;


@WebServlet("*.do")
// => 굳이 do를 사용안해도된다(임의의 명칭일뿐, 내가 원하는 이름대로 통일하면된다)
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// # 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// # 요청확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// # BoardService 객체
		BoardService service = null;
		
		// # ActionForward 객체
		ActionForward af = null;
		
		//-----------------------------------------------------------------------------------------------
		
		//# 요청에 따른 service 선택
		// - 비즈니스 로직
		switch(urlMapping) {
		case "/board/list.do" : 
			service = new BoardListService();
			break;
		case "/board/add.do" :
			service = new BoardAddService();
			break;
		
		// * 상세보기 요청
		case "/board/detail.do" :
			service = new BoardDetailService();
			break;
			
		// - 단순이동
		case "/board/insert.do" :
			af = new ActionForward();
			af.setView("/board/insert.jsp");
			af.setRedirect(false);
			break;
		}
	
		
	
		
		// # 선택된 service 실행
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//-----------------------------------------------------------------------------------------------
		
		// # 이동
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
