package com.itwill.shop.product;

import java.util.List;

public class ProductService {
	private ProductDao productDao;
	
	public ProductService() throws Exception {
		productDao = new ProductDao();
	}
	
	/*
	 * 상품 상세 보기 - productDetail
	 */
	public Product productDetail(int p_no) throws Exception {
		return productDao.findByPrimaryKey(p_no);
	}
	
	/*
	 * 전체 상품 보기- productList
	 */
	
	public List<Product> productList() throws Exception {
		return productDao.findAll();
	}
	
	
}