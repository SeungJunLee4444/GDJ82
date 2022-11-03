<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>${map.result1}</h1>
	<div>
		<%-- 파라미터 age는 param.age로 EL 처리가 가능하다. --%>
		당신의 나이는 ${param.age}살 이므로 주류 구매가 ${map.result2}합니다.
	</div>
	<br><br>
	<a href="Quiz02A.jsp">처음으로 이동</a>
	
</body>
</html>