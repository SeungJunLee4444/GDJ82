<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% request.setCharacterEncoding("UTF-8"); %>
	
	<form action="Quiz03C.jsp">
		<h3>2. 좋아하는 운동선수는 누구인가요?</h3>
		<input type="text" name="player">
		<input type="hidden" name="name" value="<%=request.getParameter("name")%>">
		<input type="hidden" name="celeb" value="<%=request.getParameter("celeb")%>">
		<button>결과보기</button>
	</form>

</body>
</html>