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
	
	
	
	
	$(document).ready(function(){
	
		
	
		// # service : 목록
		fn_list();
		
		// # service : 검색
		fn_search();
		
		// # service : 초기화 버튼 누르면 전체목록 요청
		$('#btn_init').click(function() {
			fn_list();
		});
		
	});
	
	
	
	
	
	// # service : 목록
	// get요청 
	// url : http://localhost:9090/movie/searchAllMovies
	// 응답타입 : json
	// 응답 : 성공응답만
	
	
	function fn_list() {
		
		$.ajax({
			type : 'get',
			url : '${contextPath}/searchAllMovies',
			dataType : 'json',
			success : function(resData) {
				
				// 1. 알림창 : 목록 개수
				// - 답 : 서비스 요청시 목록 개수에 대한 값을 가져와야한다
				// - count함수 추가
				
				
				
			
				
				// 2. 조회할 목록
				
				$('#list').empty();
				
				$.each(resData, function(i, movie) {
					
					
					$('<tr>')
					.append($('<td>').text(movie.title))
					.append($('<td>').text(movie.genre))
					.append($('<td>').text(movie.description))
					.append($('<td>').text(movie.star))
					.appendTo('#list')
					
				});
			}
		});
		
	}
	
	
	
	// # service : 검색버튼을 누르면 요청
	// get 또는 post 요청
	// url : http://localhost:9090/movie/searchMovie
	// 파라미터 : * get방식이면 파라미터, post방식이면 json 프로퍼티
	// 응답 : 성공 또는 실패 응답
	 function fn_search() {
		
		$('#btn_search').click(function() {
			$.ajax({
				type : 'get',
				url : '${contextPath}/searchMovie',
				data : 'column=' + $('#column').val() + '&searchText=' + $('#searchText').val(),
				dataType : 'json',
				success : function(resData) {
					
					// 1. 알림창 : 목록 개수
					// - 답 : 서비스 요청시 목록 개수에 대한 값을 가져와야한다
					// - count함수 추가
				
						console.log(resData.movieListCnt);
						alert(resData.movieListCnt);
					
						// 2. 조회할 목록
						$('#list').empty();
						$('<tr>')
						.append($('<td>').text(resData.title))
						.append($('<td>').text(resData.genre))
						.append($('<td>').text(resData.description))
						.append($('<td>').text(resData.star))
						.appendTo('#list')
					}, error : function(jqXHR) {
						alert('검색결과가 없습니다');
						$('#list').empty();
						$('<tr>')
						.append($('<td colspan="4">').text('검색결과가 없습니다'))
						.appendTo('#list')
					} 
				});
		});
	}

	
</script>
</head>
<body>

	
	

	<div>
		<%-- # 검색기능 : column과 searchText를 파라미터로 전달 --%>
		<form id="frm_search">
			
			<select id="column" name="column">
				<option value="TITLE">제목</option>
				<option value="GENRE">장르</option>
				<option value="DESCRIPTION">내용</option>
			</select>
			<input type="text" id="searchText" name="searchText">
			<input type="button" id="btn_search" value="검색">
			<input type="button" id="btn_init" value="초기화">
			
			<br><hr><br>
			
			
			<%-- # 테이블 --%>
			<table border="1">
				<thead>
					<tr>
						<td>제목</td>
						<td>장르</td>
						<td>내용</td>
						<td>평점</td>
					</tr>
				</thead>
				<tbody id="list"></tbody>
			</table>
			
		</form>
		
	</div>

</body>
</html>