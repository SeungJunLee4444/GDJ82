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

<%-- # 언어팩  --%>
<script src="${contextPath}/resources/lang/summernote-ko-KR.min.js"></script>
<script>

	$(document).ready(function() {
		
		
		// # 웹 에디터 -------------------------------------------------------
		$('#content').summernote({
			width : 800,
			height : 400,
			lang : 'ko-KR',
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']]
			]
			
		});
		
		// # 목록 돌아가기 -------------------------------------------------
		$('#btn_list').click(function(){
			location.href = '${contextPath}/board/list';
		});
		
		// # 서브밋 방지 : write와 title이 작성되지 않으면 서브밋 멈춤 -----
		// - 이유 : sql에서 title, write는 필수로 지정했음
		$('#frm_board').submit(function(event) {
			if($('#title').val() == '') {
				alert('제목은 필수다 ')
				event.preventDefault();
				return;
			}
			
		});
		
		
	});
</script>

</head>
<body>

	<%-- # 게시글 수정 화면 : modify 요청으로 수정 요청 --%>
	<%-- * 생성 : 게시글 수정화면은 게시글 생성화면을 그대로 가져와 수정하면된다
		- 수정할내용
		(1) 작성자 : readonly 처리
		(2) value 속성으로 기존의 게시글 내용 표시하기 
		(3) 서브밋 방지 수정
	
	 --%>

	
	<div>
		<h1>수정화면</h1>
		<form id="frm_board" action="${contextPath}/board/modify" method="post">	<%-- & 게시글을 추가해야하니 form 요청 사용 : name 속성 필요 --%>
			<input type="hidden" name="board_no" value="${board.board_no}">
			
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="${board.title}">
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" value="${board.writer}" readonly>
			</div>
			<div>
				<label for="content">컨텐츠</label>
				<textarea id="content" name="content">${board.content}</textarea>	<%-- & textarea는 value를 사용하지않는다 : 내용은 text() 영역에 --%>
			</div>
			<div>
				<button>수정완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		
		
		</form>
	</div>



</body>
</html>