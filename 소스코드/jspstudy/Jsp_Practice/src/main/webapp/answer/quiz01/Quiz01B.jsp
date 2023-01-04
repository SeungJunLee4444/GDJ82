<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
	String site = request.getParameter("site");

	// site에는 전체 URL이 포함되어 있다.
	// redirect : 클라이언트에게 전체 URL을 응답하면 클라이언트가 다시 이동한다.
	response.sendRedirect(site);

	// 참고로.
	// forward는 서버 내부 경로로 이동하기 때문에 전체 URL을 처리할 수 없다.
	// 아래와 같이 처리하더라도 오류가 발생한다.
	// request.getRequestDispatcher(site).forward(request, response);
%>

<%-- 다른 풀이. 자바스크립트로 이동하기 --%>
<script>
	location.href = '<%=site%>';
</script>