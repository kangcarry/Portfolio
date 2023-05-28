package com.itwill.ilhajob.corp.exception;

public class CorpNotFoundException extends Exception {
	private Object data;
	public CorpNotFoundException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
