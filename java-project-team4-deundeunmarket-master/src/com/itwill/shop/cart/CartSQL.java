package com.itwill.shop.cart;

public class CartSQL {
	public static final String CART_INSERT = "insert into cart(cart_no, cart_qty, m_id, p_no) values(cart_cart_no_SEQ.nextval, ?, ?, ?)";
	public static final String CART_SELECT_BY_M_ID = "select c.*,p.* from cart c join product p on c.p_no=p.p_no where m_id=?";
	public static final String CART_SELECT_BY_CART_NO = "select * from cart c join product p on c.p_no=p.p_no where cart_no=?";
	public static final String CART_COUNT_BY_M_ID_PRODUCT_NO = "select count(*)  as p_count from cart c join member m on c.m_id=m.m_id where m.m_id=? and c.p_no=?";
	public static final String CART_DELETE_BY_CART_NO = "delete from cart where cart_no=?";
	public static final String CART_DELETE_BY_M_ID = "delete from cart where m_id=?";
	public static final String CART_UPDATE_BY_CART_NO = "update cart set cart_qty=? where cart_no=?";
	public static final String CART_UPDATE_BY_PRODUCT_NO_M_ID = "update cart set cart_qty=cart_qty+? where m_id=? and p_no=?";
}
