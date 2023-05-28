<%@page import="java.util.Enumeration"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="login_check.jspf" %>    
<%
    if(request.getMethod().equalsIgnoreCase("GET")){
    		response.sendRedirect("shop_main.jsp");
    		return;
    	}

	String d_no = request.getParameter("delivery_no");
	DeliveryService deliveryService = new DeliveryService();
	deliveryService.deleteDelivery(Integer.parseInt(d_no));
   	response.sendRedirect("user_view_delivery.jsp");
    %>