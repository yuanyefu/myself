package com.adapter.yyf;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yyf
 * @Date: 2018/5/3 17:50
 * @Description:
 */
public class OneExtend {
    private final static String DEFAULT_PRE_KEY="Cache";

    private static Map<String,OneExtend> map=new HashMap<>();
    /**
     * 当前使用的实例
     */
    private static int num =1;

    private final static int NUM_MAX=3;

    private OneExtend(){}

    public static OneExtend getInstance(){
        String key = DEFAULT_PRE_KEY + num;
        OneExtend oneExtend = map.get(key);
        if (oneExtend == null){
            oneExtend = new OneExtend();
            map.put(key,oneExtend);
        }
        num++;
        if (num>NUM_MAX){
            num =1;
        }
        return oneExtend;
    }

}
