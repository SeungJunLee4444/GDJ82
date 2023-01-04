<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String year = request.getParameter("year");
	String month = request.getParameter("month");
	String day = request.getParameter("day");
	String marriage = request.getParameter("marriage");
	String job = request.getParameter("job");
	
	Cookie cookie1 = new Cookie("birthday", URLEncoder.encode(year + "년 " + month + "월 " + day + "일", "UTF-8"));
	cookie1.setMaxAge(60 * 60);
	response.addCookie(cookie1);
	Cookie cookie2 = new Cookie("marriage", URLEncoder.encode(marriage, "UTF-8"));
	cookie2.setMaxAge(60 * 60);
	response.addCookie(cookie2);
	Cookie cookie3 = new Cookie("job", URLEncoder.encode(job, "UTF-8"));
	cookie3.setMaxAge(60 * 60);
	response.addCookie(cookie3);
	
	// 결과는 다음 페이지에서 확인
	// 쿠키에 저장된 데이터는 페이지를 계속 이동해도 확인할 수 있으므로 그냥 redirect 해도 됨.
	response.sendRedirect("Quiz06E.jsp");
%>