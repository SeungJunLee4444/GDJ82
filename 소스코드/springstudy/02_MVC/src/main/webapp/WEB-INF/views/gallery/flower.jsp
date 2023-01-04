<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="resources/js/jquery-3.6.1.min.js"></script>	<%-- & jsp의 절대경로는 webapp 최상위 루트 기준이다 --%>
<script>
	$('document').ready(function() {
		$('#title').text('예쁜 꽃 구경하세요');
		$('#image').attr('src', 'resources/images/flower1.jpg').attr('width', '200px');
	});
	


</script>
<body>
	<%-- # 꽃보러가기 jsp : 이미지 보여주기 + js--%>
	
	<h1 id="title"></h1>
	<img id="image">

</body>
</html>