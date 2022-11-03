package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardEditService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// request에 boardNo에 해당하는 Board board에 저장하기
		request.setAttribute("board", BoardDao.getInstance().selectBoardByNo(boardNo));
		
		// board/edit.jsp 포워딩
		ActionForward af = new ActionForward();
		af.setView("/board/edit.jsp");
		af.setRedirect(false);
		return af;
		
		
	}

}
