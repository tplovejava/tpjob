package com.tp.soft.util.model;

import com.github.pagehelper.Page;

import java.util.List;

public class PageResult<T> implements java.io.Serializable{

    private static final long serialVersionUID = 4634696511476284259L;

    /**
     * 当前页
     */
    private int pageNum;

    /**
     * 页显示记录
     */
    private int pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 共几页
     */
    private int pages;

    /**
     * 数据集
     */
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

    public void autowire(Page page){
        this.setPageNum(page.getPageNum());
        this.setPageSize(page.getPageSize());
        this.setTotal(page.getTotal());
        this.setPages(page.getPages());
        this.setRows(page.getResult());
    }
}
