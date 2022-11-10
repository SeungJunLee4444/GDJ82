<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴</title>
</head>
<body>

	<%-- # 웰컴페이지 --%>
	
	<a href="${contextPath}/bbs/list">게시판목록</a>

</body>
</html>