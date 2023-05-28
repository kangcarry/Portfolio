package com.itwill.shop.product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.itwill.shop.common.DataSourceFactory;



public class ProductDao {
	private DataSource dataSource;
	public ProductDao() throws Exception {
		dataSource=DataSourceFactory.getDataSource();
	}
	/*
	 * 카테고리별 상품 검색
	 */
	public List<Product> selectByCategory(int category_no) throws Exception {
		List<Product> productList=new ArrayList<Product>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_CATEGORY);
			pstmt.setInt(1, category_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(rs.getInt("p_no"), rs.getString("p_name"), rs.getInt("p_price"), 
						  			rs.getString("p_image"), rs.getString("p_desc"), 
						  			rs.getInt("p_click_count"), rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/*
	 * 상품추가
	 */
	public int insert(Product product) throws Exception {
		Connection con = null;
		PreparedStatement pstmt =null;
		int rowCount=0;
		try {
			con=dataSource.getConnection();
			pstmt= con.prepareStatement(ProductSQL.PRODUCT_INSERT);
			pstmt.setInt(1, product.getP_no());
			pstmt.setString(2, product.getP_name());
			pstmt.setInt(3, product.getP_price());
			pstmt.setString(4, product.getP_image());
			pstmt.setString(5, product.getP_desc());
			pstmt.setInt(6, product.getP_click_count());
			pstmt.setInt(7, product.getCategory_no());
			rowCount = pstmt.executeUpdate();
		}finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	/*
	 * 상품 update
	 */
	public int update(Product product) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount=0;
		try {
			con=dataSource.getConnection();
			pstmt= con.prepareStatement(ProductSQL.PRODUCT_UPDATE);
			pstmt.setString(1, product.getP_name());
			pstmt.setInt(2, product.getP_price());
			pstmt.setString(3, product.getP_image());
			pstmt.setString(4, product.getP_desc());
			pstmt.setInt(5, product.getP_click_count());
			pstmt.setInt(6, product.getCategory_no());
			pstmt.setInt(7,product.getP_no());
			rowCount = pstmt.executeUpdate();
		}finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		
		return rowCount;
	}
	/*
	 * 상품이름으로삭제
	 */
	public int delete(String p_name )throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		int rowCount=0;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_DELETE);
			pstmt.setString(1, p_name);
			rowCount = pstmt.executeUpdate();
		}finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return rowCount;
	}
	/*
	 * 상품번호검색
	 */
	public Product selectByNo(int p_no) throws Exception{
		Product product=null;
		Connection con=null;
		PreparedStatement pstmt=null, pstmt2=null;
		ResultSet rs=null, rs2=null;
		try{
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_NO);
			pstmt.setInt(1, p_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				product = new Product(rs.getInt("p_no"),
									  rs.getString("p_name"),
									  rs.getInt("P_price"),
									  rs.getString("p_image"),
									  rs.getString("p_desc"),
									  rs.getInt("p_click_count"),
									  rs.getInt("category_no"));
				
				pstmt2 = con.prepareStatement(ProductSQL.PRODUCT_COMMENT_SELECT_BY_P_NO);
				pstmt2.setInt(1, p_no);
				rs2 = pstmt2.executeQuery();
				ArrayList<ProductComment> comments = new ArrayList<ProductComment>();
				while (rs2.next()) {
					ProductComment comment = new ProductComment();
					comment.setPc_no(rs2.getInt(1));
					comment.setContent(rs2.getString(2));
					comment.setRegDate(rs2.getDate(3));
					comment.setPc_mark(rs2.getInt(4));
					comment.setP_no(rs2.getInt(5));
					comment.setWriter(rs2.getString(6));
					
					comments.add(comment);
				}
				product.setComments(comments);
			}
		}finally {
			if (rs != null) {
				rs.close();
				rs2.close();
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt2.close();
			}
			if (con != null)
				con.close();
		}
		return product;
	}
	/*
	 * 상품전체검색
	 */
	public List<Product> selectAll() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_ALL);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}
	/************************************************************/
	/*
	 * 상품 이름으로 검색 출력 하기.
	 */
	
	public List <Product> productFindByName(String keyword) throws Exception {

		List <Product> productList = new ArrayList<>();
		Connection con = dataSource.getConnection();
		PreparedStatement pstmt = con.prepareStatement(ProductSQL.PRODUCT_FIND_BY_NAME);
		pstmt.setString(1, keyword);
		ResultSet rs = pstmt.executeQuery();

		if(rs.next()) {
			do {
				Product product = new Product(
						  rs.getInt("p_no"),
						  rs.getString("p_name"),
						  rs.getInt("p_price"),
						  rs.getString("p_image"),
						  rs.getString("p_desc"),
						  rs.getInt("p_click_count"),
						  rs.getInt("category_no"));

				productList.add(product);

			} while(rs.next());
		}
		return productList;
	}
	
	
	/************************************************************/
	
	/*
	 * 상품 조회수 증가
	 */
	public void increaseClickCount(int number) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(ProductSQL.PRODUCT_INCREASE_CLICK_COUNT);
			pstmt.setInt(1, number);
			pstmt.executeUpdate();
			
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
			} catch (Exception ex) {
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}
	}
	/*
	 * 조회수 상위 3개
	 */
	public List<Product> selectPopular() throws Exception{
		List<Product> productList=new ArrayList<Product>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_POPULAR);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product(
						rs.getInt("p_no"),
						rs.getString("p_name"),
						rs.getInt("p_price"),
						rs.getString("p_image"),
						rs.getString("p_desc"),
						rs.getInt("p_click_count"),
						rs.getInt("category_no"));
				productList.add(product);
			}
		}finally {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return productList;
	}	
	
		/*
		 * 가격 내림차순
		 */
		public List<Product> priceSortDesc() throws Exception{
			List<Product> productList=new ArrayList<Product>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=dataSource.getConnection();
				pstmt=con.prepareStatement(ProductSQL.PRODUCT_DESC_PRICE);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Product product = new Product(
							  rs.getInt("p_no"),
							  rs.getString("p_name"),
							  rs.getInt("p_price"),
							  rs.getString("p_image"),
							  rs.getString("p_desc"),
							  rs.getInt("p_click_count"),
							  rs.getInt("category_no"));
					productList.add(product);
				}
			}finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			}
			return productList;
		}
		/*
		 * 가격 오름차순
		 */
		public List<Product> priceSortAsc() throws Exception{
			List<Product> productList=new ArrayList<Product>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=dataSource.getConnection();
				pstmt=con.prepareStatement(ProductSQL.PRODUCT_ASC_PRICE);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Product product = new Product(
							  rs.getInt("p_no"),
							  rs.getString("p_name"),
							  rs.getInt("p_price"),
							  rs.getString("p_image"),
							  rs.getString("p_desc"),
							  rs.getInt("p_click_count"),
							  rs.getInt("category_no"));
					productList.add(product);
				}
			}finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			}
			return productList;
		}
		/*
		 * 조회수 내림차순
		 */
		public List<Product> clickSortDesc() throws Exception{
			List<Product> productList=new ArrayList<Product>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=dataSource.getConnection();
				pstmt=con.prepareStatement(ProductSQL.PRODUCT_DESC_CLICK);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Product product = new Product(
							  rs.getInt("p_no"),
							  rs.getString("p_name"),
							  rs.getInt("p_price"),
							  rs.getString("p_image"),
							  rs.getString("p_desc"),
							  rs.getInt("p_click_count"),
							  rs.getInt("category_no"));
					productList.add(product);
				}
			}finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			}
			return productList;
		}
		/*
		 * 조회수 오름차순
		 */
		public List<Product> clickSortCateDesc(int category_no) throws Exception{
			List<Product> productList=new ArrayList<Product>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=dataSource.getConnection();
				pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_CATEGORY_DESC_CLICK);
				pstmt.setInt(1, category_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Product product = new Product(
							  rs.getInt("p_no"),
							  rs.getString("p_name"),
							  rs.getInt("p_price"),
							  rs.getString("p_image"),
							  rs.getString("p_desc"),
							  rs.getInt("p_click_count"),
							  rs.getInt("category_no"));
					productList.add(product);
				}
			}finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			}
			return productList;
		}
		
		public List<Product> priceSortCateDesc(int category_no) throws Exception{
			List<Product> productList=new ArrayList<Product>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=dataSource.getConnection();
				pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_CATEGORY_DESC_PRICE);
				pstmt.setInt(1, category_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Product product = new Product(
							  rs.getInt("p_no"),
							  rs.getString("p_name"),
							  rs.getInt("p_price"),
							  rs.getString("p_image"),
							  rs.getString("p_desc"),
							  rs.getInt("p_click_count"),
							  rs.getInt("category_no"));
					productList.add(product);
				}
			}finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			}
			return productList;
		}
		
		public List<Product> priceSortCateAsc(int category_no) throws Exception{
			List<Product> productList=new ArrayList<Product>();
			
			Connection con=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				con=dataSource.getConnection();
				pstmt=con.prepareStatement(ProductSQL.PRODUCT_SELECT_BY_CATEGORY_ASC_PRICE);
				pstmt.setInt(1, category_no);
				rs=pstmt.executeQuery();
				while(rs.next()) {
					Product product = new Product(
							  rs.getInt("p_no"),
							  rs.getString("p_name"),
							  rs.getInt("p_price"),
							  rs.getString("p_image"),
							  rs.getString("p_desc"),
							  rs.getInt("p_click_count"),
							  rs.getInt("category_no"));
					productList.add(product);
				}
			}finally {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			}
			return productList;
		}
	
		public void insertComment(ProductComment comment) throws Exception {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = dataSource.getConnection();

				pstmt = con.prepareStatement(ProductSQL.PRODUCT_COMMENT_INSERT);
				pstmt.setString(1, comment.getContent());
				pstmt.setInt(2, comment.getPc_mark());
				pstmt.setInt(3, comment.getP_no());
				pstmt.setString(4, comment.getWriter());

				pstmt.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception ex) {
					}
				if (con != null)
					try {
						con.close();;
					} catch (Exception ex) {
					}
			}
		}

		public void deleteComment(int pc_no) throws Exception {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = dataSource.getConnection();

				pstmt = con.prepareStatement(ProductSQL.PRODUCT_COMMENT_DELETE);
				pstmt.setInt(1, pc_no);

				pstmt.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception ex) {
					}
				if (con != null)
					try {
						con.close();;
					} catch (Exception ex) {
					}
			}
		}

		public void updateComment(ProductComment comment) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				con = dataSource.getConnection();

				pstmt = con.prepareStatement(ProductSQL.PRODUCT_COMMENT_UPDATE);
				pstmt.setString(1, comment.getContent());
				pstmt.setInt(2, comment.getPc_no());

				pstmt.executeUpdate();
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception ex) {
					}
				if (con != null)
					try {
						con.close();;
					} catch (Exception ex) {
					}
			}

		}
		
		public List<ProductComment> selectCommentByUserId(String user_id) throws Exception {

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			List<ProductComment> productCommentList = new ArrayList();
			try {
				con = dataSource.getConnection();

				pstmt = con.prepareStatement(ProductSQL.PRODUCT_COMMENT_SELECT_BY_USERID);
				pstmt.setString(1, user_id);

				pstmt.executeQuery();
				rs=pstmt.executeQuery();
				while(rs.next()) {
					ProductComment productComment = new ProductComment();
					productComment.setPc_no(rs.getInt(1));
					productComment.setContent(rs.getString(2));
					productComment.setRegDate(rs.getDate(3));
					productComment.setPc_mark(rs.getInt(4));
					productComment.setP_no(rs.getInt(5));
					productComment.setWriter(rs.getString(6));
					productComment.setP_image(rs.getString(7));
					productComment.setP_name(rs.getString(8));
					productCommentList.add(productComment);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (Exception ex) {
					}
				if (con != null)
					try {
						con.close();;
					} catch (Exception ex) {
					}
			}
			return productCommentList;
		}
		
}
