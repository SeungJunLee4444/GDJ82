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
		
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		Cookie cookie1 = new Cookie("address", URLEncoder.encode(address, "UTF-8"));
		cookie1.setMaxAge(60 * 60);
		response.addCookie(cookie1);
		Cookie cookie2 = new Cookie("phone", URLEncoder.encode(phone, "UTF-8"));
		cookie2.setMaxAge(60 * 60);
		response.addCookie(cookie2);
		Cookie cookie3 = new Cookie("email", URLEncoder.encode(email, "UTF-8"));
		cookie3.setMaxAge(60 * 60);
		response.addCookie(cookie3);
	%>
	<h3>추가정보를 입력하세요</h3>
	<form action="Quiz06D.jsp" method="post">
		생년월일 
		<input type="text" name="year" size="5">년
		<input type="text" name="month" size="3">월
		<input type="text" name="day" size="3">일<br>
		결혼여부 
		<input type="radio" name="marriage" value="미혼">미혼
		<input type="radio" name="marriage" value="기혼">기혼<br>
		직업 
		<select name="job">
			<option value="developer">개발자</option>
			<option value="doctor">의사</option>
			<option value="etc">기타</option>
		</select><br><br>
		<button>확인</button>
	</form>
</body>
</html>