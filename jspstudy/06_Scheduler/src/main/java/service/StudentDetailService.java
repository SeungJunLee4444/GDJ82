package service;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentDetailService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		// => 요청할 파라미터의 타입은 string
		// => 파라미터값이 null일 수 있으니 optional.ofNullable
		
		int stuNo = Integer.parseInt(opt.orElse("0")); // => null일 경우 0으로 반환
		
		// stuNo에 해당하는 select를 request에 저장
		request.setAttribute("student", StudentDAO.getInstance().selectStudentByNo(stuNo));
		// => 요청에 데이터 저장
		
		// student/detail.jsp로 포워딩
		return new ActionForward("/student/detail.jsp", false);
		// => view로 경로 설정, false로 이동방법 설정(false는 포워드)
	}

}
