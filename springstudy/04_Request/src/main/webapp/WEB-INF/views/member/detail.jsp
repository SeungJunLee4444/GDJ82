<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
</head>
<body>

	<%-- # 상세화면 : 전달된 속성 "member"의 id,pw 상세보기 --%>

	<h1>회원 상세보기</h1>
	<div>아이디   : ${member.id}</div>	<%-- ${member.id} = member.getId() --%>
	<div>패스워드 : ${member.pw}</div>	<%-- ${member.pw} = member.getPw() --%>
	
	
	<%-- * 파라미터를 포워드로 전달했기 때문에 param el 내장객체를 통한 호출도 가능하다 * --%>
	<div>아이디   : ${param.id}</div>	

</body>
</html>