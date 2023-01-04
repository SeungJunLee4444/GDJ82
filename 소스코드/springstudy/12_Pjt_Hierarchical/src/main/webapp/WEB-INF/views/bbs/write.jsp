<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성페이지</title>
<style>



</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

	$(function() {
		
		
		
		
		
	});

</script>
</head>
<body>
	<div>
		<%-- # 작성페이지 --%>
		<%--- 필요한 파라미터는 writer, title 뿐이다(실무에서는 title 하나) --%>
		
		<h1>작성페이지</h1>
		<form id="frm" method="post" action="${contextPath}/bbs/add">
			<div>
				<input type="text" name="writer" placeholder="작성자" required>			
			</div>
			<div>
				<input type="text" name="title" placeholder="제목" required>		<%-- * required : 필수 --%>		
			</div>		
			<input type=hidden name="ip"> 
			
			<button>작성완료</button>
			<input type="reset" value="초기화">
			<input type="button" value="목록" onclick="location.href='${contextPath}/bbs/list'">
		</form>
	
	</div>

	
	
	


</body>
</html>