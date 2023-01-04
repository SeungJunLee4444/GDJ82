<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버</title>				<%-- # 수업 : 스프링 멤버 만들기 --%>
<style>
	#frm_member {
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



	// [[[ ajax 통신 : ajax는 페이지의 전환없이 통신이 가능한 방식이다

	$(document).ready(function() {
		
		$('#btn1').click(function() {
			 fn_ajax1();	
		});
		$('#btn2').click(function() {
			fn_ajax2();
		});
		$('#btn3').click(function() {
			fn_ajax3();
		});
		$('#btn4').click(function() {
			fn_ajax4();
		});
	});
	
	// [[[ 1. 성공시 text응답을 받는 ajax처리
	function fn_ajax1() {
		
		$('#result').empty();	// # 초기화작업
		
		// # detail1 AJAX : text 방식
		$.ajax({
			// # 요청
			type : 'get',
			url : '${contextPath}/member/detail1',	// => 
			data : 'id=' + $('#id').val() + '&pw=' + $('#pw').val(),  	// & 전달할 데이터 : 파라미터에 입력한 id,와 pw
			
			// # 응답
			dataType : 'text',
			success : function(resData) {								// &  성공시 result의 태그에 텍스트 내용에 추가
				$('#result').text(resData)
			},
			error : function(jqXHR) {
				$('#result').text(jqXHR.responseText);					// & 실패시 오류문답
			}
			
		});
		
	}
		
	// [[[ 2. 성공시 text응답을 받는 ajax처리
	function fn_ajax2() {
		
		$('#result').empty();
		
		// # detail2 AJAX : json 방식
		$.ajax({
			
			type : 'get',
			url : '${contextPath}/member/detail2',
			data : $('#frm_member').serialize(),		// & serialize : form태그의 모든 요청 파라미터를 한꺼번에 전달하는 함수
			
			dataType : 'json',
			success : function(resData) {
				var ul = '<ul>';
				ul += '<li>' + resData.id + '</li>';
				ul += '<li>' + resData.pw + '</li>';
				ul += '</ul>';
				$('#result').html(ul);	// 태그를 넣어줘야하니 html()사용
			},
			error : function(jqXHR) {
				$('#result').text(jqXHR.responseText);
			}
		});	
	}
		
	
	// [[[ 3. 성공시 json응답을 받는 ajax처리
	function fn_ajax3() {
		
		$('#result').empty();
			
			// # detail3 AJAX : json 방식
			$.ajax({
				
				type : 'get',
				url : '${contextPath}/member/detail3',
				data : $('#frm_member').serialize(),
				
				dataType : 'json',
				success : function(resData) {
					var ul = '<ul>';
					ul += '<li>' + resData.id + '</li>';
					ul += '<li>' + resData.pw + '</li>';
					ul += '</ul>';
					$('#result').html(ul); 
				}
			});
		}
	
	// [[[ 4. json 데이터로 요청하기 ---- *	
	// - 방법 : JSON.Stringify() : 자바스크립트를 json으로 변환시켜주는 함수를 이용
	// - 조건 : json 데이터를 보낼떄는 post방식을 써야한다
	function fn_ajax4() {
		
		$('#result').empty();
		
		$.ajax({
			// # 요청
			type : 'post',
			url : '${contextPath}/member/detail4',
			
			// data에 파라미터가 없음 : 파라미터로 전달되지 않기 때문에 주소창을 이용한 get 방식이 불가능하다
			data : JSON.stringify({					// * JSON.stringify : 자바스크립트 객체를 넣어주면 json으로 변환시켜준다
				// 제이슨 데이터 직접 만들기
				'id' : $('#id').val(),
				'pw' : $('#pw').val()
			}),
			// # 응답 
			// # 서버로 보내는 json 데이터의 mine-type을 작성
			contentType : 'application/json',
			dataType : 'json',
			success : function(resData) {
				var ul = '<ul>';
				ul += '<li>' + resData.id + '</li>';
				ul += '<li>' + resData.pw + '</li>';
				ul += '</ul>';
				$('#result').html(ul); 
			}
		});
		
	}


</script>
</head>
<body>
	
	<%-- # id,pw 파라미터 전달화면 --%>
	<form id="frm_member">
		<div>
			<label for="id">아이디</label>
			<input type="text" name="id" id="id">
		</div>
		<div>
			<label for="id">패스워드</label>
			<input type="text" name="pw" id="pw">
		</div>
		
		<div>
			<%-- # 파라미터 전달방식에 따른 버튼 3가지 --%>
			<input type="button" value="전송1" id="btn1">
			<input type="button" value="전송2" id="btn2">
			<input type="button" value="전송3" id="btn3">
			<input type="button" value="전송4" id="btn4">
		</div>
	</form>
	
	<%-- # 결과화면 --%>
	<div id="result"></div>

</body>
</html>