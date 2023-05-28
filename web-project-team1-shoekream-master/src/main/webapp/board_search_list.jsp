<%@page import="com.itwill.shop.user.User"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.board.BoardListPageMakerDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.itwill.shop.board.Board"%>
<%@page import="com.itwill.shop.board.BoardService"%>
<%@page import="com.itwill.shop.board.PageInputDto"%>
<%@page import ="com.itwill.shop.user.UserService"%>
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
String type = request.getParameter("typesel");
// 요청페이지번호	
String pageno=request.getParameter("pageno");
if(pageno==null||pageno.equals("")){
	pageno="1";
}	

// search Type
String searchType = null;
searchType = request.getParameter("searchType");
// search keyword
String keyword = null;
keyword = request.getParameter("keyword");

// 검색 결과 게시물조회
BoardListPageMakerDto boardListPage = null;
if(type.equals("title")) {
	boardListPage = BoardService.getInstance().searchByTitle(Integer.parseInt(pageno), keyword);
}else {
	boardListPage = BoardService.getInstance().pagefindBoardListByUserId(Integer.parseInt(pageno), keyword);
} 

String sUserId = (String)session.getAttribute("sUserId");

if(keyword.equals("") || keyword == null || searchType.equals("")) {
	response.sendRedirect("board_list.jsp");
	return;
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>S.KREAM | 문의 게시판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="css/styles.css" type="text/css">
<link rel=stylesheet href="css/board.css" type="text/css">
<link rel=stylesheet href="css/shop.css" type="text/css">

<script type="text/javascript">
	function boardCreate() {
		location.href = "board_write.jsp";
	}

	function search() {
		searchform.action = 'board_search_list.jsp';
		searchform.method = 'POST';
		searchform.submit();
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
							<table style="padding-left: 10px" border=0 cellpadding=0
								cellspacing=0>
								<tr>
								<td height="50">
									<a href = "board_list.jsp"><font style="font-family: S-CoreDream-5Medium;" size=5>문의 게시판</a></font>
									</td>
								</tr>
								<tr bgcolor="#FFFFFF">
									<td height="20" class="t1" align="right" valign="bottom">
										총 <font color="#FF0000"><%=boardListPage.pageMaker.getTotCount()%></font>
										건 | 현재페이지( <font color="#FF0000"><%=boardListPage.pageMaker.getCurPage()%></font>
										/ <font color="#0000FF"><%=boardListPage.pageMaker.getTotPage()%></font>
										)
									</td>
								</tr>
							</table>
							
							<!-- board list start -->
							<form name="f" method="post" action="">
								<table border="0" cellpadding="0" cellspacing="1" width="590"
									bgcolor="f4f4f4">

									<tr>
										<td width=55% align=center bgcolor="f4f4f4" height=40>제목</td>
										<td width=20% align=center bgcolor="f4f4f4">글쓴이</td>
										<td width=10% align=center bgcolor="f4f4f4">글쓴날</td>
										<td width=15% align=center bgcolor="f4f4f4">조회수</td>
									</tr>
									
									<% for (Board board : boardListPage.itemList) { %>
									<tr>
										<td width=55% bgcolor="ffffff" style="padding-left: 10px" align="left" height=30>
										<a href='board_view.jsp?boardno=<%=board.getBoard_no()%>&pageno=<%=boardListPage.pageMaker.getCurPage()%>'>
										<%=this.getTitleString(board)%></a></td>
										<td width=20% align=center bgcolor="ffffff"><%=board.getUser_id()%></td>
										<td width=10% align=center bgcolor="ffffff" style="padding-left: 10px" align="left">
											<%=board.getBoard_regDate().toString().substring(0, 10)%></td>
										<td width=15% align=center bgcolor="ffffff" align="left"><%=board.getBoard_readCount()%></td>
									</tr>
									<% } %>
									<% if(boardListPage.itemList.isEmpty() == true) { %>
										<tr><td colspan="4" height="200px" bgcolor="ffffff"><br>
										<span style="font-size: 20pt"><b>일치하는 게시글이 없습니다.</b></span>
										<br><br><br>
										<span style="font-size: 13pt"><a href = "board_list.jsp">돌아가기</a></span></td></tr>
									<% } %>
								</table>
							</form> <br>
							<!-- board list end -->
								
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<td align="center">
									</td>
								</tr>
							</table>
							<!-- button -->
							
							<table border="0" cellpadding="0" cellspacing="1" width="590">
								<tr>
									<!-- search start -->
									<td width="130px"/>
									<td><form id = "searchform" method = "post">
									<select id = 'searchType' name = 'searchType' style= height:30px;>
										<option selected value = "">선택</option>
										<option value = "title">제목</option>
										<option value = "id">글쓴이</option>
									</select>
									<input type='text' name='keyword' value = "" style= "width:400px; height:25px;">
									<input type="button" name='searchbtn' style= height:30px; value='검색' onclick = "search();">
									</form></td>
									<!-- search end -->
									
									<td align="right" width= 60px><input type="button" value="글쓰기" onclick="boardCreate();"></td>
								</tr>
							</table></td>
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
			<jsp:include page="include_common_bottom.jsp" />
			<!-- include_common_bottom.jsp end-->
		</div>
	</div>
	<!--container end-->
</body>
</html>