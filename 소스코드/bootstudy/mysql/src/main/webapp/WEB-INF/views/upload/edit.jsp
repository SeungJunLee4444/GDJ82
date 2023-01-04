<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" integrity="sha512-MV7K8+y+gLIBoVD59lQIYicR65iaqukzvf/nwasF0nqhPay5w/9lJmVM2hMDcnK1OnMGCdVK+iQrJ7lzPJQd1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(function(){
		fn_fileCheck();
		fn_removeAttach();
	});
		
	function fn_fileCheck(){
		
		$('#files').change(function(){
			
			// 첨부 가능한 파일의 최대 크기
			let maxSize = 1024 * 1024 * 10;  // 10MB
			
			// 첨부된 파일 목록
			let files = this.files;
			
			// 첨부된 파일 순회
			$('#file_list').empty();
			$.each(files, function(i, file){
				
				// 크기 체크
				if(file.size > maxSize){
					alert('10MB 이하의 파일만 첨부할 수 있습니다.');
					$(this).val('');  // 첨부된 파일을 모두 없애줌
					return;
				}
				
				// 첨부 목록 표시
				$('#file_list').append('<div>' + file.name + '</div>');
				
			});
			
		});
		
	}
	
	function fn_removeAttach(){
		// 첨부 삭제
		$('.lnk_remove_attach').click(function(event){
			if(confirm('첨부 파일을 삭제할까요?') == false){
				event.preventDefault();
				return;
			}
		});
	}
	
</script>
</head>
<body>

	<div>
	
		<h1>수정화면</h1>
		
		<form action="/upload/modify" method="post" enctype="multipart/form-data">
		
			<div>
				<button>수정완료</button>
				<input type="button" value="목록" onclick="location.href='/upload/list'">
			</div>

			<input type="hidden" name="uploadNo" value="${upload.uploadNo}">
		
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" value="${upload.title}" required="required">
			</div>
			<div>
				<label for="content">내용</label>
				<input type="text" id="content" name="content" value="${upload.content}">
			</div>
			<div>
				<label for="files">첨부 추가</label>
				<input type="file" id="files" name="files" multiple="multiple">
				<div id="file_list"></div>
			</div>
		
		</form>
		
		<div>
			<h3>첨부삭제</h3>	
			<c:forEach items="${attachList}" var="attach">
				<div>
					${attach.origin}<a class="lnk_remove_attach" href="/upload/attach/remove?uploadNo=${upload.uploadNo}&attachNo=${attach.attachNo}"><i class="fa-regular fa-trash-can"></i></a>
				</div>
			</c:forEach>
		</div>
	
	</div>
	
</body>
</html>