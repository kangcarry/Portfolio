package com.itwill.shop.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
이름        널?       유형             
--------- -------- -------------- 
O_NO      NOT NULL NUMBER(10)     
O_NAME             VARCHAR2(50)   
O_DESC             VARCHAR2(1000) 
O_DATE             DATE           
O_PRICE            NUMBER(10)     
O_ADDRESS          VARCHAR2(500)  
O_LOC              VARCHAR2(500)  
O_PAYMENT          VARCHAR2(50)   
M_ID               VARCHAR2(50)  
 */
public class Order {
	private int o_no;
	private String o_name;
	private String o_desc;
	private Date o_date;
	private int o_price;
	private String o_address;
	private String o_loc;
	private String o_payment;
	/*********FK**********/
	private String m_id;
	/******List<OrderItem> 포함 ******/
	private List<OrderItem> orderItemList;
	
	public Order() {
		
	}

	public Order(int o_no, String o_name, String o_desc, Date o_date, int o_price, String o_address, String o_loc,
			String o_payment, String m_id) {
		super();
		this.o_no = o_no;
		this.o_name = o_name;
		this.o_desc = o_desc;
		this.o_date = o_date;
		this.o_price = o_price;
		this.o_address = o_address;
		this.o_loc = o_loc;
		this.o_payment = o_payment;
		this.m_id = m_id;
		this.orderItemList = new ArrayList<OrderItem>();;
	}
	
	public int getO_no() {
		return o_no;
	}

	public String getO_name() {
		return o_name;
	}

	public String getO_desc() {
		return o_desc;
	}

	public Date getO_date() {
		return o_date;
	}

	public int getO_price() {
		return o_price;
	}

	public String getO_address() {
		return o_address;
	}

	public String getO_loc() {
		return o_loc;
	}

	public String getO_payment() {
		return o_payment;
	}

	public String getM_id() {
		return m_id;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setO_no(int o_no) {
		this.o_no = o_no;
	}

	public void setO_name(String o_name) {
		this.o_name = o_name;
	}

	public void setO_desc(String o_desc) {
		this.o_desc = o_desc;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	public void setO_price(int o_price) {
		this.o_price = o_price;
	}

	public void setO_address(String o_address) {
		this.o_address = o_address;
	}

	public void setO_loc(String o_loc) {
		this.o_loc = o_loc;
	}

	public void setO_payment(String o_payment) {
		this.o_payment = o_payment;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	@Override
	public String toString() {
		return "Order [o_no=" + o_no + ", o_name=" + o_name + ", o_desc=" + o_desc + ", o_date=" + o_date + ", o_price="
				+ o_price + ", o_address=" + o_address + ", o_loc=" + o_loc + ", o_payment=" + o_payment + ", m_id="
				+ m_id + ", orderItemList=" + orderItemList + "]\n";
	}
	

}