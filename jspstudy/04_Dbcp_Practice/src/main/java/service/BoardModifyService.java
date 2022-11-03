package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		long no = Long.parseLong(request.getParameter("no"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardDTO board = new BoardDTO();
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);
		
		int res = BoardDAO.getInstance().updateBoard(board);
		
		// 수정 결과 페이지(updateResult.jsp)로 수정 결과(res)를 보냄
		// 리다이렉트는 request 전달이 없으므로
		// res값을 직접 전달하면서 이동해야 함(?res=0 또는 ?res=1)
		// 수정완료 후 상세보기로 이동할 수 있도록 게시글 번호(no)도 함께 전달
		return new ActionForward("board/updateResult.jsp?res=" + res + "&no=" + no, true);
		
	}

}
