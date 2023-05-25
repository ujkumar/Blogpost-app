package com.ujjwal.kumar.blogapp.payloads;

public class ApiResponse {

	private String message;
	private boolean flag;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponse(String message, boolean flag) {
		super();
		this.message = message;
		this.flag = flag;
	}
	
	
	
}
