<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>	<%-- & 절대경로의 변수화 --%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
</head>
<body>
	<%-- # list.jsp : 공지사항 목록화면 --%>
	
	<table border="1">
		<thead>
			<tr>
				<td>공지번호</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${notices}" var="notice">
			<tr>
				<td>${notice.noticeNo}</td>
				<td>${notice.title}</td>
				<td>${notice.createDate}</td>
			</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">	<%-- & 세로줄 3칸 병합 --%>
					<%-- # 이전블록 : 1,2,3 페이지는 이전 블록이 없다 --%>
					<c:if test="${page > pagePerBlock}">						<%-- 블록은 3, page가 4이 상일경우 이전블록 3page로 돌아간다 --%>
						<a href="${contextPath}/notice/list.no?page=${beginPage-1}">이전블록</a>
					</c:if>						
				
				
					<%-- # 이전페이지 --%>
					<c:if test="${page != 1}">
						<a href="${contextPath}/notice/list.no?page=${page -1}">&lt;이전</a>	<%-- & &lt : < --%>
					</c:if>
				
				
					<%-- # 1 ~ 3 페이지 --%>
					<c:forEach begin="${beginPage}" end="${endPage}" step="1" var="p">
						<%-- # 상황 : 현재 페이지는 링크가 걸리지 않는다 --%>
						<c:if test="${page == p}">
							${p}						
						</c:if>
						<%-- # 현재 페이지가 아니면 링크를 건다 --%>
						<c:if test="${page != p}"> 
							<a href="${contextPath}/notice/list.no?page=${p}">${p}</a>
						</c:if>
					</c:forEach>
					
					<%-- # 다음페이지 : 마지막 페이지는 다음 페이지가 없다 --%>
					<c:if test="${page != totalPageCnt}">
						<a href="${contextPath}/notice/list.no?page=${page +1}">다음&gt;</a>	<%-- & &lt : < --%>
						
					</c:if>		
					
					<%-- # 다음블록 : 마지막 블록(endpage 가 totalpagecnt인 경우)은 다음 블록이 없다 --%>	
					<c:if test="${endPage != totalPageCnt}">
						<a href="${contextPath}/notice/list.no?page=${endPage + 1}">다음블록&gt;</a>
					</c:if>		
					
				</td>
			</tr>
		</tfoot>
	</table>

	

</body>
</html>