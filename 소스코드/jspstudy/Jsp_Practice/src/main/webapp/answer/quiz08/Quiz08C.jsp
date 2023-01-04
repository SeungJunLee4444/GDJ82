<%@page import="java.io.File"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String agreement = request.getParameter("agreement");
	if (agreement.equals("yes")) {  // 동의함
		String id = (String)session.getAttribute("id");
		String pw = (String)session.getAttribute("pw");
		String name = (String)session.getAttribute("name");
		String filename = id + ".txt";
		String realPath = request.getServletContext().getRealPath("storage");
		File dir = new File(realPath);
		if (dir.exists() == false) {
			dir.mkdirs();
		}
		File file = new File(dir, filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write("가입 아이디: " + id); bw.newLine();
		bw.write("가입 비밀번호: " + pw); bw.newLine();
		bw.write("가입 성명: " + name);
		bw.flush();
		if (bw != null) {
			bw.close();
		}
		// 가입이 끝나면 "시스템 변화"가 생긴 것으로 가정하고, redirect를 한다.
		// 파일명을 확인할 수 있도록 session에 올려 둔다.
		session.setAttribute("filename", filename);
		response.sendRedirect("Quiz08D.jsp");
	} else {  // 동의 안 함
		out.println("<h1>가입되지 않았습니다.</h1>");
		out.println("<a href=\"Quiz08A.jsp\">다시 가입하기</a>");
	}
%>