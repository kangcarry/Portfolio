<%@page import="java.util.List"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<%
boolean isLogin = false;
String sUserId=(String)session.getAttribute("sUserId");
if (session.getAttribute("sUserId") != null) {
	isLogin = true;
}

String keyword = request.getParameter("keyword");
ProductService productService = ProductService.getInstance();
List<Product> productList = productService.productFindByName(keyword);


%>
<!DOCTYPE html>
<html>
<head>
<title>쇼핑몰</title>
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
		let cartWin = window.open("about:blank","cartForm","width=260,height=130,top="+top+",left="+left+",location=no, directories=no, status=no, menubar=no, scrollbars=no,copyhistory=no");
		f.action = 'cart_add_action_popup_window.jsp';
		f.target = 'cartForm';
		f.method = 'POST';
		f.submit();
	}
}
</script> 
<style type="text/css" media="screen">
</style>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<jsp:include page="include_common_top.jsp" />
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
									<td height="50">
								<font style="font-family: S-CoreDream-5Medium;" size=5>검색결과</font></td>
								</tr>
							</table>
							<div id="f">
								<table width="100%" align="center" border="0" cellpadding="10" cellspacing="1" bgcolor="ffffff">
									<%
									int product_size=productList.size();
									int product_column_size=4;
									int product_line_count = 1;
									
									for (int i=0;i<productList.size();i++) {
											Product product=productList.get(i);
									%>
									<%
									 if(i%product_column_size==0){
									%>
									<tr>
									<%} %>
										<td align="center" width="25%"  bgcolor="f4f4f4">
										<a href="product_detail.jsp?p_no=<%=product.getP_no()%>">
										<img width="200px" height="200px" src="image/product_image/<%=product.getP_image()%>" border="0"></a><br/><br/>
											<font style="font-family: S-CoreDream-5Medium;" size=4><%=product.getP_name()%></font><br><br>
											<form style="display: inline;">
												<input type="hidden" name="p_no" value="<%=product.getP_no()%>">
												<input type="hidden" name="cart_qty" value="1">
											<font style="font-family: S-CoreDream-5Medium;" size=4>
												<%=new DecimalFormat("#,##0").format(product.getP_price())%>원</font><br><br>
											<button class="add_button" type="button" onclick="add_cart_popup_window(this.parentElement);">
													<font style="font-family: S-CoreDream-3Light;" size=2>장바구니 담기</font></button>
											</form><br> </td>
									<%if(i%product_column_size==3){%>
									</tr>
									<%} %>	
								   <%}%>	
								</table>
							</div> <br /></td>
					</tr>
				</table>
			</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<jsp:include page="include_common_bottom.jsp" />
		</div>
	</div>
	<!--container end-->
</body>
</html>