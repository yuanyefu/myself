package com.adapter.yyf;

import java.io.Serializable;

/**
 * @Author: yyf
 * @Date: 2018/5/3 11:32
 * @Description:
 */
public class LogModel  implements Serializable {

    private String logId;

    private String userId;

    private String createTime;

    private String logContent;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }
}
