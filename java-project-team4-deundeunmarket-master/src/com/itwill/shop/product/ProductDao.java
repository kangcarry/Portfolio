package com.itwill.shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itwill.shop.common.DataSource;

public class ProductDao {

	private DataSource dataSource;

	public ProductDao() throws Exception {
		dataSource = new DataSource();
	}

	
	public Product findByPrimaryKey(int p_no) throws Exception {
		Product product = null;
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
		pstmt.setInt(1, p_no);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			product = new Product(rs.getInt("p_no"), 
					rs.getString("p_name"), 
					rs.getInt("p_price"),
					rs.getString("p_image"), 
					rs.getString("p_desc"));
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return product;
	}
	
	public List<Product> findAll() throws Exception{
		List<Product> productList=new ArrayList<Product>();

		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_SELECT_ALL);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Product product = new Product(
								rs.getInt("p_no"),
								rs.getString("p_name"), 
								rs.getInt("p_price"), 
								rs.getString("p_image"), 
								rs.getString("p_desc"));
			productList.add(product);
		}
		rs.close();
		pstmt.close();
		dataSource.close(con);
		return productList;
	}
	
}