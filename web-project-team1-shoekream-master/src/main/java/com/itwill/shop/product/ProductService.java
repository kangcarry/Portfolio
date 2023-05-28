package com.itwill.shop.product;

import java.util.List;

public class ProductService {
	private static ProductService _instance;

	private ProductDao productDao;
	public ProductService() throws Exception {
		productDao = new ProductDao();
	}
	public static ProductService getInstance() throws Exception{
		if(_instance == null) {
			_instance = new ProductService();
		}
		return _instance;
	}
	/*
	 * 전체 상품 보기
	 */
	public List<Product> productList() throws Exception{
		return productDao.selectAll();
	}
	
	/*
	 * 상품 상세 보기
	 */
	public Product productDetail(int p_no) throws Exception {
		return productDao.selectByNo(p_no);
	}
	/*
	 * 카테고리별 상품 보기
	 */
	public List<Product> productCategory(int category_no) throws Exception {
		return productDao.selectByCategory(category_no);
	}
	/*
	 * 상품 정보 수정
	 */
	public int productUpdate(Product product) throws Exception {
		return productDao.update(product);
	}
	/*
	 *  상품 정보 삭제
	 */
	 public int productDelete(String P_name) throws Exception{
		 return productDao.delete(P_name);
	 }
	 /*
	  * 상품 정보 추가
	  */
	 public int productInsert(Product product) throws Exception{
		 return productDao.insert(product);
	 }
	 /***************************************************/
	 /*
	  * 상품 이름으로 검색 출력 하기.
	  */
	 public List <Product> productFindByName(String keyword) throws Exception {
		 return productDao.productFindByName(keyword);
	 }
	 
	 /***************************************************/
	 
	 /*
	 조회수 증가
	 */
	public void updateHitCount(int p_no) throws Exception{
			productDao.increaseClickCount(p_no);
	}
	/*
	 * 조회수 상위 3개 출력
	 */
	public List<Product> findPopular() throws Exception{
		return productDao.selectPopular();
	}

	/*
	 * 가격 내림차순 정렬
	 */
	public List<Product> priceSortDesc() throws Exception{
		return productDao.priceSortDesc();
	}
	
	public List<Product> priceSortCateDesc(int category_no) throws Exception{
		return productDao.priceSortCateDesc(category_no);
	}
	/*
	 * 가격 오름차순 정렬
	 */
	public List<Product> priceSortAsc() throws Exception{
		return productDao.priceSortAsc();
	}
	
	public List<Product> priceSortCateAsc(int category_no) throws Exception{
		return productDao.priceSortCateAsc(category_no);
	}
	/*
	 * 조회수 내림차순 정렬
	 */
	public List<Product> clickSortDesc() throws Exception{
		return productDao.clickSortDesc();
	}
	/*
	 * 조회수 오름차순 정렬
	 */
	public List<Product> clickSortAsc() throws Exception{
		return productDao.priceSortAsc();
	}
	
	public List<Product> clickSortCateDesc(int category_no) throws Exception{
		return productDao.clickSortCateDesc(category_no);
	}
	
	//댓글 생성
	public void saveComment(ProductComment comment) throws Exception {
		productDao.insertComment(comment);
	}
	//댓글 삭제
	public void removeComment(int pc_no) throws Exception {
		productDao.deleteComment(pc_no);
	}
	//댓글 수정
	public void modifyComment(ProductComment comment) throws Exception {
		productDao.updateComment(comment);
	}
	//user가 쓴 댓글
	public List<ProductComment> findCommentByUserId(String userId) throws Exception {
		return productDao.selectCommentByUserId(userId);
	}
	
	

}
