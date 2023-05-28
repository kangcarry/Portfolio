package com.itwill.shop.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.common.DataSource;
import com.itwill.shop.product.Product;

public class OrderDao {
	
	private DataSource dataSource;
	
	public OrderDao() throws Exception {
		dataSource = new DataSource();
	}
	
	/*
	 * 주문전체삭제 (회원아이디)
	 */
	public int deleteByMemberId(String m_id) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_M_ID);
			pstmt.setString(1, m_id);
			rowCount = pstmt.executeUpdate();
		} finally {
			close(con, pstmt);
		}
		return rowCount;
	}
	
	/*
	 * 주문 1건 삭제 (주문번호)
	 */
	public int deleteByOrderNo(int o_no) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount = 0;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(OrderSQL.ORDER_DELETE_BY_O_NO);
			pstmt.setInt(1, o_no);
			rowCount = pstmt.executeUpdate();
		} finally {
			close(con, pstmt);
		}
		return rowCount;
	}
	
	/*
	 * 주문생성 (주문 1개 + 주문아이템 1개 이상) - 트랜잭션 처리 필요.
	 */
	public int insert(Order order) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int rowCount = 0;
		
		try {
			con = dataSource.getConnection();
			con.setAutoCommit(false);
			pstmt1 = con.prepareStatement(OrderSQL.ORDER_INSERT);
			pstmt1.setString(1, order.getO_name());
			pstmt1.setString(2, order.getO_desc());
			pstmt1.setInt(3, order.getO_price());
			pstmt1.setString(4, order.getO_address());
			pstmt1.setString(5, order.getO_loc());
			pstmt1.setString(6, order.getO_payment());
			pstmt1.setString(7, order.getM_id());
			rowCount = pstmt1.executeUpdate();
			
			pstmt2 = con.prepareStatement(OrderSQL.ORDERITEM_INSERT);
			for(OrderItem orderItem:order.getOrderItemList()) {
				pstmt2.setInt(1, orderItem.getOi_qty());
				pstmt2.setInt(2, orderItem.getProduct().getP_no());
				pstmt2.executeUpdate();
			}
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
			throw e;
		} finally {
			if(pstmt1!=null) pstmt1.close();
			if(pstmt2!=null) pstmt2.close();
			if(con!=null) dataSource.close(con);
		}
		return rowCount;
	}
	
	/*
	 * 주문 전체조회 (기본내용)
	 */
	public List<Order> findByMemberId(String m_id) throws Exception {
		
		List<Order> orderList = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_BY_M_ID);
			pstmt.setString(1, m_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderList.add(new Order(rs.getInt("o_no"),
										rs.getString("o_name"),
										rs.getString("o_desc"),
										rs.getDate("o_date"),
										rs.getInt("o_price"),
										rs.getString("o_address"),
										rs.getString("o_loc"),
										rs.getString("o_payment"),
										rs.getString("m_id")
										)
							);
			}
		} finally {
			close(con, pstmt, rs);
		}
		return orderList;
	}
	
	/*
	 * 주문 1개 조회 (상세내용)
	 */
	public Order findByOrderNo(String m_id, int o_no) throws Exception {
		
		Order order = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(OrderSQL.ORDER_SELECT_WITH_ORDERITEM_PRODUCT_BY_M_ID_O_NO);
			pstmt.setString(1, m_id);
			pstmt.setInt(2, o_no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				order = new Order(rs.getInt("o_no"),
								  rs.getString("o_name"),
								  rs.getString("o_desc"),
								  rs.getDate("o_date"),
								  rs.getInt("o_price"),
								  rs.getString("o_address"),
								  rs.getString("o_loc"),
								  rs.getString("o_payment"),
								  rs.getString("m_id")
								  );
				do {
					order.getOrderItemList().add(new OrderItem(rs.getInt("oi_no"),
															   rs.getInt("oi_qty"),
															   new Product(rs.getInt("p_no"),
																	   	   rs.getString("p_name"),
																	   	   rs.getInt("p_price"),
																	   	   rs.getString("p_image"),
																	   	   rs.getString("p_desc")
																	   	   ),
															   rs.getInt("o_no")
															   )
												);
				} while(rs.next());
			}
		} finally {
			close(con, pstmt, rs);
		}
		return order;
	}
	
	//close메쏘드
	private void close(Connection con, PreparedStatement pstmt) throws Exception {
		if(pstmt!=null) pstmt.close();
		if(con!=null) dataSource.close(con);
	}
	
	//close메쏘드 - 오버로딩
	private void close(Connection con, PreparedStatement pstmt, ResultSet rs) throws Exception {
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		if(con!=null) dataSource.close(con);
	}
}