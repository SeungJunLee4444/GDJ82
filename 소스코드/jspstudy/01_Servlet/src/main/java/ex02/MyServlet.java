package ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet({"/My","/me"})		

// & URLMapping�� ���� ���� (1)������ ����, (2) <= �ٷ� ���氡��
// & URLMapping({"/my", "/me"}) ó�� 2�� �̻��� URL Mapping�� ������ �� ����

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//* */ : �����ּ�
	// 	   - Ŭ������ �����, ���, ������ �� Ŭ���� �������̽� ���ۺκп� ����ؾ��Ѵ�
	
	//-------------------------------------
	// * �������� ���� �߻���
	// (1) ������ ��ϵ� ���� context�� ����
	// (2) restart
	// (3) clear
	//-------------------------------------
	
	// & ����
	// => ���ݴ����� �Ʒ�ó�� �ڵ带 �ۼ�������, ������ ��������� ������,
	// ���Ŀ��� �ٸ����� �����ϴ� �뵵�� ���ȴ�
	

    public MyServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. ��û(request)
		//	- Ŭ���̾�Ʈ�� ���������� ������ ��û �Ǵ� ������
		//	- HttpServletRequest request ��ü�� ó��(������ �־�� ó������)
		
		// 1) ��û�� ���Ե� '�ѱ�ó��'(���ڼ� : utf-8)
		request.setCharacterEncoding("UTF-8");
		// & �����ϴ� ���� �ѱ��� ���ԾȵǸ� ������ʿ�x
		
		//--------------------------------------------------------------------------------------------------------------------
		// & ���ڵ��� ���ڵ�
		// 1) ���ڵ� : ����� ���ڸ� �����ڵ�(�ƽ�Ű�ڵ�)�� �������� ��ǻ�Ͱ� ������ �� �ִ� ����(0,1)�� �̷���� �ڵ�� ��ȯ�ϴ°�
		// - �뵵 : ���ͳ����� url�� ���� �� 
		// => url�� �ѱ��� �� ������ ������ �߻�
		// => �̴� url���� ascii���ڸ� �ؼ��� �� �ֱ� ��������(�ƽ�Ű���ڿ� ����� �ش�, �ѱ��� �ش���������)
		// => �ѱ��� % + 16��������(�ش��ϴ�)�� ��ȯ�Ǿ� url�� ���ڵ��ȴ�
		
		// 2) ���ڵ� : �ݴ�� �ڵ带 ���ڷ� ��ȯ�ϴ°�
		
		
		
		//--------------------------------------------------------------------------------------------------------------------
		
		
		// 2) ��û �Ķ����(Parameter) ����
		//	- URL?�Ķ����=��&�Ķ����=��
		//	- ��� �Ķ���ʹ� 'string'Ÿ��(���ڸ� ������ ���ڷ� �ν�)
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");	// ��� �Ķ���ʹ� stringŸ���̶� stringŸ�Կ� ����
		
//		System.out.println(name + "," + age);
		// ��� : �����߻�
		// => �Ķ���Ͱ��� ���� �ϼ����� �ʾұ⶧��
		
		// & NumberFormatException: null�� �߻��� ����

		// => �ذ� : nulló��
		int age = 0;
		if(strAge != null) {	// null�� �����Ѵ�
			age = Integer.parseInt(strAge);
		}
		
		System.out.println(name + "," + age);
		
		// 2. ����(response)
		//	- �������� Ŭ���̾�Ʈ�� ������ ����
		//  - HttpServletResponse response ��ü�� ó��(�翬�� ������ �־�� ó������)
			
		//	1) ����ڿ��� ������ ������������ html������ �����Ѵ�(����)
		//		- MINE-TYPE�� Ȱ��
		//		- MINE-TYPE�� ����
		//		(1) HTML	: text/html
		//		(2) CSS		: text/css
		//		(3) JS		: text/javascript
		//		(4) xml 	: application/xml
		//		(5) json 	: application/json
		//  (MINE-TYPE : ������ ������ �ǹ�)
		
//		response.setContentType("text/html");		(X)
		// => �ؼ� : ��������� html ���� ��������
		
		//	2) ���信 ���ԵǴ� �ѱ� ó��
//		response.setCharacterEncoding("UTF-8");		(X)
		
		//	1) + 2) MINE-TYPE + ���ڼ�(������ �̰ž���)
		response.setContentType("text/html; charset=UTF-8");		// (O)
		
		// 3) ���� ��Ʈ�� ����
		//	- ���� ��� ��Ʈ��(*writer)�� ����
		//	- response ��ü�κ��� printwriter ��ü�� ���� �� ����
		//	- IOException ���� ó���� throws�� ����������(try-catch�� �ʿ����)
		PrintWriter out = response.getWriter();
		
		// & write() �޼���� �ٹٲ�ó���� �ȵǼ�, write('\n') �ٹٲ� ó���� ������
		
		// 4) ���� �����(html ���� �����)
		// - �±׸� �Ѷ��Ѷ� �����ȴ�
		out.print("<html lang=\"ko\">");
		// & �ڹٿ����� \" �̽������� ���ڷ� ū����ǥ"�� ǥ���ؾ��Ѵ�
		out.print("<head>");
		out.print("<meat charset=\"UTF-8\">");
		out.print("<title>");
		out.print("<���� ù��° ������>");
		out.print("</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>�ȳ��ϼ���" + name + "�� �ݰ����ϴ� �� </h1>");
		if(age >= 20) {
			out.print("<h1>���ϴ�" + age + "���̹Ƿ� ������ �����մϴ�</h1>");			
		} else {
			out.print("<h1>" + age + "�� �ֵ��� ������</h1>");			
		}
		out.print("</body>");
		out.print("</html>");
		
		out.flush();	// ��� ��Ʈ���� �����ִ� ��� ������ ��������(������ ����)
		out.close();
		
		// & ������ �̷��� �ȸ���
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
