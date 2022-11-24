<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- # 날짜 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>    
   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>


<jsp:include page="../layout/header.jsp">
	<jsp:param value="${blog.blogNo}번 블로그" name="title"></jsp:param>
</jsp:include>

<script>

	$(function() {
		
		// service : 목록으로 돌아가기
		fn_list();
		fn_update();
		fn_remove();
		
	});
	
	// service + move : 목록 요청
	function fn_list() {
		
		$('#btn_list_blog').click(function() {
			location.href = '${contextPath}/blog/list';
			// * return 함부로 쓰지 말기 : return은 아래 코드를 실행시키지 않는것
		});
	}
	

</script>

<div>
	<h1>${blog.title}</h1>
	<div>
		<span>▷ 작성일 <fmt:formatDate value="${blog.createDate}" pattern="yyyy. M. d HH:mm" /></span>
		&nbsp;&nbsp;&nbsp;
		<span>▷ 수정일 <fmt:formatDate value="${blog.modifyDate}" pattern="yyyy. M. d HH:mm" /></span>
	</div>
	
	<br>
	
	<div>
		<span>조회수 <fmt:formatNumber value="${blog.hit}" pattern="#,##0" /></span>
	</div>
	
	<hr>
	
	<div>
		${blog.content}
	</div>

	<div>
		<form id="frm_btn" method="post">
		<%-- # service + move : 수정창 이동하기 --%>
			<input type="button" value="수정" id="btn_modify_blog">
		<%-- # service : 삭제하기 --%>
			<input type="button" value="삭제" id="btn_remove_blog">
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
		</form>
		<script>
			$('#btn_modify_blog').click(function() {
				// * 블로그를 삭제하면 댓글을 삭제하는게 아닌 남기되 안보이게 만듬
				
				// # service + move : 편집창 이동
				$('#frm_btn').attr('action', '${contextPath}/blog/edit');
				$('#frm_btn').submit();
			});
	
		
		
			$('#btn_remove_blog').click(function() {
				// * 블로그를 삭제하면 댓글을 삭제하는게 아닌 남기되 안보이게 만듬
				if(confirm('블로그를 삭제하면 블로그에 달린 댓글을 더이상 확인할 수 없습니다. 삭제하시겠습니까?')) {
					$('#btn_modify_blog').click(function() {
						// * 블로그를 삭제하면 댓글을 삭제하는게 아닌 남기되 안보이게 만듬
						
						// # service : 삭제요청
						$('#frm_btn').attr('action', '${contextPath}/blog/remove');
						$('#frm_btn').submit();
						
					});
				}
			});
		</script>
		
		<%-- # service : 목록 돌아가기 --%>
		<input type="button" value="목록" id="btn_list_blog">
		
	</div>
	

</div>


	
	
</body>
</html>