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
	
		// # 요청-----------------------------------------------------------------------
		// #BoardDetailService : 상세화면 
		// - list.do에서 파라미터로 boardNo값을 받아왔다
		
		// # 요청 파라미터
		// - boardNo는 not null이기 때문에, optional을 통한 null처리가 필요하다
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// => 해석 
		// (1) 파라미터값 boardNo는 null일 수 있다
		// (2) boardNo가 null일 경우 0으로 변환한다
		// (3) boardNo는 int로 저장한다(파라미터는 String타입 boardNo는 실제로 int타입
		
		// # DB로 boardNo를 보내서 해당 Board 받아오기
		Board board = BoardDao.getInstance().selectBoardByNo(boardNo);
		
		// # 응답------------------------------------------------------------------------
		// # 요청한 boardNo가 전달되지 않았거나, 존재하지않는경우
		// - ex) 예를들어 텍스트로 요청했다거나? 하면 생길수 있는 경우일듯?
		
		// 1) 반환받은 게시글이 없는경우에는 목록으로 돌아가고, 경로를 반환하지않는다
		if(board == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + boardNo + "번 게시글의 정보가 없습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
			out.close();
		} else {
		// 2) 반환받은 게시글이 있는경우, request에 board 속성의 값으로 저장 => detail은 board 속성값이 필요하다
		// 이후 포워드 방식으로 상세보기창으로 이동(파라미터를 전달
			request.setAttribute("board", board);
			ActionForward af = new ActionForward("/board/detail.jsp", false);
			return af;
		}
		return null;
		
		
	
	}

}
