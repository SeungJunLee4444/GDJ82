package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class RectangleService implements ShapeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 파라미터
		double width = Double.parseDouble(request.getParameter("width"));
		double height = Double.parseDouble(request.getParameter("height"));
		
		// 포워드 할 데이터
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("area", width * height);
		request.setAttribute("shape", "rectangle");
		
		// 어디로 어떻게?
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp");
		af.setRedirect(false);
		return af;
		
	}

}
