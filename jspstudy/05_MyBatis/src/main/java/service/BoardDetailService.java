package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardDetailService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// db로 boardNo 보내고 해당 board 받아오기
		Board board = BoardDao.getInstance().selectBoardByNo(boardNo);
		
		
		
		
		// 요청 파라미터로 boardNo가 전달되지 않았다면 목록보기로 이동  
		
		// & 상황에 맞는 응답 만들기
		if(board == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + boardNo + "번 게시글의 정보가 없습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
			out.close();
		} else {
			// request에 Board board 저장하기
			request.setAttribute("board", board);
			// detail.jsp로 포워딩
			ActionForward af = new ActionForward();
			af.setView("/board/detail.jsp");
			af.setRedirect(false);
			return af;
		}
		return null;	// => 자바 문법때문에 남겨둔 코드로 실행되지 않는 코드다
	}

}
