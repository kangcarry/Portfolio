package com.itwill.shop.test;

import com.itwill.shop.product.ProductService;

public class ProductServiceTestMain {

	public static void main(String[] args) throws Exception {
		ProductService productService = new ProductService();
		
		System.out.println("1.상품리스트 한개");
		System.out.println(productService.productDetail(2));
		
		System.out.println("2.상품리스트 전체보기");
		System.out.println(productService.productList());
	}

}