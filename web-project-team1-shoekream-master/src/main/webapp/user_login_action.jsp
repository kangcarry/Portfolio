<%@page import="com.itwill.shop.user.exception.ArrayIndexOutOfBoundsException"%>
<%@page import="com.itwill.shop.delivery.Delivery"%>
<%@page import="com.itwill.shop.delivery.DeliveryService"%>
<%@page import="com.itwill.shop.cart.Cart"%>
<%@page import="com.itwill.shop.cart.CartService"%>
<%@page import="com.itwill.shop.product.ProductComment"%>
<%@page import="com.itwill.shop.product.Product"%>
<%@page import="com.itwill.shop.product.ProductService"%>
<%@page import="com.itwill.shop.board.BoardService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.itwill.shop.order.OrderItem"%>
<%@page import="com.itwill.shop.order.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.itwill.shop.order.OrderService"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.itwill.shop.user.User"%>
<%@page import="com.itwill.shop.user.exception.PasswordMismatchException"%>
<%@page import="com.itwill.shop.user.exception.UserNotFoundException"%>
<%@page import="com.itwill.shop.user.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(request.getMethod().equalsIgnoreCase("GET")){
		response.sendRedirect("shop_main.jsp");
		return;
	}
	String user_id=null;
	String user_password=null;
	String url_history=null;
	String[] urlArray = null;
	int orderListCount = 0;
	int commentListCount = 0;
	int cart_item_count = 0;
	int boardListCount = 0;
	List<Order> orderList;
	List<Cart> cartList;
	List<Delivery> deliveryList;
	List<ProductComment> commentList;
	try{
		url_history=request.getParameter("url_history");
		urlArray = url_history.split("/"); 
		user_id=request.getParameter("user_id");
		user_password=request.getParameter("user_password");
		UserService userService=new UserService();
		User loginUser = userService.login(user_id, user_password);
		
		boardListCount = BoardService.getInstance().countBoardListByUserId(user_id);
		
		OrderService orderService = new OrderService();
		orderList = orderService.findWithOrderItemByUserId(user_id);
		if (orderService.findWithOrderItemByUserId(user_id) != null) {
			orderListCount = orderList.size();
		}
		
		ProductService productService = new ProductService();
		commentList = productService.findCommentByUserId(user_id);
		if(productService.findCommentByUserId(user_id) != null){
			commentListCount = commentList.size();
		}
		
		CartService cartService = new CartService();
		cartList = cartService.viewCartByUserId(user_id);
		if(cartService.viewCartByUserId(user_id) != null){
			cart_item_count = cartList.size();
		}
		
		DeliveryService deliveryService = new DeliveryService();
		deliveryList = deliveryService.selectDelivery(user_id);
		
		session.setAttribute("sUserId", user_id);
		session.setAttribute("sUser", loginUser);
		session.setAttribute("orderList", orderList);
		session.setAttribute("cartList", cartList);
		session.setAttribute("deliveryList", deliveryList);
		session.setAttribute("commentList", commentList);
		
		session.setAttribute("orderListCount", orderListCount);
		session.setAttribute("boardListCount", boardListCount);
		session.setAttribute("commentListCount", commentListCount);
		session.setAttribute("cartItemCount", cart_item_count);
		
		if(urlArray[4].equals("user_write_form.jsp")||urlArray[4].equals("user_login_form.jsp")){
			response.sendRedirect("shop_main.jsp");
			return;
		}else{
			System.out.println(urlArray.length+" 테스트");
			response.sendRedirect(urlArray[4]);
		}
		
	}catch(UserNotFoundException e){
		/*********************case3[forward]****************/
		request.setAttribute("msg1", e.getMessage());
		request.setAttribute("fuser",new User(user_id,user_password,"","","",""));
		RequestDispatcher rd=
				request.getRequestDispatcher("user_login_form.jsp");
		rd.forward(request, response);
		
	}catch(PasswordMismatchException e){
		/*********************case3[forward]****************/
		request.setAttribute("msg2", e.getMessage());
		request.setAttribute("fuser",new User(user_id,user_password,"","","",""));
		RequestDispatcher rd=
				request.getRequestDispatcher("user_login_form.jsp");
		rd.forward(request, response);
	}catch(Exception e){
		e.printStackTrace();
		response.sendRedirect("shop_main.jsp");
	}
%>








