package com.itwill.shop.order.test;

import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.delivery.Delivery;
import com.itwill.shop.order.*;
import com.itwill.shop.product.*;

public class OrderDaoTestMain {

	public static void main(String[] args) throws Exception {
		OrderDao orderDao = new OrderDao();
		
		System.out.println("------ insert -------");
		// 테스트 완료
		
		// 직접 주문
		ProductDao productDao = new ProductDao();
		Product product1 = productDao.selectByNo(1);
		System.out.println(orderDao.insert(new Order(0, product1.getP_name(), null, product1.getP_price(), "cream3", new Delivery(5, "", "", "", ""))));
		
		// 직접 주문 여러건
		Product product2 = productDao.selectByNo(6);
		Product product3 = productDao.selectByNo(7);
		
		List<OrderItem> orderItemList1 = new ArrayList<OrderItem>();
		orderItemList1.add(new OrderItem(0, 3, 0, product2));
		orderItemList1.add(new OrderItem(0, 2, 0, product3));
		
		System.out.println(orderDao.insert(new Order(0, product2.getP_name() + "외 1건", null, 100000, "cream3",new Delivery(5, "", "", "", ""))));
		System.out.println(orderDao.findWithOrderItemByOrderNo(5));
		
//		System.out.println("------ delete by user_id -------");
//		/*
//		System.out.println(orderDao.deleteByUserId("cream3"));
//		 */
//		
//		System.out.println("------ delete by o_no -------");
//		/*
//		 System.out.println(orderDao.deleteByOrderNo(6));
//		 */
//		
//		System.out.println("------ select by user_id -------");
//		System.out.println(orderDao.findByUserId("cream2"));
//		
//		System.out.println("------ select with orderitem by user_id -------");
//		System.out.println(orderDao.findWithOrderItemByUserId("cream2"));
//		
//		System.out.println("------ select with orderitem by o_no -------");
//		System.out.println(orderDao.findWithOrderItemByOrderNo(2));
	}

}
