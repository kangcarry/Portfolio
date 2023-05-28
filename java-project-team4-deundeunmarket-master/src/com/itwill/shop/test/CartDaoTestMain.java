package com.itwill.shop.test;

import java.util.List;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartDao;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

public class CartDaoTestMain {
		
	public static void main(String[] args) throws Exception {
		
		CartDao cartDao = new CartDao();
		
		//1 카트추가
		System.out.println("카트추가>> "+cartDao.insert(new Cart(0, 1, "sy0", new Product(2, "밀키트2", 10000, "default.jpg", "상품설명2"))));
		
		//2 id, 상품종류로 카트제품존재(개수)확인
		System.out.println("카트개수>>"+cartDao.countByProductNo("sy1", 3));
		
		//3 상품에서 카트 추가시 업데이트
		System.out.println("상품에서 카트 업데이트>>"+cartDao.updateByProductNo(new Cart(0, 10, "sy0", new Product(2, "밀키트2", 10000, "default.jpg", "상품설명2"))));
		
		//4 카트리스트에서 수정
		System.out.println("카트 내부에서 업데이트>>"+cartDao.updateByCartNo(new Cart(1, 1, "sy1", new Product(0, "밀키트2", 10000, "default.jpg", "상품설명2"))));
		
		//5 카트  id통해 전체보기
		List<Cart> cartList1 = cartDao.findByMemberId("sy1");
		System.out.println(cartList1);
		
		//6 카트 id내 전체삭제
		System.out.println("카트전체삭제>>"+cartDao.deleteByMemberId("sy1"));
		
		//7 카트 번호로 1개 삭제
		System.out.println("카트번호삭제>>"+cartDao.deleteByCartNo(1));
		
		
		
	}

}
