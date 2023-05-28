<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="java.text.DecimalFormat"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%

ProductService productService = ProductService.getInstance();
List<Product> popularList = productService.findPopular();

%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 메인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/menu.css" type="text/css"> 
<link rel=stylesheet href="css/shop.css" type="text/css"> 



<style type="text/css" media="screen">
</style>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0	marginwidth=0 marginheight=0>
	<!-- container start-->
<div id="container" height=100%>
		<!-- header start -->
	<div id="header">
			<jsp:include page="include_common_top.jsp"/>
	</div>
		<!-- header end -->
		<!-- wrapper start -->
	<div id="wrapper">
			<!-- content start -->
			<!-- include_content.jsp start-->
		<div id="content">
			
<!-- 수정 start &nbsp;&nbsp;&nbsp;-->			
<!-- ver.1 start-->
			<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
				<table style="padding-left: 10px" border=0 cellpadding=0 cellspacing=0>
								<tr>
									<td height="50">
									<font style="font-family: S-CoreDream-5Medium;" size=5>인기상품</font></td>
								</tr>
				</table>
			<div id="f">
				<table width="100%" align="center" border="0" cellpadding="10" cellspacing="0" bgcolor="f4f4f4">
						<tr>
							<td><img src="image/icons/crown.png" width=40px></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td><font style="font-family: GongGothicMedium;" size=6>BEST 1</font></td>
							<td><font style="font-family: GongGothicMedium;" size=6>BEST 2</font></td>
							<td><font style="font-family: GongGothicMedium;" size=6>BEST 3</font></td>
						</tr>
						<tr>
									<%
			for (int i=0; i<popularList.size(); i++) {
				Product product = popularList.get(i);
									%>
								<td align="center" width="30%" >
								<a href="product_detail.jsp?p_no=<%=product.getP_no()%>">
								<img width=250px; height=250px; src="image/product_image/<%=product.getP_image()%>" border="0"></a><br>
																<!-- 조회수 -->
								<img src="image/icons/heart.png" width=25px>
								<font style="font-family: GongGothicMedium; vertical-align:super;" size=5;>&nbsp;<%=product.getP_click_count()%>&nbsp;&nbsp;</font><br><br><br>
								<!-- 상품명 -->
								<font style="font-family: GongGothicMedium;" size=5><%=product.getP_name()%></font><br><br><br>
								<form align="left" style="display: inline; margin:0">
									<input type="hidden" name="p_no" value="<%=product.getP_no()%>">
									<font style="font-family: GongGothicMedium; vertical-align:super;" size=5;><%=new DecimalFormat("#,##0").format(product.getP_price())%>&nbsp;원</font><br><br><br>
						 		</form>
							</td>
							
						<%} %>
						</tr>
								   <!--상품 끝 -->
				</table>
			</div> <br /></td>
					</tr>
			</table>
<!-- ver.1 end -->	

		
		</div>
			<!-- include_content.jsp end-->
			<!-- content end -->
	</div>
		<!--wrapper end-->
		<div id="footer" class="footer_main">
			<jsp:include page="include_common_bottom.jsp"/>
		</div>
</div>
	<!--container end-->
</body>
</html>