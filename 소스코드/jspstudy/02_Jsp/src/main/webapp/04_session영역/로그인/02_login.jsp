<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// # 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	// # 로그인 처리
	// id와 pwd가 동일하면 로그인 성공으로 처리
	// - db에 경유해서 아이디, 비밀번호값을 대조할 수 없기 때문에 임의의 조건을 생성
	if(id.equals(pwd)){
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
	}
	
	// 로그인 폼으로 가기
	response.sendRedirect("01_form.jsp");					// => 리다이렉트는 파라미터값을 반환하지않음
%>