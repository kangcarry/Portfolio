<%@page import="com.itwill.shop.board.BoardService"%>
<%@page import="com.itwill.shop.board.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//Board객체를 생성하고 입력된데이타를 읽어서 객체에저장
	Board board=new Board();
	/*
	원글boardno
	*/
	board.setBoard_no(
			Integer.parseInt(request.getParameter("boardno")));
	/*
	답글 데이타
	*/
	board.setBoard_title(request.getParameter("title"));
	board.setUser_id(request.getParameter("writer"));
	board.setBoard_content(request.getParameter("content"));
	BoardService.getInstance().createReplay(board);
	
	String pageno = "1";
	if(request.getParameter("pageno")!=null){
		pageno=request.getParameter("pageno");
	}
	response.sendRedirect(
			String.format("board_list.jsp?pageno=%s",pageno));
	
%>