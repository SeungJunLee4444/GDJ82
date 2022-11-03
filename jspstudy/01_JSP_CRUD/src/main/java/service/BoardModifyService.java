package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardModifyService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// BoardModifyService : 게시글 수정하는 요청
		
		// # 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		
		// # db에 보낼 Board 생성 (* insert와 동일)
		
		// * insert, update의 특징
		// => db에 새로운 데이터를 저장하기 때문에, 
		// 요청에 저장된 파라미터값을 dto에 저장해서 db에 접근한다
		
		// * insert와 update
		// (1) insert : 새로운 번호를 시퀸스로 받는다
		// (2) update : 기존의 boardno를 파라미터로 받아서 그대로 재활용한다
		// - 이유 : 수정을 하는데, 쿼리문에서 nextval 처리를 할 수는 없기 때문
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setBoardNo(boardNo);
		
		// # DB로 Board board 보내기(수정)---------------------------------------=> dao, db
		int result = BoardDao.getInstance().updateBoard(board);
		
		// # 수정 성공 / 실패
			PrintWriter out = response.getWriter();
			
			// 1) 수정 성공 : boardNo를 파라미터로 전송해서 수정된 상세보기 화면으로 이동한다
			if(result > 0) {
				out.println("<script>");
				out.println("alert('게시글이 수정되었습니다.')");
				out.println("location.href='" + request.getContextPath() + "/board/detail.do?boardNo=" + boardNo + "'");
				out.println("</script>");
			} else {
			// 2) 수정 실패 : 돌아가기
				out.println("<script>");
				out.println("alert('게시글 수정이 실패했습니다.')");
				out.println("history.back()");
				out.println("</script>");			
			}
			out.close();
		
		
		return null;
	}

}
