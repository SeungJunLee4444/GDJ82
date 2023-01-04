package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// # selectAllList 메서드로 얻은 list타입 boards 값을 요청에 저장
		request.setAttribute("boards", BoardDao.getInstance().selectAllList());
		
		// # list.jsp로 포워딩
		ActionForward af = new ActionForward();
		af.setView("/board/list.jsp");
		af.setRedirect(false);
		
	
		return af;
	}

}
