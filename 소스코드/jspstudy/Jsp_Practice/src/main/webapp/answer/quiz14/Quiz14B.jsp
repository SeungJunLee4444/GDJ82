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
	<h3>일반 forEach문</h3>
	<c:forEach var="size" begin="${param.min}" end="${param.max}" step="1">
		<font size="${size}">폰트 사이즈 ${size}<br></font>
	</c:forEach>
	<h3>향상 forEach문</h3>
	<ul>
		<c:forEach var="food" items="${paramValues.foods}" varStatus="v">
			<li>${v.count}번째 음식: ${food}</li>
		</c:forEach>
	</ul>
</body>
</html>