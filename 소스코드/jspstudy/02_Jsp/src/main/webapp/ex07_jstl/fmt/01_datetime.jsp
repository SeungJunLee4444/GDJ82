<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="now" value="<%=new Date()%>" />
	
	<%--
		<fmt:formatDate> 태그
		
		1. SimpleDateFormat 클래스를 대체하는 태그
		2. value 속성과 pattern 속성을 이용
		3. pattern 속성에서 사용하는 패턴은 SimpleDateFormat과 동일한 패턴임
	--%>
	
	<h1><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일 E요일" /></h1>
	<h1><fmt:formatDate value="${now}" pattern="a h:mm:ss" /></h1>
	<h1><fmt:formatDate value="${now}" pattern="H:mm:ss" /></h1>
	
</body>
</html>