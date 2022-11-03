package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Student;
import repository.StudentDAO;

public class StudentAddService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
	
		// 파생값
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
		
		// DB로 보낼 STUDENT 객체를 생성
		Student student = Student.builder()
				.name(name)
				.kor(kor)
				.eng(eng)
				.ave(ave)
				.grade(grade)
				.build();
		
		int result = StudentDAO.getInstance().insertStudent(student);
		
		// 삽입 성공 실패 여부
		PrintWriter out = response.getWriter();
		if(result > 0 ) {
			out.println("<script>");
			out.println("alert('신규학생이 등록되었습니다')");
			out.println("location.href = '" + request.getContextPath() + "/student/list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('신규학생 등록에 실패했습니다')");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
		
		return null;	
		// & null 반환
		// service를 통해서 직접 응답헀기 때문에 컨트롤러로 null을 반환
		// 컨트롤러가 null을 반환받으면 리다이렉트/포워드 모두 수행하지 않는다
	}

}
