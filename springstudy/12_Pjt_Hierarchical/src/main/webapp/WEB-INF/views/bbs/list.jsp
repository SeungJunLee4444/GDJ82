<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글목록</title>
<%-- # 폰트어섬 cdn --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css" integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
* {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}

body {
	height : 600px;
	background-color: #F5F5DC;
}

h1 {
	padding-top: 30px;
	margin-bottom: 10px;
	color : #333333;
	

}

a {
    text-decoration: none;
    color : 111111;
}

.gowrite {
	color : #111111;
}

.container {
	
	background-color: #EEEEEE;
	text-align : center;
	width : 600px;
	height : 100%;
	margin : 150px auto;	/* 최상위 폴더에 margin 0 auto를 넣어줘야 페이지에 맞게 움직인다 */
	

}

.list {
	display : block;
	margin : 0 auto 20px;
	

}

.tab  {

	
	display : block;
	margin : 0 auto;
}

.write {
	border-radius : 3px;
	display : block;
	margin : 20px auto;
	background-color: #999999;
	width : 120px;
	height : 40px;
	line-height : 40px;
	font-weight: 900;


}

table {
	border-radius : 3px;
	background-color: #ffffff;
	margin : 0 auto;
	
}

.now_page {
	font-weight: 900;
	border: 1px solid gray;
	display : inline-block;
	width: 25px;
	color : red;
}

.lnk1 {

	border: 1px solid gray;
	display : inline-block;
	width: 25px;
}

.lnk2 {
}

.lnk.hover {
	
	color : red;
	background-color: #666666;
}

.blind {
	display : none;
	
}

</style>

<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script type="text/javascript">

		$(function() {	// * ready와 동일
			if(${recordPerPage} != '') {
				$('#recordPerPage').val(${recordPerPage});	// * 받아온 recordPerPage를 recordPerPage에 저장
				console.log(1);
			} else {
				$('#recordPerPage').val(10);
				console.log(2);
			}
			
			$('#recordPerPage').change(function() {	// * change :  select 목록을 바꿀때 발생하는 이벤트
				location.href="${contextPath}/bbs/list?recordPerPage=" + $(this).val();
			});
		});
</script>
</head>
<body>

	<%-- # 게시글 목록 --%>
	
	
	<div class="container">
	
		<h1>게시글목록</h1>
		
		
		<%-- # 게시글 추가창 이동 --%>
		<div class="write">
				<a class="gowrite" href="${contextPath}/bbs/write">작성하러가기</a>
		</div>
		
		
		<%-- # select로 페이지당 게시글 목록수 조절하기 --%>
		<div class="list">
			<select id="recordPerPage">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
			</select>
		</div>
	
		
		<div class="tab">
			<table border="1">
				<thead>
					<tr>
						<td>순번</td>
						<td>제목</td>
						<td>작성자</td>
						<td>IP</td>
						<td>작성일</td>
						<td></td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="bbs" items="${bbsList}" varStatus="vs">
					<c:if test="${bbs.state == 1}">
						<tr>
							<td>${beginNo - vs.index}</td>
							<td>${bbs.writer}</td>
							<td>
								<%-- # 게시글 표현하기 --%>
									<%-- DEPTH에 따른 들여쓰기 --%>
								<c:forEach begin="1" end="${bbs.depth}" step="1">
									&nbsp;&nbsp;
								</c:forEach>
								<%-- 답글은 [RE표시] --%>
								<c:if test="${bbs.depth > 0}">
									[RE]
								</c:if>
								<%-- 제목 --%>
								${bbs.title}
								<%-- 답글달기 버튼 --%>
								
									<input type="button" value="답글" class="btn_reply_write">
								<%-- 
									1단 답글로 운용하는 경우 아래와 같이 처리
										<c:if test="${bbs.depth == 0}">
											<input type="button" value="답글" class="btn_reply_write">
										</c:if>
								 --%>
								<script>
									
									// # 답글달기 버튼 : 버튼을 누르면 해당 버튼의 답글창 띄우기
									// - 평소에는 숨겨둔다 : .blind, display : none
									// - 버튼을 누르면 (1) 기존의 답글창을 모두 닫아주고, (2) 해당 버튼의 
							
									
									$('.btn_reply_write').click(function() {
										// 1. 답글창 모두 닫아주기
										$('.reply_write_tr').addClass('blind');
										// 2. this의 부모 + 부모 + 다음형제 reply_write_tr의 class를 제거해 답글창을 열어준다
										$(this).parent().parent().next().removeClass('blind');
									});
								
								</script>
							</td>
							<td>${bbs.ip}</td>
							<td>${bbs.createDate}</td>
							<td>
								<%-- # 게시글 삭제 --%>
								<form data-aaa="${bbs.bbsNo}" class="frm_remove" method="post" action="${contextPath}/bbs/remove">
								<%-- # 게시글 삭제 : hidden으로 삭제에 필요한 번호 파라미터로 전송--%>
									<input type="hidden" name="bbsNo" value="${bbs.bbsNo}">
								<%-- # 반복문으로 생성된 복수의 form을 처리하는 방법 : 버튼의 id에 번호값을 부여 *** --%>
									<a id="lnk_remove${bbs.bbsNo}"><i class="fa-solid fa-rectangle-xmark"></i></a>
								</form>
								<script>
									$('#lnk_remove${bbs.bbsNo}').click(function() {	<%-- * 처음에 class를 사용한 이유 : forEach문이라 id를 사용할경우 오류발생  --%>
									if(confirm('삭제할까요?')) {
										$(this).parent().submit();
									}
								});
								<</script>
							</td>
						</tr>
						<%-- # 댓글창처럼 구현하기 --%>
						<tr class="reply_write_tr blind">
						<%-- # reply_write_tr 와 blind class : 답글창 안보이게 하기
							답글버튼을 누르면 class 속성 없애기(보여주기)
						 --%>
						
						
							<td colspan="6">
								<form method="post" action="${contextPath}/bbs/reply/add">
									<div><input type="text" name="writer" placeholder="작성자" required></div>
									<div><input type="text" name="title" placeholder="제목" required></div>
									<div><button>답글달기</button></div>
									<input type="hidden" name="depth" value="${bbs.depth}">									
									<input type="hidden" name="groupNo" value="${bbs.groupNo}">									
									<input type="hidden" name="groupOrder" value="${bbs.groupOrder}">									
								</form>
							</td>
						
						</tr>
					</c:if>
					<%-- # 게시글 삭제 : 삭제된 경우(update해서 state가 0인경우)는 title을 안보여준다 --%>
					<c:if test="${bbs.state == 0}">
						<tr>
							<td>${beginNo - vs.index}</td>
							<td colspan="5">삭제된 게시글입니다</td>
							</tr>
					</c:if>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="10" class="foot">
							${paging}						
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	
	</div> <%-- container --%>


</body>
</html>