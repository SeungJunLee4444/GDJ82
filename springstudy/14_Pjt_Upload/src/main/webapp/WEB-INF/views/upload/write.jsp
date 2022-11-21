<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(function() {
		
		fn_fileCheck();
	})
	
	function fn_fileCheck() {
		
		// [[ changes : 파일첨부 이벤트는 change
		$('#files').change(function() {
			
			// # 첨부가능한 파일의 최대 크기
			let maxSize = 1024 * 1024 * 10; // 10MB
			
			// # 첨부된 파일 목록
			let files = this.files;
			
			// # 첨부된 파일 순회
			for(let i = 0; i < files.length; i++) {
				
				// & 크기 체크 : 하나라도 10MB이상이 되면 파일첨부 실행X
				if(files[i].size > maxSize) {
					alert('10MB 이상의 파일은 첨부할 수 없습니다');
					$(this).val('');	// & 첨부된 파일 모두 없애기
					return;
				}
				
			}
			
		});
	}


</script>
</head>
<body>
	<%-- <14장 : 다중 첨부 게시판> --%>
	<%-- - uploadDTO 참고> : title, content --%>
	<%-- - 다중파일 첨부 : input의 type을 file로, multiple 속성 부여 --%>


<div>
	
		<h1>작성화면</h1>
		
		<form action="${contextPath}/upload/add" method="post" enctype="multipart/form-data">
		
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title" required="required">
			</div>
			<div>
				<label for="content">내용</label>
				<input type="text" id="content" name="content">
			</div>
			<div>
				<label for="files">첨부</label>
				<input type="file" id="files" name="files" multiple="multiple">
			</div>
			<div>
				<button>작성완료</button>
				<input type="button" value="목록" onclick="location.href='${contextPath}/upload/list'">
			</div>
		
		</form>
	
	</div>

	
</body>
</html>