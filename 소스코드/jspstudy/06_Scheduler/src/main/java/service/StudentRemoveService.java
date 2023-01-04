package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentRemoveService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 요청 파라미터(학번)
		Optional<String> opt = Optional.ofNullable(request.getParameter("stuNo"));
		int stuNo = Integer.parseInt(opt.orElse("0"));
		
		// db에 stuno전달(삭제)
		int result = StudentDAO.getInstance().deleteStudent(stuNo);
		
		// 삭제 성공, 실패
		PrintWriter out = response.getWriter();
		if(result > 0 ) {
			out.println("<script>");
			out.println("alert('학생이 삭제되었습니다')");
			out.println("location.href = '" + request.getContextPath() + "/student/list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('학생 삭제에 실패했습니다')");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		
		return null;	
	}

}
