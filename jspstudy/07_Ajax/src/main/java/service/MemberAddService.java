package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberAddService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		String address = request.getParameter("address");
		
		// 삽입할 Member member 생성
		Member member = Member.builder()
				.id(id)
				.name(name)
				.gender(gender)
				.grade(grade)
				.address(address)
				.build();
		
		// 삽입
		int result = 0;
		try {
			
			result = MemberDao.getInstance().insertMember(member);
			
			// 응답 데이터 형식 (JSON)
			response.setContentType("application/json; charset=UTF-8");
			
			// 응답 데이터
			/*
				삽입 성공
				{
					"isSuccess": true
				}
				
				삽입 실패
				{
					"isSuccess": false
				}
			*/
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
			
			
		} catch (Exception e) {
			
			// 예외 처리 응답 ($.ajax()의 error 프로퍼티로 응답)
			
			// 예외 처리 응답 데이터 형식 : 일반 텍스트
			response.setContentType("text/plain; charset=UTF-8");
			
			// 예외 처리 응답
			PrintWriter out = response.getWriter();
			out.println("신규 회원 등록이 실패했습니다.\n입력 데이터를 확인하세요.");
			out.close();
			
		}
		
	}

}