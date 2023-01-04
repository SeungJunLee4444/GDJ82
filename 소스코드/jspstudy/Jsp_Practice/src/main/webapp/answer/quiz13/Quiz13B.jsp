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

	<!-- 1. 평균 구하기 -->
	<c:set var="average" value="${(param.kor + param.eng + param.mat) div 3}" />
	
	<!-- 2. 합격여부 구하기 -->
	<c:if test="${average - 60 ge 0}">
		<c:set var="pass" value="합격" />
	</c:if>
	<c:if test="${average  - 60 lt 0}">
		<c:set var="pass" value="불합격" />
	</c:if>
	
	<!-- 3. 학점 구하기 -->
	<c:choose>
		<c:when test="${average - 90 ge 0}"><c:set var="grade" value="A" /></c:when>
		<c:when test="${average - 80 ge 0}"><c:set var="grade" value="B" /></c:when>
		<c:when test="${average - 70 ge 0}"><c:set var="grade" value="C" /></c:when>
		<c:when test="${average - 60 ge 0}"><c:set var="grade" value="D" /></c:when>
		<c:otherwise><c:set var="grade" value="F" /></c:otherwise>
	</c:choose>

	<h3>성적처리결과</h3>
	<ul>
		<li>국어 : ${param.kor}점</li>
		<li>영어 : ${param.eng}점</li>
		<li>수학 : ${param.mat}점</li>
		<li>평균 : ${average}점</li>
		<li>학점 : ${grade}학점</li>
		<li>합격유무 : ${pass}</li>
	</ul>

</body>
</html>