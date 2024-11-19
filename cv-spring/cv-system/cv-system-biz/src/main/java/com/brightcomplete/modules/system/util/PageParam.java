package com.brightcomplete.modules.system.util;

/**
 * @MethodName
 * @Description TODO 封装获取前端分页数据
 * @Author lmy
 * @Date
 */
public class PageParam {
    //第一页
    private int pageNo = 1;
    //每页数据
    private int pageSize = 20;
    //排序字段
    private String sort;
    //降序、升序
    private String order;
    public PageParam() {
    }

    public PageParam(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
