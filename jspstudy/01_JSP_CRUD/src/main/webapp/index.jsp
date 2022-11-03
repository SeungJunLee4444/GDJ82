<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- # 컨텍스트 패스 변수화 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- * el의 객체인 pageContext를 이용해 컨텍스트 패스값을 value에 저장 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴페이지</title>
</head>
<body>
	<%-- # a링크를 통한 요청
		- 컨텍스트 패스 : 웹 기준으로는 webapp을 최상위라 보면 된다
	 --%>
	<a href="${contextPath}/board/list.do">게시판</a>
	<%-- & 설명 : list.do 요청을 하면 게시판 목록을 보여줘라는 요청
	- 파라미터 : x
	- 속성 : x
	--%>

</body>
</html>