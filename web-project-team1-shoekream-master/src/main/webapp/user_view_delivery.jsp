	<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf"%>
<%
DeliveryService deliveryService = new DeliveryService();
List<Delivery> deliveryList = deliveryService.selectDelivery(sUserId);

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 배송지관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/user.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">

<script src="js/user.js"></script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<jsp:include page="include_common_top.jsp" />
		</div>
		<!-- header end -->
		<!-- wrapper start -->
		<div id="wrapper">
			<div id="user_navigation">
				<jsp:include page="user_view_navigation.jsp" />
			</div>
			<div id="psuedo"></div>
			<div id="user_content">
				<jsp:include page="user_view_top.jsp" />
			</div>

			<div id="user_view">
				<table style="padding-left: 10px" border=0 cellpadding=0
					cellspacing=0>
					<tr>
						<td height="50">
							<font style="font-family: S-CoreDream-5Medium;" size=5>배송주소록</font></td>
					</tr>
				</table>
				<!-- view Form  -->
					<table align=center width=80% border="0" cellpadding="0"
						cellspacing="1" bgcolor="BBBBBB">
						<caption style="font-family: S-CoreDream-5Medium; font-size:12pt; text-align: left;">배송지 정보</caption>
						<tr>
							<td height=25 align=center bgcolor="f4f4f4" class=t1>받으시는분</td>
							<td height=25 align=center bgcolor="f4f4f4" class=t1>연락처</td>
							<td height=25 align=center bgcolor="f4f4f4" class=t1>주소</td>
							<td width=10 height=25 align=center bgcolor="f4f4f4" class=t1></td>
						</tr>
						<%for(Delivery deliveryAddress : deliveryList) {%>
						<tr>
							<td width=130 height=30 align=center bgcolor="ffffff" class=t1><%=deliveryAddress.getD_name()%></td>
							<td width=130 height=30 align=center bgcolor="ffffff" class=t1><%=deliveryAddress.getD_phone()%></td>
							<td width=150 height=30 align=center bgcolor="ffffff" class=t1><%=deliveryAddress.getD_address()%></td>
							<td width=150 height=30 align=center bgcolor="ffffff" class=t1>
							<form id="delivery_f" name="delivery_f" method="post" action="user_view_delivery_delete_address_action.jsp">
								<input type="hidden" id="delivery_no" name="delivery_no" value="<%=deliveryAddress.getD_no()%>">
								<input type="image"	src='image/delete.png' >
							</form>
							</td>
						</tr>
						<%} %>
					</table>
				<br />
				<br />
				<div>
					<input type="button" value="배송지 추가" onClick="deliveryAddAction();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="돌아가기" onClick="returnUserView()">
				</div>
			</div>
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer"></div>
	</div>
	<!--container end-->
</body>
</html>
