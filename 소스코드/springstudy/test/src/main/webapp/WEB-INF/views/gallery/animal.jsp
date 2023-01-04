<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- # 이미지 갤러리 화면--%>
	<h1>여기 예쁜 동물 좀 보세요</h1>
	<img src="resources/images/animal1.jpg" width="200px">	<%-- & src : 절대경로(webapp기준) --%>
	
	<h1>저도 봐주세요</h1>
	<img src="assets/images/animal2.jpg" width="200px">
	<%-- => 안되는이유 :  --%>
</body>
</html>