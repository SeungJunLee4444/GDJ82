<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>  
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.boardNo}번 게시글</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		
		$('#btn_edit').click(function(event) {
			location.href = '${contextPath}/board/edit.do?boardNo=${board.boardNo}';
		});
		
		
		$('#btn_remove').click(function(event) {
			if(confirm('게시글을 삭제할까요?')) {
				location.href = '${contextPath}/board/remove.do?boardNo=${board.boardNo}';
			} else {
				alert('취소');
			}
		});
		
		// $(document).ready = 제이쿼리, 자바스크립트의 onload와 같은 역할
		
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});

</script>
</head>
<body>
	<h1>${board.title}</h1>
	<pre>${board.content}</pre>
	<div>${board.createDate}</div>
	
	<hr>
	
	<div>
		<input type="button" value="편집" id="btn_edit">
		<input type="button" value="삭제" id="btn_remove">
		<input type="button" value="목록" id="btn_list">
	</div>
</body>
</html>