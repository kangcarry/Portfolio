<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="com.itwill.shop.order.OrderItem"%>
<%@page import="com.itwill.shop.order.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.order.OrderService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="login_check.jspf"%>
<%
OrderService orderService = new OrderService();
List<Order> orderList = orderService.findWithOrderItemByUserId(sUserId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>S.KREAM | 주문 내역</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
 
<style type="text/css" media="screen">
</style>
<script type="text/javascript">
	function order_delete_all_action(){
		document.order_delete_all_form.action='order_delete_all_action.jsp';
		document.order_delete_all_form.method='POST';
		document.order_delete_all_form.submit();
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
<form name="order_delete_all_form" style="margin: 0">
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
									<font style="font-family: S-CoreDream-5Medium;" size=5><%= sUser.getUser_name() %>님 주문 리스트</font></td>
								</tr>
							</table> <!--form-->
							<form name="f" method="post">
								<table align=center width=80%  border="0" cellpadding="0"
									cellspacing="1">
									
									<!-- order start -->
									<%
									if(orderList.isEmpty() == false) {
									//if(orderList!=null){
									for (Order order : orderList) {
										List<OrderItem> orderItemList = order.getOrderItemList();
									%>
									<tr>
										<td colspan= "2" height=24 align=left bgcolor="f4f4f4" class=t1>
											<span style="font-size: 10pt">&nbsp;[No. <%= order.getO_no() %>]</span>
											<span style="font-size: 10pt; font-style: bold;">&nbsp;<%= order.getO_date() %> 구매</span>
										</td>
										<td colspan="1" height=24 align=right bgcolor="f4f4f4" class=t1>
											<span style="font-family: S-CoreDream-3Light; align-content: right;" size=2;>
											<a href="order_detail.jsp?o_no=<%= order.getO_no() %>">&nbsp; 주문 상세보기&nbsp;&nbsp;&nbsp;&nbsp;</a> </span>
										
										<!-- 주문 상세보기 버튼 ver
										<button class="add_button" type="button" value="주문 상세보기" onclick="location='order_detail.jsp?o_no=<%= order.getO_no() %>'">
															<font style="font-family: S-CoreDream-3Light;" size=2>주문 상세보기</font></button>
										 -->
										
										</td>
									</tr>
									<tr>
										<td colspan="6" height=4 align=left class=t1 >
										</td>
									</tr>
									<tr>
										<td width="95%" colspan="8" bgcolor="ffffff" class=t1>
											<!--  -->
											<table align="left" border="0" cellspacing="1" bgcolor="EEEEEE">
												<tr>
													<%
													int orderItemSize = orderItemList.size();
													int remainSize = 8 - orderItemSize;
													for(int j=0;j<orderItemSize;j++){
														OrderItem orderItem = orderItemList.get(j);
														Product product = orderItem.getProduct();
													%>
													<!--상품시작 -->
													<tr>
													<td align="center" style="padding: 0px; width: 40%" bgcolor="ffffff">
													<a style="padding: 0px" href="product_detail.jsp?p_no=<%=product.getP_no()%>">
														<img width="200px" height="200px"
														src="image/product_image/<%=product.getP_image() %>" border="0" style="padding-top: 5px"></a>
													</td>
													<td align="center" style="padding: 0px; width: 50%" bgcolor="ffffff">
													<span><a href = "product_detail.jsp?p_no=<%=product.getP_no()%>">
														<font style="font-family: S-CoreDream-3Light;" size=4><%=product.getP_name()%></font></a></span>
													<br><br>
													<span>
														<font style="font-family: S-CoreDream-3Light;" size=4><%=new DecimalFormat("#,###").format(orderItem.getOi_qty()*product.getP_price())%>원, <%=orderItem.getOi_qty()%>개</font></span>
													</td>
													<td align="center" style="padding: 0px; width: 10%" bgcolor="ffffff">
														<button class="add_button" type="button" value="리뷰 작성하기" onclick="location='product_detail.jsp?p_no=<%=product.getP_no()%>'">
															<font style="font-family: S-CoreDream-3Light;" size=2>리뷰 작성하기</font></button>
											
													</td>
													</tr>
													<!--상품 끝 -->
													<%} %>
													<%
													for(int j = 0;j < remainSize; j++){
													%>
													<!--상품시작 -->
													<!-- <td align="center" style="padding: 0px;width: 55px" bgcolor="ffffff"> </td> -->
													<!--상품 끝 -->
													<%
													}	
													%>
											</table>
										</td>
									<tr>
										<td colspan="5" width=145 height=10 align=center
											bgcolor="ffffff" class=t1></td>
									</tr>
									<!-- order end -->
									<%
									// 주문 내역이 없을 경우 보여줄 내용
									}}else if(orderList.isEmpty() == true) {
									%>
									<tr><td height="200px"><br>
									<span style="font-size: 20pt"><b>주문 내역이 없습니다.</b></span></td></tr>
									<%} %>
									<!-- order end -->
								</table>
								
							</form> <br />
							<hr>
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align=center>&nbsp;&nbsp;
									<a href=product_list.jsp
										class=m1>계속 구경하기</a>
									<a href='javascript:order_delete_all_action();'
										class=m1>주문내역 전체 삭제</a>
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
			<jsp:include page="include_common_bottom.jsp" />
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>