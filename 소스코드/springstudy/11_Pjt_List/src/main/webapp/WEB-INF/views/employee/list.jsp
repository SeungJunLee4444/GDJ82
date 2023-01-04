<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원목록</title>
</head>
<style>
* {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
a {
    text-decoration: none;
    color: #000000;
}

.container {
	width : 700px;
	margin : 0 auto;	/* 최상위 폴더에 margin 0 auto를 넣어줘야 페이지에 맞게 움직인다 */
	
	
}

.title {
	
	text-align : center;
	font-size: 50px;
	font-weight: 900;
	width : 700px;
	height : 150px;
	color: #eeeeee;
	margin : 50px auto;
	background-color: #007777;
	line-height : 150px;
	margin-bottom: 5px;
	
}






.search {
	
	
	margin : 20px 0;
	text-align : center;
	


}

.table {

	text-align : center;
	width : 700px;
	height : 800px;
	margin : 0 auto;
	padding: 50px 0 50px;
}

}

table {

	margin : 0 auto;	
}


thead {

	font-weight: 900;
	background-color: #aaaaaa;
}

tfoot {

	font-size: 20px;
	font-weight: 900;
	
}

td {

	border : 1px solid #000000;

}


.paging {
		width: 210px;
		margin: 0 auto;
		color: gray;
}
.paging a, .paging span {
		display: inline-block;
		width: 30px;
		height: 30px;
		line-height: 30px;
		text-align: center;
}
.hidden {
		visibility: hidden;
}
.now_page {
		border: 1px solid gray;
		color: teal;
		font-weight: 900;
}
.lnk:hover {
		border: 1px solid gray;
		color: skyblue;
}



</style>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>
	
	$(document).ready(function() {
		
		
		$(function() {	// * ready와 동일
			if(${recordPerPage} != '') {
				$('#recordPerPage').val(${recordPerPage});	// * 받아온 recordPerPage를 recordPerPage에 저장
			} else {
				$('#recordPerPage').val(10);
			}
			
			$('#recordPerPage').change(function() {	// * change :  select 목록을 바꿀때 발생하는 이벤트
				location.href="${contextPath}/emp/list?recordPerPage=" + $(this).val();
			});
		});
		
		// # area1, area2 표시 
		// 초기상태 : area1, area2 둘다 숨김
		$('#area1, #area2').css('display', 'none');
		
		// # column 선택에 따른 area1, area2 표시
		// $('#column') = $(this)
		$('#column').change(function(){
			let combo = $(this);
			if(combo.val() == ''){
				$('#area1, #area2').css('display', 'none');
			} else if(combo.val() == 'HIRE_DATE' || combo.val() == 'SALARY'){
				$('#area1').css('display', 'none');
				$('#area2').css('display', 'inline');
			} else {
				$('#area1').css('display', 'inline');
				$('#area2').css('display', 'none');
			}
		});
		
		// # 검색시 선택한게 없으면 서브밋 방지
		$('#frm_search').submit(function(event) {
			if($('#column').val() == '') {
				event.preventDefault();
				return;
			}
		});
		
		
		$('#btn_all').click(function() {
			location.href = '${contextPath}/emp/list';
		});
		
		// # 자동완성 
		// keyup 이벤트 : 한글자 쓸때마다 이벤트가 발생
		
		$('#param').keyup(function() {
			
			// & 초기화
			$('#auto_complete').empty();
			
			if($(this).val() == ''){
				return;
			}
			
			// & AJAX : 
			$.ajax({
				// 요청
				type: 'get',
				url: '${contextPath}/emp/autoComplete',
				data: 'target=' + $('#target').val() + '&param=' + $(this).val(),
				// 응답
				dataType : 'json',
				success : function(resData) {
					if(resData.status == 200) {
						$.each(resData.list, function(i, emp) {	// & datalist 안에 내용 추가하기 : option 태그 필요
							// console.log(emp.email);
							$('#auto_complete')
							.append($('<option>').val(emp[resData.target]));
						});
					}
				}
				
			});
			
		});
		
	});


</script>

<body>

	<%-- # 사원목록 페이지 --%>
	
	<div class="container">
		<div class="title">-- 사원목록 페이지 --</div>
		
		<%-- # 게시판 검색 --%>
		<%-- & 사원번호, 부서번호 : 일치하는 번호 찾기 : 쿼리문 = 사용--%>
		<%-- & 성, 이름 ,연락처 : 일부만 일치해도 조회하기 : 쿼리문 like '%a' 사용 --%>
		<%-- & 입사일자, 연봉 : 범위로 조회하기 : 쿼리문 between 사용 --%>
		<div class="search">
			<form id="frm_search" action="${contextPath}/emp/search">
				<select id="column" name="column">	<%-- * 일부러 칼럼 이름처럼 적음, 쿼리문에도 들어간다 --%>
					<option value="">:::선택:::</option>
					<option value="EMPLOYEE_ID">사원번호</option>
					<option value="E.DEPARTMENT_ID">부서번호</option>
					<option value="LAST_NAME">성</option>
					<option value="FIRST_NAME">이름</option>
					<option value="PHONE_NUMBER">연락처</option>
					<option value="HIRE_DATE">입사일자</option>
					<option value="SALARY">연봉</option>
				</select>
				<span id ="area1">
					<input type="text" id="query" name="query">
				</span>
				<span id="area2">
					<input type="text" id="start" name="start">
					~
					<input type="text" id="stop" name="stop">
				</span>
				<span id="area3">
					<input type="submit" value="검색" id="search">
					<input type="button" value="전체사원 조회" id="btn_all">
				</span>
			</form>
		</div>
		
		<%-- # 이메일 자동완성 목록 만들기 : form이 아닌 'ajax'로 통신처리 --%>
		<div>
			<select name="target" id="target">
				<option value="FIRST_NAME">이름</option>
				<option value="LAST_NAME">성</option>
				<option value="EMAIL">이메일</option>
			</select>
			<input type="text" id="param" name="param" list="auto_complete">
			<datalist id="auto_complete"></datalist>
		</div>
		
		<%-- # select로 페이지당 게시글 목록수 조절하기 --%>
		<div>
			<select id="recordPerPage">
				<option value="">선택</option>
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
			</select>
		</div>
		
		<hr>
		
		
		<%-- # 게시판 목록 --%>
		<div class="table">
			<table border="1">
				<thead>
					<tr>
						<td>순번</td>				<%-- * 각 항목은 EmpDTO를 참고 --%>
						<td>사원번호</td>
						<td>사원명(성+이름)</td>
						<td>이메일</td>
						<td>전화번호</td>
						<td>입사일자</td>
						<td>연봉</td>
						<td>커미션</td>
						<td>부서번호</td>
						<td>부서명</td>				<%-- * JOIN을 해야하는 이유 : 부서명은 DeptDTO에 존재 --%>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${employees}" var="emp" varStatus="vs">	<%-- * 인덱스를 쓰고싶으면 varStatus 사용 --%>
							<tr>
								<td>${beginNo - vs.index}</td>	<%-- * 시작페이지 beginNo를 속성으로 전달받음, 반복되는 인덱스만큼 빼주기, vs.index : 반복문의 인덱스값(0,1,2...) --%>
								<td>${emp.employeeId}</td>
								<td>${emp.firstName} ${emp.lastName}</td>
								<td>${emp.email}</td>
								<td>${emp.phoneNumber}</td>
								<td>${emp.hireDate}</td>
								<td>${emp.salary}</td>
								<td>${emp.commissionPct}</td>
								<td>${emp.managerId}</td>
								<td>${emp.deptDTO.departmentId}</td>
							</tr>
						</c:forEach>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="10" class="foot">
						<%-- # 게시판 이동(블록) --%>
							${paging}	<%-- * 해당 내용은 pageUitl 클래스에서 이루어졌다
							
										css에 사용된 class도 전부 거기서 작성되있음 --%>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
</body>
</html>