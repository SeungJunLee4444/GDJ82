<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../assets/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<%--
	[로그인 유지] 
	- session에 저장된값을 쿠키에 저장하여 로그인상태를 유지
	- session : 브라우저가 종료되면 저장된 값이 사리짐 
	- cookie  : 정해진 시간동안 유지
	
	 --%>
	

	<%
		// 로그인 유지하기 상태
		// 1. 쿠키에 id, pwd가 저장된 상태
		// 2. 쿠키에 저장된 정보를 모두 세션으로 옮김
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i = 0; i < cookies.length; i++){
				if(cookies[i].getName().equals("id")){
					session.setAttribute("id", cookies[i].getValue());
				} else if(cookies[i].getName().equals("pwd")){
					session.setAttribute("pwd", cookies[i].getValue());
				}
			}
		}
	%>
	
	<%
		// 로그인 처리
		Object id = session.getAttribute("id");
		Object pwd = session.getAttribute("pwd");
	%>

	<% if(id != null && pwd != null){ %>
		${id}님 환영합니다.
		<input type="button" value="로그아웃" onclick="location.href='03_logout.jsp'">
	<% } else { %>
		<div>
			<form method="POST" action="02_login.jsp">
				<div>
					<input type="text" name="id" placeholder="아이디">
				</div>
				<div>
					<input type="password" name="pwd" placeholder="비밀번호">
				</div>
				<div>
					<button>로그인</button>
				</div>
				<div>
					<label for="chk_keep_login">
						<input type="checkbox" name="chk_keep_login" id="chk_keep_login">	<%-- & 로그인 유지 파라미터전송 --%>
						로그인 유지하기
					</label>
				</div>
			</form>
		</div>
	<% } %>

</body>
</html>