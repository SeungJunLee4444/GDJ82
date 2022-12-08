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

	$(function() {
		
		
		fn_add();		// 추가
		fn_init();		// 초기화
		fn_list();		// 목록
		fn_detail();	// 상세
		fn_modify();	// 수정
		fn_remove();	// 삭제
		
		
	});
	
	// == ajax service : 추가
	function fn_add() {
		
		$('#btn_add').click(function() {
			
			// 1. 추가할 회원정보를 json으로 만든다
			let member = JSON.stringify({
				'id' : $('#id').val(), 
				'name' : $('#name').val(), 
				'gender' : $(':radio[name=gender]:checked').val(),
				'address' : $('#address').val()
			// * JSON.stringify : 제이슨만드는 함수
			// * 자바스크립트의 {중괄호}는 객체를 의미한다
			// * 복수의 radio 처리 ':radio[name=gender]:checked : name속성이 gender인 선택된 radio
			});
			
			// 2. 추가한 회원정보를 db에 보낸다
			$.ajax({
				
				type: 'post',
				url: '${contextPath}/members',
				data: member,							// * 파라미터 이름 없음(본문에 member을 포함)
				contentType: 'application/json',		// * contentType : 내가 보내는 member의 mineType을 보내라는 말			
				
				dataType : 'json',
				success: function(resData) {
					if(resData.insertResult > 0) {
						alert('회원이 등록되었습니다');
						fn_list();
						fn_init();
						
					}
					
				},
				error : function(jqXHR) {
					alert('에러코드(' + jqXHR.status + ')')	// * impl의 catch에서 던져진 setStatus값
				}
				
			});
			
	
		});
		
	}
	
	// # js : 입력 초기화
	function fn_init() {
		$('#id').val('').prop('readonly', false);	// 상세조회후 초기화할때 id란의 readonly 속성을 지워서 다시 아이디를 입력할 수 있도록 처리
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false);
		$('#address').val('');
	}
	
	// # 목록 페이징 처리를 위한 page값 : 파라미터로 전송하는 유일한 값
	var page = 1;
	
	// # ajax service : 목록
	function fn_list() {
		$.ajax({
			
			type: 'get',
			url: '${contextPath}/members/page/' + page,
			contentType: 'application/json',		
			
			dataType : 'json',
			success: function(resData) {
				$('#member_list').empty();
				$.each(resData.memberList, function(i, member) {
					var tr = '<tr>';
					tr += '<td><input type="checkbox" class="check_one" value="'+ member.memberNo +'"></td>';
					tr += '<td>' + member.id + '</td>';
					tr += '<td>' + member.name + '</td>';
					tr += '<td>' + (member.gender == 'M' ? '남자' : '여자') + '</td>';
					tr += '<td>' + member.address + '</td>';
					tr += '<td><input type="button" value="조회" class="btn_detail" data-member_no="'+ member.memberNo +'"></td>';
					tr += '</tr>';
					$('#member_list').append(tr);
				});
				
			},
			error : function(jqXHR) {
				alert('에러코드(' + jqXHR.status + ')')	// * impl의 catch에서 던져진 setStatus값
			}
			
		});
	}
	
	function fn_detail(){
		// * 나중에 만든 버튼
		$(document).on('click', '.btn_detail', function(){
			$.ajax({
				type: 'get',
				url: '${contextPath}/members/' + $(this).data('member_no'),
				dataType: 'json',
				success: function(resData){
					let member = resData.member;
					if(member == null){
						alert('해당 회원을 찾을 수 없습니다.');
					} else {
						$('#memberNo').val(member.memberNo);
						$('#id').val(member.id).prop('readonly', true);
						$('#name').val(member.name);
						$(':radio[name=gender][value='+ member.gender +']').prop('checked', true);
						
						// ? [][] 는 뭐지? : name속성이 gender이고, value값이 member.gender인 radio롤 지칭한다
						$('#address').val(member.address);
					}
				}
			});
		});
	}
	
	
	function fn_modify() {
		$('#btn_modify').click(function () {
			let member = JSON.Stringify({
				memberNo: $('#memberNo').val(),
				name : $('#name').val(),
				gender : $(':radio[name=gender].checked)'), // 두개의 radio중에 체크된 것
				address : $('#address').val()
			});
			$.ajax({
				type : 'put',
				url: '${contextPath}/members',
				data: member,
				contentType : 'application/json',
				
				dataType : 'json',
				success: function(resData) {
					if(resData.updataResult >0 ) {
						alert('회원 정보가 수정되었습니다');
						fn_list();	// 수정후 목록을 다시 뿌려줘야함
					} else {
						alert('수정에 실패했습니다');
					}
				},
				error : function(jqXHR) {
					alert('에러코드(' + jqXHR.status + ')')	// * impl의 catch에서 던져진 setStatus값
				}				
			})
			
			
		});
	}
	
	function fn_remove() {
		
		$('#btn_remove').click(function () {
			if(confirm('선택한 회원을 모두 삭제할까요?')) {
				// 삭제할 회원번호
				let memberNoList = ''
				for(let i = 0; i < $('.check_one').length; i++) {
					if($($('.check_one')[i]).is(':checked'))								
						memberNoList += $($('.check_one')[i]).val() + ',';	// 만약 3과 1을 참고했다면 3,1, 로 출력
						// $('.check_one')[i] : [i]로 인해 자바변수가 됨
						// $()를 한번 더 감싸면 제이쿼리가 된다
						// is 상태확인
						
						// * 마지막 콤마 자르기
						memberNoList = memberNoList.substr(0, memberNoList.length =1);	// substr : 문자열 자르기, substring과 사용법이 다름
						$.ajax({
							type : 'delete',
							url : '${contextPath}/members/' + memberNoList,
							dataType : 'json',
							success : function(resData) {
								if(resData.deleteResult > 0) {
									alert('선택된 회원정보가 삭제되었습니다');
									fn_list();
								} else {
									alert('선택된 회원 정보가 삭제되지 않았습니다');
								}
							}
						});
				}																
			}
		});
		
	}
	
	

</script>
</head>
<body>


	<h1>회원관리</h1>
	<input type="hidden" id="memberNo">

	<div>
			
			<div>
				<label for="id">
					아이디<input type="text" id="id">
				</label>
			</div>
			<div>
				<label for="name">
					이름<input type="text" id="name">
				</label>
			</div>
			
			<div>
				<label for="male">
					남자<input type="radio" id="male" name="gender" value="M">
				</label>
				<label for="female">
					여자<input type="radio" id="female" name="gender" value="F">
				</label>
			</div>
			<div>
				<label for="address">
					주소
					<select id="address">
						<option value="">없음</option>
						<option value="서울">서울</option>
						<option value="경기">경기</option>
						<option value="인천">인천</option>
					</select>
				</label>
			</div>
			<div>
				<input type="button" value="초기화" id="btn_init" onclick="fn_init()"> 
				<input type="button" value="등록하기" id="btn_add"> 
				<input type="button" value="수정하기" id="btn_modify"> 
			</div>
			
			<hr>
			<div>
				<input type="button" value="선택삭제" id="btn_remove"> 
				<table border="!">
					<thead>
						<tr>
							<td><input type="checkbox" id="check_all"></td>
							<td>아이디</td>
							<td>이름</td>
							<td>성별</td>
							<td>주소</td>
							<td></td>
						</tr>
					</thead>
					<tbody id="member_list"></tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div id="paging"></div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			
			
			
		
	
	
	</div>

	

</body>
</html>