package com.adapter.yyf;

import sun.dc.pr.PRError;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2018/5/3 14:35
 * @Description:
 */
public class Adapter implements LogDbOperateApi {

    private LogFileOperateApi adaptee;

    public Adapter(LogFileOperateApi adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void createLog(LogModel model) {
        List<LogModel> logModels = adaptee.readLogFile();
        logModels.add(model);
        adaptee.writeLogFile(logModels);
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
}
