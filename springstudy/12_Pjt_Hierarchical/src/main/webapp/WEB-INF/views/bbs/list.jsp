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
	

}

a {
	
    text-decoration: none;
    color: #000000;
}

.container {
	
	background-color: #EEEEEE;
	text-align : center;
	width : 600px;
	height : 100%;
	margin : 200px auto;	/* 최상위 폴더에 margin 0 auto를 넣어줘야 페이지에 맞게 움직인다 */
	

}

.list {
	display : block;
	margin : 0 auto;

}

.tab  {

	
	display : block;
	margin : 0 auto;
}

.write {
	display : block;
	margin : 20px auto;
	background-color: #999999;
	width : 120px;
	height : 40px;
	line-height : 40px;
	font-weight: 900;


}

table {
	background-color: #ffffff;
	margin : 0 auto;
	
}

.now_page {
	font-weight: 900;
	border: 1px solid black;
	display : inline-block;
	width: 25px;
}

.lnk {

	border: 1px solid black;
	display : inline-block;
	width: 25px;
}

.lnk.hover {
	color : red;
	background-color: yellow;
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
				<a href="${contextPath}/bbs/write">작성하러가기</a>
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
						<tr>
							<td>${beginNo - vs.index}</td>
							<td>${bbs.writer}</td>
							
							<%-- # 게시글 삭제 : 삭제된 경우(update해서 state가 0인경우)는 title을 안보여준다 --%>
							<td>
								<c:if test="${bbs.state == 0}">
									삭제된 게시글 입니다
								</c:if>
								<c:if test="${bbs.state == 1}">
									${bbs.title}
								</c:if>
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
								</script>
							</td>
						</tr>
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