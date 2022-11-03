<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../../assets/js/jquery-3.6.1.min.js"></script>
</head>
<body>

	<%
		// rememberId 쿠키가 있으면 해당 쿠키값을 String rememberId에 저장하기
		String rememberId = "";	
		Cookie[] cookies = request.getCookies();				// 쿠키는 반환시 배열타입이다
		if(cookies != null){									// => 로그인체크를 해서 쿠키가 존재하는경우
			for(int i = 0; i < cookies.length; i++){
				if(cookies[i].getName().equals("rememberId")){	// => getName() 쿠키의 이름이 rememberId와 동일한경우
					rememberId = cookies[i].getValue();			// => 쿠키의 값을 rememberId에 저장한다
					break;
				}
			}
		}
	%>

	<div>
		<form method="POST" action="02_remember_id.jsp">
			<div>
				<input type="text" name="id" id="id" placeholder="아이디">
			</div>
			<div>
				<input type="password" name="pwd" placeholder="비밀번호">
			</div>
			<div>
				<button>로그인</button>
			</div>
			<div>
				<label for="chk_remember_id">
					<input type="checkbox" name="chk_remember_id" id="chk_remember_id">
					아이디 기억하기
				</label>
			</div>
		</form>
	</div>
		
	<%-- & 아이디가 쿠키로 저장된 상태일때, 저장된 아이디를 보여주고, 로그인유지도 체크상태 --%>
	<script>
	
		// String rememberId가 ""이 아니면 아이디 기억하기를 한 상황
		if('<%=rememberId%>' != ''){						//  rememberId가 null이 아닌 경우 : 쿠키에 저장해서 rememberId를 가져온상태
			$('#id').val('<%=rememberId%>');				// => id의 값을 rememebrId값으로 미리 보여줌
			$('#chk_remember_id').prop('checked', true);	// => 아이디기억하기 체크버튼도 체크된 상태로 만듬
		}
	</script>

</body>
</html>