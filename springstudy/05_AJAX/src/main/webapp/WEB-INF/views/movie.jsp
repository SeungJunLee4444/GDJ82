<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>박스오피스</title>		<%-- # api 실습 페이지 --%>

<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>	<%-- # jquery ui 사용 : 제이쿼리가 먼저, ui가 나중에 --%>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script>	
<link rel="stylesheet" href="${contextPath}/resources/css/images/jquery-ui.min.css"></link>
<script>

	$(document).ready(function() {
		
		<%-- # datepicker 기능 구현 --%>
		$('#targetDt').datepicker({
			dateFormat: 'yymmdd'		<%-- & datepicker 포멧 : 지정한 형식으로 날짜를 초기화
											   * yymmdd : 실제로 yyyymmdd로 적용 --%>
		});	
		
		<%-- & datepicker 
			- 용도 : Datepicker 위젯사용
			- 기능 : 특정 id에 지정하면 클릭시 달력이 펼쳐짐
			- 다운로드주소 : https://jqueryui.com/
		--%>
		
		$('#btn').click(function() {
			$.ajax({
				// # 요청 ---------------------------------------------------------------------------
				type : 'get',
				url : '${contextPath}/movie/boxOfficeList',
				data : 'targetDt=' + $('#targetDt').val(),	// * data : 전달할 파라미터값
				
				// # 응답
				dataType : 'json',					// * string데이터 = json 이라 생각하기-----------
				success : function(resData) {
					// # 기존 목록의 초기화
					$('#boxOfficeList').empty();
					// # 가져온 목록 나타내기
					$.each(resData.boxOfficeResult.dailyBoxOfficeList, function(i, movie){	// * 제이쿼리 배열 $.each(배열, function(i, movie) {});
						$('<tr>')
						.append($('<td>').text(movie.rank))
						.append($('<td>').text(movie.movieNm))
						.append($('<td>').text(movie.openDt))
						.append($('<td>').text(movie.audiCnt))
						.append($('<td>').text(movie.audiAcc))
						.appendTo('#boxOfficeList');	// tr
						
						// * 제이쿼리 배열 : ?
						
					});	// each
				}
			});
		});
	});
</script>
</head>
<body>
	<div>
		<label for="targetDt">조회날짜</label>
		<input type="text" id="targetDt">
		<input type="button" value="조회" id="btn">	<%-- & 서브밋이 아니니 name속성은 필요없다 --%>
	
	</div>

	<hr>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순위</td>					<%-- & table에 받아온 api 데이터를 출력 --%>
					<td>영화제목</td>
					<td>개봉일</td>
					<td>일일관객수</td>
					<td>누적관객수</td>
				</tr>
			</thead>
			<tbody id="boxOfficeList"></tbody>
		</table>
	</div>

</body>
</html>