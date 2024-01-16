package com.sapient.app.exception;

public class controllerException extends RuntimeException{
	
//	public controllerException(String message)
//	{
//		super(message);
//	}
	String errorCode;
	String errorMessage;
	
	public controllerException() {
		super();
	}

	public controllerException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
