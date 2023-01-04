package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import repository.BoardDAO;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// 상세보기에서 session에 올려 둔 updateHit 속성을 제거
		HttpSession session = request.getSession();
		if(session.getAttribute("updateHit") != null) {
			session.removeAttribute("updateHit");
		}
		
		// board/list.jsp로 전달할 데이터를 request에 저장한 뒤 포워딩
		request.setAttribute("boards", BoardDAO.getInstance().selectAllBoards());
		request.setAttribute("count", BoardDAO.getInstance().selectAllBoardsCount());
		
		return new ActionForward("board/list.jsp", false);
		
	}

}
