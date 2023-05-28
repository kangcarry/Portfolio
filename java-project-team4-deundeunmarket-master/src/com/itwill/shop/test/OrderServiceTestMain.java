package com.itwill.shop.test;

import com.itwill.shop.order.Order;
import com.itwill.shop.order.OrderService;

public class OrderServiceTestMain {

	public static void main(String[] args) throws Exception {
		
		OrderService orderService = new OrderService();
		int rowCount = 0;
		Order order = null;
		
		/*
		 * 주문 1개 삭제 - deleteByOrderNo
		 */
		//rowCount = orderService.deleteByOrderNo(1);
		System.out.println(rowCount+"개의 주문 삭제");
		
		/*
		 * 주문 전체 삭제 - delete
		 */
		//rowCount = orderService.delete("sy1");
		System.out.println(rowCount+"개의 주문 삭제");
		
		/*
		 * 주문 목록 보기 - orderList
		 */
		System.out.println("orderList");
		System.out.println(orderService.orderList("sy0"));
		
		/*
		 * 주문상세보기 - orderDetail
		 */
		System.out.println("orderDetail");
		System.out.println(orderService.orderDetail("sy0", 4));
		
		/*
		 * 상품에서 직접 주문 - orderCreate
		 */
		System.out.println("orderCreate - 상품에서 직접 주문");
		order = new Order(0, "김민선", null, null, 0, "서울시 강남구", "조심히 놔주세요", "현금", "sy2");
		rowCount = orderService.orderCreate(order, 1, 1);
		System.out.println(rowCount+"개의 주문 생성");
		
		/*
		 * 카트에서 주문 - orderCreate 오버로딩
		 */
		System.out.println("orderCreate - 카트에서 주문");
		order = new Order(0, "김민선", null, null, 0, "서울시 강남구", "조심히 놔주세요", "현금", "sy0");
		rowCount = orderService.orderCreate(order);
		System.out.println(rowCount+"개의 주문 생성");
		
		/*
		 * 카트에서 선택 주문 - orderCreate 오버로딩 (선택)
		 */
		System.out.println("orderCreate - 카트에서 선택 주문");
		order = new Order(0, "김민선", null, null, 0, "서울시 강남구", "조심히 놔주세요", "현금", "sy1");
		String[] cart_no_StrArray = {"3", "4"};
		rowCount = orderService.orderCreate(order, cart_no_StrArray);
		System.out.println(rowCount+"개의 주문 생성");
	}
}