package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDao;

public class MemberModifyService implements MemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String grade = request.getParameter("grade");
		String address = request.getParameter("address");
		
		// db로 보낼 Member member 만들기(dto)
		Member member = Member.builder()
				.id(id)
				.name(name)
				.gender(gender)
				.grade(grade)
				.address(address)
				.build();

		// => 해당 파라미터의 값이 null일 경우 예외 발생
		
		// 수정결과
		int result = 0;
		try {
			// 수정
			result = MemberDao.getInstance().updateMember(member);
			
			// 성공 응답 데이터 타입은 json
			response.setContentType("application/json; charset=utf-8");
			
			/* 
			   // 수정성공 응답데이터
			  {"isSucess" : true}
			  
			   
			   // 수정실패 응답데이터
			  {"isSucess" : false}
			 */
			
			JSONObject obj = new JSONObject();
			obj.put("isSuccess", result > 0);
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.close();
			
			// # 아무런 값도 입력하지 않은 경우 : 예외발생
		} catch (Exception e) {
			// 예외발생 시 응답 : unique 제약조건 위반, not null위반, 
			response.setContentType("text/plain; charset=utf-8");
			
			// 응답
			PrintWriter out = response.getWriter();
			out.println("회원정보가 수정되지 않았습니다. \n입력정보를 획인하세요");
			out.close();
			
		}
		
	}

}
