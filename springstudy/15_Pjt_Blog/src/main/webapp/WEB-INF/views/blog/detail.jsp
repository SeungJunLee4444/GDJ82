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

<style>
	.blind {
		display: none;
	}
</style>


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
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
		<%-- # service + move : 수정창 이동하기 --%>
			<input type="button" value="수정" id="btn_modify_blog">
		<%-- # service : 삭제하기 --%>
			<input type="button" value="삭제" id="btn_remove_blog">
			<%-- # service : 목록 돌아가기 --%>
			<input type="button" value="목록" id="btn_list_blog">
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
		
		
	</div>
	
	<hr>
	
	<%-- # page : 댓글영역 --%>
	
	<span id="btn_comment_list">
		댓글
		<span id="comment_count"></span>개
	</span>
	
	<hr>
	
	<%-- # page : 댓글목록 --%>
	<div id="comment_area" class="blind">
		<div id="comment_list"></div>
		<div id="paging"></div>
	</div>
	<hr>
	
	<%-- # page : 댓글영역 --%>
	<div>
		<form id="frm_add_comment">
			<div class="add_comment">
				<div class="add_comment_input">
					<input type="text" name="content" id="content" placeholder="댓글을 작성하려면 로그인 해 주세요">
				</div>
				<div class="add_comment_btn">
					<input type="button" value="작성완료" id="btn_add_comment">
				</div>
			</div>
			<input type="hidden" name="blogNo" value="${blog.blogNo}">
		</form>
	</div>
	
	<!-- 현재 페이지 번호를 저장하고 있는 hidden -->
	<input type="hidden" id="page" value="1">
	
	
	
	<script>
	
		// # ajax service : 댓글 총개수
		fn_commentCount();
		// # ajax service : 댓글작성
		// # 댓글목록 가리기
		fn_switchCommentList();
		fn_addComment();
		// # 리스트 확인
		fn_commentList();
		// # 페이지 이동
		fn_changePage();
		
		
		function fn_commentCount(){
			$.ajax({
				type: 'get',
				url : '${contextPath}/comment/getCount',
				data : 'blogNo=${blog.blogNo}',
				dataType : 'json',
				success : function(resData) {
					$('#comment_count').text(resData.commentCount);	
					// * 반환값 : resdata = {"commentCount" : 개수}
					
				}
			});
		}
		// * 목록가리기 + css
		function fn_switchCommentList(){
			$('#btn_comment_list').click(function(){
				$('#comment_area').toggleClass('blind');
			});
		}
		
		function fn_addComment(){
			
			$('#btn_add_comment').click(function() {
				if($('#comment').val() == '') {
					alert('댓글 내용을 입력하세요');
					return;	// * ajax 실행을 막는다
				}
				$.ajax({
					type : 'post',
					url : '${contextPath}/comment/add',
					data : $('#frm_add_comment').serialize(),
					dataType : 'json',
					success : function(resData) {	
					// * 반환값 : resData = {"isAdd", true}
						if(resData.isAdd) {
							alert('댓글이 등록되었습니다');
							$('#content').val('');
							fn_commentList();	// * ajax service : 댓글목록을 요청하는 함수
							fn_commentCount();	// * ajax service : 댓글목록 개수 갱신하는 함수
						}
						
					}
				});
			});
			
		}
		
		
		// # ajax service : 댓글리스트 + pageing처리
	function fn_commentList(){
		$.ajax({
			type: 'get',
			url: '${contextPath}/comment/list',
			data: 'blogNo=${blog.blogNo}&page=' + $('#page').val(),
			dataType: 'json',
			success: function(resData){
					// console.log(resData);

					$('#comment_list').empty();
					$.each(resData.commentList, function(i, comment){
						var div = '';
						if(comment.depth == 0){
							div += '<div>';
						} else {
							div += '<div style="margin-left: 40px;">';
						}
						if(comment.state == 1) {
							div += '<div>' + comment.content + '</div>';
						} else {
							if(comment.depth == 0) {
								div += '<div>삭제된 댓글입니다.</div>';
							} else {
								div += '<div>삭제된 답글입니다.</div>';
							}
						}
						div += '<div>';
						moment.locale('ko-KR');
						div += '<span style="font-size: 12px; color: silver;">' + moment(comment.createDate).format('YYYY. MM. DD hh:mm') + '</span>';
						div += '</div>';
						div += '</div>';
						$('#comment_list').append(div);
						$('#comment_list').append('<div style="border-bottom: 1px dotted gray;"></div>');
					});
					// * 페이징
					$('#paging').empty();
					var pageUtil = resData.pageUtil;
					var paging = '';
					
					// 1) 이전블록
					if(pageUtil.beginPage != 1) {
						paging += '<span class="enable_link" data-page="'+ (pageUtil.beginPage - 1) +'">◀</span>';
					}
					
					// 2) 페이지번호
					for(let p = pageUtil.beginPage; p <= pageUtil.endPage; p++) {
						if(p == $('#page').val()){
							paging += '<strong>' + p + '</strong>';
						} else {
							paging += '<span class="enable_link" data-page="'+ p +'">' + p + '</span>';
						}
					}
				
					// 3) 다음블록
					if(pageUtil.endPage != pageUtil.totalPage){
						paging += '<span class="enable_link" data-page="'+ (pageUtil.endPage + 1) +'">▶</span>';
					}
					$('#paging').append(paging);
				}
			});
		}
		
			/*
			resData = {
				"commentList" : [
					{댓글1},
					{댓글2},
					...
				]
				"pageUtil" : {
					page : x,
					....
				}
				
		}
		
		*/
		
		// * 화면에 댓글 목록 뿌리기 : pageUtil의 getPaging을 쓰지않고 자바스크립트에 직접 만듬
		// depth가 0이면 그대로 1이면 오른쪽으로 들어가게 만들기
		
		function fn_changePage() {
			$(document).on('click', '.enable_link', function() {
				
				$('#page').val($(this).data('page'));
				fn_commentList();
			});
		}
		// $('.enable_link').click(function() {});		// * 자바스크립트에서 만든 속성(동적요소)은 이벤트를 걸수 없다 ***
		// 올바른 해결법
		// - enable_link 를 클릭하면 page파라미터값을 변경하고 list요청 (a링크대신)
	</script>
	

</div>


	
	
</body>
</html>