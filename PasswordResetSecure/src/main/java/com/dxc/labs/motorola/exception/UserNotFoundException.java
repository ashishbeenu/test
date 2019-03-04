package com.dxc.labs.motorola.exception;

public class UserNotFoundException extends Exception{

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
	public UserNotFoundException(String erroCd,String message){
		super(message);
		this.erroCd=erroCd;
		this.message=message;
	}

}
