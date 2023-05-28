<%@page import="com.itwill.shop.user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String msg1=(String)request.getAttribute("msg1");
if(msg1==null)msg1="";
String msg2=(String)request.getAttribute("msg2");
if(msg2==null)msg2="";
User fuser=(User)request.getAttribute("fuser");
if(fuser==null){
	fuser=new User("","","","","","");
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 로그인</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/user.css" type="text/css">
<link rel=stylesheet href="css/menu.css" type="text/css"> 
<link rel=stylesheet href="css/shop.css" type="text/css"> 
 
<script type="text/javascript">
	function userCreate() {
		f.action = "user_write_form.jsp";
		f.submit();
	}

	function login() {
		if (!f.user_id.value) {
			alert("사용자 아이디를 입력하십시요.");
			f.userId.focus();
			return false;
		}
		if (!f.user_password.value) {
			alert("비밀번호를 입력하십시요.");
			f.password.focus();
			return false;
		}
		
       if (document.f.idsave.checked == true) { // 아이디 저장을 체크 하였을때
           setCookie("id", document.f.user_id.value, 7); //쿠키이름을 id로 아이디입력필드값을 7일동안 저장
       } else {
           setCookie("id", document.f.user_id.value, 0); //날짜를 0으로 저장하여 쿠키삭제
       }
       if (document.f.pwsave.checked == true) {
           setCookie("pw", document.f.user_password.value, 7);
       } else {
           setCookie("pw", document.f.user_password.value, 0); 
       }
		
		document.getElementById('url_history').value = document.referrer
		f.action = "user_login_action.jsp";
		f.submit();
	}
	
    window.onload = function() {
    	 
       if (getCookie("id")) { // getCookie함수로 id라는 이름의 쿠키를 불러와서 있을경우
            document.f.user_id.value = getCookie("id"); //input 이름이 id인곳에 getCookie("id")값을 넣어줌
            document.f.idsave.checked = true; // 체크는 체크됨으로
        }
       if (getCookie("pw")) { // getCookie함수로 id라는 이름의 쿠키를 불러와서 있을경우
           document.f.user_password.value = getCookie("pw"); //input 이름이 id인곳에 getCookie("id")값을 넣어줌
           document.f.pwsave.checked = true; // 체크는 체크됨으로
       }
 
    }
 
    function setCookie(name, value, expiredays) //쿠키 저장함수
    {
        let todayDate = new Date();
        todayDate.setDate(todayDate.getDate() + expiredays);
        document.cookie = name + "=" + escape(value) + "; path=/; expires="
                + todayDate.toGMTString() + ";"
    }
 
    function getCookie(Name) { // 쿠키 불러오는 함수
        let search = Name + "=";
        if (document.cookie.length > 0) { 
            offset = document.cookie.indexOf(search);
            if (offset != -1) { 
                offset += search.length; 
                end = document.cookie.indexOf(";", offset);
                if (end == -1)
                    end = document.cookie.length;
                return unescape(document.cookie.substring(offset, end));
            }
        }
    }
 
	
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container" height=100%>
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
			<div id="login_content">

				<table border=0 cellpadding=0 cellspacing=0>
					<tr>
						<td><br />
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td height="50">
										<font style="font-family: S-CoreDream-5Medium;" size=5>로그인</font></td>
								</tr>
							</table> <!-- login Form  -->
							<form name="f" method="post">
								<table border="0" cellpadding="0" cellspacing="1"
									bgcolor="BBBBBB">
									<tr>
										<td id="id_icon" width=100 align=center bgcolor="ffffff" height="50"></td>
										<td width=490 align="left" bgcolor="ffffff" style="padding-left: 10px;">
										<input type="text" style="font-family: S-CoreDream-3Light; font-size: 12pt" placeholder="아이디" name="user_id" id="user_id"
										value="<%=fuser.getUser_id()%>"><div><font color="red"><%=msg1%></div></font></td>
									</tr>
									<tr>
										<td id="pw_icon" width=100 align=center bgcolor="ffffff" height="50"></td>
										<td width=490 align="left" bgcolor="ffffff"
											style="padding-left: 10px">
										<input type="password" style="font-family: S-CoreDream-3Light; font-size: 12pt" placeholder="패스워드" name="user_password" id="user_password"
										value="<%=fuser.getUser_password()%>"><div><font color="red"><%=msg2%></div></font></td>
									</tr>
									<tr>
                                        <td colspan="2" align="left" style="text-align: center;">
                                        <input type="checkbox"
                                            name="idsave" value="saveOk">아이디 저장
                                    	<input type="checkbox"
                                            name="pwsave" value="saveOk">패스워드 저장
										</td>
                                    </tr>
								</table>
								<input type="hidden" id="url_history" name="url_history" value="">
							</form> <br />
							<table border="0" cellpadding="0" cellspacing="1">
								<tr>
									<td align=center>
									<button class="add_button" type="button" value="로그인" onclick="login();">
											<font style="font-family: S-CoreDream-3Light;" size=2>로그인</font></button>&nbsp;&nbsp;	
									<button class="add_button" type="button" value="회원가입" onclick="userCreate();">
											<font style="font-family: S-CoreDream-3Light;" size=2>회원가입</font></button><br><br>
									</td>
								</tr>
							</table></td>
					</tr>
				</table>
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
