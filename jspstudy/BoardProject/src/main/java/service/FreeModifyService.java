package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeModifyService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// # 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		long freeNo = Long.parseLong(request.getParameter("freeNo"));
		
		// # dto에 저장
		Free free = new Free();
		free.setTitle(title);
		free.setContent(content);
		free.setFreeNo(freeNo);
		
		// # db로 전송
		int result = FreeDAO.getInstance().updateBoard(free);
		
		// # 응답
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 수정되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/detail.do?freeNo=" + freeNo + "'");
			out.println("</script>");
		} 
		out.close();
		
		return null;
	}

}
