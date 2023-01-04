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
	// # show() , hide() : 제이쿼리로 보여주기, 안보이게하기

	$(function() {
		
		fn_showHide();
		fn_pwCheck();
		fn_pwCheckAgain();
		fn_pwSubmit();
		
		
	});
	
	// # 전역변수 : 함수 안에 들어가선 안된다
	var pwPass = false;
	var rePwPass = false;
	
	
	// [[ 화면 보여주기, 숨기기 함수
	function fn_showHide() {
		
		// # 펑소에는 숨겨있음
		$('#modify_pw_area').hide();
		
		// # 버튼을 누르면 화면을 보여준다
		$('#btn_edit_pw').click( function() {
			fn_init();
			$('#modify_pw_area').show();
			});
		
		// # 취소버튼 : 입력된 내용 비우고, 화면 가리기
		$('#btn_edit_pw_cancel').click(function() {
			fn_init();
			$('#modify_pw_area').hide();
			// * 가리기만하면 기존의 입력된 내용들은 남게된다
			
		});
		
		
	}
	
	function fn_init() {
		$('#pw').val('');
		$('#re_pw').val('');
		$('#msg_pw').val('');
		$('#msg_re_pw').val('');
	}
	
	
	// 2. 패스워드
	function fn_pwCheck(){
		
		$('#pw').keyup(function(){
			
			// 입력한 패스워드
			let pwValue = $(this).val();
			
			// 정규식(8~20자, 소문자+대문자+숫자+특수문자8종(!@#$%^&*) 3개 이상 조합)
			let regPw = /^[0-9a-zA-Z!@#$%^&*]{8,20}$/;
			
			// 3개 이상 조합 확인
			let validatePw = /[0-9]/.test(pwValue)        // 숫자가 있으면 true, 없으면 false
			               + /[a-z]/.test(pwValue)        // 소문자가 있으면 true, 없으면 false
			               + /[A-Z]/.test(pwValue)        // 대문자가 있으면 true, 없으면 false
			               + /[!@#$%^&*]/.test(pwValue);  // 특수문자8종이 있으면 true, 없으면 false
			
			// 정규식 및 3개 이상 조합 검사
			// * 자바스크립트에서 boolean은 true는 1, false는 0으로 변환될 수 있다
			if(regPw.test(pwValue) == false || validatePw < 3){
				$('#msg_pw').text('8~20자의 소문자, 대문자, 숫자, 특수문자(!@#$%^&*)를 3개 이상 조합해야 합니다.');
				pwPass = false;
			} else {
				$('#msg_pw').text('사용 가능한 비밀번호입니다.');
				pwPass = true;
			}
			               
		});  // keyup
		
	}  // fn_pwCheck
	
	// 3. 패스워드 확인
	function fn_pwCheckAgain(){
		
		$('#re_pw').keyup(function(){
			
			// 입력한 패스워드 확인
			let rePwValue = $(this).val();
			
			// 패스워드와 패스워드 재입력 검사
			if(rePwValue != '' && rePwValue != $('#pw').val()){
				$('#msg_re_pw').text('비밀번호를 확인하세요.');
				rePwPass = false;
			} else {
				$('#msg_re_pw').text('');
				rePwPass = true;
			}
			
		});  // keyup
		
	}  // fn_pwCheckAgain
	
	
	function fn_pwSubmit() {
		
		$('#frm_edit_pw').submit(function(event) {
			if(pwPass == false || rePwPass == false) {
				alert('비밀번호 입력을 확인하세요');
				event.preventDefault();
				return;
			}
		});
		
		
		
	}
	


</script>

</head>
<body>

	<div>
	
		<%-- [[[ 상세페이지 --%>
		<%-- # 이벤트 : 비밀번호 변경 --%>
	
		<h1>마이페이지</h1>

		<div>
			<input type="button" value="비밀번호변경" id="btn_edit_pw">
		</div>
		<div id="modify_pw_area" class="edit">
			<form id="frm_edit_pw" action="${contextPath}/user/modify/pw" method="post">
			
					<!-- 패스워드 -->
				<div>
					<label for="pw">패스워드*</label>
					<input type="password" name="pw" id="pw">
					<span id="msg_pw"></span>
				</div>
				
				<!-- 패스워드 재확인 -->
				<div>
					<label for="re_pw">패스워드 확인*</label>
					<input type="password" id="re_pw">
					<span id="msg_re_pw"></span>
				</div>	
				
				<div>
					<button>비밀번호 변경</button>
					<input type="button" value="취소" id="btn_edit_pw_cancel">
				</div>
			</form>
		</div>
	
	</div>
	
	
	


	

</body>
</html>