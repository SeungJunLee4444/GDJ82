<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 창</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
		// #) 1 서브밋시 전달받은 board의 타이틀과 내용이 입력한 것과 동일한경우 서브밋 방지
		$('#frm_edit').submit(function(event) {
			if('${board.title}' == $('#title').val() && '${board.content}' == $('#content').val() ) {
			alert('변경된 내용이 없습니다');
			event.preventDefault();
			return;
			}
			// #) 2 타이틀의 not null 처리 : 삽입과 동일하게 스크립트에서 해결
			if($('#title').val() == '') {
				alert('제목은 필수입니다');
				event.preventDefault();
				return;
			}
		});
	
		// #) 3 목록으로 돌아가기
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});
</script>
</head>
<body>
		<%-- # 게시글 편집창 --%>
		
		<%--- 경우 : detail.jsp에서 edit.do 요청시 이동해온다
			- 조건 : detail.jsp에서 파라미터로 boardNo를 통해 해당 번호의 board를 반환해왔음
			- 요청 : 파라미터로 전달, insert, update는 post방식
		  --%>
		  
		  <%-- & 조건 : board 속성값이 필요하다--%> 
		  
		  
		  <%-- & 정리 
		  (1) 전달받은 board 속성값을 이용해, 기존의 내용을 value값에 대입한다
		  (2) boardNo의 경우, 쿼리문에서 시퀸스로 새로 생성하는 것이 아닌 기존의 값을 그대로 수정해야하기 때문에 파라미터로 전송해야한다
		  - 방법 : input type hidden 태그를 이용해 보이지 않는 버튼으로 파라미터 전송을 실시
		  
		  --%>
		
		<h1>게시글 편집 화면</h1>
		<div>
			<form id="frm_edit" method="POST" action="${contextPath}/board/modify.do">
				<div>
					<label for="title">제목</label>
					<input type="text" id="title" name="title" value="${board.title}">
				</div>
				<div>
					<label for="content">내용</label><br>
					<textarea id="content" name="content" rows="5" cols="30">${board.content}</textarea>
				</div>
				<input type="hidden" name="boardNo" value="${board.boardNo}"> <%-- & input type hidden 사용예시 : 편집에서 실시 --%>
				<div>
					<input type="submit" value="수정완료">
					<input type="reset" value="작성취소">
					<input type="button" value="목록" id="btn_list">
				</div>
			</form>
		</div>

</body>
</html>