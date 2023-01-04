package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeDetailService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// # 파라미터 : boards의 freeNo를 파라미터로 전달받음
		Optional<String> opt = Optional.ofNullable(request.getParameter("freeNo"));
		long freeNo = Long.parseLong(opt.orElse("0"));
		
		// # db에 freeNo 보내기
		Free free = FreeDAO.getInstance().selectBoardByNo(freeNo);
		
		
		// # 조회수 증가 : 목록에서 상세보기로 넘어가는 경우에만
		String referer = request.getHeader("referer");  
		HttpSession session = request.getSession();
		if(referer.endsWith("list.do") && session.getAttribute("updateHit") == null) {  
			FreeDAO.getInstance().updateHit(freeNo);
			session.setAttribute("updateHit", "done");
		}
		
		// # 예외사항
		if(free == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + freeNo + "번 게시글의 정보가 없습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
			out.close();
		} else {
			// # free를 속성으로 전달
			request.setAttribute("free", free);
			
			// # 경로
			ActionForward af = new ActionForward();
			af.setView("/detail.jsp");
			af.setRedirect(false);
			return af;
		}
		return null;
	}

}
