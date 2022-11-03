<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <% String contextPath = request.getContextPath(); %> --%>
<%-- & ${contextPath}로 쓸수없는 이유
=> 4개의 영역 아무곳에도 저장하지 않았기 때문
 --%>
 <% String contextPath = request.getContextPath();
 pageContext.setAttribute("contextPath", contextPath);
 %>
 <%-- & el은 라이브러리 없이도 사용가능하며, c:set이 동일한 기능을 처리한다
 	  & 자바코드를 태그로 바꾼것이 jstl
  --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../assets/css/member.css">
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>

	// = onload = function() {} (자바스크립트)
	$(document).ready(function(){
		fn_init();
		fn_getAllMembers();
		fn_getMember();
		fn_registration();
		fn_modify();
		fn_remove();
	});
	
	// # 초기화함수
	function fn_init(){
		$('#id').val('').prop('readonly', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#grade').val('');
		$('#address').val('');
	}
	
	function fn_getAllMembers(){
		$.ajax({
			/* 요청 */
			type: 'get',
			url: '${contextPath}/member/list.do',
			/* 응답 */
			dataType: 'json',
			success: function(resData){  // resData : {"count": 3, "members": [{}, {}, {}]}
				// 1. resData.count, resData['count']
				$('#count').text(resData.count);
				// 2. member_list 영역 초기화
				$('#member_list').empty();
				// 3. resData.members : [{}, {}, {}]
				//    $.each(배열, function(인덱스, 배열요소){})
				$.each(resData.members, function(i, member){
					var tr = '<tr>';
					tr += '<td>' + member.memberNo + '</td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.grade + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="hidden" value="' + member.memberNo + '"><input type="button" value="조회" class="btn_detail"><input type="button" value="삭제" class="btn_remove"><input type="hidden" value="' + member.memberNo + '"></td>';
					tr += '</tr>';
					$('#member_list').append(tr);
				});
			}
		});
	}
				// & input type=hidden을 쓰는 이유
				// 1) btn_detail의 ajax의 파라미터값으로 멤버번호값이 필요하다
				// 2) input type=hidden은 보이지않는 버튼값이다(사용자에게 보이지않지만 파라미터 전송이 가능)
				// 3) 조회 버튼을 누르면, 파라미터값으로 이전 태그(prev())인 input type=hidden의 val()값을 호출해 저장하는 함수를 생성
				// 이런복잡한 구조를 만든 이유는, btn_detail이 정적 버튼이 아니라 동적 버튼이기 때문이다
				
				
			
				// & 제이쿼리
				// html : 원래 있는 내용을 지워서 추가
				// append : 원래있는 내용에 추가
				
				// & btn_detail은 id대신 class를 사용해야한다
				// - 상황 : 동일한 버튼이 3개 만들어질 예정
				// - 동일한 id는 불가능, class는 가능
		
			/* => 등록되있는 3명의 데이터를 확인할 수 있다 */
			
	
	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	// & 동적버튼 : 이벤트에 의해 생성된 버튼은 일반적인 버튼 이벤트로는 작동하지않는다
	// => 해결을 위해서는 on()에 정적요소 부모를 추가해야한다
	// - 형태 : $('부모요소').on(이벤트타입, 이벤트대상, 이벤트리스너)
	
	// 목적 : btn_detail로  조회를 누르면, db에 저장된 해당 데이터를 텍스트창에 호출한다 
	
	// 1) 실패
		
			/* 	$('.btn_detail').click(function()  */
			
	// 2) 성공
		function fn_getMember(){
		
		// "조회" 버튼은 동적 요소이기 때문에 다음 이벤트 방식을 사용해야 한다.
		// $(부모요소).on(이벤트타입, 이벤트대상, 이벤트리스너)
		
		$('body').on('click', '.btn_detail', function(){
			$.ajax({
				/* 요청 */
				type: 'get',
				url: '${contextPath}/member/detail.do',
				data: 'memberNo=' + $(this).prev().val(),
				/* 응답 */
				dataType: 'json',
				success: function(resData){  // resData : {"exists": true, "member": {"id": "user3", ...}}
					if(resData.exists) {
						alert('회원 정보가 조회되었습니다.');
						$('#id').val(resData.member.id).prop('readonly', true);
						$('#name').val(resData.member.name);
						$(':radio[name=gender][value=' + resData.member.gender + ']').prop('checked', true);
						$('#grade').val(resData.member.grade);
						$('#address').val(resData.member.address);
						$('#memberNo').val(resData.member.memberNo);	// input type=hidden
					} else {
						alert('조회된 회원 정보가 없습니다.');
					}
				}
			});
		});
	}
	
	
	// & ajax가 ajax를 내부에서 부르면 promise의 대상이 될 수 있다
	
	// & insert, update는 post로 진행
	// # 신규등록 버튼을 누를 때(정적요소) 이벤트 발생
function fn_registration(){
		
		$('#btn_add').click(function(){
			
			$.ajax({
				/* 요청 */
				type: 'post',
				url: '${contextPath}/member/add.do',
				data: $('#frm_member').serialize(),  // serialize() : 폼의 모든 입력 요소를 파라미터로 변환
				/* 응답 */
				dataType: 'json',
				// 정상 응답
				success: function(resData){  // resData : {"isSuccess": true}
					if(resData.isSuccess){
						alert('신규 회원이 등록되었습니다.');
						fn_getAllMembers();  // 목록을 새로 가져와서 갱신함
						fn_init();  // 입력된 데이터를 초기화
					} else {
						alert('신규 회원 등록이 실패했습니다.');
					}
				},
				// 예외 응답
				error: function(jqXHR){  // 예외 처리 응답 데이터(일반 텍스트)는 jqXHR 객체의 responseText 속성에 저장됨
					alert(jqXHR.responseText);
				}
			});  // ajax
			
		}); // click
		
	}  // function
	
	
	function fn_modify(){
		
		$('#btn_modify').click(function(){
			
			$.ajax({
				/* 요청 */
				type: 'post',
				url: '${contextPath}/member/modify.do',
				data: $('#frm_member').serialize(),
				/* 응답 */
				dataType: 'json',
				success: function(resData){  // resData : {"isSuccess": true}
					if(resData.isSuccess){
						alert('회원 정보가 수정되었습니다.');
						fn_getAllMembers();  // 수정된 내용이 반영되도록 회원목록을 새로 고침
					} else {
						alert('회원 정보 수정이 실패했습니다.');
					}
				},
				error: function(jqXHR){
					alert(jqXHR.responseText);
				}
			});  // ajax
			
		});  // click
		
	}  // function
	
	
	// # memberNo를 이용한 멤버삭제
	// - 번호는 조회사항에 존재하지않는다
	// - input type=hidden을 이용해 해당 태그에 조회로 번호를 value값을 저장
	// - btn_remove의 next()인 input type=hidden의 value값, 즉 memberNo값을 꺼내서 삭제할 회원번호로 넘겨준다

	function fn_remove() {
		// 목록 삭제와 조회삭제는 기본적으로 기능이 같다
		// 새롭게 삭제버튼을 생성하고,
		// class 속성부여만으로 해결이 가능해짐
		// 동적 버튼이니 on 메서드사용
		$('body').on('click', '.btn_remove', function() {
			
		
	
			if(confirm('삭제할까요?') == false) {
				return; // 코드진행을 막는것
			}
			$.ajax({
				/* 요청 */
				type : 'get',
				url : '${contextPath}/member/remove.do',
				data : 'memberNo=' + $(this).next().val(),
				
				/* 응답 */
				dataType : 'json',
				success : function(resData) {
					if(resData.isSuccess) {
						alert('회원정보가 삭제되었습니다');
						fn_getAllMembers();
						fn_init();	// 초기화
					} else {
						alert('회원정보 삭제가 실패했습니다');	// 없는번호일때
					}
				},
				error : function(jqXHR) {
					// 잘못된 회원번호가 전달되었습니다가 전달된상태		// 잘못된번호일때
					alert(jqXHR.responseText);
					
				}
			})
		});
	}
	
</script>
</head>
<body>
	<div class="wrap">
		<h1 class="title">회원관리</h1>
		<form id="frm_member">
			<label for="id">아이디</label>
			<div class="ipt_area">
				<input type="text" id="id" name="id" class="frm_member_ipt">
			</div>
			<label for="name">이름</label>
			<div class="ipt_area">
				<input type="text" id="name" name="name" class="frm_member_ipt">
			</div>
			<label>성별</label>
			<div class="gender_area">
				<label for="male">
					남자
					<input type="radio" id="male" name="gender" value="M">
				</label>
				<label for="female">
					여자
					<input type="radio" id="female" name="gender" value="F">
				</label>
			</div>
			<label for="grade">회원등급</label>
			<div class="ipt_area">
				<select id="grade" name="grade"  class="frm_member_ipt">
					<option value="">등급선택</option>
					<option value="gold">골드</option>
					<option value="silver">실버</option>
					<option value="bronze">브론즈</option>
				</select>
			</div>
			<label for="address">주소</label>
			<div class="ipt_area">
				<input type="text" id="address" name="address"  class="frm_member_ipt">
			</div>
			<div class="btn_area">
				<input type="button" value="초기화" class="btn_primary" onclick="fn_init();">
				<input type="button" value="신규등록" id="btn_add" class="btn_primary">
				<input type="button" value="변경내용저장" id="btn_modify" class="btn_primary">
				<input type="button" value="회원삭제" id="btn_remove" class="btn_primary btn_remove">
				<input type="hidden" id="memberNo">
			</div>
		</form>
		<hr>
		<table class="member_table">
			<caption>전체 회원수 : <span id="count"></span>명</caption>
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>등급</td>
					<td>주소</td>
					<td></td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
		</table>
	</div>

</body>
</html>