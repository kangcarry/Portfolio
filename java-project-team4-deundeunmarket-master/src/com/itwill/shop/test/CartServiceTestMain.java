package com.itwill.shop.test;

import com.itwill.shop.cart.Cart;
import com.itwill.shop.cart.CartService;
import com.itwill.shop.product.Product;
import com.itwill.shop.product.ProductService;

public class CartServiceTestMain {

	public static void main(String[] args) throws Exception {
		
		CartService cartService = new CartService();
		
		// 카트 추가 or 수정 - addCart
		System.out.println(
				"addCart >> " + cartService.addCart(new Cart(0, 0, "sy2", new Product(3, "밀키트2", 10000, "default.jpg", "상품설명2"))));

		// 카트 아이템 1개 보기 - getCartItemByCartNo
		Cart findCart = cartService.getCartItemByCartNo(2);
		System.out.println("getCartItemByCartNo >> " + findCart);

		// 카트 수량 변경 - updateCart
		findCart.setCart_qty(findCart.getCart_qty() + 1);
		System.out.println("updateCart >> " + cartService.updateCart(findCart));

		// 카트 전체보기 - getCartItemByUserId
		System.out.println("getCartItemByUserId >> " + cartService.getCartItemByMemberId("sy0"));

		// 카트 아이템 1개 삭제 - deleteCartItemByCartNo
		System.out.println("deleteCartItemByCartNo >> " + cartService.deleteCartItemByCartNo(7));

		// 카트 전체 삭제- deleteCartItemByUserId
		System.out.println("deleteCartItemByUserId >> " + cartService.deleteCartItemByMemberId("sy2"));

		// 카트 리스트 가격 총합 -addCartTotal
		System.out.println("addCartTotal >>"+cartService.addCartListTotal("sy0"));
		
		// 카트 내 가격 총합
		System.out.println("addCartTotal >>"+cartService.addCartTotal(1));
		
	}

}
