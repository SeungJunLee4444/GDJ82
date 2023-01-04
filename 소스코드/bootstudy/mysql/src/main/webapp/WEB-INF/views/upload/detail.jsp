<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
<!-- jquery-ui 사용을 위한 js와 css -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<style>
	a {
		text-decoration: none;
	}
</style>
<script>

	$(function(){
		
		// 게시글 수정화면으로 이동
		$('#btn_upload_edit').click(function(event){
			$('#frm_upload').attr('action', '${contextPath}/upload/edit');
			$('#frm_upload').submit();
		});
		
		// 게시글 삭제
		$('#btn_upload_remove').click(function(event){
			if(confirm('첨부된 모든 파일이 함께 삭제됩니다. 삭제할까요?')){
				$('#frm_upload').attr('action', '${contextPath}/upload/remove');
				$('#frm_upload').submit();
			}
		});
		
		// 게시글 목록
		$('#btn_upload_list').click(function(event){
			location.href = '${contextPath}/upload/list';
		});
		
		// 첨부 이미지에 툴팁 적용
		// 태그의 title 속성 값이 툴팁으로 나타남
		$('.attach_img').tooltip();
		
	});

</script>
</head>
<body>

	<div>
		<h1>업로드 게시글 정보</h1>
		<ul>
			<li>제목 : ${upload.title}</li>
			<li>내용 : ${upload.content}</li>
			<li>작성일 : ${upload.createDate}</li>
			<li>수정일 : ${upload.modifyDate}</li>
		</ul>
		<div>
			<form id="frm_upload" method="post">
				<input type="hidden" name="uploadNo" value="${upload.uploadNo}">
				<input type="button" value="게시글편집" id="btn_upload_edit"> 			
				<input type="button" value="게시글삭제" id="btn_upload_remove"> 			
				<input type="button" value="게시글목록" id="btn_upload_list"> 			
			</form>
		</div>
	</div>
	
	<hr>
	
	<div>
		<h3>첨부 목록 및 다운로드</h3>
		<div>
			<c:forEach items="${attachList}" var="attach">
				<a href="/upload/download?attachNo=${attach.attachNo}">
					<c:if test="${attach.hasThumbnail == 1}">
						<img src="/upload/display?attachNo=${attach.attachNo}" class="attach_img" title="${attach.origin}">
					</c:if>
					<c:if test="${attach.hasThumbnail == 0}">
						<img src="/resources/images/attach.png" width="50px" class="attach_img" title="${attach.origin}">
					</c:if>
				</a>
			</c:forEach>
		</div>
		<br><br>
		<div>
			<a href="/upload/downloadAll?uploadNo=${upload.uploadNo}">모두 다운로드</a>
		</div>
	</div>
	
</body>
</html>