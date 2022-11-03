package prac_v;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/* 
 * http://localhost:9090/01_Servlet/now �ּҸ� �Է��ϸ�,
 * ���� ��¥�� �ð��� �����ֱ�
 * => ��û�ϴ� �Ķ���Ͱ� ������ ��û�� �ʿ����
 * => �������� �ð��� out.print()�ϸ� �ȴ�
 * 
 * */

@WebServlet("/now")		// urlmapping���� now�� ����
public class prac01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public prac01() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// ���� ������ Ÿ��
		response.setContentType("text/html; charset=utf-8");
		
		// ���� ��Ʈ�� ����
		PrintWriter out = response.getWriter();
		
		// ���糯¥ �����
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH + 1);	// calendar�� ���� �ϳ� ���� ����
		int day = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		// ���� �����
		out.print("<h1>���� �ð���" + year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��" + second + "���Դϴ�</h1>");
		
		out.flush();
		out.close();
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
