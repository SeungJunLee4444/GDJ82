<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%-- # 페이지 포함 : header --%>
<%-- * header.jsp에 파라미터로 title값을 전달 --%>
<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그목록" name="title"></jsp:param>	
</jsp:include>
	
	
	<%-- 
	# 목록 : 전달받은 파라미터들 
		 (1) totalRecord(전체 게시글 개수),
		 (2) blogList(게시글 목록), 
		 (3) beginNo(게시글 순번), 
		 (4) paging(하단블록) 
	 --%>
	
		<%-- * 회원만 작성할 수 있게 만들기 --%>
	<div>
	
		<h1>블로그 목록(전체 : ${totalRecord} 개)</h1>
		
		<%-- <c:if test="${loginUser} != null"> --%>
		<div>
			<input type="button" value="블로그 작성하기" onclick="location.href='${contextPath}/blog/write'">
		</div>
		<%-- </c:if> --%>
		
		<div>
			<table border="1">
				<thead>
					<tr>
						<td>순번</td>
						<td>제목</td>
						<td>조회수</td>
						<td>작성일</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${blogList}" var="blog" varStatus="vs">	<%-- * 인덱스를 사용하려면 무조건 varStatus를 사용해야한다 --%>
						<tr>
							<td>${beginNo - vs.index}</td>	<%-- * beginNo와 var 인덱스를 이용해 번호 생성 --%>
							<td>${blog.title}</td>
							<td>${blog.hit}</td>
							<td>${blog.createDate}</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							${paging}
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
<%-- # 페이지 포함 : footer --%>
	
	
</body>
</html>