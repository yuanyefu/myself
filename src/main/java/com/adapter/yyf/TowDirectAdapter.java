package com.adapter.yyf;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2018/5/3 14:51
 * @Description:
 */
public class TowDirectAdapter implements LogDbOperateApi,LogFileOperateApi {
    private LogDbOperateApi dbLog;
    private LogFileOperateApi fileLog;

    public TowDirectAdapter(LogDbOperateApi dbLog, LogFileOperateApi fileLog) {
        this.dbLog = dbLog;
        this.fileLog = fileLog;
    }

    @Override
    public void createLog(LogModel model) {

    }

    @Override
    public void updateLog(LogModel model) {

    }

    @Override
    public void removeLog(LogModel model) {

    }

    @Override
    public List<LogModel> getAllLog() {
        return null;
    }

    @Override
    public List<LogModel> readLogFile() {
        return null;
    }

    @Override
    public void writeLogFile(List<LogModel> list) {

    }
}
