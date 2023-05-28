package com.itwill.ilhajob.corp.exception;

public class NotFoundException extends Exception {
	private Object data;
	public NotFoundException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
