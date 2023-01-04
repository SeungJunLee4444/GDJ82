<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록페이지</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
		
	});
</script>

</head>
<body>

	<%-- # 게시글 추가 페이지 이동 --%>
	<div>
		<a href="${contextPath}/board/write">게시글 추가</a>
	</div>
	

	<%-- # 전체 목록 영역 --------------------------------------------------------------------------------------------------------- --%>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>작성자</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>					<%-- & 포워드한 데이터 : boards --%>
				<c:forEach items="${boards}" var="board">
					<tr>
						<td>${board.boardNo}</td>
						<td><a href="${contextPath}/board/detail?boardNo=${board.boardNo}">${board.title}</a></td>			<%-- # 게시글 상세보기 요청 : 파라미터로 게시글 번호 전달 --  --%>
						<td>${board.writer}</td>
						<td>${board.createDate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>