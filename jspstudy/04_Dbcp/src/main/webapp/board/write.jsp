<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시글 작성</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		$('#frm_board').submit(function(event){
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				event.preventDefault();  // 서브밋 방지
				return;                  // 코드 진행 방지
			}
		});
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});

</script>
</head>
<body>

	<h1>새 게시글 작성 화면</h1>
	<div>
		<form method="POST" action="${contextPath}/board/add.do" id="frm_board">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="content">내용</label><br>
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