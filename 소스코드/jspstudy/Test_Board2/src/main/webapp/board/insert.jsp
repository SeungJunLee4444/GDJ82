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
	<div>
		<form action="${contextPath}/add.do" method="POST">
			<table border="1">
				<tr>
					<td>작성자</td>
					<td>
						<input type="text" name="name">
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" name="title">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" rows="5" cols="40"></textarea>
					</td>
				</tr>
				<tr>
					<td>
						<button>등록</button>				<%-- & add.do 서비스 요청 --%>
						<input type="button" value="목록" onclick="location.href='${contextPath}/list.do'">	<%-- & 단순이동 --%>
					</td>
				</tr>
			</table>
		</form>
		
	</div>
	
	


</body>
</html>