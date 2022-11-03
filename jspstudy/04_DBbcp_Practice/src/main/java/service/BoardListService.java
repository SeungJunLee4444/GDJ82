package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		// [BoardListServide : 게시판 목록창]
		// - 기능 : 게시판 전체목록으로 이동하는 서비스
		// - 경로 :
		// (1) 최초 index.jsp에서 이동
		// (2) 상세, 수정창에서 목록으로 돌아오는 경우
		// => list.do가 단순이동이 아닌 서비스인 이유
		
		
		// # 상세보기에 올려준 updateHit 속성 제거
		
		
		// # board/list.jsp로 전달할 데이터를 request에 저장해서 포워드
		// 1) 전체를 조회
		request.setAttribute("boards", BoardDAO.getInstance().selectAllBoards());
		// 2) 전체 게시글 수
		request.setAttribute("count", BoardDAO.getInstance().selectAllBoardsCount());
		
		
		return null;
	}

}
