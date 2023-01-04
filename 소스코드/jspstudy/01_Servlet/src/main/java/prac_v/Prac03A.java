package prac_v;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Prac03A")
public class Prac03A extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청
		request.setCharacterEncoding("utf-8");
		
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String content = request.getParameter("content");
		
		// 파일명
		String fileName = new Date(System.currentTimeMillis()) + "-" + from + ".txt";
		// & long타입 date는 타임스탬프값(70년1월1일부터 1000분의 1초단위로 증가하는값)
		
		// 폴더 객체
		File dir = new File(request.getServletContext().getRealPath("storage"));	
		// & 폴더 생성시 경로?
		// getServletContext : 프로젝트 01_Servlet
		// getRealPath : 진짜 경로(서버,톰캣의 진짜경로) = C:\GDJ\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_Servlet
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 파일 객체
		File file = new File(dir, fileName);
		
		// 출력
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("to. " + to + "\n");
		bw.write(content + "\n");
		bw.write("from. " + from + "\n");
		bw.close();
		
		// 이동(redirect든 forward든 상관없음)
		// => 생성된 파일로 이동
		// => 한글이 포함될 수 있으니 인코딩
//		response.sendRedirect("/01_Servlet/Prac03B?fileName=" + URLEncoder.encode(fileName, "utf-8"));
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Prac03B");
		requestDispatcher.forward(request, response);
		
		//----------------------------
		// & redirect와 forward의 차이
		// (1) redirect	: 사용시 요청과 응답을 클라이언트에게 바로 반환한다(즉 처리되는 과정에서 클라이언트의 요청과 응답에 저장된 파라미터값을 확인할 수 없다)
		// (2) forward	: 사용시 요청과 응답을 서버에 전달한 후에 클라이언트에게 반환한다(클라이언트의 요청과 응답에 저장된 파라미터값을 확인 가능하다)
		//----------------------------
		
		
		// & redirect와 forward의 차이
		// & redirect	: prac03a가 url상에 노출되지 않는다	=> prac03b
		// & forward	: prac03a가 url상에 노출된다		=> prac03a	 예상 : 페이지를
		// => urlmapping의 노출여부의 차이?
		
		// & Redirect는 요청의 응답을 client에 보내지 않고 종료시키기 때문에 url에서
		// prac03a
		

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
