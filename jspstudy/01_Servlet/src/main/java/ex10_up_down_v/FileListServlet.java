package ex10_up_down_v;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/FileListServlet")
public class FileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// upload 디렉터리의 경로
		String realPath = getServletContext().getRealPath("upload"); // generic서블릿이 가지고있는 메서드(객체없이도 호출가능)
		// => upload 실제경로를 객체에 저장
		// getServletContext().getRealPath => 구동중인 서버의 절대경로(webapp안, 실제는 C:\GDJ\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_Servlet)
		
		// upload 디렉터리에 저장된 파일 목록 가져오기
		File dir = new File(realPath);
		File[] files = dir.listFiles();
		
		// 응답
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		for(int i = 0; i < files.length; i++) {
			out.println("<div><a href=\"/01_Servlet/DownloadServlet?filename=" + files[i].getName() + "\">" + files[i].getName() + "</a></div>");
		}
		out.close();
		out.close();
		
		// & file 클래스, getName : 파일이름 가져오는 메서드
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
