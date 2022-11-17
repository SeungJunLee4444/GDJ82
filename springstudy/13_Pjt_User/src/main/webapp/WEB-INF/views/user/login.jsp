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
<%-- # cdn : jquery cookie --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js" integrity="sha512-3j3VU6WC5rPQB4Ld1jnLV7Kd5xr+cq9avvhwqzbH/taCRNURoeEpoPBK9pDyeukwSxwRPJ8fDgvYXd6SkaZ2TA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	
	$(function() {
		
		// [[[ 아이디 저장
		fn_login();
		fn_displayRememberId();
		
		
		
		
	});
	
	// [[ 아이디 저장1
	function fn_login() {
		$('#frm_login').submit(function(event) {
			
			// # id, pw가 빈문자열일 경우 서브밋 중지
			if($('#id').val() == '' || $('#pw').val() == '')  {
				event.preventDefault();
				return;
			}
			
			// # 
			if($('#rememberId').is(':checked')) {
				$.cookie('rememberId', $('#id').val());	
				// * $.cookie : 제이쿼리 쿠키 라이브러리로 인해 사용된다
				// 해석 : rememberId라는 이름으로 사용자가 입력한 id값을 저장하겠다
			} else {
				$.cookie('rememberId', '');
			}
			
			
		});
	}
	
	// [[ 아이디 저장2
	// # 저장된 아이디를 다음에 로그인할때 화면에 표시
	function fn_displayRememberId() {
		
		let rememberId = $.cookie('rememberId');
		// 1) 쿠키에 저장된 아이디가 없으면, 아이디칸은 빈값, 아이디 저장 체크박스는 체크안됨
		if(rememberId == '') {
			$('#id').val('');
			$('#rememberId').prop('checked', false)
		} else {
		// 2) 쿠키에 저장된 아이디가 있으면, 아이디칸은 저장된 아이디값, 아이디 저장 체크박스는 체크됨
			$('#id').val(rememberId);
			$('#rememberId').prop('checked', true)
			
		}
	}

</script>
</head>
<body>

	<div>
	
		<h1>로그인</h1>
		
		<form id="frm_login" action="${contextPath}/user/login" method="post">
			
			<%-- #  로그인 후 이동할 url값 : model에 저장해 가져온 --%>
			<input type="hidden" name="url" value="${url}">
			
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id">
			</div>
			
			<div>
				<label for="pw">비밀번호</label>
				<input type="password" name="pw" id="pw">
			</div>
			
			<div>			
				<button>로그인</button>
			</div>
			
			<div>			
				<label for="rememberId">
					<input type="checkbox" id="rememberId">
					아이디 저장
				</label>
				<label for="keepLogin">
					<input type="checkbox" name="keepLogin" value="keep" id="keepLogin">
					로그인 유지
				</label>
			</div>
		
		</form>
			
		<div>
			<a href="${contextPath}/user/findId">아이디 찾기</a> | 
			<a href="${contextPath}/user/findPw">비밀번호 찾기</a>
		</div>
	
	</div>
	
</body>
</html>