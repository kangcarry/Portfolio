package com.itwill.shop.cart;

import java.util.List;

public class CartService {
	private CartDao cartDao;
	
	public CartService() throws Exception {
		cartDao = new CartDao();
	}
/*
 * 카트 추가 or 수정 - addCart
 */
	public int addCart(Cart cart) throws Exception {
		if (cartDao.countByProductNo(cart.getM_id(), cart.getProduct().getP_no()) > 0) {
			return cartDao.updateByProductNo(cart);
		} else {
			return cartDao.insert(cart);
		}
	}
/*
 * 카트 수량 변경 - updateCart
 */
	public int updateCart(Cart cart) throws Exception {
		return cartDao.updateByCartNo(cart);
	}
/*
 * 카트 전체보기 - getCartItemByUserId
 */
	public List<Cart> getCartItemByMemberId(String m_id) throws Exception {
		return cartDao.findByMemberId(m_id);
	}
/*
 * 카트 아이템 1개 보기 - getCartItemByCartNo
 */
	public Cart getCartItemByCartNo(int cart_no) throws Exception {
		return cartDao.findByCartNo(cart_no);
	}
/*
 * 카트 아이템 1개 삭제 - deleteCartItemByCartNo
 */
	public int deleteCartItemByCartNo(int cart_no) throws Exception {
		return cartDao.deleteByCartNo(cart_no);
	}
/*
 * 카트 전체 삭제 - deleteCartItemByUserId
 */
	public int deleteCartItemByMemberId(String m_id) throws Exception {
		return cartDao.deleteByMemberId(m_id);
	}
	
/*
 * 카트 리스트 가격 총합 - addCartTotal
 */
	public int addCartListTotal(String m_id) throws Exception {
		int total = 0;
		List<Cart> findCartList = cartDao.findByMemberId(m_id);
		for (Cart cart : findCartList) {
			total+=cart.getCart_qty()*(cart.getProduct().getP_price());
		}
		return total;
	}
	
//카트 1개 총합
	public int addCartTotal(int cart_no) throws Exception {
		int total = 0;
		Cart findCart = cartDao.findByCartNo(cart_no);
		total = findCart.getCart_qty()*(findCart.getProduct().getP_price());
		return total;
	}
	
	
}
