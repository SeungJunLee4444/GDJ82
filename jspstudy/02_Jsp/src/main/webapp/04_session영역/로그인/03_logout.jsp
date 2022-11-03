<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// session에 저장된 정보를 초기화하기
	session.invalidate();

	// 로그인 폼으로 가기
	response.sendRedirect("01_form.jsp");
%>