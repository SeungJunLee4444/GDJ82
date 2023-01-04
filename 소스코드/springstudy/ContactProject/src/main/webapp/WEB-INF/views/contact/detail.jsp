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
		
		var frm = $('#frm_detail');
		
		// 수정
		$('#btn_modify').click(function(){
			frm.attr('action', '${contextPath}/card/modify');
			frm.submit();
		});
		
		// 삭제
		$('#btn_remove').click(function(){
			if(confirm('연락처를 삭제할까요?')) {
				frm.attr('action', '${contextPath}/card/remove');
				frm.submit();
			}
		});
		
		// 목록
		$('#btn_list').click(function(){
			location.href = '${contextPath}/card/list';
		});
		
	})
	
</script>
</head>
<body>

	<h3>연락처 보기</h3>

	<form id="frm_detail" method="post">	
		<label for="name">이름*</label><br>
		<input type="text" name="name" id="name" value="${contact.name}"><br><br>
		<label for="tel">전화*</label><br>
		<input type="text" name="tel" id="tel" value="${contact.tel}"><br><br>
		<label for="addr">주소*</label><br>
		<input type="text" name="addr" id="addr" value="${contact.addr}"><br><br>
		<label for="email">이메일*</label><br>
		<input type="text" name="email" id="email" value="${contact.email}"><br><br>
		<label for="note">비고</label><br>
		<input type="text" name="note" id="note" value="${contact.note}"><br><br>	
		<input type="hidden" name="no" value="${contact.no}">
		
		<input type="button" value="수정하기" id="btn_modify">
		<input type="button" value="삭제하기" id="btn_remove">
		<input type="button" value="전체연락처" id="btn_list">	
	</form>
	
</body>
</html>