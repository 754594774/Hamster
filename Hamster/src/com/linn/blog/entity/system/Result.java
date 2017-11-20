package com.linn.blog.entity.system;

public class Result {

	private Boolean success;
	private String msg;
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return "Result [msg=" + msg + ", success=" + success + "]";
	}
}
