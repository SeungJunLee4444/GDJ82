package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDAO;

public class BoardRemoveService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		long no = Long.parseLong(optNo.orElse("0"));
		
		int res = BoardDAO.getInstance().deleteBoard(no);
		
		// 삭제 결과 페이지(deleteResult.jsp)로 삭제 결과(res)를 보냄
		// 리다이렉트는 request 전달이 없으므로
		// res값을 직접 전달하면서 이동해야 함(?res=0 또는 ?res=1)
		return new ActionForward("board/deleteResult.jsp?res=" + res, true);
		
	}

}
