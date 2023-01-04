<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 이 페이지는 에러 페이지이다. --%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div>
		요청을 처리하는 과정에서 오류가 발생했습니다.<br><br>
		예외 타입: <%=exception.getClass().getName()%><br>
		예외 메시지: <%=exception.getMessage()%><br><br>
		<a href="mailto:admin@home.com">오류 신고하기</a><br>
		<a href="Quiz02A.jsp">처음부터 다시하기</a>
	</div>
	
</body>
</html>