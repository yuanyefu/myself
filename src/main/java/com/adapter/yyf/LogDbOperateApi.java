package com.adapter.yyf;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2018/5/3 14:26
 * @Description:
 */
public interface LogDbOperateApi {

    void createLog(LogModel model);

    void updateLog(LogModel model);

    void removeLog(LogModel model);

    List<LogModel> getAllLog();
}
