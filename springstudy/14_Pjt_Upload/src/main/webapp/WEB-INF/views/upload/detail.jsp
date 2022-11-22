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
		
		// [[ 삭제
		// 1. 첨부파일 삭제
		$('.btn_attach_remove').click(function() {
			
			if(confirm('해당 첨부파일을 삭제할까요?')) {
				location.href = '${contextPath}/upload/attach/remove?uploadNo=' + $(this).data('upload_no') + '&attachNo=' + $(this).data('attach_no');
			}																	// * data 속성 표기법
			
		});
		
	});


</script>

</head>
<body>
	<%-- <14장 : 다중 첨부 게시판> --%>
	<%-- * 나중에 form 태그를 이용해 post 방식으로 바꾸기 --%>
	
	<div>
		<h1>상세보기</h1>	<%-- * 상세보기 : uploadNo 속성에 저장 --%>
		<ul>
			<li>제목   : ${upload.title}</li>
			<li>내용   : ${upload.content}</li>
			<li>작성일 : ${upload.createDate}</li>
			<li>수정일 : ${upload.modifyDate}</li>
		</ul>
		
	</div>
	
	<hr>
	
	<div>
		<h1>첨부목록</h1>  <%-- * 첨부목록 : attachList 속성에 저장 --%>
		<%-- * list로 반환되니 반복문 사용 --%>
		<c:forEach items="${attachList}" var="attach">
			<div>
				<%-- # 클릭하면 다운로드 --%>
				<a href="${contextPath}/upload/download?attachNo=${attach.attachNo}">${attach.origin}</a>
				<%-- # 삭제 --%>
				<input type="button" value="삭제" class="btn_attach_remove" data-upload_no="${upload.uploadNo}" data-attach_no="${attach.attachNo}">
																		<%-- * data-attach_no : 삭제버튼에 특정 속성값 저장 -> 스크립트에서 꺼내쓸 수 있음 --%>
													<%-- * 반복문에서 id는 class로 바꿔야한다 : 복수 존재하기 떄문 --%>
			</div>
		</c:forEach>
	</div>	
</body>
</html>