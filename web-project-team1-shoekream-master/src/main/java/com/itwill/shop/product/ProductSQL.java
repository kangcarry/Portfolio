package com.itwill.shop.product;

public class ProductSQL {
	public final static String PRODUCT_INSERT = "insert into product values(?,?,?,?,?,?,?)";
	public final static String PRODUCT_UPDATE = "update product set p_name=?,p_price=?,p_image=?,p_desc=?,P_click_count=?,category_no=?where p_no =?";
	public final static String PRODUCT_DELETE = "delete from product where p_name = ?";
	public final static String PRODUCT_SELECT_BY_NO = "select * from product where p_no = ?";
	public final static String PRODUCT_SELECT_ALL = "select * from product";
	public final static String PRODUCT_SELECT_BY_CATEGORY = "select * from product where category_no=?";
	
	//상품이름으로 검색해서 결과 값 받기
	public static final String PRODUCT_FIND_BY_NAME = "select * from product where upper(p_name) like upper('%'||?||'%')";

	//조회수 증가
	public static final String PRODUCT_INCREASE_CLICK_COUNT = "update product set p_click_count = p_click_count + 1 where p_no = ?";
	
	//조회수 순위대로 3개 출력
	public static final String PRODUCT_SELECT_POPULAR = 
			"select * from (select * from product order by p_click_count desc) where rownum <= 3";
	
	public static final String PRODUCT_DESC_PRICE = "select * from product order by p_price desc";
	public static final String PRODUCT_ASC_PRICE = "select * from product order by p_price asc";
	public final static String PRODUCT_SELECT_BY_CATEGORY_DESC_PRICE = "select * from product where category_no=? order by p_price desc";
	public final static String PRODUCT_SELECT_BY_CATEGORY_ASC_PRICE = "select * from product where category_no=? order by p_price asc";
	public static final String PRODUCT_DESC_CLICK = "select * from product order by p_click_count desc";
	public final static String PRODUCT_SELECT_BY_CATEGORY_DESC_CLICK = "select * from product where category_no=? order by p_click_count desc";
	
	public final static String PRODUCT_COMMENT_INSERT = "INSERT INTO product_comment (pc_no,pc_content,pc_regdate,pc_mark,p_no, writer) "
			+ "VALUES (product_comment_pc_no_seq.nextval,?, sysdate, ?,?,?)";
	public final static String PRODUCT_COMMENT_DELETE = "DELETE FROM product_comment WHERE pc_no = ?";
	public final static String PRODUCT_COMMENT_UPDATE = "UPDATE product_comment SET content = ? WHERE pc_no = ?";
	public final static String PRODUCT_COMMENT_SELECT_BY_P_NO = "SELECT pc_no, pc_content, pc_regdate, pc_mark, p_no,writer"
			+ " FROM product_comment WHERE p_no = ?";	
	public final static String PRODUCT_COMMENT_SELECT_BY_USERID = "select pc.*,p.p_image,p.p_name from product_comment pc"
			+ " join product p on pc.p_no = p.p_no where writer = ?";
}
