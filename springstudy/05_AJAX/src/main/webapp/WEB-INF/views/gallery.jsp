<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>갤러리</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<h1>예쁜 동물을 보고 가세요</h1>
	
	<%-- # 이미지 요청 : 절대경로의 이미지를 요청해서 img 태그로 표시 --%>
	<%-- * img 태그는 절대경로(C:\GDJ\images\animal1.jpg)는 사용불가능하다, 상대경로로 작성해야한다 --%>
	
	<%-- => 해석 : image/display는 맵핑 + 나머지는 파라미터 --%>
	<div id="galleries">
	</div>
	<script>
	
	// # 이미지 10개 출력하기 : 자바스크립트의 for문을 이용해서 해결가능
	// * 현재 이미지들 : animal1.jpg animap2.jpg....
	// * 만약 이미지가 aaa.jpg, bbb.jpg 이런식이면 배열해서 파일이름을 전부 저장해서 사용해야한다
	
		for(let n = 1; n <= 10; n++) {
			$('<div>')
			.append($('<img>')
				.attr('src', '${contextPath}/image/display?path=' + encodeURIComponent('C:\\GDJ\\images') + '&filename=animal'+ n +'.jpg')		<%-- & encodeURIcomponent : 인코딩을 위한 자바스크립트 함수, URI로 데이터를 전달하기 위해서 문자열을 인코딩 --%>
				.attr('width', '200px'))
			.appendTo('#galleries');
		}	
	</script>
	<%-- & 요청방법
		(1) 요청
			- 경로 + 이미지를 파라미터로 전송
		(2) 응답
			- 이미지의 byte[] --%>
	
	

</body>
</html>