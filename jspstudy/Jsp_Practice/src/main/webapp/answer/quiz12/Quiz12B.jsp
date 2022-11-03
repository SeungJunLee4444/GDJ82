<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${param.n - 0 ge 0}">
		<c:set var="abs" value="${param.n}" />
	</c:if>
	<c:if test="${param.n - 0 lt 0}">
		<c:set var="abs" value="${param.n * -1}" />
	</c:if>

	<h3>${param.n}의 절대값은 ${abs}이다.</h3>

</body>
</html>