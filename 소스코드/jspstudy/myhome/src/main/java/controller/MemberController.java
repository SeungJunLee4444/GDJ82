package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("*.me")

public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 요청확인
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// # MemberServiceImpl 객체
		MemberService service = new MemberServiceImpl();
		
		// actionforward 객체
		ActionForward af = null;
		
		// # 요청에 따른 service 선택
		
		switch(urlMapping) {
		// 1) 서비스 요청
		case "/member/login.me" : 
			af = service.login(request, response);	// 로그인 성공시 actionforward 경로로 이동/ session에 login을 저장해서 login.jsp 창으로 돌아온다
			break;
		case "/member/logout.me" :
			af = service.logout(request, response);
			break;
			
			
		// 2) 회원가입----------------------------------------------------------------------------
		// (1) 회원가입 요청 이동
		case "/member/join.me" :
			af = new ActionForward();
			af.setView("/member/join.jsp");
			break;
		// (2) 회원등록 요청
		case "/member/register.me" : 
			service.register(request, response);	// & af없이 register 메서드 내부에서 직접 이동
			break;
		//----------------------------------------------------------------------------------------
			
		// 3) 회원 탈퇴
		case "/member/cancel.me" :
			service.cancel(request, response);		// & af없이 register 메서드 내부에서 직접 이동
			break;
		
		//###########################################################################################	
		// # 매핑 잘못 작성한 경우
		default :
			System.out.println("맵핑을 확인하세요");
		//###########################################################################################		
	
		}
	
		
		
		
	
		
		// 어디로 어떻게 이동하는가																			=> null값을 반환하면 아무런 이동도 하지않는다
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
