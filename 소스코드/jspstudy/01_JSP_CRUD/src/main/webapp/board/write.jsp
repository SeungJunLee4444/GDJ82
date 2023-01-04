<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가화면</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	$(document).ready(function(){
		// # 서브밋 이벤트 : 제목이 빈값일 경우 서브밋 정지
		// - 이유 : sql 칼럼에서 title은 not null 이기 때문이다
		$('#frm_write').submit(function(event){
			if($('#title').val() == ''){
				alert('제목은 필수입니다.');
				$('#title').focus();
				event.preventDefault();
				return;
			}
		});
		
		// # 목록이벤트 : 목록 버튼을 돌아가면 목록으로 돌아간다
		$('#btn_list').click(function(event){
			location.href = '${contextPath}/board/list.do';
		});
		
	});
</script>
</head>
<body>
	<%-- # 추가화면 --%>
	<%-- - 경우 : list.jsp에서 write.jsp로 이동하는 경우 --%>
	
	<%-- & 요청 : 파라미터로 title과 content를 보내면 반환값 없이 db에 등록해라
		- 받아와야할 속성값 : 파라미터로 요청하는 화면이니 필요없다
		
		* mvc 화면들의 특징 : 요청과 반환*-----------------------------------------------------*
		(1) 결과를 보이는 화면 : 반환받은 값을 띄우기 위해 request에 저장된 속성값들이 필요하다
		(2) 요청하는 화면 : 요청하기 위해 a링크 또는 form태르를 사용하는 경우가 많다
							+ 요청시 파라미터를 사용해 전달한다
	 	//-------------------------------------------------------------------------------------*
	 --%>
	
	<%-- 정리 --%>
	<%-- 
	(1) form 태그를 이용한 add.do 추가요청
	(2) insert의 경우 post 방식을 사용한다
	(3) 칼럼은 총 4개, 이중 직접 전달해야하는 파라미터는 제목과 내용 2개(나머지는 시퀸스, sysdate)
	(4) 반환?
	
	
	 --%>
	<h1>게시글 작성 화면</h1>
	<div>
		<form id="frm_write" action="${contextPath}/board/add.do" method="POST">
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="content">내용</label>
				<br>
				<textarea id="content" name="content" rows="5" cols="30"></textarea>
			</div>
			<div>
				<input type="submit" value="작성완료">
				<input type="reset" value="다시작성">
				<input type="button" value="목록" id="btn_list">
			</div>
		</form>
	</div>
	

</body>
</html>