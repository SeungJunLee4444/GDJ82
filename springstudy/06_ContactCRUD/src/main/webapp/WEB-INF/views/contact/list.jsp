<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록페이지</title>
<script src="${contextPath}/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<%-- # 목록페이지 : 시작화면 + 비즈니스 로직--%>
	<%-- - 페이지 조건 : 
		(1) 웰컴페이지로 지정해야한다
		(2) 전체목록을 조회하기 위해 ${contacts}를 db에서 가져와야한다 
		(3) 추가 페이지로 이동하기 위한 단순이동 요청이 필요하다
		--%>
	
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>번호</td>
					<td>이름</td>
					<td>전화</td>
					<td>주소</td>
					<td>이메일</td>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty contacts}">	
					<tr>
						<td colspan="5">없음</td>	<%-- & colspan은 가로로합병, rowspan은 세로로합병 : td에 줘야한다(td가 하나의 셀이니까)--%> 
					</tr>
				</c:if>
				<c:if test="${not empty contacts}">
					<c:forEach items="${contacts}" var="contact">
						<tr>
							<td>${contact.no}</td>
							<td>${contact.name}</td>
							<td>${contact.tel}</td>
							<td>${contact.addr}</td>
							<td>${contact.email}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="5"><a href="${contextPath}/insertContactPage.do">신규연락처 등록하기</a></td>	<%-- # 추가화면 : 단순이동 --%>
				</tr>
			
			</tfoot>
			
		</table>
	</div>
</body>
</html>