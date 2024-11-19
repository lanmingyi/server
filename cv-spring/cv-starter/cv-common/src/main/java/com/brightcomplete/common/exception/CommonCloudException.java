package com.brightcomplete.common.exception;

/**
 * @Description: cv-spring自定义异常
 * @author lmy
 * @Date: 2022-05-30
 */
public class CommonCloudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CommonCloudException(String message) {
        super(message);
    }

}
