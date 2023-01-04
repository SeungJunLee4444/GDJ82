package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardEditService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			// # BoardEditService : 편집할 창을 가져오는 메서드
		
			// * boardNo를 파라미터로 전달하여 수정에 필요한 데이터를 db에서 가져와야하기 때문에,------------
			// 코드는 detailservice와 완전히 동일하다
			// 차이점 : 경로가 edit.jsp라는 점이다
			//-----------------------------------------------------------------------------------------------
		
			// # 요청 파라미터
			Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
			int boardNo = Integer.parseInt(opt.orElse("0"));
			
			// request에 boardNo에 해당하는 Board board 저장하기
			request.setAttribute("board", BoardDao.getInstance().selectBoardByNo(boardNo));
			
			// board/edit.jsp로 포워딩
			ActionForward af = new ActionForward();
			af.setView("/board/edit.jsp");
			af.setRedirect(false);
			return af;


	}

}
