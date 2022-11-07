package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Free;
import repository.FreeDAO;

public class FreeAddService implements FreeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		// 요청 파라미터
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		// & ip처리 : request를 이용해 ip값을 얻어서 db에 저장
		String ip = request.getRemoteAddr();
		
		// dto에 저장
		Free free = new Free();
		free.setTitle(title);
		free.setWriter(writer);
		free.setContent(content);
		free.setIp(ip);
		
		// db로 보내기
		int result = FreeDAO.getInstance().insertBoard(free);
		
		// 결과창으로 이동

		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/list.do'");
			out.println("</script>");
		} 
		
		out.close();
		return null;
	}

}
