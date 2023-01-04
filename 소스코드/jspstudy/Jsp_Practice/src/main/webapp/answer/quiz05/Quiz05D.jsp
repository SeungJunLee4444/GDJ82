<%@page import="java.io.File"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	
	<%
		request.setCharacterEncoding("UTF-8");
	
		String filename = request.getParameter("filename");
		String realPath = request.getServletContext().getRealPath("storage");
		File file = new File(realPath, filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		StringBuilder sb = new StringBuilder();;
		String buffer = null;
		while ((buffer = br.readLine()) != null) {
			sb.append(buffer + "<br>");
		}
		if (br != null) {
			br.close();
		}
	%>
	
	<h1>가입되었습니다.</h1>
	<h3>가입내용</h3>
	<%=sb.toString()%>
	<br><br>
	<form id="f" action="Quiz05E.jsp" method="post">
		<input type="hidden" name="filename" value="<%=filename%>">
		<input type="submit" value="탈퇴">
	</form>
	<script>
		$('#f').on('submit', function(event){
			if (confirm('정말 탈퇴하시겠습니까?') == false) {
				event.preventDefault();
				return false;
			}
			return true;
		}
	</script>

</body>
</html>