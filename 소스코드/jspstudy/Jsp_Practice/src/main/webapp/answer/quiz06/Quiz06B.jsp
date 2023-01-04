<%@page import="java.net.URLEncoder"%>
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
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		
		Cookie cookie1 = new Cookie("id", URLEncoder.encode(id, "UTF-8"));
		cookie1.setMaxAge(60 * 60);
		response.addCookie(cookie1);
		Cookie cookie2 = new Cookie("pw", URLEncoder.encode(pw, "UTF-8"));
		cookie2.setMaxAge(60 * 60);
		response.addCookie(cookie2);
		Cookie cookie3 = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
		cookie3.setMaxAge(60 * 60);
		response.addCookie(cookie3);
	%>
	<h3>연락처를 입력하세요</h3>
	<form action="Quiz06C.jsp" method="post">
		주소 <input type="text" name="address"><br>
		전화번호 <input type="text" name="phone"><br>
		이메일 <input type="text" name="email"><br><br>
		<button>확인</button>
	</form>
</body>
</html>