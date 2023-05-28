<%@page import="com.itwill.shop.product.ProductService"%>
<%@page import="com.itwill.shop.product.ProductComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="login_check.jspf"%>
<%
	if(request.getMethod().equalsIgnoreCase("GET")){
		response.sendRedirect("board_list.jsp");
		return;
	}
	String url = request.getParameter("url_check");
	ProductComment comment=new ProductComment();
	comment.setContent(request.getParameter("cmt_able"));
	comment.setWriter((String)session.getAttribute("sUserId"));
	comment.setP_no(Integer.parseInt(request.getParameter("p_no_check")));
	comment.setPc_mark(Integer.parseInt(request.getParameter("mark_value")));
	
	ProductService.getInstance().saveComment(comment);
	response.sendRedirect("product_detail.jsp"+url);
%>
