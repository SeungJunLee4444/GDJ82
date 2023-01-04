package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.BoardDao;

public class BoardRemoveService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// db로 boardNo 보내기(삭제)
		int result = BoardDao.getInstance().deleteBoard(boardNo);
		
		// 성공실패 알림
		PrintWriter out = response.getWriter();
		if(result > 0) {	// 성공
			out.println("<script>");
			out.println("alert('게시글이 삭제되었습니다')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
			
		} else {			// 실패
			out.println("<script>");
			out.println("alert('게시글 삭제에 실패했습니다')");
			out.println("history.back()");	// = history.go(-1);	=> 이전 페이지로 돌아가기(사실상 원래페이지로 돌아가기?)
			out.println("</script>");
		}
		
		
		return null;
	}

}
