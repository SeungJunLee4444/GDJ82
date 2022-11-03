<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		// 시작할 때 session 초기화 해 두기.
		session.invalidate();
	%>
	<h3>신규 회원 정보를 입력하세요</h3>
	<form action="Quiz08B.jsp" method="post">
		아이디 <input type="text" name="id"><br>
		비밀번호 <input type="password" name="pw"><br>
		이름 <input type="text" name="name"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>