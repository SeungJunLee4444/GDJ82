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
<script>

	$(document).ready(function(){
		
		// 등록
		$('#frm_write').submit(function(event){
			if ($('#name').val() == '' || 
				$('#tel').val() == '' || 
				$('#addr').val() == '' ||
				$('#email').val() == '') {
				alert('필수 정보를 모두 입력하세요.');
				event.preventDefault();
				return;
			}
		});
		
		// 목록
		$('#btn_list').click(function(){
			location.href = '${contextPath}/card/list';
		});
		
	});
	
</script>
</head>
<body>

	<h3>연락처 등록</h3>
	
	<form id="frm_write" action="${contextPath}/card/register" method="post">
		<label for="name">이름*</label><br>
		<input type="text" name="name" id="name"><br><br>
		<label for="tel">전화*</label><br>
		<input type="text" name="tel" id="tel"><br><br>
		<label for="addr">주소*</label><br>
		<input type="text" name="addr" id="addr"><br><br>
		<label for="email">이메일*</label><br>
		<input type="text" name="email" id="email"><br><br>
		<label for="note">비고</label><br>
		<input type="text" name="note" id="note"><br><br>
		<button>연락처 저장하기</button>
		<input type="button" value="전체연락처" id="btn_list">
	</form>

</body>
</html>