<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- # 파라미터 전송 : a,b에 입력된 값은 02_parameter2.jsp로 전달된다 --%>
	<%-- form태그의 서브밋 이동방식과 상관없이 파라미터를 다음페이지로 전달해준다 --%>
	<div>
		<form method="GET" action="02_parameter2.jsp">
			<div>
				<input type="text" name="a">
			</div>
			<div>
				<input type="text" name="b">
			</div>
			<div>
				<input type="submit" value="전송">
			</div>
		</form>
	</div>

</body>
</html>