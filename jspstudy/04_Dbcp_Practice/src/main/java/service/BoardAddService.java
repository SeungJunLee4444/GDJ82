package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String ip = request.getRemoteAddr();
		
		BoardDTO board = new BoardDTO();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setIp(ip);
		
		int res = BoardDAO.getInstance().insertBoard(board);
		
		// 삽입 결과 페이지(insertResult.jsp)로 삽입 결과(res)를 보냄
		// 리다이렉트는 request 전달이 없으므로
		// res값을 직접 전달하면서 이동해야 함(?res=0 또는 ?res=1)
		return new ActionForward("board/insertResult.jsp?res=" + res, true);
		
	}

}
