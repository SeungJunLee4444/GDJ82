<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세화면</title>
</head>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	
	// # 상세화면에 1) 수정하기 2) 삭제하기 3) 목록 돌아가기 세가지 옵션이 다 들어가있다----------------------------------------*
	$(document).ready(function(){
		
		// 1) 수정하기 : 편집버튼을 누르면 edit 요청, 파라미터로 boardNo값도 같이 전달
		// - 이유 : 번호를 전달해서 수정해야할 게시글을 db에서 조회해야하기때문
		$('#btn_edit').click(function(event){
			location.href = '${contextPath}/board/edit.do?boardNo=${board.boardNo}';
		});
		
		// 2) 삭제하기 : 마찬가지로 remove.do 요청과 같이 파라미터로 boardNo를 전달한다
		// * delete 특징
		// - 삭제를 위해 db에 접근해야한다
		// * delete는 그저 삭제만 하고 detail.jsp로 돌아오면 되기 때문에 특별한 jsp 페이지가 필요하지않다, 서비스만 필요 --------*
		
		$('#btn_remove').click(function(event){
			if(confirm('게시글을 삭제할까요?')){
				location.href = '${contextPath}/board/remove.do?boardNo=${board.boardNo}';
			} else {
				alert('취소되었습니다.');
			}
		});
		
		// 3) 목록으로 돌아가기
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});
	
</script>
<body>
	<%-- # 상세화면  --%>
	<%--- 경우 : list.jsp에서 detail.do 요청시 이동 (비즈니스로직)
		- 조건 : boardNo를 파라미터로 받으면 해당 boarnNo를 지닌 board를 가져와 조회  --%>
	
	<%-- # 가져온 board의 title, content, createDate값 --%>
	<h1>${board.title}</h1>
	<pre>${board.content}</pre>
	<div>${board.createDate}</div>
	
	<hr>
	
	<%-- <상세화면의 조건 : 'board' 속성값 가져오기>  --%>
	
	<!-- *   -->
	<div>
		<input type="button" value="편집" id="btn_edit">	
		<input type="button" value="삭제" id="btn_remove">
		<input type="button" value="목록" id="btn_list">
	</div>
</body>
</html>