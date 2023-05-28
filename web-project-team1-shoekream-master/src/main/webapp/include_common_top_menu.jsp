<%@page import="com.itwill.shop.cart.CartService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
int cart_item_count=0;
	String sUserId=(String)session.getAttribute("sUserId");
	if(sUserId!=null){
		CartService cartService=new CartService();
	  	cart_item_count = cartService.viewCartByUserId(sUserId).size();

	}
%>
<script type="text/javascript">
/*
function p_search(){
	if(document.getElementById("keyword").value==""){
		alert('검색어를 입력하세요');
		return;
	}else{
		document.top_f.action="product_search_action.jsp";
		document.top_f.method="GET";
		document.top_f.submit;
	}
}
*/

function product_search(){
	searchform.action = "product_search_list.jsp";
	searchform.method = 'GET';
	searchform.submit();
}


</script>		
<div id="menu">
	<ul>
		<li id="logo" style="height:60px;"><a href="shop_main.jsp"></a></li>
		<%if(sUserId==null){  %>
			<li id="cart" title="장바구니"><span class="w3-badge-no-login w3-green-no-login w3-margin-right"></span><a href="user_login_form.jsp" title="장바구니"></a></li>
			<li id="join" title="회원가입" ><a href="user_write_form.jsp" ></a></li>
			<li id="login" title="로그인" ><a href="user_login_form.jsp" ></a></li>
			
		<%}else{ %>
			<li id="cart" title="장바구니"><span class="w3-badge w3-green w3-margin-right"><%=cart_item_count%></span><a href="cart_view.jsp"></a></li>
			<li id="mypage" title="나의페이지" ><a href="user_view.jsp"></a></li>
			<li id="logout" title="로그아웃" ><a href="user_logout_action.jsp"></a></li>
			
		<%} %>
		<li id="p_search" >
				<img src="image/icons/search.png" height=40 style="cursor:pointer; margin-right:60px;" onclick="product_search();"></li>
		<li>
			<form id="searchform" method="POST">
				<input type="text" id="keyword" name="keyword" style="width:200px;height:30px;font-size:18px;" onKeypress="javascript:if(event.keyCode==13) {product_search();}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</form>
		</li>
		
	</ul>

</div>