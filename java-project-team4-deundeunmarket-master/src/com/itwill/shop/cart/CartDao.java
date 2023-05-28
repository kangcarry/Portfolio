package com.itwill.shop.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.common.DataSource;
import com.itwill.shop.product.Product;

public class CartDao {

	private DataSource dataSource;
	public CartDao() throws Exception {
		dataSource = new DataSource();
	}
	//카트추가
	public int insert(Cart cart) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRowCount = 0;
		con = dataSource.getConnection();
		pstmt = con.prepareStatement(CartSQL.CART_INSERT);
		pstmt.setInt(1, cart.getCart_qty());
		pstmt.setString(2, cart.getM_id());
		pstmt.setInt(3, cart.getProduct().getP_no());
		insertRowCount = pstmt.executeUpdate();

		pstmt.close();
		dataSource.close(con);
		return insertRowCount;
	}
	//cart제품 존재 여부
	public int countByProductNo(String m_id,int p_no) throws Exception{
		int count = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_COUNT_BY_M_ID_PRODUCT_NO);
		pstmt.setString(1, m_id);
		pstmt.setInt(2, p_no);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return count;
	}
	//  상품에서 카트 추가시 update(이미 카트에 담긴 제품 수량 추가)
	public int updateByProductNo(Cart cart) throws Exception {
		int rowCount = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_UPDATE_BY_PRODUCT_NO_M_ID);
		pstmt.setInt(1, cart.getCart_qty());
		pstmt.setString(2, cart.getM_id());
		pstmt.setInt(3, cart.getProduct().getP_no());
		rowCount = pstmt.executeUpdate();

		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	// cart update(카트리스트에서 수정)
	public int updateByCartNo(Cart cart) throws Exception {
		int rowCount = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_UPDATE_BY_CART_NO);
		pstmt.setInt(1, cart.getCart_qty());
		pstmt.setInt(2, cart.getCart_no());

		rowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return rowCount;
	}
	//cart list 카트 전체보기 (회원아이디로)
	public List<Cart> findByMemberId(String m_id) throws Exception{
		List<Cart> cartList = new ArrayList<Cart>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_SELECT_BY_M_ID);
		pstmt.setString(1, m_id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			cartList.add(new Cart(rs.getInt("cart_no"),
								 rs.getInt("cart_qty"),
								 rs.getString("m_id"),
								 new Product(rs.getInt("p_no"),
										 	 rs.getString("p_name"),
											 rs.getInt("p_price"),
											 rs.getString("p_image"),
											 rs.getString("p_desc"))
			));
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return cartList;
	}
	//카트 번호로 찾기
	public Cart findByCartNo(int cart_no) throws Exception {
		Cart cart = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_SELECT_BY_CART_NO);
		pstmt.setInt(1, cart_no);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			cart = new Cart(rs.getInt("cart_no"),
							rs.getInt("cart_qty"),
							rs.getString("m_id"),
							new Product(rs.getInt("p_no"),
										rs.getString("p_name"),
										rs.getInt("p_price"),
										rs.getString("p_image"),
										rs.getString("p_desc"))
			);
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return cart;
	}
	//카트 1개 삭제
	public int deleteByCartNo(int cart_no) throws Exception {
		int deleteRowCount = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_DELETE_BY_CART_NO);
		pstmt.setInt(1, cart_no);
		deleteRowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return deleteRowCount;
	}
	//카트 전체 삭제
	public int deleteByMemberId(String m_id) throws Exception {
		int deleteRowCount = 0;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(CartSQL.CART_DELETE_BY_M_ID);
		pstmt.setString(1, m_id);
		deleteRowCount = pstmt.executeUpdate();
		pstmt.close();
		dataSource.close(con);
		return deleteRowCount;
	}
	
	
	
	
	
	
	
}
