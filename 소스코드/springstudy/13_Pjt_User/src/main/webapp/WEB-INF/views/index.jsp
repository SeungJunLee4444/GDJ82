<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>


	<%-- [[[ 로그인이 안된 상태 --%>
	<c:if test="${loginUser == null}">
	
		<%-- [[ 회원가입 --%>
		<a href="${contextPath}/user/agree">회원가입페이지</a>	
		
		<%-- [[[ 로그인하기 : 로그인창에 이동시, 로그인후 돌아갈 주소 url을 보내줘야한다 --%>
		<a href="${contextPath}/user/login/form">로그인하기</a>	
		
		
		
		
		
	</c:if>
	
	<%-- [[[ 로그인이 된 상태 --%>
	
	<c:if test="${loginUser != null}">
		<%-- [[ 로그아웃 --%>
		<a href="${contextPath}/user/logout">로그아웃</a>	
		<div>
		
		<%-- [[ 마이페이지 이동 전 비밀번호 체크 --%>
			<a href="${contextPath}/user/check/form">${loginUser.name}</a>님 반갑습니다
		</div>
		<%-- [[ 회원탈퇴 : a태그로 form을 이용한 post 요청하는법--%>
		<a id="lnk_retire" href="javascript:fn_abc()">회원탈퇴</a>
		<form id="lnk_retire" action="${contextPath}/user/retire" method="post"></form>
		<script>
				function fn_abc() {
				// # 탈퇴시 여부 확인 : 취소를 누르면 취소이벤트 발생
				$('#lnk_retire').click(function(event) {
					if(confirm('탈퇴하시겠습니까?') == false) {
						$('#lnk_retire').submit();
					}
				});					
				}
		
		</script>
	</c:if>
	
	

</body>
</html>