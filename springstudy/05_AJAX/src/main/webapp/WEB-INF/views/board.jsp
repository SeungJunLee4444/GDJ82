<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>		<%-- # 실습 : 스프링 게시판 만들기 --%>
<style>
	#frm_board {
		width: 480px;
		margin : 0 auto;
	}
	#label {
		display: inline-block;
		width: 80px;
	}
</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
		
		fn_ajax1();
		fn_ajax2();
		fn_ajax3();
		
	});
		
	function fn_ajax1() {
		
		$('#btn1').click(function() {
			
			$('#result').empty();
			$.ajax({
				
				// # 요청
					type : 'get',
					url : '${contextPath}/board/detail1',
					data : $('#frm_board').serialize(),
				
				// # 응답
					dataType : 'json',
					success : function(resData) {
						$('<ul>')									// & ul 태그 생성
						.append($('<li>').text(resData.title))		// & li 태그 생성
						.append($('<li>').text(resData.content))
						.appendTo('#result');					// * append to : 선택한 요소(자식)을 (부모) 요소 마지막에 넣는다
					},
					error : function(jqXHR) {
						$('#result').text(jqXHR.status);			// & status : 200,300,400번 오류경고숫자	// responseText : 오류구문
					}												// * 200은 통신은 문제없으나 받아온 데이터가 없을때 발생한다?
					
					// * append()와 appendTo의 차이 ----------------------------------------------
					// (1) append	: 선택한 요소의 마지막에 새로운 요소(태그) 또는 컨텐츠를 추가
					// (2) appendTo : 선택한 요소를 to(요소)의 마지막에 추가한다 
				
			});
			
		});
		
	}
	
	function fn_ajax2() {
		
		$('#btn2').click(function() {
			
			$('#result').empty();
			$.ajax({
				
				// # 요청
					type : 'get',
					url : '${contextPath}/board/detail2',
					data : $('#frm_board').serialize(),
					
					dataType : 'json',
					success : function(resData) {
						$('<ul>')
						.append($('<li>').text(resData.title))
						.append($('<li>').text(resData.content))
						.appendTo('#result');
					},
					error : function(jqXHR) {
						if(jqXHR.status == 500) {
							alert('제목은 필수입니다')
						} 
					}
				
				// # 응답
				
			});
			
		});
		
	}
	
	function fn_ajax3() {
		
		$('#btn3').click(function() {
			
			$('#result').empty();
			$.ajax({
				
				// # 요청
					type : 'get',
					url : '${contextPath}/board/detail2',
					data : $('#frm_board').serialize(),
					
					dataType : 'json',
					success : function(resData) {
						$('<ul>')
						.append($('<li>').text(resData.title))
						.append($('<li>').text(resData.content))
						.appendTo('#result');
					},
					error : function(jqXHR) {
						if(jqXHR.status == 500) {
							alert('제목은 필수입니다')
						} 
					}
				
				// # 응답
				
			});
			
		});
		
	}

</script>
</head>
<body>
	
	<%-- # id,pw 파라미터 전달화면 --%>
	<form id="frm_board">
		<div>
			<label for="title">제목</label>
			<input type="text" name="title" id="title">
		</div>
		<div>
			<label for="content">내용</label>
			<input type="text" name="content" id="content">
		</div>
		
		<div>
			<%-- # 파라미터 전달방식에 따른 버튼 3가지 --%>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
		</div>
	</form>
	
	<%-- # 결과화면 --%>
	<div id="result"></div>

</body>
</html>