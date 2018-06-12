package com.cd.uap.bean;

public class Response {

	private int status;

	private ResultCode result;

	private Object data;

	public Response(int status, ResultCode result, Object data) {
		super();
		this.status = status;
		this.result = result;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ResultCode getResult() {
		return result;
	}

	public void setResult(ResultCode result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}