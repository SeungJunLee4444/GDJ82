<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<div>

		<div>
			<a href="/upload/write">작성</a>
		</div>
		
		<hr>
		
		<div>
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
							<td>${upload.attachCnt}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							${paging}
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
	</div>
	
</body>
</html>