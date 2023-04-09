package com.bright.common.exception;

/**
 * 自定义异常
 */
public class CommonCloudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CommonCloudException(String message) {
        super(message);
    }

}
