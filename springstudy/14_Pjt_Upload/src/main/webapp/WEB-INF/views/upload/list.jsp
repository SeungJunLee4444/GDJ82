<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<%-- <14장 : 다중 첨부 게시판> --%>

	<div>
		<div>
			<a href="${contextPath}/upload/write">작성</a>			
		</div>
		
		<hr>
		
		<%-- # 테이블 생성 --%>
		<%-- uploadDTO에서 번호,제목, 작성일, attachDTO에서 첨부파일 개수를 참조 => 조인 --%>
		<table border="1">
			<thead>
				<tr>
					<td>번호</td>
					<td>제목</td>
					<td>작성일</td>
					<td>첨부개수</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${uploadList}" var="upload">
					<tr>
						<td>${upload.uploadNo}</td>
						<td><a href="${contextPath}/upload/detail?uploadNo=${upload.uploadNo}">${upload.title}</a></td>
						<td>${upload.createDate}</td>
						<td>${upload.attachCnt}</td>	<%-- * 첨부파일 개수 --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	
</body>
</html>