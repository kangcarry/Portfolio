<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="login_check.jspf" %>    
<%
if(request.getMethod().equalsIgnoreCase("GET")){
	response.sendRedirect("shop_main.jsp");
	return;	
}

try{
	String param = "";
	Enumeration params = request.getParameterNames();
	while(params.hasMoreElements()){
	param = (String)params.nextElement();
	}
	String url = request.getParameter(param);
	String[] urlArray = url.split("=");
	ProductService.getInstance().removeComment(Integer.parseInt(param.substring(13)));
	response.sendRedirect("product_detail.jsp"+url);
}catch(Exception e){
	e.printStackTrace();
	response.sendRedirect("user_error.jsp");
}	
%>