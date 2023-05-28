package com.itwill.ilhajob.common.controller;

public class ResponseStatusCode {

	public static final int LOGIN_SUCCESS = 2100;
	public static final int READ_USER = 2200;
	public static final int CREATED_USER = 2300;
	public static final int UPDATE_USER = 2400;
	public static final int DELETE_USER = 2500;
	public static final int LOGOUT_USER = 2600;
	
	public static final int NOT_FOUND_USER = 5000;
	public static final int NOT_FOUND_CORP = 5001;
	public static final int PASSWORD_MISMATCH_USER = 5300;
	public static final int PASSWORD_MISMATCH_CORP = 5301;
	public static final int LOGIN_FAIL = 5100;
	public static final int EXISTED_USER = 5200;
	public static final int UNAUTHORIZED_USER = 5400;
	
	public static final int WRITTEN_FAIL_REVIEW = 6001;
}