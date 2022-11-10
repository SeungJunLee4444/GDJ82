<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴 페이지</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<%-- # 상세페이지로 파라미터로 데이터 전달 --%>

	<img src="${contextPath}/resources/images/hooray.jpg" alt="두손번쩍" width="200px">
	
	<hr>
	
	<%-- # member로 시작하는 맵핑   --%>
	
	<h3>Member 관련 요청</h3>			<%-- & 파라미터와 같이 보내기 --%>
	<%-- 1) a링크와 파라미터 전송 --%>
	<%-- - 요청방식 : get 방식(a링크는 기본적으로 get방식이다)
		 & getMapping으로 받아야한다
	--%>
	<h5>a태그</h5>
	<div>
		<a href="${contextPath}/member/detail1">전송</a>
	</div>
	
	<hr>
	
	<%-- 2) location.href 와 파라미터 전송--%>
	<%-- - 요청방식 : get 방식 --%>
	<h5>location.href</h5>
	<div>
		<input type="button" value="전송" id="btn">
	</div>
	<script>
		$('#btn').click(function() {
			
			// # 전달할 파라미터가 있는 경우
			// location.href='${contextPath}/member/detail2?id=admin&pw=1234';
			// # 전달하는 파라미터가 없을 경우
			// location.href='${contextPath}/member/detail2'; 
			// # 어노테이션 생략 후 파라미터가 있는경우
			location.href='${contextPath}/member/detail3?id=admin&pw=1234';
			// # 어노테이션 생략 후 파라미터가 없는경우
			//location.href='${contextPath}/member/detail3';
		});
	</script>	
	
	<hr>
	
	<%-- 3) form태그  --%>
	<h5>form태그</h5>
	<form action="${contextPath}/member/detail4" method="post">	<%-- get/post --%>
		<div>
			<input type="text" name="id" placeholder="아이디">
		</div>
		<div>
			<input type="text" name="pw" placeholder="패스워드">
		</div>
		<button>전송</button>
	</form>
		
	<hr>
	
	
	<%-- # board로 시작하는 맵핑   --%>
	<%-- 1) a링크 요청   --%>
	<%-- (1) 리다이렉트1(백엔드영역) =>   --%>
	<div>
		<a href="${contextPath}/board/detail1?title=gongji&hit=10">전송</a>
	</div>
	<%-- (2) 리다이렉트2(백엔드영역, 값을 전송하는) =>   --%>
	<div>
		<a href="${contextPath}/board/detail3?title=gongji&hit=10">전송</a>
	</div>
	
	
	
	
	
</body>
</html>