package com.itwill.ilhajob.corp.exception;

public class ExistedCorpException extends Exception{
	private Object data;
	public ExistedCorpException(String msg) {
		super(msg);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
