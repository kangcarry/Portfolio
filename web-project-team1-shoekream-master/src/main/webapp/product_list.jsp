<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
boolean isLogin = false;
String sUserId=(String)session.getAttribute("s_u_id");
if (session.getAttribute("sUserId") != null) {
	isLogin = true;
}
String category_noStr=request.getParameter("category_no");
String sort_select=request.getParameter("sort_select");
if(category_noStr==null)category_noStr="0";

ProductService productService = new ProductService();
List<Product> productList = null;
if(category_noStr.equals("0")){
	productList=productService.productList();
	if(sort_select==null) {
		sort_select="";
	}else {
		if(sort_select.equals("price_sort_asc")){
			productList=productService.priceSortAsc();
		}else if(sort_select.equals("price_sort_desc")){
			productList=productService.priceSortDesc();
		}else if(sort_select.equals("click_sort_desc"))
			productList=productService.clickSortDesc();
	}
}else{
	productList=productService.productCategory(Integer.parseInt(category_noStr));
	if(sort_select==null) {
		sort_select="";
	}else {
		if(sort_select.equals("price_sort_asc")){
			productList=productService.priceSortCateAsc(Integer.parseInt(category_noStr));
		}else if(sort_select.equals("price_sort_desc")){
			productList=productService.priceSortCateDesc(Integer.parseInt(category_noStr));
		}else if(sort_select.equals("click_sort_desc"))
			productList=productService.clickSortCateDesc(Integer.parseInt(category_noStr));
	}
}


%>

<!DOCTYPE html>
<html>
<head>
<title>S.KREAM | 상품목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">
<script type="text/javascript">

function add_cart_popup_window(f){
	if (<%=!isLogin%>) {
		alert('로그인 하세요');
		location.href = 'user_login_form.jsp';
	} else {
		let left = Math.ceil(( window.screen.width)/5);
		let top = Math.ceil(( window.screen.height)/3); 
		let cartWin = window.open("about:blank","cartForm","width=400,height=130,top="+top+",left="+left+",location=no, directories=no, status=no, menubar=no, scrollbars=no,copyhistory=no");
		f.action = 'cart_add_action_popup_window.jsp';
		f.target = 'cartForm';
		f.method = 'POST';
		f.submit();
	}
}

function product_alignment_action_form_submit(){
	let type = document.getElementById('sort_option');
	let url = location.search;
	document.getElementById('sort_select').value = type.options[type.selectedIndex].value;
	if(document.getElementById('sort_select').value!==null){
		url = url.split('&',1)+"&sort_select="+document.getElementById('sort_select').value;
	}
	location.href="product_list.jsp"+url;
}
</script> 
<style type="text/css" media="screen">
</style>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp" />
			<!-- include_common_top.jsp end-->
		</div>
		<!-- header end -->
		<!-- wrapper start -->
		<div id="wrapper">
			<!-- content start -->

			<!-- include_content.jsp start-->
			<div id="content">
				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td bgcolor="ffffff" height="50"><font style="font-family: S-CoreDream-5Medium;" size=5>상품리스트</font></td>
								</tr>
							</table>
							<br><font style="font-family: S-CoreDream-3Light;" size=3>정렬&nbsp;</font>
							<select id="sort_option" onchange="product_alignment_action_form_submit();">
								<option value="normal">선택
								<option value="price_sort_asc">가격 오름차순
								<option value="price_sort_desc">가격 내림차순
						   		<option value="click_sort_desc">조회수 순 
							</select> <br><br> 
						    <input id="sort_select" name="sort_select" type="hidden" value="">
							<div id="f">
								<table width="100%" align="center" border="0" cellpadding="10" cellspacing="1" bgcolor="ffffff">
								
									<%
									int product_size=productList.size();
									int product_column_size=4;
									int product_line_count = 1;
									
									
									for (int i=0;i<productList.size();i++) {
											Product product=productList.get(i);
									%>
									<!--상품시작 -->
									<%
									 if(i%product_column_size==0){
									%>
									<tr>
									<%} %>

										<td align="center" width="25%"  bgcolor="f4f4f4">
										<a href="product_detail.jsp?p_no=<%=product.getP_no()%>">
										<img width="200px" height="200px" src="image/product_image/<%=product.getP_image()%>" border="0">
										</a><br />

											
											<br /><font style="font-family: S-CoreDream-5Medium;" size=4><%=product.getP_name()%></font><br><br>
											<form style="display: inline;">
												<input type="hidden" name="p_no" value="<%=product.getP_no()%>">
												<input type="hidden" name="cart_qty" value="1">
												<!-- 
												<font style="font-family: GongGothicMedium;" size=4>
													<%=new DecimalFormat("#,##0").format(product.getP_price())%>&nbsp;원<br><br>
												</font>
												 -->
												<font style="font-family: S-CoreDream-5Medium;" size=4>
													<%=new DecimalFormat("#,##0").format(product.getP_price())%>&nbsp;원<br><br>
												</font>
												<button class="add_button" type="button" onclick="add_cart_popup_window(this.parentElement);">
													<font style="font-family: S-CoreDream-3Light;" size=2>장바구니 담기</font></button>
											
											</form><br><br></td> 
									<%if(i%product_column_size==3){%>
									</tr>
									<%} %>	
									
								   <!--상품 끝 -->
								   <%}%>
								</table>
								
							</div> <br /></td>
					</tr>
				</table>
				
				<div class="moveTopBtn" onClick="javascript:window.scrollTo(0,0)" alt="맨위로" >
				<img src = "image/up.png" width="50px" height="50px" ></div>
				
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp" />
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>