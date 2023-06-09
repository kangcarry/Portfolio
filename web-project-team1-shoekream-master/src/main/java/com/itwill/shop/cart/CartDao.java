package com.itwill.shop.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itwill.shop.common.DataSourceFactory;
import com.itwill.shop.product.Product;

public class CartDao {
	private DataSource dataSource;
	
	
	public CartDao() throws Exception{
		dataSource=DataSourceFactory.getDataSource();
	}

	/*
	 * cart에 제품이 존재하는지 확인
	 * 존재한다면(1) => update / 존재하지 않는다면 => insert / 
	 */
	public int countByProductNo(String sUserId, int p_no) throws Exception {
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			pstmt = con.prepareStatement(CartSQL.CART_COUNT_BY_USERID_PRODUCT_NO);
			pstmt.setString(1, sUserId);
			pstmt.setInt(2, p_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return count;
	}
		
		
	/*
	 * insert
	 * cart에 존재하지 않는 제품 추가
	 */
	//cart_no,user_Id,p_no,cart_qty
	public int insertCart(Cart cart) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		int insertRowCount = 0;
		
		try {
			pstmt = con.prepareStatement(CartSQL.CART_INSERT);
			pstmt.setString(1, cart.getUser_Id());
			pstmt.setInt(2, cart.getCart_qty());
			pstmt.setInt(3, cart.getProduct().getP_no());
			insertRowCount = pstmt.executeUpdate();
			
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return insertRowCount;
}
	
	
	/*
	 * delete
	 */
	//카트에 담긴 상품 전체 삭제
	public int deleteByUserId(String sUserId) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		int deleteRowCount = 0;
		
		try {
			pstmt = con.prepareStatement(CartSQL.CART_DELETE_BY_USERID);
			pstmt.setString(1, sUserId);
			deleteRowCount = pstmt.executeUpdate();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return deleteRowCount;
	}
	
	
	//카트에 담긴 상품 중 한가지 항목 삭제
	public int deleteByCartNo(int cart_no) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		int deleteRowCount=0;
		
		try {
			pstmt = con.prepareStatement(CartSQL.CART_DELETE_BY_CART_NO);
			pstmt.setInt(1, cart_no);
			deleteRowCount = pstmt.executeUpdate();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return deleteRowCount;
	}
		
	
	/*
	 * update
	 */
	//상품 페이지에서 장바구니에 추가 (수량 변경)
	public int updateByProductNo(Cart cart) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		int updateRowCount = 0;
		
		try {
			pstmt = con.prepareStatement(CartSQL.CART_UPDATE_BY_PRODUCT_NO_USERID);
			pstmt.setInt(1, cart.getCart_qty());
			pstmt.setString(2, cart.getUser_Id());
			pstmt.setInt(3, cart.getProduct().getP_no());
			updateRowCount = pstmt.executeUpdate();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return updateRowCount;
	}	
	

	//카트에서 수량 변경
	public int updateByCartNo(Cart cart) throws Exception{
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		int updateRowCount = 0;
		
		try {
			pstmt = con.prepareStatement(CartSQL.CART_UPDATE_BY_CART_NO);
			pstmt.setInt(1, cart.getCart_qty());
			pstmt.setInt(2, cart.getCart_no());
			updateRowCount = pstmt.executeUpdate();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return updateRowCount;
	}
	
	
	/*
	 * List
	 */
	//카트 전체 목록 (for 전체주문)
	public List<Cart> findByUserId(String user_Id) throws Exception{
		List<Cart> cartList = new ArrayList<Cart>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		pstmt = con.prepareStatement(CartSQL.CART_SELECT_BY_USERID);
		pstmt.setString(1, user_Id);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			cartList.add(new Cart( rs.getInt("cart_no"),
								   rs.getString("user_Id"),
								   rs.getInt("cart_qty"),
							       new Product(rs.getInt("p_no"),
							    		       rs.getString("p_name"),
							    		       rs.getInt("p_price"),
							    		       rs.getString("p_image"),
							    		       rs.getString("p_desc"), 
							    		       rs.getInt("p_click_count"),
							    		       rs.getInt("category_no"))
			
					));
		}
		} finally {
			if(con!=null) {
				rs.close();
				pstmt.close();
				con.close();
			}
		}
		return cartList;
	}
		
	
	//카트 일부 목록 (for 선택주문)
		public Cart findByCartNo(int cart_no) throws Exception{
			Cart cart = null;
			Connection con = dataSource.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
			pstmt = con.prepareStatement(CartSQL.CART_SELECT_BY_CARTNO);
			pstmt.setInt(1, cart_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cart = (new Cart( rs.getInt("cart_no"),
						   			   rs.getString("user_Id"),
						   			   rs.getInt("cart_qty"),
						   			   new Product(rs.getInt("p_no"),
								    		       rs.getString("p_name"),
								    		       rs.getInt("p_price"),
								    		       rs.getString("p_image"),
								    		       rs.getString("p_desc"), 
								    		       rs.getInt("p_click_count"),
								    		       rs.getInt("category_no"))
							));
				}
			}finally {
				if(con!=null) {
					rs.close();
					pstmt.close();
					con.close();
				}
			}
			return cart;
		}
	
	
}
