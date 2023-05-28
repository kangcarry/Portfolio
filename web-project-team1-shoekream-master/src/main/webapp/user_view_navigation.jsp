<%@page import="com.itwill.shop.board.BoardService"%>
<%@page import="com.itwill.shop.user.UserService"%>
<%@page import="com.itwill.shop.user.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<ol class="mypage">마이페이지
	<li></li>
	<li></li>
	<li></li>
	<li>
		<span class="mypage_title">쇼핑내역</span>
		<li class="mypage_item">
		<a href="order_list.jsp" class="">주문내역 조회</a>
		</li>
	</li>	
	<li>
		<span class="mypage_title">개인정보</span>
			<li class="mypage_item">
			<a href="user_view_delivery.jsp" class="">배송주소록 관리</a>
			</li>
			<li class="mypage_item">
			<a href="user_view_update.jsp" class="">개인정보 수정</a>
			</li>
			<li class="mypage_item">
			<a href="user_remove_form.jsp" class="" >회원탈퇴</a>
			</li>
			<li class="mypage_item">
			<a href="user_logout_action.jsp">로그아웃</a>
			</li>
	</li>
	<li><span class="mypage_title" >고객지원</a></span></li>
			<li class="mypage_item">
			<a href="board_list.jsp">문의게시판</a>
			</li>
		
</ol>
