package com.sun.gisvisualition.config.exception;

public class DataResultException extends RuntimeException {

    private Integer code;
    private Long count;
    private Object data;
    private String msg;

    public DataResultException(String msg) {
        this.code = 1;
        this.count = 0L;
        this.data = "";
        this.msg = msg;
    }

    public DataResultException(int code, String msg) {
        this.code = code;
        this.count = 0L;
        this.data = "";
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
