<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.board.Board"%>
<%@page import="com.itwill.shop.board.BoardService"%>
<%@page import="com.itwill.shop.board.BoardListPageMakerDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="login_check.jspf" %>     
<%!public String getTitleString(Board board) {
		StringBuilder title = new StringBuilder(128);
		String t = board.getBoard_title();
		if (t.length() > 15) {
			//t = t.substring(0,15);
			//t = t+"...";
			t = String.format("%s...", t.substring(0, 15));
		}
		//답글공백삽입
		
		for (int i = 0; i < board.getBoard_depth(); i++) {
			title.append("&nbsp;&nbsp;");
		}
		
		if (board.getBoard_depth() > 0) {
			title.append("<img border='0' src='image/re.gif'/>");
		}
		
		title.append(t.replace(" ", "&nbsp;"));
		
		return title.toString();
	}
	%>
<%
// 요청페이지번호	
String pageno=request.getParameter("pageno");
if(pageno==null||pageno.equals("")){
	pageno="1";
}	
// 게시물조회
List<Board> boardList = BoardService.getInstance().findBoardListByUserId(sUserId);
int boardCount = BoardService.getInstance().boardCountByUserId(sUserId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>쇼핑몰 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">

 
<style type="text/css" media="screen">
</style>
<script type="text/javascript">
	function boardCreate() {
		location.href = "board_write.jsp";
	}
</script>
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0
	marginwidth=0 marginheight=0>
	<!-- container start-->
	<div id="container">
		<!-- header start -->
		<div id="header">
			<!-- include_common_top.jsp start-->
			<jsp:include page="include_common_top.jsp"/>
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
						<td><br/>
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
									<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b><%= sUser.getUser_name() %>님의 게시글 리스트 </b></td>
								</tr>
							</table>
							<!--form-->
							<form name="f" method="post" action="order_delete_action.jsp">
								<table align="center" width="80%"  border="0" cellpadding="0" cellspacing="1"  bgcolor="FFFFFF" >
									<caption style="text-align: right;">총 <b><%= boardCount%></b> 건</caption>
									<tr>
										<td width=15% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>번호</font></td>
										<td width=15% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>제목</font></td>
										<td width=20% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>글쓴이</font></td>
										<td width=20% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>글쓴날</font></td>
										<td width=15% height=25 bgcolor="f4f4f4" align=center class=t1><font
											>조회수</font></td>
									</tr>
									<%
										for (Board board : boardList) {
									%>
									<tr>
										<td width=15% height=25 align=center class=t1><font>
										<%= board.getBoard_no() %></font></td>
										<td width=15% height=25 align=center class=t1><font>
										<a href = 'board_view.jsp?boardno=<%= board.getBoard_no()%>'>
										<%= board.getBoard_title() %></a></font></td>
										<td width=15% height=25 align=center class=t1><font><%= board.getUser_id() %></font></td>
										<td width=15% height=25 align=center class=t1><font><%= board.getBoard_regDate() %></font></td>
										<td width=15% height=25 align=center class=t1><font><%= board.getBoard_readCount() %></font></td>
									</tr>
									<% } %>
								</table>
								<br>
							</form> <br />
							<hr>
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align=center> 
										&nbsp;&nbsp;<a href=board_list.jsp
										class=m1>게시판으로</a>
										&nbsp;&nbsp;<a href=user_view.jsp
										class=m1>돌아가기</a>
									</td>
								</tr>
							</table><hr></td>
					</tr>
				</table>
			</div>
			<br>
							
			<!-- include_content.jsp end-->
			<!-- content end -->
		</div>
		<!--wrapper end-->
		<div id="footer">
			<!-- include_common_bottom.jsp start-->
			<jsp:include page="include_common_bottom.jsp"/>
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>