package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class AdderService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 파라미터
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));
		
		// 직접 응답을 만들기
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + a + "+" + b + "=" + (a + b) + "')");
		out.println("history.back()");
		out.println("</script>");
		out.close();
		
		// 직접 응답한 경우에는 ActionForward를 반환하지 않는다!
		return null;
		
	}

}
