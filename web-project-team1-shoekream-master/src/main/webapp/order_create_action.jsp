<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@page import="com.itwill.shop.user.UserService"%>
<%@page import="com.itwill.shop.order.OrderService"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@page import="com.itwill.shop.order.Order"%>
<%@page import="com.itwill.shop.order.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf"%>
<%
/*
주문생성
*/
if (request.getMethod().equalsIgnoreCase("GET")) {
	response.sendRedirect("order_list.jsp");
	return;
}
String buyType = request.getParameter("buyType");
String p_noStr = request.getParameter("p_no");
String p_qtyStr = request.getParameter("p_qty");
String d_no = request.getParameter("d_checked_no");

String[] cart_item_no_strArray = request.getParameterValues("cart_item_no");

OrderService orderService = new OrderService();
CartService cartService = new CartService();

if (buyType.equals("cart")) {
	orderService.cartOrder(sUserId, Integer.parseInt(d_no));
} else if (buyType.equals("cart_select")) {
	orderService.cartSelectOrder(sUserId, cart_item_no_strArray, Integer.parseInt(d_no));
} else if (buyType.equals("direct")) {
	orderService.directOrder(sUserId, Integer.parseInt(p_noStr), Integer.parseInt(p_qtyStr), Integer.parseInt(d_no));
}
response.sendRedirect("order_list.jsp");
%>