<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	// # jsp에서 사용될 title과 contextPath 변수처리
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("Welcome");
	pageContext.setAttribute("title", title);
	
	pageContext.setAttribute("contextPath", request.getContextPath());
	// <c:set var="contextPath" value="${pageContext.request.contextPath}" /> 대신 사용
	
%>

	<%-- # 레이아웃 페이지 : 모든 페이지의 상단에는 해당 페이지가 존재 --%>
	<%-- * header에 제이쿼리 스크립트를 만들면 참조하는 모든 페이지에서 제이쿼리를 사용할 수 있다 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script src="${contextPath}/resources/js/moment-with-locales.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.js"></script>
<script src="${contextPath}/resources/summernote-0.8.18-dist/lang/summernote-ko-KR.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.css">
</head>
<body>


	<%-- # 목록 --%>
	
	<div>
		<h1>Welcome to my Blog</h1>
	
	</div>
	
