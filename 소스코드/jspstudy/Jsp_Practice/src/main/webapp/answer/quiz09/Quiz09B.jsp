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
	<%-- 파라미터로 전달된 배열은 paramValues.배열 형식으로 EL을 사용할 수 있다. --%>
	<c:forEach var="singer" items="${paramValues.singers}" varStatus="v">
		<div>좋아하는 가수${v.count} : ${singer}</div>  <%-- v.count는 v.index + 1과 같다. --%>
	</c:forEach>
</body>
</html>