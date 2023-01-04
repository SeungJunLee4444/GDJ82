<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>게시글 수정화면</h1>
	
	<form action="${contextPath}/modify.do" method="POST">
	
		<div>글번호 ${board.no}</div>
		<div>작성자 ${board.writer}</div>
		<div>작성IP ${board.ip}</div>
		<div>조회수 ${board.hit}</div>
		<div>작성일 ${board.create_date}</div>
		
		<div>
			제목 
			<input type="text" name="title" value="${board.title}">
		</div>
		<div>
			<textarea name="content" rows="5" cols="30">${board.content}</textarea>
		</div>
		<div>
			<input type="hidden" name="no" value="${board.no}">
			<input type="submit" value="수정완료">
			<input type="button" value="목록" onclick="location.href='${contextPath}/list.do'">
		</div>
		
	</form>
	
</body>
</html>