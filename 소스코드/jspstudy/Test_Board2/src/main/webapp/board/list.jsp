<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>							<%-- & list서비스 : 요청으로 전달된 'boards'가 해당 el변수값들을 채운다  --%>
		총 게시글 : ${count}개 		<%-- & 쿼리문에서 count 기본값을 0개로 시작하면된다 --%>
	</div>
	<div>
		<table border="1">
			
			<thead>
				<tr>
					<td>순번</td>
					<td>작성자</td>
					<td>제목</td>
					<td>작성일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty boards}">	
					<tr>
						<td colspan="4">게시물이 없습니다.</td>	
					</tr>
				</c:if>
				<c:if test="${not empty boards}">
				<c:forEach items="${boards}" var="board">
					<tr>
						<td>${board.board_no}</td>
						<td>${name}</td>
						<td><a href="${contextPath}/detail.do?no=${board.boardNo}">${board.title}</a></td>	
						<td>${board.create_date}</td>
					</tr>
				</c:forEach>
				</c:if>
					<tr>
						<td>
							<a href="${contextPath}/board/insert.do"><button>새글작성</button></a>
						</td>
					</tr>
			</tbody>
		</table>
	</div>

</body>
</html>