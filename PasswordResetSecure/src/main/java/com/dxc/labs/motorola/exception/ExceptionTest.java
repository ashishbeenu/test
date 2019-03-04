package com.dxc.labs.motorola.exception;

public class ExceptionTest extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String erroCd;
	public String message;
	
	public String getErroCd() {
		return erroCd;
	}
	public void setErroCd(String erroCd) {
		this.erroCd = erroCd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExceptionTest(String erroCd,String message){
		super(message);
		this.erroCd=erroCd;
		this.message=message;
	}
}
