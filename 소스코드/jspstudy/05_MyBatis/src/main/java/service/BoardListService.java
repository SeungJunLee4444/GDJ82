package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// #request에 게시글 목록 저장
		request.setAttribute("boards", BoardDao.getInstance().selectAllBoards());
		
		// # list.jsp로 포워딩(경로 저장)
		ActionForward af = new ActionForward();
		af.setView("/board/list.jsp");
		af.setRedirect(false);
		
		return af;
	}

}
