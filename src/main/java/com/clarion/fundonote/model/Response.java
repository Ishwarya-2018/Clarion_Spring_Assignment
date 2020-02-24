package com.clarion.fundonote.model;

public class Response {
	private String msg;
	private int statusCode;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Response() {

	}

	public Response(String msg, int statusCode) {
		this.msg = msg;
		this.statusCode = statusCode;
	}

}
