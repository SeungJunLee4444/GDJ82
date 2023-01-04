<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="assets/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<a href="${contextPath}/insertPage.do">작성하러가기</a>
	
	<hr>
	
	<table border="1" id="btn_board">
		<thead>
			<tr>
				<td>게시글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>조회수</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boards}" var="b">
				<tr>
					<td>${b.freeNo}</td>
					<td><a href="${contextPath}/detail.do?freeNo=${b.freeNo}">${b.title}</a></td>
					<td>${b.writer}</td>
					<td>${b.hit}</td>
					<td><a href="${contextPath}/remove.do?freeNo=${b.freeNo}">X</a></td>
				</tr>
			</c:forEach>
		
		</tbody>
	</table>

</body>
</html>