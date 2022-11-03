package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentListService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StudentDAO dao = StudentDAO.getInstance();
		// => dao 클래스의 메서드 호출
		
		// request에 필요한 정보 저장
		request.setAttribute("students", dao.selectAllStudents());
		request.setAttribute("count", dao.selectAllStudentsCount());
		request.setAttribute("average", dao.selectAllStudentsAverage());
		
		// student/list.jsp로 포워딩(이동)
		return new ActionForward("/student/list.jsp", false);
		// => 롬복으로 @AllArgsConstructor
	}
}
