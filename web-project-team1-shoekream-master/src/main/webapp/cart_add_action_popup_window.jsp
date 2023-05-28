<%@page import="com.itwill.shop.product.Product"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="login_check.jspf"%>
<%
	if(request.getMethod().equalsIgnoreCase("GET")){
		response.sendRedirect("product_list.jsp");
		return;
	}
	
	String cart_qty = request.getParameter("cart_qty");
	String p_no = request.getParameter("p_no");
	
	CartService cartService = new CartService();
   	cartService.addCart(new Cart(0,sUserId,Integer.parseInt(cart_qty),
   			new Product(Integer.parseInt(p_no),null,0,null,null,0,0)));
   	
   	
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
<link rel=stylesheet href="css/cart.css" type="text/css">

<title> 장바구니</title>
<script type="text/javascript">
</script>
</head>
<body>

<div style="width:350px; margin:0 auto; padding: 0px; font-family: S-CoreDream-3Light; font-size: 12pt; position: absolute; top:20px; left:20px ; vertical-align: top; ">	
	<img src="image/cartplus.png" width="20px" height="20px" alt="장바구니이미지" >
	<strong>장바구니에 상품이 담겼습니다.</strong>


	
	<div  style="margin-top: 5px; margin-left: auto; margin-right: auto; padding: 5px ;" >
	
		<div  style="margin: 0 auto;padding: 0px 25px"><br>
			<button class="add_button" onclick="window.close();opener.location.reload();">
			<font style="font-family: S-CoreDream-3Light; font-size:10pt;">
				계속 쇼핑</font>
			</button>

			<button class="add_button"  onclick="window.close();opener.location.href='cart_view.jsp';">
			<font style="font-family: S-CoreDream-3Light; font-size:10pt;">
				장바구니</font>
			</button>
			
		</div>
	</div>
</div>
</body>
</html>
