<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.order.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="login_check.jspf"%>
<%
/****** order ******/
OrderService orderService = new OrderService();
List<Order> orderList = orderService.findByUserId(sUserId);
List<OrderItem> orderItemList = new ArrayList<OrderItem>();

String o_noStr = request.getParameter("o_no");
Order thisOrder = orderService.findWithOrderItemByOrderNo(Integer.parseInt(o_noStr));

/****** order ******/
DeliveryService deliveryService = new DeliveryService();
List<Delivery> deliveryInfo = deliveryService.selectDelivery(sUserId);

Delivery thisDelivery = orderService.findWithOrderItemByDno(Integer.parseInt(o_noStr));
// 화폐단위 구분을 위해 numberFormat 설정
NumberFormat numberFormat = NumberFormat.getInstance();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 주문 상세보기</title>

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
function order_delete_action(){
	document.order_delete_all_form.action='order_delete_action.jsp';
	document.order_delete_all_form.method='POST';
	document.order_delete_all_form.submit();
}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp"/>
			<!-- include_common_top.jsp end-->
		</div>
		<!-- header end -->
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
									<td height="50">
									<font style="font-family: S-CoreDream-5Medium;" size=5><%= sUser.getUser_name() %>님의 주문 상세 조회</font></td>
								</tr>
							</table> <!--form-->
							<form name="f" method="post" action="order_delete_action.jsp">
								<input type="hidden" name="o_no" value="<%= thisOrder.getO_no()%>">
								<table align="center" width="80%"  border="0" cellpadding="0" cellspacing="1"  bgcolor="FFFFFF" >
									<caption style="text-align: left;">주문상세정보</caption>
									<tr>
										<td width=15% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>주문번호</font></td>
										<td width=20% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>주문일</font></td>
										<td width=20% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>주문자</font></td>
										<td width=15% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>비 고</font></td>
									</tr>
									
									
									<tr>
										<td width=15% height=26 align=center bgcolor="ffffff" class=t1><%= thisOrder.getO_no() %></td>
										<td width=20% height=26 align=center bgcolor="ffffff" class=t1><%=new SimpleDateFormat("yyyy/MM/dd").format(thisOrder.getO_date()) %></td>
										<td width=30% height=26 align=center bgcolor="ffffff" class=t1><%= sUserId %></td>
										<td width=15% height=26 align=center bgcolor="ffffff" class=t1>
												<input type="submit" value="삭제"  style="width:50px; height:30px; font-family: S-CoreDream-3Light; font-size:10pt;">
										</td>
									</tr>
								</table>
								<br>
								
								<table align="center" width="80%"  border="0" cellpadding="0" cellspacing="1"  bgcolor="FFFFFF" >
									<caption style="text-align: left;">배송 정보</caption>
									<tr>
										<td width=25% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>받는이</font></td>
										<td width=30% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>연락처</font></td>
										<td width=30% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>배송지</font></td>	
										<td width=15% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>비 고</font></td>
									</tr>
									
									
									<tr>
										<td width=25% height=26 align=center bgcolor="ffffff" class=t1><%= thisDelivery.getD_name() %></td>
										<td width=30% height=26 align=center bgcolor="ffffff" class=t1><%= thisDelivery.getD_phone() %></td>
										<td width=30% height=26 align=center bgcolor="ffffff" class=t1><%= thisDelivery.getD_address() %></td>
										<td width=15% height=26 align=center bgcolor="ffffff" class=t1></td>
									</tr>
								</table>
								
									
								<br/>	
								<table align=center  width=80% border="0" cellpadding="0" cellspacing="1"  bgcolor="FFFFFF" >
									<caption style="text-align: left;">주문제품목록</caption>
									<tr style="border: 0.1px solid">
										<td width=290 height=25 align=center bgcolor="f4f4f4" class=t1>상품 이미지</td>
										<td width=290 height=25 align=center bgcolor="f4f4f4" class=t1>상품명</td>
										<td width=112 height=25 align=center bgcolor="f4f4f4" class=t1>수 량</td>
										<td width=166 height=25  align=center bgcolor="f4f4f4" class=t1>가 격</td>
										<td width=50 height=25  align=center bgcolor="f4f4f4" class=t1>비 고</td>
									</tr>
									
									<!-- order item start -->
									
									<% 
									int tot_price = 0;
									for(OrderItem orderItem : thisOrder.getOrderItemList()) { 
									tot_price = orderItem.getOi_qty() * orderItem.getProduct().getP_price();
									%>
									<tr>
										
										<td width=290 height=50 align=center  bgcolor="ffffff" class=t1>
										<a style="padding: 0px" href="product_detail.jsp?p_no=<%= orderItem.getProduct().getP_no() %>">
										<img width="150px" height="150px"
										src="image/product_image/<%= orderItem.getProduct().getP_image() %>" border="0" style="padding-top: 5px"></a>
										</td>
										<td width=290 height=50 align=center  bgcolor="ffffff" class=t1>
										<a href='product_detail.jsp?p_no=<%= orderItem.getProduct().getP_no() %>'><%= orderItem.getProduct().getP_name() %></a>
										</td>
										
										<td width=110 height=26 align=center  bgcolor="ffffff" class=t1><%= orderItem.getOi_qty() %></td>
										
										<td width=150 height=26 align=center bgcolor="ffffff" class=t1><%= numberFormat.format(tot_price) %></td>
										<td width=60 height=26 align=center class=t1 bgcolor="ffffff"></td>
									</tr>
									<% }
									
									String order_tot_price = numberFormat.format(thisOrder.getO_price());
									%>
									
									<!-- order item end -->
									
									<tr>
										<td width=640 colspan=5 height=26  bgcolor="ffffff" class=t1>
										
											<p align=right style="padding-top: 10px">
												<!-- 총 주문금액 TOTAL ver. 
												<font color=#FF0000>TOTAL : <%= order_tot_price %> 원</font>
												 -->
												<font style="font-family: S-CoreDream-5Medium;" size=4>총주문금액 :<%= order_tot_price %> 원</font>
												
											</p>
										</td>
									</tr>
								</table>
							</form> <br />
							<hr>
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align=center> 
										<button class="add_button" type="button" onclick="location='order_list.jsp'">
											<font style="font-family: S-CoreDream-3Light;" size=2>주문목록</font></button>&nbsp;&nbsp; 
										<button class="add_button" type="button" onclick="location='product_list.jsp'">
											<font style="font-family: S-CoreDream-3Light;" size=2>계속 쇼핑하기</font></button>&nbsp;&nbsp; 
									</td>
								</tr>
							</table><hr></td>
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
			<jsp:include page="include_common_bottom.jsp"/>
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>