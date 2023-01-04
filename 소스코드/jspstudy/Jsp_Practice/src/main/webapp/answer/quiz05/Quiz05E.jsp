<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	String filename = request.getParameter("filename");
	String realPath = request.getServletContext().getRealPath("storage");
	
	File file = new File(realPath, filename);
	if (file.exists()) {
		file.delete();
	}
	
	boolean isExist = file.exists();  // 삭제 여부 확인
%>
<script>
	if (<%=isExist%>) {
		alert('탈퇴되지 않았습니다.');
		history.back();
	} else {
		alert('탈퇴되었습니다. 이용해 주셔서 감사합니다.');
		location.href = 'Quiz05A.jsp';
	}
</script>