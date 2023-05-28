package com.itwill.shop.product.test;

import java.util.List;

import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductDao;

public class ProductDaoTestMain {

	public static void main(String[] args)throws Exception  {
		
		ProductDao productDao=new ProductDao();
		
//		System.out.println(productDao.selectAll());
		
//		System.out.println(productDao.insert(new Product(10,"shoes10",100000,"shoes10.jpg","기타등등",0,1)));

//		System.out.println(productDao.selectByNo(1));
		
//		System.out.println(productDao.update(new Product(10,"shoes22",2000000,"shoes11.jpg","기타등등등",0,2)));
		
//		System.out.println(productDao.delete("shoes2"));
		
//		System.out.println(productDao.selectByCategory(1));
		
//		System.out.println(productDao.productFindByName("s"));
		
		//상품 이름으로 출력 후 검색 결과 보기.
		List<Product> findProduct = productDao.productFindByName("nike");
		System.out.println(findProduct);
		
		
		System.out.println("조회수 상위 3개");
		System.out.println(productDao.selectPopular());
		
//		Product updateProduct = 
//				new Product(
//						31, "Chanel Boots Caoutchouc", 28880000, "Boots01.png","Black", 0,3);  
//		System.out.println(productDao.update(updateProduct));

		
		

	}

}
