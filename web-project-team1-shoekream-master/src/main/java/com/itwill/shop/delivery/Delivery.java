package com.itwill.shop.delivery;

public class Delivery {
	private int d_no;
	private String d_address;
	private String d_phone;
	private String d_name;
	//fk
	private String user_id;

	public Delivery() {
	}

	
	public Delivery(int d_no, String d_address, String d_phone, String d_name, String user_id) {
		super();
		this.d_no = d_no;
		this.d_address = d_address;
		this.d_phone = d_phone;
		this.d_name = d_name;
		this.user_id = user_id;
	}
	
	public int getD_no() {
		return d_no;
	}


	public String getD_address() {
		return d_address;
	}

	public void setD_address(String d_address) {
		this.d_address = d_address;
	}

	public String getD_phone() {
		return d_phone;
	}

	public void setD_phone(String d_phone) {
		this.d_phone = d_phone;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	@Override
	public String toString() {
		return "Delivery [d_no=" + d_no + ", d_address=" + d_address + ", d_phone=" + d_phone + ", d_name=" + d_name
				+ ", user_id=" + user_id + "]";
	}
	
	
}
