package com.adapter.yyf;

import org.apache.commons.lang3.StringUtils;

import javax.print.attribute.standard.NumberUp;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * @Author: yyf
 * @Date: 2018/5/3 11:39
 * @Description:
 */
public class LogFileOperate implements LogFileOperateApi {

    private String logFilePathName= "AdapterLog.log";

    public LogFileOperate(String logFilePathName) {
        if (StringUtils.isNotBlank(logFilePathName)){
            this.logFilePathName = logFilePathName;

        }
    }

    @Override
    public List<LogModel> readLogFile() {
        System.out.println("readLogFile");
        return null;
    }

    @Override
    public void writeLogFile(List<LogModel> list) {
        System.out.println("writeLogFile");
    }
}
