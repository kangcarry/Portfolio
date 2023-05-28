package com.itwill.shop.test;

import java.util.List;

import com.itwill.shop.order.Order;
import com.itwill.shop.order.OrderDao;
import com.itwill.shop.order.OrderItem;
import com.itwill.shop.product.Product;

public class OrderDaoTestMain {

	public static void main(String[] args) throws Exception {
		OrderDao orderDao = new OrderDao();
		int rowCount = 0;
		
		/*
		 * 주문전체삭제 (회원아이디)
		 */
		rowCount = orderDao.deleteByMemberId("sy0");
		System.out.println(rowCount+"개의 주문 삭제");
		
		/*
		 * 주문 1건 삭제 (주문번호)
		 */
		rowCount = orderDao.deleteByOrderNo(2);
		System.out.println(rowCount+"개의 주문 삭제");
		
		/*
		 * 주문생성 (주문 1개 + 주문아이템 1개 이상) - 트랜잭션 처리 필요.
		 */
		Order newOrder = new Order(0, "김민선","밀키트1 외 2개", null, 30000, "서울시 송파구", "문 앞에 놔주세요", "카드", "sy0");
		newOrder.getOrderItemList().add(new OrderItem(0, 1, new Product(1, "밀키트1", 10000, "default.jpg", "상품설명1"), 0));
		newOrder.getOrderItemList().add(new OrderItem(0, 2, new Product(2, "밀키트2", 10000, "default.jpg", "상품설명2"), 0));
		rowCount = orderDao.insert(newOrder);
		System.out.println(rowCount+"개의 주문 생성");
		
		/*
		 * 주문 전체조회 (기본내용)
		 */
		System.out.println("orderList");
		List<Order> orderList = orderDao.findByMemberId("sy0");
		for (Order order : orderList) {
			System.out.println(order);
		}
		
		/*
		 * 주문 1개 조회 (상세내용)
		 */
		System.out.println("orderDetail");
		Order findOrder = orderDao.findByOrderNo("sy0", 3);
		System.out.println(findOrder);
	}
}