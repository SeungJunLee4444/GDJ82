package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardListService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// # BoardListService : 게시판 목록을 보여주는 서비스
		// - 요청되는 경우
		// (1) 웰컴페이지에서 list.do 요청
		// (2) 글 상세화면에서 게시판으로 이동
		// (3) 글 추가화면에서 게시판으로 이동
		// (4) 글 삭제화면에서 게시판으로 이동
		
		// # request에 게시글 전체목록을 저장
		
		request.setAttribute("boards", BoardDao.getInstance().selectAllBoards());
		
		
		// # 이동(포워딩)
		ActionForward af = new ActionForward("/board/list.jsp", false);
		
		
		return af;
	}

}
