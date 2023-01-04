<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
	* {
		box-sizing: border-box;
		padding: 0;
		margin: 0;
		color: #333;
	}
	a {
		text-decoration: none;
	}
	h1 {
		margin-top: 30px;
		text-align: center;
	}
	.btn_write {
		width: 100px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		margin: 40px auto 20px;
		background-color: teal;
		color: #fff;
		cursor: pointer;
	}
	.btn_write:hover {
		background-color: orange;
	}
	ul {
		list-style: none;
		display: flex;
		flex-wrap: wrap;
		width: 630px;
		margin: 30px auto;
	}
	ul > li {
		width: 200px;
		height: 200px;
		padding-top: 10px;
		margin-top: 10px;
		margin-right: 10px;
		text-align: center;
		border: 1px solid gray;
		border-radius: 5px;
	}
	ul > li > a {
		display: block;
		width: 100%;
		height: 100%;
	}
	ul > li:hover {
		background-color: orange;
	}
</style>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	
	// # 게시글 추가창으로 단순이동하는 이벤트 
	// * 단순이동은 js로, 서비스 요청은 백엔드 영역에서 이루어져야한다
	$(document).ready(function() {
		$('#btn_write').click(function(event) {
			location.href= '${contextPath}/board/write.do';
		})
	}); 

</script>
</head>
<body>
	<h1>게시판 목록</h1>
	
	<%-- 정리 --%>
	<%--
	(1) ${boards} : 서비스로부터 boards 속성을 받아와 저장된 값을 사용하여 목록을 생성
	 => 이때 반복문은 jstl을 사용
	(2) 받아온 boards의 목록 하나당 변수명인 b를 이용해,
	    목록 하나의 게시판 번호명을 파라미터로 전달하면 상세보기창으로 이동하는 서비스 요청
	(3) 자바스크립트를 이용해, 게시판 추가로 단순이동 이벤트를 생성한다
	
	 --%>
	 
	 <%-- <목록페이지의 조건 : 'boards' 속성값을 가져와야한다> --%>
	
	<div class="btn_write" id="btn_write">추가</div>
	<ul>
		<c:forEach items="${boards}" var="b">			<%-- & jstl 사용 --%>
			<li>			
				<a href="${contextPath}/board/detail.do?boardNo=${b.boardNo}">
				<%-- & 설명 : boardNo를 파라미터로 전달하면 상세페이지로 이동 --%>
					<div>${b.title}</div>
					<div>${b.createDate}</div>
				</a>
			</li>
		</c:forEach>
	
	</ul>
	
</body>
</html>