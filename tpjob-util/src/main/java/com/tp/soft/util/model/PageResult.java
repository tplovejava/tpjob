package com.tp.soft.util.model;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    //当前页数
    private int pageNum;
    //每页显示多少数据
    private int pageSize;
    //总页数
    private long total;

    private int pages;

    private List<T> rows;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
