<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf" %>     
<%
	if(sUser==null){
		response.sendRedirect("user_login_form.jsp");
		return;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 마이 페이지</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/user.css" type="text/css">
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
			<jsp:include page="include_common_top.jsp"/>
		</div>
		<!-- header end -->
		<!-- wrapper start -->
		<div id="wrapper">
			<div id="user_navigation">
				<jsp:include page="user_view_navigation.jsp"/>
			</div>
			<div id="psuedo"></div>
			<div id="user_content">
				<jsp:include page="user_view_top.jsp"/>
			</div>
			
			<div id="user_view">
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td height="50">
									<font style="font-family: S-CoreDream-5Medium;" size=5>내정보보기</font></td>
								</tr>
							</table> <!-- view Form  -->
							<form name="f" method="post">
								<table border="0" cellpadding="0" cellspacing="1" width="590"
									bgcolor="BBBBBB">
									<tr>
										<td width=100 align=center bgcolor="f4f4f4" height="30">아이디</td>
										<td width=490 bgcolor="ffffff" style="padding-left: 10"><%=sUser.getUser_id() %>
										</td>
									</tr>
									<tr>
										<td width=100 align=center bgcolor="f4f4f4" height="30">이름</td>
										<td width=490 bgcolor="ffffff" style="padding-left: 10"><%=sUser.getUser_name() %>
										</td>
									</tr>
									<tr>
										<td width=100 align=center bgcolor="f4f4f4" height="30">전화번호</td>
										<td width=490 bgcolor="ffffff" style="padding-left: 10"><%=sUser.getUser_phone() %>
										</td>
									</tr>
									<tr>
										<td width=100 align=center bgcolor="f4f4f4" height="30">이메일
											주소</td>
										<td width=490 bgcolor="ffffff" style="padding-left: 10"><%=sUser.getUser_email() %>
										</td>
									</tr>
									<tr>
										<td width=100 align=center bgcolor="f4f4f4" height="30">주소</td>
										<td width=490 bgcolor="ffffff" style="padding-left: 10"><%=sUser.getUser_address() %>
										</td>
									</tr>
								</table>
							</form> <br />

			</div>
				<!-- content end -->
			</div>
			<!--wrapper end-->
			<div id="footer"></div>
		</div>
		<!--container end-->
</body>
</html>
