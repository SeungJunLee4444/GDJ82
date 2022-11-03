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

// # 웹서블릿 어노테이션 : 하나의 서블릿이 복수의 요청 처리할 수 있도록 *.do 처리
@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// # 컨트롤러 : 서블릿 컨트롤러에서 제일 먼저 요청을 받게되며, 해당 서비스를
		// 조회해서 실행시킨다
		
		// # 요청 / 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// # 요청 확인
		String requestURI = request.getRequestURI();				
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// & 메서드 설명---------------------------------------------------------------------&
		// (1) getRequestURI()  : 컨텍스트패스 + URLMapping값
		// (2) getContextPath() : 컨텍스트 패스값만
		//-----------------------------------------------------------------------------------&
		
		// & URI와 URL 개념------------------------------------------------------------------&
		// URI : 컨텍스트 패스 + URLMapping
		// URL : 전체 주소
		//-----------------------------------------------------------------------------------&
		
		// # BoardService 객체
		BoardService service = null;
				
		// # ActionForward 객체
		ActionForward af = null;
		
		// # 요청에 따른 서비스 선택
		// * 비즈니스 로직/단순이동의 구분 : db에 접근하냐 안하냐의 차이
		switch(urlMapping) {
		// 1) 비즈니스 로직 : 서비스 내에 actionforward 경로가 생성되있음
		// (1) 목록 요청
		case "/board/list.do" :
			service = new BoardListService();
			break;
			
		// (2) 상세화면 요청
		case "/board/detail.do":
			service = new BoardDetailService();
			break;
			
		// (3) 게시글 추가 요청
		case "/board/add.do":
			service = new BoardAddService();
			break;
			
		// (4) 편집창 이동 요청 : 단순한 이동이 아닌 boardNo를 파라미터로 전달하여, 해당 번호의 데이터를 db에서 가져와야하기 때문 
		case "/board/edit.do":
			service = new BoardEditService();
			break;
			
		// (5) 게시글 수정 요청
		case "/board/modify.do":
			service = new BoardModifyService();
			break;
			
		// (6) 게시글 삭제하기
		case "/board/remove.do":
			service = new BoardRemoveService();
			break;
		
		
		// 2) 단순이동(포워딩) : service값이 null값, 직접 actionforward 경로를 생성
		// (1) 수정화면 이동 : 단순이동
		// * 컨트롤러 이상으로 작업할 필요x(db에 접근안하기 떄문, 서비스는 실행x, 만든 경로를 따라 이동)
		case "/board/write.do":
		af = new ActionForward();
		af.setView("/board/write.jsp");
		af.setRedirect(false);
		break;
		}
		
		
		// # 선택된 서비스 실행 
		// - 설명 : 서비스들은 excute 메서드를 실행하면 actionforward 경로를 반환한다
		try {
			if(service != null) {
				af = service.execute(request, response);	// 해석 : excute 메서드를 실행하면 actionforward 객체를 반환
			} // 비즈니스 로직이라면
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// # 이동
		if(af != null) {	// 해석 : 서비스가 실행되었다면
			// 1) 리다이렉트 이동 : actionforward가 true일 경우
			if(af.isRedirect()) {
				response.sendRedirect(af.getView());
			} else {
			//2 ) 포워드 이동 : actionforward가 false값일 경우(* 특징 : 파라미터 전달)
				request.getRequestDispatcher(af.getView()).forward(request, response);
			}
		}
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
