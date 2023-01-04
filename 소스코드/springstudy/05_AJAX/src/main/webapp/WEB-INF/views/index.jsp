<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴페이지</title>	<%-- # 웰컴작업하기 --%>
</head>
<body>
	<%-- # MyController1 : 5장 수업 --%>
	<div>
		<a href="${contextPath}/member">회원관리</a>
	</div>
	
	<hr>
	
	<%-- # MyController2 : 5장 실습 --%>
	<div>
		<a href="${contextPath}/board">게시판</a>
	</div>
	
	<hr>
	
	<%-- # MyController4 : post 요청 --%>
	<div>
		<a href="${contextPath}/contact">연락처관리</a>
	</div>
	
	<hr>
	
	<%-- # MyController3 : api 실습 --%>
	<div>
		<a href="${contextPath}/movie">박스오피스</a>
	</div>
	
	<hr>
	
	<%-- # MyController5 : ajax로 사진 받아오기 --%>
	<div>
		<a href="${contextPath}/gallery">갤러리가기</a>
	</div>

</body>
</html>