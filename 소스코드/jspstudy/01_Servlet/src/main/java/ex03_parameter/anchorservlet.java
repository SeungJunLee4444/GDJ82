package ex03_parameter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/AnchorServlet")
public class anchorservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public anchorservlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ��û �ĸ�����
		request.setCharacterEncoding("utf-8");
		String stra = request.getParameter("a");
		String strb = request.getParameter("b");
		int a = 0;
		int b = 0;
		if(stra != null ) {
			a = Integer.parseInt(stra);			
		}
		if(strb != null) {
			b = Integer.parseInt(strb);			
		}
		
		System.out.println("��" + (a + b));
		
		// ���� �����
		// => ��û�� �����̾���, ���丸 setcontenttype�� ����
		response.setContentType("text/html; charset=utf-8");
	
		PrintWriter out = response.getWriter();
		// => getWriter�� ��ȯ�ϴ°� printwriter
		
		
		
		// ���� ���
		// => ����� html����
		out.println("<h1>hello world</h1>");
		out.println("<h1>" + a + "+" + b + "=" + (a+b) + "</h1>"); 
		
	
		
		
		

		
		
		
		out.flush();
		out.close();
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
