<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// # 요청 파라미터				=> form으로부터 item과 amount를 파라미터로 전달받았음
	request.setCharacterEncoding("UTF-8");
	String item = request.getParameter("item");
	int amount = Integer.parseInt(request.getParameter("amount"));
	
	// # 제품명, 구매수량을 담을 수 있도록 map 타입으로 저장
	// => 전달받은 파라미명과 값을 저장
	Map<String, Object> product = new HashMap<>();
	product.put("item", item);
	product.put("amount", amount);
	
	// # 장바구니 list에 만든 map을 상품으로서 저장--------------------------------------------------------------
	// * 장바구니는 session영역에 저장하는것이 유리하다
	// 1) 장바구니에 물건이 있는 경우
		// => cart_list에서 이동해옴
		// => 물건이 있는 상태기 때문에 getAttribute메서드를 통해, 기존에 저장된 물건들을 호출한다
	
	// 2) 장바구니에 물건이 없는 경우
		// - form.jsp에서 이동해옴
		// - 물건이 하나도 없는 상태기 때문에 cart는 null값
		// => 배열을 생성하고, cart 속성을 추가한다--------------------------------------------------------------
	
	// session에 장바구니를 cart 속성으로 저장한 상황이다.
	// 1. session에 cart 속성이 있는지 확인한다.
	// 2. cart 속성이 없으면 새로 만들어서 저장한다.	
	List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");
	
	// & session은 object 타입이기 떄문에 list타입으로 강제캐스팅이 필요하다
		
	if(cart == null){
		cart = new ArrayList<>();
		session.setAttribute("cart", cart);
	}
	
	// session의 cart에 product 저장하기
	cart.add(product);
%>
<script>
	alert('<%=product.get("item")%> 제품을 장바구니에 추가했습니다.');
	if(confirm('장바구니를 확인하려면 "확인", 계속 쇼핑하려면 "취소"를 누르세요.')){
		location.href = '03_cart_list.jsp';
	} else {
		location.href = '01_form.jsp';
	}
</script>