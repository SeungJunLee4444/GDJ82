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
		
		// 함수 호출
		fn_checkAll();
		fn_checkOne();
		fn_removeList();
		fn_iconNew();
		
		// 전체선택
		function fn_checkAll(){
			$('#check_all').click(function(){
				$('.check_one').prop('checked', $('#check_all').prop('checked'));
			});
		}
		
		// 개별선택
		function fn_checkOne(){
			$('.check_one').click(function(){
				var checkCount = 0;
				for(let i = 0; i < $('.check_one').length; i++){
					checkCount += $($('.check_one')[i]).prop('checked');				
				}
				$('#check_all').prop('checked', checkCount == $('.check_one').length);
			});
		}
		
		// 선택삭제
		function fn_removeList(){
			$('#frm_list').submit(function(event){
				if(confirm('선택한 모든 게시글을 삭제할까요?') == false){
					event.preventDefault();
					return;
				}
				if($('.check_one:checked').length == 0){
					alert('선택된 게시글이 없습니다.');
					event.preventDefault();
					return;
				}
			});
		}
		
		// new 아이콘
		function fn_iconNew(){
			$.each($('.icon_new'), function(i, icon){
				if($(icon).data('pass_day') <= 7) {
					$(icon).addClass('show_new_icon');
				} else {
					$(icon).removeClass('show_new_icon');
				}
			});
		}
		
	});
	
</script>
<style>
	.blind {
		display: none;
	}
	#lbl_check_all {
		cursor: pointer;
	}
	#lbl_check_all:hover {
		color: crimson;
	}
	.show_new_icon {
		display: inline-block;
		width: 64px;
		height: 38px;
		background-image: url(../resources/images/new-icon.png);
		background-size: 64px 38px;
		background-repeat: no-repeat;
	}
</style>
</head>
<body>

	<div>
		<a href="${contextPath}/board/write">새글작성</a>
	</div>

	<hr>

	<div>
		<form id="frm_list" action="${contextPath}/board/remove/list" method="post">
			<div>
				<button>선택삭제</button>
			</div>
			<table border="1">
				<thead>
					<tr>
						<td>
							<label for="check_all" id="lbl_check_all">전체선택</label>
							<input type="checkbox" id="check_all" class="blind">
						</td>
						<td>글번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>작성일</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${boards}" var="board">
						<tr>
							<td><input type="checkbox" name="boardNoList" value="${board.boardNo}" class="check_one"></td>
							<td>${board.boardNo}</td>
							<td>
								<a href="${contextPath}/board/detail?boardNo=${board.boardNo}">${board.title}</a>
								<span class="icon_new" data-pass_day="${board.passDay}"></span>
								<%-- * passday : mapper에서 계산, 현재 시간에서 게시글 생성일을 뺀 시간
									- passday를 data 속성으로 저장하여 자바스크립트에서 일주일동안 해당 게시글은 특정 이미지가 보여지도록 처리
								 --%>
							</td>
							<td>${board.writer}</td>
							<td>${board.createDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>