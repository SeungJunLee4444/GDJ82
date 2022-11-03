<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 너비/높이 입력 폼 : 삼각형 버튼, 사각형 버튼 --%>
	<div>
		<form id="frm">
			<div>
				<label for="width">너비</label>
				<input type="text" name="width" id="width">
			</div>
			<div>
				<label for="height">높이</label>
				<input type="text" name="height" id="height">
			</div>
			<div>
				<input type="button" value="사각형 넓이 확인" onclick="fn_rectangle()">
				<input type="button" value="삼각형 넓이 확인" onclick="fn_triangle()">
			</div>
		</form>
	</div>
	<script>
		function fn_rectangle(){
			var frm = document.getElementById("frm");
			frm.action = '${contextPath}/rectangle.do';
			frm.submit();
		}
		function fn_triangle(){
			var frm = document.getElementById("frm");
			frm.action = '${contextPath}/triangle.do';
			frm.submit();
		}
	</script>
	
	<hr>
	
	<%-- 반지름 입력 폼 : 원 버튼 --%>
	<div>
		<form action="${contextPath}/circle.do">
			<div>
				<label for="radius">반지름</label>
				<input type="text" name="radius" id="radius">
			</div>
			<div>
				<button>원 넓이 확인</button>
			</div>
		</form>
	</div>

</body>
</html>