<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- # 절대경로의 변수화 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>	     
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<%-- # 회원가입(추가) : register.me 서비스 요청, 추가는 post 방식 --%>
			<%-- * 사용자가 전달해야 하는 정보는 id, pw, name, email로 4개,
			 memberNo와 registedDate는 알아서 추가될 사항이다*****--%>
		<form action="${contextPath}/member/register.me" method="post">
			<div>
				<input type='text' name="id" placeholder="아이디">	<%-- # name은 domain(dto) 보고 만든다 --%>
			</div>
			<div>
				<input type='text' name="pw" placeholder="패스워드">
			</div>
			<div>
				<input type='text' name="name" placeholder="이름">
			</div>
			<div>
				<input type='text' name="email" placeholder="이메일">
			</div>
			<div>
				<button>회원가입</button>
				<input type="reset" value="입력초기화">
			</div>
		</form>
	</div>

</body>
</html>