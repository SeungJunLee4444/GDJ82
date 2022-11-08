<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
	
		// # 목록이동
		$('#btn_list').click(function(event) {
			location.href = '${contextPath}/list.do';
		});
	});

</script>
</head>
<body>
	<h1>자유게시판 게시글 상세보기 화면</h1>
	
	<br>
	
	게시글번호 ${free.freeNo}<br>
	작성자 ${free.writer}<br>
	작성IP ${free.ip}<br>
	조회수 ${free.hit}<br>
	<form action="${contextPath}/modify.do" method="POST">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title" value="${free.title}">
		</div>
		<textarea name="content" rows="3" cols="40">${free.content}</textarea>
		
		<input type="hidden" name="freeNo" value="${free.freeNo}">
				
		<div>
			<button>수정</button>
			<input type="button" id="btn_list" value="목록">
		</div>
	</form>

</body>
</html>