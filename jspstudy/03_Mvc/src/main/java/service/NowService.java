package service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public class NowService implements MyService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// NowService가 하는 일
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("a h:mm:ss");
		String now = sdf.format(date);
		
		// 뷰(Jsp)로 전달할 데이터는 request에 속성으로 저장한다.
		request.setAttribute("result", now);
		
		// 어디로 갈 것인가?(응답 Jsp 명시)
		// 어떻게 갈 것인가?(리다이렉트 또는 포워드)
		ActionForward actionForward = new ActionForward();
		actionForward.setView("views/result.jsp");
		actionForward.setRedirect(false);  // 포워드하겠다!
		
		// ActionForward 반환
		return actionForward;
		
	}
	
}
