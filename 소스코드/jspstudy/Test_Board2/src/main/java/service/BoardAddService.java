package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// & insert.jsp에서 전달한 파라미터는 title, name, content
		// & 쿼리문에서 순번과 시간을 처리했음
		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board board = new Board();
		board.setTitle(title);
		board.setName(name);
		board.setContent(content);
		
		int result = BoardDao.getInstance().insertBoard(board);
		
		// # 파라미터를 전달할 필요없으니 리다이렉트이동
		return new ActionForward("board/insertResult.jsp?res=" + result, true);
		
		// * result를 전달한 이유 : 전달값의 크기에 따라 결과창에서 스크립트로 성공/실패여부를 표현하기 위
	}

}
