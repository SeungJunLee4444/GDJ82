<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 로그인 성공 시 session에 id, pwd 저장된 상태임 --%>
	<%
		Object id = session.getAttribute("id");
		Object pwd = session.getAttribute("pwd");
	%>
																	<%-- & 조건문 사용 : 일반if문 또는 코어라이브러리로 c:if태그 사용가능 --%>
	<% if(id != null && pwd != null){ %>							<%-- & null이 아니면 로그인상태, 로그아웃 버튼 --%>
		${id}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" value="로그아웃" id="btn_logout">
	<% } else { %>
		<div>
			<form method="POST" action="02_login.jsp">
				<div>												<%-- & null이면 로그인창 보여주기--%>
					<input type="text" name="id" placeholder="아이디">
				</div>
				<div>
					<input type="password" name="pwd" placeholder="비밀번호">
				</div>
				<div>
					<button>로그인</button>
				</div>
			</form>
		</div>
	<% } %>
	
	<script>
		document.getElementById('btn_logout').onclick = function(event){
			location.href = '03_logout.jsp';
		}
	</script>

</body>
</html>