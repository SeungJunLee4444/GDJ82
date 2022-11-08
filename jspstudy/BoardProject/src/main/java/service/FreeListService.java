package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import repository.FreeDAO;

public class FreeListService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// # db에서 가져오기
		request.setAttribute("boards", FreeDAO.getInstance().selectAllBoards());
		
		// # hit처리
		HttpSession session = request.getSession();
		if(session.getAttribute("updateHit") != null) {
			session.removeAttribute("updateHit");
		}
		
		// # 이동
		ActionForward af = new ActionForward();
		af.setView("/list.jsp");
		af.setRedirect(false);
		
		return af;
	}

}
