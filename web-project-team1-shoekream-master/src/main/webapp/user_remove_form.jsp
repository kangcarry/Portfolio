<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf"%>
<%
DeliveryService deliveryService = new DeliveryService();
List<Delivery> deliveryList = deliveryService.selectDelivery(sUserId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>내정보수정</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/user.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">

<script src="js/user.js"></script>
<script type="text/javascript">
function userRemoveCheck(){
	if (confirm("정말 삭제하시겠습니까?")) {
		if (document.getElementById('remove_password').value != <%=sUser.getUser_password()%>) {
			document.getElementById('remove_password').focus;
			document.getElementById('remove_password').select;
			document.getElementById('pw_check').innerText = "비밀번호를 확인해주세요.";
			document.getElementById('pw_check').style.color = "red";
			return false;
		}else{
			document.getElementById('remove_f').action = "user_remove_action.jsp";
			document.getElementById('remove_f').method='POST';
			document.getElementById('remove_f').submit();
		}
	}
	
}
</script>
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

			<div id="user_view">
				<table style="padding-left: 10px" border=0 cellpadding=0
					cellspacing=0>
					<tr>
						<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>회원탈퇴</b></td>
					</tr>
				</table>
				<!-- view Form  -->
				<form id="remove_f" name="remove_f" method="post">
					<div style="font-size:20px">비밀번호
						<input id="remove_password" name="remove_password" style="width:200px;height:25px;font-size:12px;" 
						placeholder="탈퇴하시려면 비밀번호를 입력하세요" type="password" value="">
						<div id="pw_check" style="font-size:12pt"></div>
					</div>
				</form>
					<input type="button" value="삭제하기" onClick="userRemoveCheck();">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="돌아가기" onClick="returnUserView()">
				</div>
			</div>
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer"></div>
	</div>
	<!--container end-->
</body>
</html>