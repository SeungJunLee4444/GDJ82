<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규학생등록</title>
<link rel=>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
		
		$(document).ready(function() {
			
			$('#frm_write').submit(function(event) {
				var kor = $('#kor');
				var kor = $('#eng');
				var kor = $('#math');
				if(kor.val() == '' || isNaN(kor.val()) || kor.val() < 0 || kor.val() > 100) {
					alert('국어점수를 확인하세요');
					kor.focus();
					event.preventDefault();
					return;
					// & isNaN 함수 : 해당값이 숫자면 true, 아니면 false 반환
				}
				if(eng.val() == '' || isNaN(eng.val()) || eng.val() < 0 || eng.val() > 100) {
					alert('영어점수를 확인하세요');
					eng.focus();
					event.preventDefault();
					return;
				}
				if(math.val() == '' || isNaN(math.val()) || math.val() < 0 || math.val() > 100) {
					alert('수학점수를 확인하세요');
					math.focus();
					event.preventDefault();
					return; 
				}
			});
			
			$('#btn_list').click(function(event) {
				location.href = '${contextPath}/student/list.do';
			});
		
			
		});

</script>


<body>
	<h1>신규학생등록 화면</h1>
	<div>
		<form id="frm_write" method="post" action="${contextPath}/student/add.do">
		<!-- => add.do 요청을 통해 컨트롤로에서 add.do 요청을 실행한다 -->
			<div>
				<label for="name">이름</label>
				<input type="text" id="name" name="name">
			</div>
			<div>
				<label for="kor">국어</label>
				<input type="text" id="kor" name="kor">
			</div>
			<div>
				<label for="eng">영어</label>
				<input type="text" id="eng" name="eng">
			</div>
			<div>
				<label for="math">수학</label>
				<input type="text" id="math" name="math">
			</div>
			<hr>
			<div>
				<input type="submit" value="등록완료">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" id="btn_list">
			</div>
		
		</form>
	</div>














	
</body>
</html>