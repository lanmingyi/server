package com.brightcomplete.modules.system.util;

import java.io.Serializable;
import java.util.List;
/**
 * @MethodName
 * @Description TODO 分页相关参数
 * @Author lmy
 * @Date
 */
public class PageSet<T> implements Serializable {
    private List<T> rows; //总数据
    private int pageSize; //每页多少数据
    private int pageNo; //当前是第几页
    private int totalPage;//总共多少页
    private int totalCount; //总共多少条数据

    public PageSet() {
    }

    public PageSet( List<T> rows, int pageSize, int pageNo,int totalPage, int totalCount) {
        this.rows = rows;
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }


}

