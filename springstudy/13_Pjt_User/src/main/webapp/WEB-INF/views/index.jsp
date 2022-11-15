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
			<a href="${contextPath}/user/mypage">${loginUser.name}</a>님 반갑습니다
		</div>
		<%-- [[ 회원탈퇴 --%>
		<a id="lnk_retire" href="${contextPath}/user/retire">회원탈퇴</a>
		<script>
				// # 탈퇴시 여부 확인 : 취소를 누르면 취소이벤트 발생
				$('#lnk_retire').click(function(event) {
					if(confirm('탈퇴하시겠습니까?') == false) {
						event.preventDefault();	// * a링크의 기본이벤트인 href="${contextPath}/user/retire"를 막는것
						return;
					}
				});
		</script>
	</c:if>
	
	

</body>
</html>