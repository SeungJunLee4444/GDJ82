<%@page import="java.sql.Date"%>
<%@page import="domain.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
			
			Student board = new Student();
			board.setTitle(request.getParameter("title"));
			board.setWriter(request.getParameter("writer"));
			board.setContent(request.getParameter("content"));
			board.setDate(new Date(System.currentTimeMillis()));
			
			// EL 사용을 위해서.
			pageContext.setAttribute("board", board);
	%>	
	<div>제목 : ${board.title}</div>
	<div>작성 : ${board.writer}</div>
	<div>내용 : ${board.content}</div>
	<div>게시 : ${board.date}</div>
</body>
</html>