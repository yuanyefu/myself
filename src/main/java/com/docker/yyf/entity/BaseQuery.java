package com.docker.yyf.entity;

import com.docker.yyf.base.OtherQuery;

public class BaseQuery<EO extends BaseEntity> {
    private EO query;

    private OtherQuery otherQuery;

    private Integer pageNum;

    private Integer pageSize;

    public EO getQuery() {
        return query;
    }

    public void setQuery(EO query) {
        this.query = query;
    }

    public OtherQuery getOtherQuery() {
        return otherQuery;
    }

    public void setOtherQuery(OtherQuery otherQuery) {
        this.otherQuery = otherQuery;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
