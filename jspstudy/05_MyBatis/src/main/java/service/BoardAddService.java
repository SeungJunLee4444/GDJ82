package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// db로 보내기 위해 Board board 생성
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		
		// db Board board 보내기(스압)
		int result = BoardDao.getInstance().insertBoard(board);
		
		// 삽입 성공/실패 응답
		PrintWriter out = response.getWriter();
		if(result > 0) {	// 성공
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
			
		} else {			// 실패
			out.println("<script>");
			out.println("alert('게시글 등록에 실패했습니다')");
			out.println("history.back()");	// = history.go(-1);
			out.println("</script>");
		}
		
		out.close();

		return null;	// => 작동한다, 컨트롤러로 null을 반환, 컨트롤러는 redirect&포워드 모두 수행하지 않는다
	}
	
	

}
