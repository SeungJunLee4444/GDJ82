<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴페이지</title>	<%-- # [6장 : 스프링 + JDBC] --%>
</head>
<body>
	
	<%--# 전체목록 조회하는 요청 : list --%>
	<a href="${contextPath}/board/list">게시판</a>
	
</body>
</html>