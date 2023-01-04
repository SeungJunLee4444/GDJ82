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
	
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		String op = request.getParameter("op");
		String answer = request.getParameter("answer");
		String result = request.getParameter("result");
		
		String message = "";
		if (answer.equals(result)) {
			message += a + op + b + "=" + result + "<br>" + "정답입니다.";
		} else {
			message += "제출: " + a + op + b + "=" + result + "<br>";
			message += "정답: " + a + op + b + "=" + answer;
		}	
	%>

	<%=message%>
	<br><br>
	<a href="Quiz04A.jsp">다시풀기</a>

</body>
</html>