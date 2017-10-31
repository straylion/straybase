package com.lostray.master.driver;

public class HttpResult {
	private int statuscode;
	private String sBody;
	private byte[] bBody;

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getsBody() {
		return sBody;
	}

	public void setsBody(String sBody) {
		this.sBody = sBody;
	}

	public byte[] getbBody() {
		return bBody;
	}

	public void setbBody(byte[] bBody) {
		this.bBody = bBody;
	}
	
	@Override
	public String toString() {
		return "bBody=" + this.getbBody() + "\n" + "sBody=" + this.getsBody();
	}

}
