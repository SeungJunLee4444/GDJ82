<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<!-- 파라미터 res는 EL로 ${param.res} 표현한다. -->
<!-- 파라미터 no는  EL로 ${param.no}  표현한다. -->

<script>
	if (${param.res} > 0) {
		alert('게시글이 수정되었습니다.');
		location.href = '${contextPath}/detail.do?no=${param.no}';
	} else {
		alert('게시글이 수정되지 않았습니다.');
		history.back();
	}
</script>