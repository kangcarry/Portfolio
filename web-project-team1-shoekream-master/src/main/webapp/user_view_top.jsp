<%@page import="com.itwill.shop.order.OrderService"%>
<%@page import="com.itwill.shop.board.BoardService"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="login_check.jspf" %>  

<div class="member-grade-box">
	<div class="grade-info-box">
		<span class="grade-info"><span><%=sUserId%></span> 님 환영합니다.</span>

		<form id="mypageChangeForm" name="mypageChangeForm" method="post">
			<input type="hidden" id="changeMemberNo" name="changeMemberNo">
		</form>
	</div>
	<div class="right-box">
		<div class="shopping-info point">
			<div class="info-item">
				<span class="info-title" ><img class="icon-cart" style="background-size:97%">장바구니</span> <span
					class="info-data"> <a href="cart_view.jsp"><%=session.getAttribute("cartItemCount")%>
						<span class="unit">개</span></a></span>
			</div>
			<div class="info-item">
				<span class="info-title">
				<img class="icon-order" style="background-position: center; border: 0;">구매목록</span>
				<span class="info-data"><a href="order_list.jsp"><%=session.getAttribute("orderListCount")%>
						<span class="unit">개</span></a></span>
			</div>
		</div>
		<div class="shopping-info">
			<div class="info-item">
				<span class="info-title"><img class="icon-board" style="background-position: center;"></i>게시글 </span> <span
					class="info-data"><a href="user_view_board.jsp"><%=session.getAttribute("boardListCount")%>
						<span class="unit">개</span></a></span>
			</div>
			<div class="info-item">
				<span class="info-title"><img class="icon-reply" style="background-position: center;"></i>상품댓글</span> <span
					class="info-data"> <a href="user_view_comment.jsp"><%=session.getAttribute("commentListCount") %> <span class="unit">개</span></a></span>
			</div>
		</div>
	</div>