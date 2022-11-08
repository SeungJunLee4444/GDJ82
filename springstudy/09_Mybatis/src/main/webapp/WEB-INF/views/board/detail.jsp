<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴페이지</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>

<%-- # summernote(웹 에디터) 추가 --%>
<link rel="stylesheet" href="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.css">
<script src="${contextPath}/resources/summernote-0.8.18-dist/summernote-lite.min.js"></script>

<%-- # 언어팩 --%>
<script src="${contextPath}/resources/lang/summernote-ko-KR.min.js"></script>
<script>

	$(document).ready(function() {
		
		
		// # 삭제와 편집버튼 처리
		// - get방식이 아닌 post 방식으로 처리해야한다(파라미터 노출로 인한 보안사고 방지)
		// - 방법 : form 태그 사용
		
		// * 변수처리 : $의 반복적 호출 방지
		
		// 1) 편집 화면 이동---------------------------------------------
		var frm= $('#frm_btn');
		
		$('#btn_edit').click(function() {
			
				frm.attr('action', '${contextPath}/board/edit');	// - 오류 ;
				frm.submit();	
		});
		
		// 2) 삭제 요청  --- ---------------------------------------------
		$('#btn_remove').click(function() {
			if(confirm('삭제할까요?')) {
				frm.attr('action', '${contextPath}/board/remove')
				frm.submit();
				return;
					}
			
		});
		
		
		// # 목록
		$('#btn_list').click(function() {
			
			location.href="${contextPath}/board/list";
		});
		
		
	});
</script>

</head>
<body>	
	<%-- # 상세보기화면 --%>
	<%-- - 설명 : board_no를 파라미터로 전달해서 board dto를 받아온다 --%>
	<ul>
		<li>글번호 : ${board.board_no}</li>
		<li>제목   : ${board.title}</li>
		<li>작성자 : ${board.writer}</li>
		<li>작성일 : ${board.createDate}</li>
		<li>수정일 : ${board.modifyDate}</li>
	</ul>
	<div>
		${board.content}
	</div>
	
	<hr>
	
	<div>
	
		<%-- # url과 파라미터 ------------------------------------------------------------------------------------------------------------------------ --%>
		<%-- * 생성, 편집 삭제가 post방식인 이유 : 편집하려는 주소와 게시글번호만 알면 url을 이용해서 삭제, 편집화면에 들어가 수정이 가능하다 -- *
		    - 해결 : post 방식으로 처리한다
		    - 이유 : post방식은 파라미터 전송하는 값을 url에 노출시키지 않는다(즉, 파라미터로 전달되는 게시글번호를 안보이게 만들 수 있다)
		--%>
	
		<%-- * 삭제버튼 post 방식으로 적용하는법 : 1) form태그로 버튼 감싸기, 2) 버튼의 타입을 submit 3) hidden을 이용해 form으로 전달할 파라미터 생성--%>
		<form id="frm_btn" method="post">
			
			<%-- * input type="hidden"
			(1) 수정화면으로 이동하기 위한 상세보기 쿼리문 전달을 위한 board_no 필요
			(2) 삭제를 위해 전송할 게시글 번호값(name, value 속성 둘다 필요) --%>
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			
			<input type="button" value="편집" id="btn_edit">	<%-- # 편집 화면 이동 --%>
			<input type="button" value="삭제" id="btn_remove">	<%-- # 삭제 요청 --%>
			<input type="button" value="목록" id="btn_list">	<%-- # 목록 이동 --%>
			
		</form>
	</div>


</body>
</html>