<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- # jsp 단순이동 => animal --%>
	<a href="${contextPath}/animal">동물보러가기</a>
	
	<hr>
	
	<%-- # jsp 단순이동 => flower --%>
	<a href="${contextPath}/flower">꽃보러가기</a>
	
	<hr>
	
	<%-- # insert 예시 => flower5 --%>
	<a href="${contextPath}/animal/flower">동물보러갔다가 꽃보러가기</a>

	<hr>
	
	<%-- # 요청으로전달 : forward + 파라미터 전달후 꺼내쓰기 => animal5--%>
	<a href="${contextPath}/want/animal?filename=animal5.jpg">animal5.jsp</a>
	
	<hr>
	
	<%-- # 응답 :  --%>
	<a href="${contextPath}/response">응답만들어받기</a>
	
	
</body>
</html>