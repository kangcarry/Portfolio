<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@page import="com.itwill.shop.user.UserService"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf"%>
<%
if (request.getMethod().equalsIgnoreCase("GET")) {
	response.sendRedirect("order_list.jsp");
	return;
}

String buyType = request.getParameter("buyType");
String p_noStr = request.getParameter("p_no");
String p_qtyStr = request.getParameter("p_qty");
String[] cart_item_noStr_array = request.getParameterValues("cart_item_no");

if(buyType==null)buyType="";
if(p_noStr==null)p_noStr="";
if(p_qtyStr==null)p_qtyStr="";
if(cart_item_noStr_array==null)
	cart_item_noStr_array=new String[]{};
System.out.println(p_qtyStr);
CartService cartService = new CartService();
UserService userService = new UserService();
ProductService productService = new ProductService();
DeliveryService deliveryService = new DeliveryService();

List<Delivery> deliveryList = deliveryService.selectDelivery(sUserId);
List<Cart> cartItemList = new ArrayList<Cart>();
User user = userService.findUser(sUserId);

if (buyType.equals("cart")) {
	cartItemList = cartService.viewCartByUserId(sUserId);
} else if (buyType.equals("cart_select")) {
	for (String cart_item_noStr : cart_item_noStr_array) {
		cartItemList.add(cartService.viewCartByCartNo(Integer.parseInt(cart_item_noStr)));
	}
} else if (buyType.equals("direct")) {
	Product product = productService.productDetail(Integer.parseInt(p_noStr));
	cartItemList.add(new Cart(0,sUserId,Integer.parseInt(p_qtyStr), product));
}


%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>쇼핑몰 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">

<style type="text/css" media="screen">
/*
form > table tr td{
	border: 0.1px solid black;
}
*/
</style>
<script type="text/javascript">

	function order_create_form_submit() {
		if(document.order_create_form.d_checked_no.value==""){
			alert("배송지를 선택해주세요");
			return;
		}else{
			document.order_create_form.method = 'POST';
			document.order_create_form.action = 'order_create_action.jsp';
			document.order_create_form.submit();
		}
		
	}
	
	function d_click() {
		 const d_clickList
		  = document.getElementsByName('d_no');
		  d_clickList.forEach((node) => {
		    if(node.checked)  {
		      document.getElementById('d_checked_no').value
		        = node.value;
		    }
		  })
	}
	
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<form name="order_create_form" method="post">
		<input type="hidden" name="buyType" value="<%=buyType%>"> <input
			type="hidden" name="p_no" value="<%=p_noStr%>"> <input
			type="hidden" name="p_qty" value="<%=p_qtyStr%>">
		<%
		for (String cart_item_noStr : cart_item_noStr_array) {
		%>
		<input type="hidden" name="cart_item_no" value="<%=cart_item_noStr%>">
		<%
		}
		%>
		<input type="hidden" id="d_checked_no" name="d_checked_no" value="">
		
		
	</form>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp" />
			<!-- include_common_top.jsp end-->
		</div>
		<!-- header end -->
		<!-- navigation start-->
		<div id="navigation">
			<!-- include_common_left.jsp start-->
			<!-- include_common_left.jsp end-->
		</div>
		<!-- navigation end-->
		<!-- wrapper start -->
		<div id="wrapper">
			<!-- content start -->

			<!-- include_content.jsp start-->
			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>쇼핑몰 -
											주문/결제폼</b></td>
								</tr>
							</table> <!--form-->
								<table align=center width=80% border="0" cellpadding="0"
									cellspacing="1" bgcolor="BBBBBB">
									<caption style="text-align: left;">구매자정보</caption>
									<tr>
										<td width=290 height=25 align=center bgcolor="f4f4f4" class=t1>아이디</td>
										<td width=112 height=25 align=center bgcolor="f4f4f4" class=t1>이름</td>
										<td width=166 height=25 align=center bgcolor="f4f4f4" class=t1>이메일</td>
										<td width=50 height=25 align=center bgcolor="f4f4f4" class=t1>비고</td>
									</tr>
									<tr>
										<td width=290 height=26 align=center bgcolor="ffffff" class=t1><%=user.getUser_id()%></td>
										<td width=112 height=26 align=center bgcolor="ffffff" class=t1><%=user.getUser_name()%></td>
										<td width=166 height=26 align=center bgcolor="ffffff" class=t1><%=user.getUser_email()%></td>
										<td width=50 height=26 align=center bgcolor="ffffff" class=t1><%=user.getUser_address()%></td>
									</tr>
								</table>

								<br />

								<table align=center width=80% border="0" cellpadding="0"
									cellspacing="1" bgcolor="BBBBBB">
									<caption style="text-align: left;">주문제품목록</caption>
									<tr style="border: 0.1px solid">
										<td width=290 height=25 bgcolor="f4f4f4" align=center class=t1>상품명</td>
										<td width=112 height=25 bgcolor="f4f4f4" align=center class=t1>수량</td>
										<td width=166 height=25 bgcolor="f4f4f4" align=center class=t1>가격</td>
										<td width=50 height=25 bgcolor="f4f4f4" align=center class=t1>비고</td>
									</tr>
									<%
									int tot_price = 0;
									for (Cart cart : cartItemList) {
										tot_price += cart.getCart_qty() * cart.getProduct().getP_price();
									%>
									<!-- cart item start -->
									<tr>
										<td width=290 height=26 align=center bgcolor="ffffff" class=t1>
											<a href='product_detail.jsp?p_no=<%=cart.getProduct().getP_no()%>'><%=cart.getProduct().getP_name()%></a>
										</td>
										<td width=112 height=26 align=center bgcolor="ffffff" class=t1><%=cart.getCart_qty()%></td>
										<td width=166 height=26 align=center bgcolor="ffffff" class=t1>
											<%=new DecimalFormat("#,###").format(cart.getCart_qty() * cart.getProduct().getP_price())%>
										</td>
										<td width=50 height=26 align=center bgcolor="ffffff" class=t1></td>
									</tr>
									<!-- cart item end -->
									<%}%>
									<tr>
										<td width=640 colspan=4 height=26 bgcolor="ffffff" class=t1>
											<p align=right style="padding-top: 10px">
												<font color=#FF0000>총 주문 금액 : <%=new DecimalFormat("#,###").format(tot_price)%>원</font>
											</p>
										</td>
									</tr>
								</table>
								<br>
								<table align=center width=80% border="0" cellpadding="0"
									cellspacing="1" bgcolor="BBBBBB">
									<caption style="text-align: left;">배송지 정보</caption>
									<tr>
										<td width=150 height=25 align=center bgcolor="f4f4f4" class=t1>받으시는 분</td>
										<td width=150 height=25 align=center bgcolor="f4f4f4" class=t1>연락처</td>
										<td width=200 height=25 align=center bgcolor="f4f4f4" class=t1>배송지</td>
										<td width=100 height=25 align=center bgcolor="f4f4f4" class=t1>배송지선택</td>
									</tr>
											<% for(Delivery delivery : deliveryList) { %>
											<tr>
											<td width=130 height=26 align=center bgcolor="ffffff" class=t1 ><%=delivery.getD_name()%></td>
											<td width=130 height=26 align=center bgcolor="ffffff" class=t1 ><%=delivery.getD_phone()%></td>
											<td width=150 height=26 align=center bgcolor="ffffff" class=t1 ><%=delivery.getD_address()%></td>
											<td width=50 height=26 align=center bgcolor="ffffff" class=t1 >
												<input type="radio" onclick ="d_click(event);" name="d_no" value="<%=delivery.getD_no()%>">
											</td>
											</tr>
											<%}%>
								</table>
							
							<br />
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<br>
									<td align=center>&nbsp;&nbsp; <a
										href="javascript:order_create_form_submit();" class=m1>구매/결제하기</a>
										&nbsp;&nbsp;<a href=product_list.jsp class=m1>계속 쇼핑하기</a>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<br><br>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp" />
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>