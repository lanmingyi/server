package com.brightcomplete.common.exception;

/**
 * @Description: cv-spring自定义异常
 * @author lmy
 */
public class CommonException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CommonException(String message){
		super(message);
	}

	public CommonException(Throwable cause)
	{
		super(cause);
	}

	public CommonException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
