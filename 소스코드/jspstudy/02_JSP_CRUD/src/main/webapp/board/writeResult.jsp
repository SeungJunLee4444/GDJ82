<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script src="../assets/js/jquery-3.6.1.min.js"></script>
<script>
	<%-- & 게시글 추가 결과창 : 리다이렉트로 다시 전달받은 파라미터 result를 확인하기위해
		param 내장객체를 이용한다
	 --%>

	if (${param.res} > 0) {
		alert('게시글이 삽입되었습니다.');
		location.href = '${contextPath}/board/list.do';
	} else {
		alert('게시글이 삽입되지 않았습니다.');
		history.back();
	}
</script>