<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>

	<%-- # 상세화면 : 전달된 속성 title과 hit 확인 --%>

	<h1>회원 상세보기</h1>
	<div>아이디   : ${title}, ${board.title}</div>
	<div>패스워드 : ${hit}, ${board.hit}</div>
	


</body>
</html>