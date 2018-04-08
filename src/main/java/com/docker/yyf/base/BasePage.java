package com.docker.yyf.base;

import java.io.Serializable;
import java.util.List;

public class BasePage<T> implements Serializable {
    private static final long SERVIAL_VERSIONUID = 1L;

    private static int DEFAULT_PAGE_SIZE = 20;

    private long pageSize;

    private long pageNum;

    private List<T> data;

    private long totalCount;

    public BasePage(){
    }

    public BasePage(long pageSize, long pageNum, List<T> data,long totalCount) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
        this.data = data;
        this.totalCount = totalCount;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
