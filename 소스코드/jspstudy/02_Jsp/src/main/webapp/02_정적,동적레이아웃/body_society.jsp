<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%--
		1. 동적 레이아웃
			1) 포함할 페이지에 변경되는 부분이 있음
			2) <jsp:include> 액션 태그를 사용
			3) <jsp:param> 액션 태그를 이용해 파라미터를 전달함
	--%>
	<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:include page="header.jsp">
		<jsp:param value="사회" name="title"/>
	</jsp:include>
	
	<section>
		<div>사회1</div>
		<div>사회2</div>
		<div>사회3</div>
		<div>사회4</div>
	</section>
	
	<%--
		2. 정적 레이아웃
			1) 포함할 페이지에 변경되는 부분이 없음
			2) <%@ include %> 지시어를 사용
	--%>
	<%@ include file="footer.jsp" %>