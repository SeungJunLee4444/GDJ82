package service;

import java.io.PrintWriter;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDAO;

public class StudentModifyService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 요청 파라미터(학번)
		int stuNo = Integer.parseInt(request.getParameter("stuNo"));
		// => 기존에 있는 번호를 수정하는 것이니 null 처리를 할 필요가 없다
		
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
	
		
		// 파생값(수정된 성적의 ave와 grade값을 얻기 위해서)
		double ave = (kor + eng + math) / 3.0;		
		// => & 소수점 데이터 출력을 위해 3.0 또는 double 타입으로 캐스팅하기
		String grade;
		switch((int)ave / 10) {
		case 10 :
		case 9  : grade = "A";
		case 8  : grade = "B";
		case 7  : grade = "C";
		case 6  : grade = "D";
		default : grade = "F";
		}
		
		
		// db로 보낼 student student 생성(update니까 전달할 값을 dto로 db에 전달)
		Student student = Student.builder()
				.stuNo(stuNo)
				.name(name)
				.kor(kor)
				.eng(eng)
				.math(math)
				.ave(ave)
				.grade(grade)
				.build();
				
		// db로 보낼 student student 보내기(수정)
		int result = StudentDAO.getInstance().updateStudent(student);
		// => 성공하면 0또는1 실패하면 -1
		// => update문이니까 수정한 student를 전달하면 성공, 실패여부를 숫자int로 반환한다
		
		// 수정 성공/ 실패
		PrintWriter out = response.getWriter();
		if(result > 0 ) {
			out.println("<script>");
			out.println("alert('학생정보가 수정되었습니다')");
			out.println("location.href = '" + request.getContextPath() + "/student/detail.do?stuNo=" + stuNo + "'");
			// => request.getContextPath : 프로젝트명
			// => 즉, 이동경로 주소를 작성한 것
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('학생정보 수정에 실패했습니다')");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		
			
		return null;
	}

}
