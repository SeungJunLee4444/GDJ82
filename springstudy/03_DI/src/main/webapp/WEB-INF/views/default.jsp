<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var ="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴페이지</title>
</head>
<body>

	<%-- # 요청 : 상세보기창으로 이동--%>
	<a href="${contextPath}/board/detail">게시판</a>
	<%-- & 스프링에서는 detail.do .do를 쓰지않는다 --%>  
	<%-- * a링크 방식은 get방식 --%>

	<hr>

	<%-- #  --%>
	<a href="${contextPath}/notice/detail">공지사항</a>
	<%-- * /notice/detail : 맵핑을 의미(jsp가 아니라) => 컨트롤러에 요청
			.do를 써주면 컨트롤러에서도 .do를 써줘야한다
	 --%>
	


</body>
</html>