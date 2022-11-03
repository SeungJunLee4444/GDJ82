<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
		
		// 1) 빈경우에는 
		$('#frm_write').submit(function(event) {
			
			if($('title').val() == '') {
				alert('제목은 필수입니다');
				$('#title').focus();	// => 타이틀에 포커스 남기기
				event.preventDefault();
				return;
			}
		})
		
		// 2) 목록으로 이동
		$('#btn_list').click(function(event) {
			location.href = '${contextPath}/board/list.do';
		})
	})
		
		
		
		


</script>
</head>
<body>
	<h1>게시글 작성 화면</h1>
	<div>
		<form id="frm_write" action="${contextPath}/board/add.do" method="POST">
				<div>
					<label for="title">제목</label>
					<input type="text" id="title" name="title">
				</div>
				<div>
					<label for="content">내용</label>
					<textarea id="content" name="content" rows="5" cols="30"></textarea> 
				</div>
				<div>
					<input type="submit" value="작성완료">
					<input type="reset" value="다시작성">
					<input type="button" value="목록" id="btn_list">
				</div>
		</form>
	</div>
</body>
</html>