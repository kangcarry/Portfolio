<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="login_check.jspf"%>
<%
	CartService cartService = new CartService();
	List<Cart> cartList = cartService.viewCartByUserId(sUserId);
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 장바구니</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">

<style type="text/css" media="screen"></style>

<script src="js/cart.js"></script>
</head>

<body onload="cart_item_select_count();" bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0	marginwidth=0 marginheight=0>
	<form name="cart_view_form" style="margin:0">
		<input type="hidden" name="buyType">
	</form>
	<!-- container start-->
	<div id="container">
		<div id="header">
			<jsp:include page="include_common_top.jsp" />
		</div>
		<div id="wrapper">
			<!-- content start -->

			<!-- include_content.jsp start-->
			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td height="50"><font style="font-family: S-CoreDream-5Medium;" size=5>장바구니</font></td>
								</tr>
							</table> <!--form name="f" method="post"--> 

							<div id='f'>
								<table align=center width=80% border="0" cellpadding="0" cellspacing="1" bgcolor="BBBBBB">
		<tr>
			<!-- 체크박스 전체 선택 -->
			<td height=50 bgcolor=ffffff>
				<input type="checkbox" id="all_select_checkbox" checked="checked" onchange="cart_item_all_select(event);cart_item_select_count();"></td>
			<td bgcolor=ffffff>
				<font style="font-family: S-CoreDream-3Light;" size=4>이미지</font></td>
			<td bgcolor=ffffff>
				<font style="font-family: S-CoreDream-3Light;" size=4>상품명</font></td>
			<td bgcolor=ffffff>
				<font style="font-family: S-CoreDream-3Light;" size=4>수량</font></td>
			<td bgcolor=ffffff>
				<font style="font-family: S-CoreDream-3Light;" size=4>가격</font></td>
			<td bgcolor=ffffff>
				<font style="font-family: S-CoreDream-3Light;" size=4>삭제</font></td>
		</tr>
									<!-- cart item start -->
									<%
									int tot_price = 0;
									for (Cart cart : cartList) {
										tot_price += cart.getProduct().getP_price() * cart.getCart_qty();
									%>
<tr>
			<!-- 체크박스 -->
			<td width=30 height=50 align=center bgcolor="ffffff">
				<input type="checkbox" name="cart_item_no_check" onchange="cart_item_all_select_checkbox_deselect();cart_item_select_count();" value="<%=cart.getCart_no()%>" checked="checked">
			</td>
			<!-- 이미지 -->
			<td width=40 height=50 align=center bgcolor="ffffff">
				<img src='image/product_image/<%=cart.getProduct().getP_image()%>' width="200px" height="200px" />
			</td>
			<!-- 상품명 -->
			<td width=200 height=50 align=center bgcolor="ffffff" >
				<a href='product_detail.jsp?p_no=<%=cart.getProduct().getP_no()%>'>
				<font style="font-family: S-CoreDream-3Light;" size=4><%=cart.getProduct().getP_name()%></font></a>
			</td>
			<!-- 수량변경 폼 -->
			<td width=100 height=50 align=center bgcolor="ffffff">
				<form action="cart_update_item_action.jsp" method="post"	id="cart_update_form_<%=cart.getCart_no()%>">
					<input type="hidden" name="cart_no"	value="<%=cart.getCart_no()%>"> 
					<input type="button" value="-"	onclick="modify_qty('-','cart_update_form_<%=cart.getCart_no()%>');"/>
					<input type="text" readonly="readonly" size="2"	style="text-algin: center; width: 30px" name="cart_qty"	value="<%=cart.getCart_qty()%>"> 
					<input type="button" value="+"	onclick="modify_qty('+','cart_update_form_<%=cart.getCart_no()%>');"/>
					<input type="hidden" name="cart_product_unit_price" value="<%=cart.getProduct().getP_price()%>"/><br><br>	
					<input type='submit' style="width:50px; height:30px; font-family: S-CoreDream-3Light;" size=4 value='변경'>											
				</form>
			</td>
			<!-- 가격 -->
			<td width=100 height=26 align=center bgcolor="ffffff" class=t1>
				<font style="font-family: S-CoreDream-3Light;" size=4>
				<%=new DecimalFormat("#,##0").format(cart.getProduct().getP_price() * cart.getCart_qty())%></font></td>
			<!-- 삭제 폼 -->
			<td width=50 height=26 align=center bgcolor="ffffff" class=t1>
				<form action="cart_delete_item_action.jsp" method="post">
					<input type="hidden" name="cart_no" value="<%=cart.getCart_no()%>">
					<input type="image" src='image/delete.png' >
					
				</form>
			</td>
</tr>
									<%}%>
									<!-- cart item end -->

									<tr>
										<td width=640 colspan=6 height=26 class=t1 bgcolor="ffffff">
											<p align=right>
												<br /> 
												<font style="font-family: S-CoreDream-5Medium;" size=4>총주문금액 :&nbsp;&nbsp; <span id="tot_order_price"> <%=new DecimalFormat("#,##0").format(tot_price)%></span> 원</font>
											</p>
										</td>
									</tr>
								</table>

							</div> 
							<!-- </form> --> <br />

							<table style="padding-left: 10px" border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align=center>&nbsp;&nbsp; 
									
									<button class="add_button" type="button" onclick="location='product_list.jsp'">
													<font style="font-family: S-CoreDream-3Light;" size=2>계속 쇼핑하기</font></button>&nbsp;&nbsp; 
													
									<% if (cartList.size() >= 1) {%> 
									<button class="add_button" type="button" onclick="cart_view_form_select_submit();">
													<font style="font-family: S-CoreDream-3Light;" size=2>총 <span style="font-weight: bold;" id="cart_item_select_count"></span>개 주문</font></button>&nbsp;&nbsp; 
									<button class="add_button" type="button" onclick="cart_delete();">
													<font style="font-family: S-CoreDream-3Light;" size=2>장바구니 비우기</font></button>			
											<%}%>
											<br><br><br>	
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<div id="footer">
			<jsp:include page="include_common_bottom.jsp" />
		</div>
	</div>
	<!--container end-->
</body>
</html>