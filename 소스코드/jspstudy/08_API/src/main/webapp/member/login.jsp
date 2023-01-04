<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());	// c:set의 역할과 동일하다
	// pageContext : JSP 페이지에 대한 정보를 저장한다.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	$(document).ready(function(){
		$('#btn_refresh').click(function(){
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/member/refreshCaptcha.do',
				/* 응답 */
				dataType: 'json',
				success: function(resData){  // resData : {"dirname": "", "filename": ""}
					$('#ncaptcha').prop('src', '../' + resData.dirname + '/' + resData.filename);
					$('#key').val(resData.key);
				}
			});
		});
	});
</script>


</head>
<body>
								<%--# 캡차화면 제작 --%>
	<div class="wrap">
		<h1>로그인</h1>
		<form action="${contextPath}/member/validateCaptcha.do" method="post">
			<div>
				<input type="text" name="id" id="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pw" id="pw" placeholder="패스워드">
			</div>
			<div>
				<p>아래 이미지를 보이는 대로 입력해주세요</p>
				<div style="display :flex;">
					<div>
						<img src="../${dirname}/${filename}" id="ncaptcha">	<!-- 이미지는 절대경로가 아닌 상대경로 -->
					</div>
					<%-- & 이미지의 경로 : 상대경로로 작성해야한다
					../ member의 윗폴더 webapps => 이후 ncapcha 폴더로 이동
					 --%> 
					<div>
						<input type="button" value="새로고침" id="btn_refresh">
					</div>
				</div>
			</div>
			<div>
				<input type="text" name="value" placeholder="자동입력 방지문자">		<%--=> value 파라미터는 검증에 사용 --%>
				<input type="text" id="key" name="key" value="${key}">				<%--& hidden, 확인할려면 text --%>
			</div>
			<div>
				<button>로그인</button>	
				<%--# 사용자 입력값 검증 요청 : code, key, value 파라미터 전달
				이미지별로 키값이 다름
				 --%>
			</div>
		</form>
	</div>
								<%--# 화면넘기기미션 --%>
	

</body>
</html>