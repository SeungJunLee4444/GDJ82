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

public class BoardController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청 확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length() + 1);
		
		// BoardService 선언
		BoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// 요청(urlMapping)에 따른 Service 생성
		switch(urlMapping) {
		// 비즈니스 로직이 있는 경우
		case "board/list.do":
			service = new BoardListService();
			break;
		case "board/detail.do":
			service = new BoardDetailService();
			break;
		case "board/add.do":
			service = new BoardAddService();
			break;
		case "board/edit.do":
			service = new BoardEditService();
			break;
		case "board/modify.do":
			service = new BoardModifyService();
			break;
		case "board/remove.do":
			service = new BoardRemoveService();
			break;
		// 비즈니스 로직이 없는 경우(단순이동)
		case "board/write.do":
			af = new ActionForward();
			af.setView("/board/write.jsp");
			af.setRedirect(false);  // 단순이동은 forward
			break;
		}
		
		// Service 실행
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 어디로 어떻게?
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
