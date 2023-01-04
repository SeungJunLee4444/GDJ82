package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import repository.MemberDao;

public class MemberListService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 응답 데이터 형식(JSON)
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 만들기
		/*
			{
				"count": 3,
				"members": [
					{
						"memberNo": 3,
						"id": "user3",
						"name": "회원3",
						"gender": "F",
						"grade": "bronze",
						"address": "yeosu"
					},
					{
						"memberNo": 2,
						"id": "user2",
						"name": "회원2",
						"gender": "M",
						"grade": "silver",
						"address": "seoul"
					},
					{
						"memberNo": 1,
						"id": "user1",
						"name": "회원1",
						"gender": "F",
						"grade": "gold",
						"address": "jeju"
					}
				]
			}
		*/
		JSONObject obj = new JSONObject();
		obj.put("count", MemberDao.getInstance().selectAllMembersCount());
		obj.put("members", MemberDao.getInstance().selectAllMembers());
		
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());  // JSON 문자열 응답
		out.close();
		
	}

}