package com.adapter.yyf;

import java.util.List;

/**
 * @Author: yyf
 * @Date: 2018/5/3 11:37
 * @Description:
 */
public interface LogFileOperateApi {

     List<LogModel> readLogFile();

     void writeLogFile(List<LogModel> list);
}
