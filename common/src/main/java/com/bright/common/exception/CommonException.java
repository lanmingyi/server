package com.bright.common.exception;

/**
 * 自定义异常
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
