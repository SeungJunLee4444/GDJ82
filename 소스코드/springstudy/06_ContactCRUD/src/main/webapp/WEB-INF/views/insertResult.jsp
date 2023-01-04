<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
<script>

	
	if(${param.res} > 0) {
		alert('연락처가 추가되었습니다');
		location.href='${contextPath}/contact/list';
		
	} else {
		alert('다시 작성하세요');
		history.back();
		
	}

</script>
