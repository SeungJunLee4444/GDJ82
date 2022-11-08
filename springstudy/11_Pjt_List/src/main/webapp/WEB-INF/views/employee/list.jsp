<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원목록</title>
</head>
<body>

	<%-- # 사원목록 페이지 --%>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>				<%-- * 각 항목은 EmpDTO를 참고 --%>
					<td>사원번호</td>
					<td>사원명(성+이름)</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td>입사일자</td>
					<td>연봉</td>
					<td>커미션</td>
					<td>부서번호</td>
					<td>부서명</td>				<%-- * JOIN을 해야하는 이유 : 부서명은 DeptDTO에 존재 --%>
				</tr>
			</thead>
			<tbody>
				<tr>
					<c:forEach items="${employees}" var="emp">
						<tr>
							<td>순번자리(아직계산안됨)</td>
							<td>${emp.employeeId}</td>
							<td>${emp.firstName} ${emp.lastName}</td>
							<td>${emp.email}</td>
							<td>${emp.phoneNumber}</td>
							<td>${emp.hireDate}</td>
							<td>${emp.salary}</td>
							<td>${emp.commissionPct}</td>
							<td>${emp.managerId}</td>
							<td>${emp.deptDTO.departmentId}</td>
						</tr>
					</c:forEach>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="10">
						1 2 3 4 5
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>