package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberRemoveService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터(memberNo는 pk로 null이여서는 절대 안된다, 예외를 0처리로 만드는 코드) <===========컨트롤러
		
	
		try {
			Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		int	memberNo = Integer.parseInt(opt.orElse("0"));
			
			
		// 삭제============================================================================> dao
		int result = MemberDao.getInstance().deleteMember(memberNo);
		
		// 응답 데이터 형식 : json
		response.setContentType("application/json");
		
		// 응답 데이터
		/* 
		 성공 : {"isSuccess" : true}
		 실패 : {"isSuccess" : false}
		 */
		
		JSONObject obj = new JSONObject();
		obj.put("isSuccess", result > 0);
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.close();

		} catch (NumberFormatException e) {
			response.setContentType("text/plain; charset=utf-8");
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println("잘못 입력했습니다. 숫자만 가능합니다");
			out.close();
		}
	}

}
