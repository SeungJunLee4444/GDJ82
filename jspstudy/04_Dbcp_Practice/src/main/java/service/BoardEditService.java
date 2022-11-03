package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardEditService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		long no = Long.parseLong(opt.orElse("0"));
		
		BoardDTO board = BoardDAO.getInstance().selectBoardByNo(no);
		
		request.setAttribute("board", board);
		
		return new ActionForward("board/edit.jsp", false);
		
	}

}
