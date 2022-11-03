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
		request.setCharacterEncoding("UTF-8");
	
		// session에 두면 페이지를 이동해도 언제든 확인 가능하다.
		session.setAttribute("name", request.getParameter("name"));
		session.setAttribute("celeb", request.getParameter("celeb"));
	%>
	
	<form action="Quiz07C.jsp">
		<h3>2. 좋아하는 운동선수는 누구인가요?</h3>
		<input type="text" name="player">
		<button>결과보기</button>
	</form>

</body>
</html>