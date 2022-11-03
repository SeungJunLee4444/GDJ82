<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연락처 저장화면</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
	
		// # 목록 돌아가기 ----------------------------------------
		$('#btn_list').click(function() {	
			location.href='${contextPath}/list';
		});
		
		// # 서브밋 방지 ------------------------------------------
		$('#frm_contact').sumbit(function(event) {
			if($('#name').val() == '' || $('#addr').val() == '') {
				alert('이름과 주소는 필수입니다');
				event.preventDefault();
				return;
			}
			
		});
		
		
	}
</script>
</head>
<body>

	<%-- # 연락처 저장 화면 --%>
	<%-- - 조건 : form 태그로 서브밋 --%>
	<h1>연락처 등록</h1>
	
	<div>
		<form id="frm_contact" action="${contextPath}/insertContact.do" method="post">
			<label for="name">이름*</label>
			<input type="text" id="name" name="name" >
			<br>
			<label for="tel">전화*</label>
			<input type="text" id="tel" name="tel" >
			<br>
			<label for="addr">주소*</label>
			<input type="text" id="addr" name="addr" >
			<br>
			<label for="email">이메일*</label>
			<input type="text" id="email" name="name" >
			<br>
			<label for="note">비고</label>
			<input type="text" id="note" name="note" >
			<br>
			
			<%-- & 서브밋 버튼은 form태그 안에 --%>
			<button>연락처 저장하기</button>
			<input type="button" value="전체연락처" id="btn_list">
		</form>
	
	</div>
		
	
	</form>

</body>
</html>