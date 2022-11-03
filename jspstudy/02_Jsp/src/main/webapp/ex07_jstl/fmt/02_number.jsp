<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="n" value="12345.6789" />
	
	<%--
		<fmt:formatNumber> 태그
		
		1. DecimalFormat 클래스를 대체하는 태그
		2. value 속성과 pattern 속성과 type 속성 이용
		3. pattern 속성에서 사용하는 패턴은 DecimalFormat과 동일한 패턴임
		4. 자릿수는 반올림 처리함
	--%>
	
	<%-- 천 단위 구분 기호(,) 사용 --%>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0" /></h1>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0.0" /></h1>
	<h1><fmt:formatNumber value="${n}" pattern="#,##0.00" /></h1>

	<%-- 천 단위 구분 기호(,) 없음 --%>
	<h1><fmt:formatNumber value="${n}" pattern="0" /></h1>
	<h1><fmt:formatNumber value="${n}" pattern="0.0" /></h1>
	<h1><fmt:formatNumber value="${n}" pattern="0.00" /></h1>
	
	<%-- 백분율(%) : 값에 100을 곱한 뒤 %를 붙임 --%>
	<h1><fmt:formatNumber value="${n}" type="percent" /></h1>
	
	<%-- 통화 : 천 단위 구분 기호(,)와 $, ￦ 기호 붙임 --%>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="$" /></h1>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="￦" /></h1>
	<h1><fmt:formatNumber value="${n}" type="currency" currencySymbol="￥" /></h1>
	
</body>
</html>