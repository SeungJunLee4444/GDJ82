<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>보고싶은동물 : ${param.filename}</h1>	<%-- % param 객체 : 파라미터로 전달된 값을 el로 받을때 --%>
	<img src="${contextPath}/resources/images/${param.filename}" width="200px">
	
	<%-- & 파리미터와 속성
	(1) 파라미터로 전달된 값 : ${param} 객체 사용
	(2) 속성으로 전달된 값   : ${} 그냥 el사용
	--%>  
	
	<%-- 
	& 파라미터 전달 범위 : 1회 요청에 한함, 애초에 getparameter메서드는 request로 호출한다
	- 결과 : 따라서 index.jsp에서 파라미터로 전달한 값은 animal5에서도 유효하다
	- 규칙 : index.jsp에서 파라미터로 전달된 filename값은 ${param.filename} 내장객체로 확인할 수 있다
	
	
	 --%>
	
</body>
</html>