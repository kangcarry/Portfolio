<%@page import="com.itwill.shop.product.ProductComment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf"%>
<%
int commentListCount = (int)session.getAttribute("commentListCount");
List<ProductComment> commentList = (List)session.getAttribute("commentList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>내정보수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/user.css" type="text/css">
<link rel=stylesheet href="css/product.css" type="text/css">
<link rel=stylesheet href="css/menu.css" type="text/css">
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

			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b><%=sUser.getUser_name()%>님의
											상품평 리스트 </b></td>
								</tr>
							</table>
							<table style="padding-left: 10px" border=0 cellpadding=0>
							<tr>
							<td>
							<ul style="padding: 0;">
							<%
							if (commentList.size() == 0) {
							%>
							<li id="comment" class="fdb_itm" style="text-align: center;">
								<div class="meta">
								</div>
								<input type="text" disable style="font-size: 40px; text-align:center; border: none;
								  " value="등록된 후기가 없습니다.">
								<div>
									<div class="xe_content"></div>
								</div>
							</li>
							<%}else {%>
							<%for (ProductComment comment : commentList) {%>
							<table style="padding-left: 0" border=0 cellpadding=0>
							<td style="padding: 0px 13px 0px 0px; border-top: 1px solid #eee; border-bottom: 1px solid #eee; border-right: 1px solid #eee; width: 100px; ">
							<img src = "image/product_image/<%=comment.getP_image() %>" style="width: 80%;">
							<a style="margin: 0;" href="product_detail.jsp?p_no=<%=comment.getP_no() %>" ><%=comment.getP_name() %></a>
							</td>
							<td>
							<li id="comment" class="fdb_itm">
								<div class="meta">
									<img src="image/user_comment.png" style="margin-right: 5px"
										class="writer"><%=comment.getWriter()%>
									<span class="date"><%=comment.getRegDate()%></span>
								</div>
								<div>
									<div class="xe_content"><%=comment.getContent()%>
									</div>
								</div>
								<div style="margin-top: 10px">
									<%if(comment.getWriter().equals(session.getAttribute("sUserId"))) { %>
									<form id="comment_remove_form<%=comment.getPc_no() %>">
									<span style="float: right; margin-right: 10px;">
									<button id="comment_remove<%=comment.getPc_no() %>" type="button" value="" 
									onclick="comment_remove(<%=comment.getPc_no() %>)">삭제
									</button>
									<input type="hidden" id="comment_pc_no<%=comment.getPc_no() %>" name="comment_pc_no<%=comment.getPc_no() %>" value="">
									</span>
									</form>
									<%}%>	
									<%for (int i = 0; i < comment.getPc_mark(); i++) {%>
									<span class="mark"> <em><img class="mark_image"
											src="image/heart_comment.png"> </em>
									</span>
									<%}%>
								</div>
							</li>
							</table>
							<%}%>
						<%}%>
						</ul>
						</td>
						</tr>	
						</table>
					</tr>
				</table>
			</div>
			<br>
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer"></div>
	</div>
	<!--container end-->
</body>
</html>
