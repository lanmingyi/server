package com.sun.gisvisualition.entity;

public class DataResult {
    private Integer code;
    private Long count;//数据长度
    private Object data;//存储数据
    private String msg;//后台返回前台的信息

    public DataResult() {
        this.code = 0;
        this.count = 0L;
        this.data = "";
        this.msg = "";
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
