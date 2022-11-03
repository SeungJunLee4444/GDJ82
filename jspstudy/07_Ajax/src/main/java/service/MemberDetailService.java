package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberDetailService implements MemberService {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("memberNo"));
		int memberNo = Integer.parseInt(opt.orElse("0"));
		
		// 응답 데이터 형식 JSON
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 만들기
		/*
			조회된 회원이 있는 경우
			{
				"exists": true,
				"member": {
					"memberNo": 3,
					"id": "user3",
					"name": "회원3",
					"gender": "F",
					"grade": "bronze",
					"address": "yeosu"
				}
			}
			
			조회된 회원이 없는 경우
			{
				"exists": false
			}
		*/
		Member member = MemberDao.getInstance().selectMemberByNo(memberNo);
		boolean exists = member != null;
		
		JSONObject obj = new JSONObject();
		obj.put("exists", exists);
		if(exists) {
			obj.put("member", new JSONObject(member));
		}
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.close();
		
	}

}