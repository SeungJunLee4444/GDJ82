<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>post 요청</title>		<%-- # post요청, 'json방식으로 ajax요청' : @responseBody 사용 --%>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function() {
		
		$('#btn1').click(function() {
			fn_ajax1();
		});
		$('#btn2').click(function() {
			fn_ajax2();
		});
	});

	
	// [[[ 제이슨 데이터를 서버로 보내기 
	// - 방법 : JSON.Stringify() : 자바스크립트를 json으로 변환시켜주는 함수를 이용
	// - 조건 : json 데이터를 보낼떄는 post방식을 써야한다
	function fn_ajax1(){
		
		$('#result').empty();
		
		$.ajax({
			
			/* 요청 */
			
			url: '${contextPath}/contact/detail1',
			
			// JSON 데이터를 서버로 보낼 때는 반드시 post방식을 사용해야 함
			type: 'post',
			
			// data에 파라미터가 없음을 주의!
			// 파라미터로 전달되지 않기 때문에 주소창을 이용한 get방식이 불가능함
			data: JSON.stringify({
				'name': $('#name').val(),
				'tel': $('#tel').val()
			}),
			
			// 서버로 보내는 JSON 데이터의 MIME-TYPE을 작성해 줌
			contentType: 'application/json',
			
			/* 응답 */
			
			dataType: 'json',
			success: function(resData){
				var ul = '<ul>';
				ul += '<li>' + resData.name + '</li>';
				ul += '<li>' + resData.tel + '</li>';
				ul += '</ul>';
				$('#result').html(ul);
			}
			
		});  // ajax
		
	}  // function
	
	function fn_ajax2(){
			
			$('#result').empty();
			
			$.ajax({
				
				/* 요청 */
				
				url: '${contextPath}/contact/detail2',
				
				// JSON 데이터를 서버로 보낼 때는 반드시 post방식을 사용해야 함
				type: 'post',
				
				// data에 파라미터가 없음을 주의!
				// 파라미터로 전달되지 않기 때문에 주소창을 이용한 get방식이 불가능함
				data: JSON.stringify({
					'name': $('#name').val(),
					'tel': $('#tel').val()
				}),
				
				// 서버로 보내는 JSON 데이터의 MIME-TYPE을 작성해 줌
				contentType: 'application/json',
				
				/* 응답 */
				
				dataType: 'json',
				success: function(resData){
					var ul = '<ul>';
					ul += '<li>' + resData.name + '</li>';
					ul += '<li>' + resData.tel + '</li>';
					ul += '</ul>';
					$('#result').html(ul);
				}
				
			});  // ajax
			
		}  // function
</script>

</head>
<body>
	<%-- # name, tel 파라미터 전달화면 --%>
	<form id="frm_contact">
		
		<div>
			<label for="name">이름</label>
			<input type="text" name="name" id="name">
		</div>
		
		<div>
			<label for="tel">전화번호</label>
			<input type="text" name="tel" id="tel">
		</div>
		
		<div>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
		</div>
		
	</form>
	
	<%-- # 결과화면 --%>
	<div id="result"></div>
	

</body>
</html>