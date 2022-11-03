<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<ul>
		<li><a href="${contextPath}/board/list.do">게시판</a></li>
		<li><a href="#">공지사항</a></li>
		<li><a href="#">QnA</a></li>
	</ul>

</body>
</html>