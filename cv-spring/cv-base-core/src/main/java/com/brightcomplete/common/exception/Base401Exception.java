package com.brightcomplete.common.exception;

/**
 * @Description: cv-spring自定义401异常
 * @author lmy
 */
public class Base401Exception extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public Base401Exception(String message){
		super(message);
	}

	public Base401Exception(Throwable cause)
	{
		super(cause);
	}

	public Base401Exception(String message, Throwable cause)
	{
		super(message,cause);
	}
}
