package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.FreeAddService;
import service.FreeDetailService;
import service.FreeListService;
import service.FreeModifyService;
import service.FreeRemoveService;
import service.FreeService;



@WebServlet("*.do")

public class FreeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// # 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// # 요청확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// # boardservice 객체
		FreeService service = null;
		
		// # actionforward 객체
		ActionForward af = null;
		
		// # 서비스선택
		switch(urlMapping) {
		
		case "/list.do" :
			service = new FreeListService();
			break;
			
			
		// # 상세화면 서비스 : 비즈니스 로직 -----------------------------
		case "/detail.do" :
			service = new FreeDetailService();
			break;
		
			// # 추가 창 이동 : 단순이동 -------------------------------------
		case "/insertPage.do" : 
			af = new ActionForward();
			af.setView("/insertPage.jsp");
			af.setRedirect(false);
			break;
		
		// # 추가서비스 호출 : 비즈니스 로직
		case "/insert.do" : 
			service = new FreeAddService();	// af반환
			break;
		
		// # 수정서비스 : 비즈니스 로직 --------------------------------------
		case "/modify.do" :
			service = new FreeModifyService();
			break;
		
		// # 삭제 서비스 : 비즈니스 로직 -------------------------------------
		case "/remove.do" :
			service = new FreeRemoveService();
			break;
			
		}
		
		
		// # service 실행 --------------------------------------
		try {
			if(service != null) {
				af = service.execute(request, response);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		// # 이동 ----------------------------------------------
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
