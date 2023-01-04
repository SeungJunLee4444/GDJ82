package service;

import java.io.IOException;
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
		
		// [상세보기 이동하는 서비스]
		
		Optional<String> optNo = Optional.ofNullable(request.getParameter("no"));
		long no = Long.parseLong(optNo.orElse("0"));	// null은 0으로 반환
		
		Board board = BoardDao.getInstance().selectBoardByNo(no);
		
		
		 if(board == null) {
				try {
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('조회 결과가 없습니다.')");
					out.println("history.back()");
					out.println("</script>");
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			// 조회 결과가 있는 경우
			} else {
				request.setAttribute("board", board);
				return new ActionForward("board/detail.jsp", false);
			}
			
		}

}
