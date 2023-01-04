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

	<h3>이용 약관</h3>
	-------------------------------------------------------------<br>
	1. 회원 정보는 웹 사이트 운영을 위해서만 사용됩니다. <br>
	2. 원치 않으면 정보 제공을 하지 않을 수 있습니다. <br>
	3. 다만 이 경우 정상적인 웹 사이트 이용이 제한됩니다. <br>
	-------------------------------------------------------------<br><br>
	<form action="Quiz05C.jsp" method="post">
		위 약관에 동의하십니까?<br><br>
		<input type="radio" name="agreement" value="yes"> 동의 함
		<input type="radio" name="agreement" value="no"> 동의 안 함<br><br>
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>">
		<input type="hidden" name="pw" value="<%=request.getParameter("pw")%>">
		<input type="hidden" name="name" value="<%=request.getParameter("name")%>">
		<input type="submit" value="회원가입">
	</form>
	
</body>
</html>