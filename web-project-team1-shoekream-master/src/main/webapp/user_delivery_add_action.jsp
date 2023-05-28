<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="login_check.jspf" %>    
<%
    String d_user_name=request.getParameter("d_user_name");
    String d_user_phone=request.getParameter("d_user_phone");
    String d_user_address=request.getParameter("d_user_address");
	DeliveryService deliveryService = new DeliveryService();
	deliveryService.insertDelivery(d_user_address, d_user_phone, d_user_name, sUserId);
   	response.sendRedirect("user_view_delivery.jsp");
%>