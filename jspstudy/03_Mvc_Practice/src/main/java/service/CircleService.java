package service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class CircleService implements ShapeService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 요청 파라미터
		double radius = Double.parseDouble(request.getParameter("radius"));
		
		// 포워드 할 데이터
		request.setAttribute("radius", radius);
		request.setAttribute("area", Math.PI * Math.pow(radius, 2));
		request.setAttribute("shape", "circle");
		
		// 어디로 어떻게?
		ActionForward af = new ActionForward();
		af.setView("views/output.jsp");
		af.setRedirect(false);
		return af;
		
	}

}
