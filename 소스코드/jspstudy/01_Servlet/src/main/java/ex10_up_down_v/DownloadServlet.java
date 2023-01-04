package ex10_up_down_v;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// [목록에 있는 링크 클릭시, 다운로드(cos와는상관x)]
		
		// 1. 요청 파라미터
		request.setCharacterEncoding("UTF-8");
		String filename = request.getParameter("filename");
		
		System.out.println(filename);
		
		// 2. 다운로드할 파일 경로
		String realPath = getServletContext().getRealPath("upload");
		// => 실제경로
		
		// 3. 다운로드할 파일 객체
		File file = new File(realPath, filename);
		
		// => & 파일을 읽어드려야하니 입력스트림(바이트 기반 스트림)
		
		
		// 4. 다운로드할 파일을 읽어들일 바이트 기반 입력스트림
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		
		// 5. 다운로드 응답 헤더
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "utf-8"));
		// => 해당 파일 이름으로 다운로드
		// 6. 바이트단위 파일 크기
		response.setHeader("Content-Length", file.length() + "");
		
		// => 헤더는 string, file.length는 long타입 => 강제 string 캐스팅
		
		// 7. 응답으로 내보낼 바이트 기반 출력 스트림
		BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
		// => 문자기반 스트림 printwriter이 아님, 
		
		// 8. 파일 복사
		byte[] b = new byte[1024];
		int readByte = 0;
		while((readByte = in.read(b)) != -1) {
			out.write(b, 0, readByte);
		}
		out.close();
		in.close();
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
