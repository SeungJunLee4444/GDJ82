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

		// BoardRemoveService : 게시글 삭제하는 요청

		// # 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("boardNo"));
		int boardNo = Integer.parseInt(opt.orElse("0"));
		
		// # DB로 boardNo 보내기(삭제) : boardNo만 전달하면 되기 떄문에 dto가 필요없다 -----------------------------------*
		int result = BoardDao.getInstance().deleteBoard(boardNo);
		// * dto 없이도 계층간 이동이 가능하긴 하다
		// * dto : 복수의 값들을 하나의 클래스로 저장하여 계층간 이동이 가능해진다(자바빈)
		
		// # 삭제 성공 / 실패
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 삭제되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");	// - 삭제하고 나서는 게시글 목록으로 돌아간다
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 삭제가 실패했습니다.')");
			out.println("history.back()");  // history.go(-1)
			out.println("</script>");
		}
		out.close();
		
		return null;  // 컨트롤러로 null을 반환하면 컨트롤러는 리다이렉트&포워드 모두 수행하지 않음
	}

}
