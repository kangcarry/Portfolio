package com.itwill.shop.order;

import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartDao;
import com.itwill.shop.delivery.Delivery;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductDao;

public class OrderService {
	private OrderDao orderDao;
	private ProductDao productDao;
	private CartDao cartDao;
	
	public OrderService() throws Exception {
		orderDao = new OrderDao();
		productDao = new ProductDao();
		cartDao = new CartDao();
	}
	
	/*************** 상품 상세보기에서 직접 주문 하기 ***************/
	/*
	 * 해당 product의 정보를 가져와서(p_no로 찾기) 그 정보를 결과적으로는 order에 넣어줘야 함.
	 * orderItem에 product를 넣어주고,
	 * orderItemList에 orderItem을 넣어줌(order에 orderItem 정보를 넣기 위해서는 setter를 이용해서 List의 형태 넣어줘야 함.)
	 * 새로운 order를 먼저 생성하고,
	 * order에 setOrderItemList() 메소드를 사
	 */
	public int directOrder(String user_id, int p_no, int oi_qty,int d_no) throws Exception {
		Product product = productDao.selectByNo(p_no);
		OrderItem orderItem = new OrderItem(0, oi_qty, 0, product);
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		orderItemList.add(orderItem);
		
		Order order = new Order(0, orderItem.getProduct().getP_name(), null, orderItemList.get(0).getOi_qty() * orderItemList.get(0).getProduct().getP_price(), user_id, new Delivery(d_no, null, null, null, null));
		order.setOrderItemList(orderItemList);
		
		return orderDao.insert(order);
	}
	
	/*************** 카트에서 전체 상품 주문하기  ***************/
	public int cartOrder(String user_id,int d_no) throws Exception {
		List<Cart> cartList = cartDao.findByUserId(user_id);
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		String o_desc = null;
		int o_tot_price = 0;
		int oi_tot_qty = 0;
		
		for (Cart cart : cartList) {
			OrderItem orderItem = new OrderItem(0, cart.getCart_qty(), 0, cart.getProduct());
			orderItemList.add(orderItem);
			o_tot_price += cart.getProduct().getP_price() * orderItem.getOi_qty();
			oi_tot_qty += cart.getCart_qty();
		}
		
		if (orderItemList.size() < 2) {
			o_desc = orderItemList.get(0).getProduct().getP_name();
		} else {
			o_desc = orderItemList.get(0).getProduct().getP_name() + " 외 " + (oi_tot_qty - 1) + "건";
		}
		
		Order order = new Order(0, o_desc, null, o_tot_price, user_id,new Delivery(d_no, null, null, null, null));
		order.setOrderItemList(orderItemList);
		
		cartDao.deleteByUserId(user_id);
		
		return orderDao.insert(order);
	}
	
	/***************** 카트에서 선택 상품 주문하기 *******************/
	public int cartSelectOrder(String user_id, String[] cart_item_noStr_array,int d_no) throws Exception {
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		String o_desc = null;
		int o_tot_price = 0;
		int oi_tot_qty = 0;
		
		for(int i = 0; i < cart_item_noStr_array.length; i++) {
			Cart cartItem = cartDao.findByCartNo(Integer.parseInt(cart_item_noStr_array[i]));
			OrderItem orderItem = new OrderItem(0, cartItem.getCart_qty(), 0, cartItem.getProduct());
			orderItemList.add(orderItem);
			o_tot_price += orderItem.getOi_qty() * orderItem.getProduct().getP_price();
			oi_tot_qty += orderItem.getOi_qty();
		}
		
		if (orderItemList.size() < 2) {
			o_desc = orderItemList.get(0).getProduct().getP_name();
		} else {
			o_desc = orderItemList.get(0).getProduct().getP_name() + " 외 " + (oi_tot_qty - 1) + "건";
		}
		
		Order order = new Order(0, o_desc, null, o_tot_price, user_id,new Delivery(d_no, null, null, null, null));
		order.setOrderItemList(orderItemList);
		
		for(int i =0;i<cart_item_noStr_array.length;i++) {
			cartDao.deleteByCartNo(Integer.parseInt(cart_item_noStr_array[i]));
		}
		
		return orderDao.insert(order);
	}
	
	/*************** 회원의 주문 선택 삭제 ***************/
	public int deleteByOrderNo(int o_no) throws Exception {
		return orderDao.deleteByOrderNo(o_no);
	}
	
	/*************** 회원의 주문 전체 삭제 ***************/
	public int deleteByUserId(String user_id) throws Exception {
		return orderDao.deleteByUserId(user_id);
	}
	
	/*************** 회원의 전체 주문 내역(상품 정보 없음) ***************/
	public List<Order> findByUserId(String user_id) throws Exception {
		return orderDao.findByUserId(user_id);
	}
	
	/*************** 회원의 전체 주문의 상세 내역(아이디로 조회) ***************/
	public List<Order> findWithOrderItemByUserId(String user_id) throws Exception {
		try {
			return orderDao.findWithOrderItemByUserId(user_id);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*************** 회원의 주문 1건의 상세 내역(주문번호로 조회) ***************/
	public Order findWithOrderItemByOrderNo(int o_no) throws Exception {
		return orderDao.findWithOrderItemByOrderNo(o_no);
	}
	
	public Delivery findWithOrderItemByDno(int o_no) throws Exception {
		return orderDao.findByOrderNo(o_no);
	}
}


