<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>	<%-- & 절대경로의 변수화 --%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>

	<%-- # 공지사항 만들기 =======================--%>
	<a href="${contextPath}/notice/list.no">공지사항</a>


	<%-- ======================================================================================================================================================= --%>


	<c:if test="${login == null}">		<%-- & 회원가입이 안되어있으면 로그인 창을 보여주겠다 --%>
	<div>
	
			<%-- # 로그인 --%>
		<form method="post" action="${contextPath}/member/login.me">
			<div>
				<input type="text" name="id" placeholder="아이디">
			</div>
			<div>
				<input type="text" name="pw" placeholder="비밀번호">
			</div>
			<div>
				<button>로그인</button>
			</div>
			
			<%-- # 회원가입 --%>
			<div>
				<a href="${contextPath}/member/join.me">회원가입</a>	
			</div>
			
		</form>
	</div>
	</c:if>
	
	<%-- # 로그인 상태화면 --%>
	<%-- - 로그인, 또는 회원가입시 session영역의 login 속성은 null값이 아니기 때문에, 로그인 상태화면으로 전환된다 --%>
	<c:if test="${login != null}">
	<div>
		${login.name} 님 어서오세요
		
		<%-- # 로그아웃 --%> 
		<input type="button" value="로그아웃" onclick="location.href='${contextPath}/member/logout.me'">	<%-- & 로그아웃시 로그아웃 요청 --%>
	</div>
	<div>
		<%-- # 회원탈퇴 --%> 
		<a id="cancel_link" href="${contextPath}/member/cancel.me">회원탈퇴</a>
		<%-- * cancel메서드에서 전달된 파라미터없이 회원탈퇴를 한다
			- 만약 파라미터를 전송할경우 a링크의 ?파라미터를 작성하듯이 href에도 작성하면된다***
		 --%> 
		
		<script>
			${'#cancel_link'}.click(function(event) {
				if(confirm('탈퇴하시겠습니까?') == false) {
					alert('취소합니다');
					event.preventDefault();	// * event.preventDefault() : 이벤트 대상인 a링크의 기본 디폴트 동작을 막는다
					return;					// => 잘 모르면 preventDefault, return을 둘다 쓰는것이 안전하다
				} else {
					
				}
			})
		</script>
	</div>
	</c:if>

</body>
</html>