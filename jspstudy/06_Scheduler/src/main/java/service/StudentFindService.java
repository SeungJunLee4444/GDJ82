package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import repository.StudentDAO;

public class StudentFindService implements StudentService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터
		double begin = Double.parseDouble(request.getParameter("begin"));
		double end = Double.parseDouble(request.getParameter("end"));
		
		// DB로 보낼 MAP 생성(파라미터, 파리미터 값을 키 ,속성으로 저장하기 위해 map을 사용한다?)
		Map<String, Double> map = new HashMap<String, Double>();
		map.put("begin", begin);
		map.put("end", end);
		
		
		// student dao 객체 생성
		StudentDAO dao = StudentDAO.getInstance();
		// => dao 클래스의 메서드 호출
		
		// request에 필요한 정보 저장
		request.setAttribute("students", dao.selectStudentByAve(map));
		request.setAttribute("count", dao.selectStudentByAveCount(map));
		request.setAttribute("average", dao.selectStudentByAveAverage(map));
		
		// student/list.jsp로 포워딩(이동)
		return new ActionForward("/student/list.jsp", false);
		// => 롬복으로 @AllArgsConstructor
	}
}
