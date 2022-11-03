package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.NaverCaptchaServiceimpl;
import service.NaverCaptureService;


@WebServlet("*.do")

public class NaverController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// ========================================================================================
		//[1] 사전준비 단계
		// # 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		// # 요청 확인
		String requestURI = request.getRequestURI();				// 컨텍스트패스 + urlmapping
		String contextPath = request.getContextPath();				// 컨텍스트 패스
		String urlMapping = requestURI.substring(contextPath.length());	// urlmapping
				
	
		
		
		// NaverCaptureimpl 객체 생성		
		NaverCaptureService service = new NaverCaptchaServiceimpl(); 
		
		// # ActionForward 객체
		ActionForward af = null;
		
		// ========================================================================================
		// [2] 서비스 요청및 실행단계
		
		// # 요청에 따른 Service 선택
		switch(urlMapping) {
		case "/member/loginPage.do" :
			// 캡차키 발급요청
			String key = service.getCaptchaKey();
			
			// 캡차이미지 발급요청
			Map<String, String> map = service.getCaptchaImage(request, key);
			request.setAttribute("dirname", map.get("dirname"));
			request.setAttribute("filename", map.get("filename"));
			request.setAttribute("key", map.get("key"));
		
			// actionforward 생성
			af = new ActionForward("/member/login.jsp", false);
			break;
		case "/member/refreshCaptcha.do" :
			service.refreshCaptcha(request, response);	// 요청, 응답에 저장된 데이터 전송(ajax이동, 페이지이동없이 이동, actionforward x)
			break;
			
		case "/member/validateCaptcha.do":
			boolean result = service.validateUserInput(request);
			if(result) {
				af = new ActionForward("/member/success.jsp", false);
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('자동입력 방지문자를 확인하세요');");
				out.println("location.href='" + request.getContextPath() + "/member/loginPage.do';");	
				// loginPage.do => 잘못입력했을경우 새로운 키값, 키값에 따른 새로운 이미지를 발급요청하고, login.jsp로 이동한다
				// 이때 dirname, filename, key 변수저장
				out.println("</script>");
				out.close();
			}
		}
		
		// # 서비스의 메서드가 복수가 때문에 모양이 변화---------------------(%change!%)
	
		
		
		
		// ========================================================================================
		// [3] 이동단계
		
		// # 어디로 어떻게 이동하는가?
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
