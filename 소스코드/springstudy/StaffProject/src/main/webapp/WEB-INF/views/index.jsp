<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴페이지</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	$(function() {
		
		// # 사원목록
		list();
		
		// # 사원등록
		add();
		
		// # 상세조회
		detail();
		
		// # 전체목록 다시보기 버튼
		$('#btn_list').click(function() {
			$('#result').empty();
			list();
		})
	});
	
	function list() {
		$.ajax({
			type : 'get',
			url : '${contextPath}/list.json',

			dataType : 'json',
			success : function(resData) {
				$.each(resData, function(i, staff) {
					
					// # 자바스크립트로 작성
					//var tr = '<tr>';
					/* tr += '<td>' + staff.sno + '</td>'; 
					tr += '<td>' + staff.name + '</td>'; 
					tr += '<td>' + staff.dept + '</td>'; 
					tr += '<td>' + staff.salary + '</td>'; 
					tr += '</td>';
					$('#result').append(tr); */
					
					// # 제이쿼리로 작성
					$('<tr>')
					.append($('<td>').text(staff.sno))
					.append($('<td>').text(staff['name']))
					.append($('<td>').text(staff.dept))
					.append($('<td>').text(staff.salary))
					.appendTo('#result')
					
				});
				
			}			
		});
	}
	
	function add() {
		
		$('#btn_add').click(function() {
			// # 정규식 사원번호는 숫자 다섯자리만 가능하다
			if(/^[0-9]{5}$/.test($('#sno').val()) == false) {
				alert('사원번호는 5자리 숫자입니다');
				return;
				// * return : 밑으로 못내려가게 막는다(ajax못하게)
			}
			// * ^$의 의미 : 위를 기준으로, 숫자로 시작해서 숫자로 끝나야한다
			// * 정규식.test(대상) : 정규식 테스트 메서드
			
			// # 부서명을 한글로만 3~5글자
			if(/^[가-힣]{3,5}$/.test($('#dept').val()) == false) {
				alert('부서명은 3~ 5자리 한글입니다');
				return;
				// * return : 밑으로 못내려가게 막는다(ajax못하게)
			}
			
			// # ajax
			$.ajax({
				type : 'post',
				url : '${contextPath}/add',
				data : 'sno=' + $('#sno').val() + '&name=' + $('#name').val() + '&dept=' + $('#dept').val(),
				// * 사원 등록시 파라미터 전송
				// (1) 파라미터 일일히 보내기 : 협업시 가독성을 위해 필요
				// (2) 폼에 있는 name 속성 전부 보내주기 : serialize()
				
				dataType : 'text',
				// # 성공 : try에서 전달
				success : function(resData) {	// '사원등록이 성공헀다'
					alert(resData);	
				
					// # 등록하기전 기존의 등록내용 지우기 : 안쓰면 기존 목록에 그대로 추가해버린다
					$('#result').empty();
					
					// # 새로 등록된 사람이 아래 목록에 나타날 수 있도록 목록함수 다시 호출
					list();
				
					// # 리스트에 등록된 이후 등록창에 입력한 내용 초기화
					$('#sno').val('')
					$('#name').val('')
					$('#dept').val('')
				
				},
				
				// # 실패 : catch에서 전달
				// # 응답실패값도 전달되기 떄문에 error 도 설정해야한다
				error : function(jqXHR) {
					alert(jqXHR.responseText);	// * responseText 응답텍스트
					
				}
				
				// # success, error로 응답 보내는법
				// => 자바에서 try에서 응답을 만들면 success로, catch에서 만들면 error로 전달된다				
			});
			
		})
	}
	
	function detail() {
		
		$('#btn_detail').click(function() {
		
			// # 사원번호 입력이 공백일경우 : 아래 예와방지보다 위에 작성해줘야한다 ---- *
			if($('#no').val() == '') {
				alert('사원번호를 입력하세요');
				return;
			}
			
			// # 사원번호 예외 방지
			if(/^[0-9]{5}$/.test($('#no').val()) == false) {
				alert('사원번호는 5자리 숫자입니다');
				return;
			}
			
			// # ajax
			$.ajax({
				
				type : 'get',
				url : '${contextPath}/detail.json',
				data : 'no=' + $('#no').val(),
				
				datatype : 'json',
				
				success : function(resData) {
					console.log(resData);
					if(resData != '') {
						
					$('#result').empty();
					
					
					// # json 데이터 전달 확인
					// let str = JSON.stringify(resData);
					// alert(str); 
			
					 $('<tr>')
					.append($('<td>').text(resData.sno))
					.append($('<td>').text(resData.name))
					.append($('<td>').text(resData.dept))
					.append($('<td>').text(resData.salary))
					.appendTo('#result');
					 
					} else {
						$('#result').empty();
						alert('조회된 사원 정보가 없습니다'); 
						
						// * 입력한 내용이 db에 존재하지 않는 경우	---- ?
						// (1) error가 아닌 success다 : 통신은 성공했기 때문
						// (2) jsp영역에서 '' 인 경우에 해당한다
					}
				
					
				},
				error : function(jqXHR) {
					$('#result').empty();
					alert('조회된 사원 정보가 없습니다'); 
				}
			});
		});
	}
		
			
			
				
				
				


</script>

</head>
<body>
	<%-- [[[ ajax : 사원등록  --%>
	<h3>사원등록</h3>
	<form id="frm_add">
		<input type="text" id="sno" name="sno" placeholder="사원번호">
		<input type="text" id="name" name="name" placeholder="사원명">
		<input type="text" id="dept" name="dept" placeholder="부서명">
		<input type="button" value="등록" id="btn_add">
	
	</form>
	
	<%-- [[[ ajax : 사원조회  --%>
	<h3>사원조회</h3>
	<form id="frm_detail">
		<input type="text" id="no" name="no" placeholder="사원번호">
		<input type="button" value="조회" id="btn_detail">
		<input type="button" value="전체" id="btn_list">
	</form>
	
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서명</td>
				<td>연봉</td>
			</tr>
		</thead>
		<tbody id="detail">	<%-- # 결과 : 반복문 사용할필요 없음  --%>
			
			
		</tbody>
	</table>
	
	
	<%-- [[[ ajax : 사원목록 --%>
	
	<h3>사원목록</h3>
	<table border="1">
		<thead>
			<tr>
				<td>사원번호</td>
				<td>사원명</td>
				<td>부서명</td>
				<td>연봉</td>
			</tr>
		</thead>
		<tbody id="result">	<%-- # 결과 : 반복문 사용할필요 없음  --%>
			
			
		</tbody>
	</table>

</body>
</html>